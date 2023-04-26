package com.supplierportal.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product_step_keyprocess", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"product_step_id", "keyprocess_id"})
})
public class ProductStepKeyprocess {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_step_keyprocess_id_seq")
    @SequenceGenerator(name = "product_step_keyprocess_id_seq", sequenceName = "product_step_keyprocess_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_step_id", nullable = false)
    private ProductStep productStep;

    @ManyToOne(optional = false)
    @JoinColumn(name = "keyprocess_id", nullable = false)
    private Process process;

    @Column(name = "is_bottleneck", nullable = false)
    private Boolean isBottleneck = false;

}