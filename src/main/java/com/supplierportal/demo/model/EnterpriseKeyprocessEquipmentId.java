package com.supplierportal.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
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
public class EnterpriseKeyprocessEquipmentId implements Serializable {

    @Column(name = "enterprise_id")
    private Integer enterpriseId;

    @Column(name = "product_step_keyprocess_id")
    private Integer productStepKeyprocessId;

    @Column(name = "equipment_id")
    private Integer equipmentId;

}