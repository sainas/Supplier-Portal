package com.supplierportal.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "enterprise_keyprocess_equipment", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "enterprise_id", "product_step_keyprocess_id", "equipment_id" })
})
public class EnterpriseKeyprocessEquipment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "enterprise_keyprocess_equipment_id_seq")
    @SequenceGenerator(name = "enterprise_keyprocess_equipment_id_seq", sequenceName = "enterprise_keyprocess_equipment_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "enterprise_id", nullable = false)
    private Enterprise enterprise;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_step_keyprocess_id", nullable = false)
    private ProductStepKeyprocess productStepKeyprocess;

    @ManyToOne(optional = false)
    @JoinColumn(name = "equipment_id", nullable = false)
    private Equipment equipment;

}