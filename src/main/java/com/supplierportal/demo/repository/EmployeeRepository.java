package com.supplierportal.demo.repository;

import com.supplierportal.demo.model.Employee;
import com.supplierportal.demo.model.EmployeeId;
import com.supplierportal.demo.model.Enterprise;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, EmployeeId> {

    List<Employee> findAllByEnterprise(Enterprise enterprise);
}