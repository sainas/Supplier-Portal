package com.supplierportal.demo.repository;

import com.supplierportal.demo.model.EmployeeId;
import com.supplierportal.demo.model.Enterprise;
import com.supplierportal.demo.model.EnterpriseProductVolume;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EnterpriseProductVolumeRepository extends CrudRepository<EnterpriseProductVolume, EmployeeId> {

    List<EnterpriseProductVolume> findAllByEnterprise(Enterprise enterprise);

    List<EnterpriseProductVolume> findAllByEnterpriseAndYear(Enterprise enterprise, Integer year);
}