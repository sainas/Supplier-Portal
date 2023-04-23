package com.supplierportal.demo.controller;

import com.supplierportal.demo.model.Enterprise;
import com.supplierportal.demo.model.Factory;
import com.supplierportal.demo.repository.EnterpriseRepository;
import com.supplierportal.demo.request.NewEnterpriseRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/enterprise")
public class EnterpriseController {

    private final EnterpriseRepository enterpriseRepository;

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
    private ResponseEntity<String> createEnterprise(
            @RequestBody Enterprise newEnterprise) {
        Enterprise savedNewEnterprise = enterpriseRepository.save(newEnterprise);
        return ResponseEntity.ok(
                String.format("Enterprise created successfully with id %d", savedNewEnterprise.getId())
        );
    }
}
