package com.supplierportal.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;


@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeId implements Serializable {

    @Column(name = "employee_type", columnDefinition = "employee_type_enum")
    @Enumerated(EnumType.STRING)
    private EmployeeType employeeType;

    @Column(name = "enterprise_id")
    private Integer enterpriseId;

}