package com.supplierportal.demo.controller;

import com.supplierportal.demo.model.Enterprise;
import com.supplierportal.demo.model.EnterpriseProductVolume;
import com.supplierportal.demo.model.Product;
import com.supplierportal.demo.repository.EnterpriseRepository;
import com.supplierportal.demo.repository.ProductRepository;
import com.supplierportal.demo.repository.EnterpriseProductVolumeRepository;
import com.supplierportal.demo.request.NewEnterpriseProductVolumeRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class EnterpriseProductVolumeController {

    private final EnterpriseRepository enterpriseRepository;
    private final ProductRepository productRepository;
    private final EnterpriseProductVolumeRepository enterpriseProductVolumeRepository;

    public EnterpriseProductVolumeController(EnterpriseRepository enterpriseRepository,
                                             ProductRepository productRepository,
                                             EnterpriseProductVolumeRepository enterpriseProductVolumeRepository) {
        this.enterpriseRepository = enterpriseRepository;
        this.productRepository = productRepository;
        this.enterpriseProductVolumeRepository = enterpriseProductVolumeRepository;
    }

    @PostMapping("/enterprise/{enterpriseId}/productvolume")
    public ResponseEntity<String> createEnterpriseProductVolume(
            @PathVariable Integer enterpriseId,
            @RequestBody List<NewEnterpriseProductVolumeRequest> productVolumes
    ) {
        Optional<Enterprise> enterprise = enterpriseRepository.findById(enterpriseId);

        if (enterprise.isEmpty()) {
            return ResponseEntity.badRequest().body(
                    String.format("Enterprise with id %d not found", enterpriseId)
            );
        }

        List<EnterpriseProductVolume> productVolumeList = new ArrayList<>();
        for (NewEnterpriseProductVolumeRequest request : productVolumes) {
            Optional<Product> product = productRepository.findById(request.getProductId());

            if (product.isEmpty()) {
                return ResponseEntity.badRequest().body(
                        String.format("Product with id %d not found", request.getProductId())
                );
            }

            EnterpriseProductVolume productVolume = new EnterpriseProductVolume();
            productVolume.setEnterprise(enterprise.get());
            productVolume.setProduct(product.get());
            productVolume.setYear(request.getYear());
            productVolume.setPlanned(request.getPlanned());
            productVolume.setOccupied(request.getOccupied());
            productVolumeList.add(productVolume);
        }
        enterpriseProductVolumeRepository.saveAll(productVolumeList);
        return ResponseEntity.ok().body("Enterprise product volumes created");
    }

    @GetMapping("/enterprise/{enterpriseId}/productvolume")
    public ResponseEntity<?> getEnterpriseProductVolume(
            @PathVariable Integer enterpriseId
    ) {
        Optional<Enterprise> enterprise = enterpriseRepository.findById(enterpriseId);

        if (enterprise.isEmpty()) {
            return ResponseEntity.badRequest().body(
                    String.format("Enterprise with id %d not found", enterpriseId)
            );
        }
        List<EnterpriseProductVolume> productVolumeList = enterpriseProductVolumeRepository.findAllByEnterprise(enterprise.get());
        return ResponseEntity.ok().body(productVolumeList);
    }

}
