package com.supplierportal.demo.controller;

import com.supplierportal.demo.model.*;
import com.supplierportal.demo.repository.EnterpriseKeyprocessEquipmentRepository;
import com.supplierportal.demo.repository.EnterpriseRepository;
import com.supplierportal.demo.repository.EquipmentRepository;
import com.supplierportal.demo.repository.ProductStepKeyprocessRepository;
import com.supplierportal.demo.request.NewKeyprocessEquipmentRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
public class EnterpriseKeyprocessEquipmentController {

    private final EnterpriseKeyprocessEquipmentRepository enterpriseKeyprocessEquipmentRepository;
    private final ProductStepKeyprocessRepository productStepKeyprocessRepository;

    private final EquipmentRepository equipmentRepository;

    private final EnterpriseRepository enterpriseRepository;

    public EnterpriseKeyprocessEquipmentController(
            EnterpriseKeyprocessEquipmentRepository enterpriseKeyprocessEquipmentRepository,
            ProductStepKeyprocessRepository productStepKeyprocessRepository,
            EquipmentRepository equipmentRepository,
            EnterpriseRepository enterpriseRepository) {
        this.enterpriseKeyprocessEquipmentRepository = enterpriseKeyprocessEquipmentRepository;
        this.productStepKeyprocessRepository = productStepKeyprocessRepository;
        this.equipmentRepository = equipmentRepository;
        this.enterpriseRepository = enterpriseRepository;
    }

    @PostMapping("/enterprise/{enterpriseId}/keyprocess")
    private ResponseEntity<String> createKeyprocess(
            @PathVariable Integer enterpriseId,
            @RequestBody List<NewKeyprocessEquipmentRequest> newKeyprocessEqipmentRequests) {

        Optional<Enterprise> enterprise = enterpriseRepository.findById(enterpriseId);

        if (enterprise.isEmpty()) {
            return ResponseEntity.badRequest().body(
                    String.format("Enterprise with id %d not found", enterpriseId)
            );
        }

        List<EnterpriseKeyprocessEquipment> enterpriseKeyprocessEquipmentList = new ArrayList<>();


        for (NewKeyprocessEquipmentRequest request : newKeyprocessEqipmentRequests) {

            Optional<ProductStepKeyprocess> productStepKeyprocess = productStepKeyprocessRepository.findById(request.getProductStepKeyprocessId());
            if (productStepKeyprocess.isEmpty()) {
                return ResponseEntity.badRequest().body(
                        String.format("Product Step Key Process with id %d not found", request.getProductStepKeyprocessId())
                );
            }

            Optional<Equipment> equipment = equipmentRepository.findById(request.getEquipmentId());
            if (equipment.isEmpty()) {
                return ResponseEntity.badRequest().body(
                        String.format("Equipment with id %d not found", request.getEquipmentId())
                );
            }
            EnterpriseKeyprocessEquipmentId id = new EnterpriseKeyprocessEquipmentId(
                    enterpriseId, productStepKeyprocess.get().getId(), equipment.get().getId());

            EnterpriseKeyprocessEquipment newKeyprocessEquipment = new EnterpriseKeyprocessEquipment(
                    id,
                    enterprise.get(),
                    productStepKeyprocess.get(),
                    equipment.get(),
                    request.getQuantity(),
                    request.getCapacityPerHour(),
                    request.getPurchaseCycle()
            );

            enterpriseKeyprocessEquipmentList.add(newKeyprocessEquipment);
        }

        enterpriseKeyprocessEquipmentRepository.saveAll(enterpriseKeyprocessEquipmentList);

        return ResponseEntity.ok(
                String.format(
                        "Equipments for Key Processes created successfully for enterprise %d",
                        enterpriseId)
        );
    }

}

