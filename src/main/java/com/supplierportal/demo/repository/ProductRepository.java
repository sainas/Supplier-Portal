package com.supplierportal.demo.repository;

import com.supplierportal.demo.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {
}