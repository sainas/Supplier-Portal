package com.supplierportal.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "employee", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "enterprise_id", "employee_type"})
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
    @JsonIgnore
    private Enterprise enterprise;

    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "employee_type")
    private EmployeeType employeeType;

    @Column(name = "number")
    private Integer number;

}