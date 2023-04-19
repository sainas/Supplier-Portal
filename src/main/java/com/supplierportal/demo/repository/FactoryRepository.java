package com.supplierportal.demo.repository;

import com.supplierportal.demo.model.Factory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FactoryRepository extends CrudRepository<Factory, Integer> {
    @Query("select f from Factory f where f.enterprise.id = ?1")
    List<Factory> findByEnterprise_Id(Integer id);
}