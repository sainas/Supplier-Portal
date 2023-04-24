package com.supplierportal.demo.controller;

import com.supplierportal.demo.model.Employee;
import com.supplierportal.demo.model.EmployeeType;
import com.supplierportal.demo.model.Enterprise;
import com.supplierportal.demo.model.EnterpriseKeyprocessEquipment;
import com.supplierportal.demo.repository.EmployeeRepository;
import com.supplierportal.demo.repository.EnterpriseRepository;
import com.supplierportal.demo.request.NewEmployeeRequest;
import com.supplierportal.demo.request.NewKeyprocessEquipmentRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController

public class EmployeeController {

    private final EmployeeRepository employeeRepository;
    private final EnterpriseRepository enterpriseRepository;

    public EmployeeController(EmployeeRepository employeeRepository, EnterpriseRepository enterpriseRepository) {
        this.employeeRepository = employeeRepository;
        this.enterpriseRepository = enterpriseRepository;
    }

    @PostMapping("/enterprise/{enterpriseId}/employee")
    public ResponseEntity<String> createEmployee(
            @PathVariable Integer enterpriseId,
            @RequestBody List<NewEmployeeRequest> employees
    ) {
        Optional<Enterprise> enterprise = enterpriseRepository.findById(enterpriseId);

        if (enterprise.isEmpty()) {
            return ResponseEntity.badRequest().body(
                    String.format("Enterprise with id %d not found", enterpriseId)
            );
        }

        List<Employee> employeeList = new ArrayList<>();
        for (NewEmployeeRequest request : employees) {
            EmployeeType employeeType = EmployeeType.OTHER;
            try {
                employeeType = EmployeeType.valueOf(request.getEmployeeType());
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body(
                        String.format("Employee type %s not found, please choose from %s",
                                request.getEmployeeType(), EmployeeType.getAllOptions()
                        ));
            }

            Employee employee = new Employee();
            employee.setEnterprise(enterprise.get());

            employee.setEmployeeType(employeeType);
            employee.setNumber(request.getNumber());
            employeeList.add(employee);
        }
        employeeRepository.saveAll(employeeList);
        return ResponseEntity.ok().body("Employees created");

    }

    @GetMapping("/enterprise/{enterpriseId}/employee")
    public ResponseEntity<?> getEmployee(
            @PathVariable Integer enterpriseId
    ) {
        Optional<Enterprise> enterprise = enterpriseRepository.findById(enterpriseId);

        if (enterprise.isEmpty()) {
            return ResponseEntity.badRequest().body(
                    String.format("Enterprise with id %d not found", enterpriseId)
            );
        }
        List<Employee> employeeList = employeeRepository.findAllByEnterprise(enterprise.get());
        return ResponseEntity.ok().body(employeeList);
    }

}