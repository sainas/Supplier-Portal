package com.supplierportal.demo.repository;

import com.supplierportal.demo.model.Employee;
import com.supplierportal.demo.model.EmployeeId;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, EmployeeId> {
}