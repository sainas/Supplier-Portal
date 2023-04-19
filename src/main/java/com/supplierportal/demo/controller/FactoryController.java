package com.supplierportal.demo.controller;

import com.supplierportal.demo.model.Enterprise;
import com.supplierportal.demo.model.Factory;
import com.supplierportal.demo.repository.EnterpriseRepository;
import com.supplierportal.demo.repository.FactoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class FactoryController {

    private FactoryRepository factoryRepository;
    private EnterpriseController enterpriseController;
    private final EnterpriseRepository enterpriseRepository;

    public FactoryController(FactoryRepository factoryRepository,
                             EnterpriseRepository enterpriseRepository) {
        this.factoryRepository = factoryRepository;
        this.enterpriseRepository = enterpriseRepository;
    }

    @GetMapping("/factory/{requestedId}")
    public ResponseEntity<Factory> findById(@PathVariable Integer requestedId) {
        Optional<Factory> factoryOptional = factoryRepository.findById(requestedId);
        if (factoryOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(factoryOptional.get());
    }



    @PostMapping("/enterprise/{enterpriseId}/factory")
    private ResponseEntity<String> createFactory(
            @PathVariable Integer enterpriseId, @RequestBody Factory newFactoryRequest) {
        Optional<Enterprise> enterprise = enterpriseRepository.findById(enterpriseId);
        if (enterprise.isPresent()) {
            newFactoryRequest.setEnterprise(enterprise.get());
            Factory savedfactory = factoryRepository.save(newFactoryRequest);
            return ResponseEntity.ok(savedfactory.toString() + "123123");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

