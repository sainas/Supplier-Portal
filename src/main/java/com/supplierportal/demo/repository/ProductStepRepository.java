package com.supplierportal.demo.repository;

import com.supplierportal.demo.model.ProductStep;
import org.springframework.data.repository.CrudRepository;

public interface ProductStepRepository extends CrudRepository<ProductStep, Integer> {
}