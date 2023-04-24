package com.supplierportal.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "employee", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "enterprise_id", "employee_type" })
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(EmployeeId.class)
public class Employee {

    @Id
    @ManyToOne
    @JoinColumn(name = "enterprise_id")
    private Enterprise enterprise;

    @Id
    @Column(name = "employee_type")
    private String employeeType;

    @Column(name = "number")
    private Integer number;

}