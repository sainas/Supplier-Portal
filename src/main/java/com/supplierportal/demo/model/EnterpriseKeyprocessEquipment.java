package com.supplierportal.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "enterprise_keyprocess_equipment", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "enterprise_id", "product_step_keyprocess_id", "equipment_id"})
})

public class EnterpriseKeyprocessEquipment {
    @EmbeddedId
    private EnterpriseKeyprocessEquipmentId id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "enterprise_id", nullable = false)
    @MapsId("enterpriseId")
    private Enterprise enterprise;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_step_keyprocess_id", nullable = false)
    @MapsId("productStepKeyprocessId")
    private ProductStepKeyprocess productStepKeyprocess;

    @ManyToOne(optional = false)
    @JoinColumn(name = "equipment_id", nullable = false)
    @MapsId("equipmentId")
    private Equipment equipment;

    private Integer quantity;

    private Double capacity_per_hour;

    private Integer purchase_cycle;

}

