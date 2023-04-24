package com.supplierportal.demo.repository;

import com.supplierportal.demo.model.EmployeeId;
import com.supplierportal.demo.model.Enterprise;
import com.supplierportal.demo.model.EnterpriseProductVolume;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EnterpriseProductVolumeRepository extends CrudRepository<EnterpriseProductVolume, EmployeeId> {

    public List<EnterpriseProductVolume> findAllByEnterprise(Enterprise enterprise);

    public List<EnterpriseProductVolume> findAllByEnterpriseAndYear(Enterprise enterprise, Integer year);
}