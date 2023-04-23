package com.supplierportal.demo.repository;

import com.supplierportal.demo.model.EnterpriseKeyprocessEquipment;
import com.supplierportal.demo.model.Equipment;
import org.springframework.data.repository.CrudRepository;

public interface EquipmentRepository extends CrudRepository<Equipment, Integer> {

}