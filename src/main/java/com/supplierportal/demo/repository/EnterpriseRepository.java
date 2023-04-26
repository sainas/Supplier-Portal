package com.supplierportal.demo.repository;

import com.supplierportal.demo.model.Enterprise;
import org.springframework.data.repository.CrudRepository;

public interface EnterpriseRepository extends CrudRepository<Enterprise, Integer> {
    public Iterable<Enterprise> findAllByOrderByNameAsc();
}