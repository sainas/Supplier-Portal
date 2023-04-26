package com.supplierportal.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "product_step", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"product_id", "step_number"})
})
public class ProductStep {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_step_id_seq")
    @SequenceGenerator(name = "product_step_id_seq", sequenceName = "product_step_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "step_number", nullable = false)
    private Integer stepNumber;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "productStep", orphanRemoval = true)
    @OrderBy("id ASC")
    private Set<ProductStepKeyprocess> productStepKeyprocesses = new LinkedHashSet<>();

}