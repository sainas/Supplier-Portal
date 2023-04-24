package com.supplierportal.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee")
public class Employee {

    @EmbeddedId
    private EmployeeId id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "enterprise_id", nullable = false)
    @MapsId("enterpriseId")
    private Enterprise enterprise;

    @Column(name = "employee_type")
    @Enumerated(EnumType.STRING)
    @MapsId("employeeType")
    private EmployeeType employeeType;


    @Column(name = "number")
    private Integer number;
}