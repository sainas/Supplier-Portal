package com.supplierportal.demo.controller;

import com.supplierportal.demo.model.Factory;
import com.supplierportal.demo.model.Product;
import com.supplierportal.demo.model.ProductStep;
import com.supplierportal.demo.model.ProductStepKeyprocess;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.supplierportal.demo.repository.EnterpriseKeyprocessEquipmentRepository;
import com.supplierportal.demo.repository.ProductRepository;
import jakarta.persistence.Tuple;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/report")
public class ReportController {
    private final EnterpriseKeyprocessEquipmentRepository enterpriseKeyprocessEquipmentRepository;
    private final ProductRepository productRepository;

    public ReportController(EnterpriseKeyprocessEquipmentRepository enterpriseKeyprocessEquipmentRepository,
                            ProductRepository productRepository) {
        this.enterpriseKeyprocessEquipmentRepository = enterpriseKeyprocessEquipmentRepository;
        this.productRepository = productRepository;
    }

    @GetMapping("/keyprocess-equipment/{productId}")
    public ResponseEntity<byte[]> exportKeyProcessEquipementToExcel(@PathVariable Integer productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isEmpty()) {
            return ResponseEntity.badRequest().body(String.format("Product with id %d not found", productId).getBytes());
        }
        Product product = productOptional.get();

        List<ProductStepKeyprocess> productStepKeyprocesses = new ArrayList<>();
        for (ProductStep ps: product.getProductSteps()) {
            for (ProductStepKeyprocess psk: ps.getProductStepKeyprocesses()) {
                if (psk.getIsBottleneck()) {
                    productStepKeyprocesses.add(psk);
                }
            }
        }

        List<Tuple> results
                = enterpriseKeyprocessEquipmentRepository
                .findEnterpriseIdAndProductStepIdByProductIdAndIsBottleneckIsTrue(productId);


        try (Workbook workbook = new XSSFWorkbook()) {
            // Create a new workbook and sheet

            Sheet sheet = workbook.createSheet("Key Process Equipment");


            // Create the header row
            Row headerRow0 = sheet.createRow(0);
            Row headerRow1 = sheet.createRow(1);
            headerRow0.createCell(0).setCellValue("企业名称");

            Map<String, Integer> stepProcessMap = new HashMap<>();
            for (int i = 0; i < productStepKeyprocesses.size(); i++) {
                ProductStepKeyprocess productStepKeyprocess = productStepKeyprocesses.get(i);
                Integer stepNumber = productStepKeyprocess.getProductStep().getStepNumber();
                String processName = productStepKeyprocess.getProcess().getName();
                String fullname = String.format("步骤%d：工艺-%s", stepNumber, processName);
                stepProcessMap.put(fullname, i);
                headerRow0.createCell(i * 3 + 1).setCellValue(fullname);
                headerRow1.createCell(i * 3 + 1).setCellValue("设备名称");
                headerRow1.createCell(i * 3 + 2).setCellValue("设备总台数");
                headerRow1.createCell(i * 3 + 3).setCellValue("平均加工产能（台/小时）");
            }

            // Add the data rows
            int rowNum = 2;
            Row row = headerRow1;
            String prevEnterpriseName = "";
            int maxStep = 0;
            for (Tuple tuple : results) {

                String enterpriseName = tuple.get("enterprise_name", String.class);
                Integer stepNumber = tuple.get("step_number", Integer.class);
                String processName = tuple.get("process_name", String.class);
                String fullname = String.format("步骤%d：工艺-%s", stepNumber, processName);
                maxStep = Math.max(maxStep, stepNumber);

                if (!prevEnterpriseName.equals(enterpriseName)) {
                    row = sheet.createRow(rowNum++);
                    row.createCell(0).setCellValue(enterpriseName);
                    prevEnterpriseName = enterpriseName;
                }

                List<String> equipmentNames = Arrays.asList((String[]) tuple.get("equipment_names"));

                StringBuilder equipmentNamesBuilder = new StringBuilder();
                for (String equipmentName : equipmentNames) {
                    equipmentNamesBuilder.append(equipmentName).append(", ");
                }
                String equipmentNamesString = equipmentNamesBuilder.toString().trim();
                if (equipmentNamesString.endsWith(",")) {
                    equipmentNamesString = equipmentNamesString.substring(0, equipmentNamesString.length() - 1);
                }

                int colNum = stepProcessMap.get(fullname);
                row.createCell(colNum * 3 + 1).setCellValue(equipmentNamesString);
                row.createCell(colNum * 3 + 2).setCellValue(tuple.get("total_count", Long.class));
                row.createCell(colNum * 3 + 3).setCellValue(tuple.get("avg_capacity", Double.class));
            }

            // Resize the columns to fit the data
            for (int i = 0; i < productStepKeyprocesses.size() * 3 + 1; i++) {
                sheet.autoSizeColumn(i);
            }

            // Merge the cells
            sheet.addMergedRegion(new CellRangeAddress(
                    0, 1, 0, 0));
            for (int i = 0; i < productStepKeyprocesses.size(); i++) {
                sheet.addMergedRegion(new CellRangeAddress(
                        0, 0, i * 3 + 1, i * 3 + 3));
            }

            // Write the workbook to a ByteArrayOutputStream
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);

            // Set the response headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Disposition", "attachment; filename=KeyProcessEquipment.xlsx");
            headers.set("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

            // Return the response entity
            return new ResponseEntity<>(out.toByteArray(), headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}