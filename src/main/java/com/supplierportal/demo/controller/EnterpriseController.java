package com.supplierportal.demo.controller;

import com.supplierportal.demo.model.Enterprise;
import com.supplierportal.demo.model.Factory;
import com.supplierportal.demo.repository.EnterpriseRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/enterprise")
public class EnterpriseController {

    private EnterpriseRepository enterpriseRepository;

    public EnterpriseController(EnterpriseRepository enterpriseRepository) {
        this.enterpriseRepository = enterpriseRepository;
    }

    @GetMapping("/{requestedId}")
    public ResponseEntity<Enterprise> findById(@PathVariable Integer requestedId) {
        Optional<Enterprise> enterpriseOptional = enterpriseRepository.findById(requestedId);
        if (enterpriseOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        enterpriseOptional.get().getFactories();
        return ResponseEntity.ok(enterpriseOptional.get());
    }

    @PostMapping
    private ResponseEntity<Enterprise> createEnterprise(
            @RequestBody Enterprise newEnterpriseRequest) {
        Enterprise savedEnterprise = enterpriseRepository.save(newEnterpriseRequest);
        return ResponseEntity.ok(savedEnterprise);
    }
}
