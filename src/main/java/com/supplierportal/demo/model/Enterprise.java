package com.supplierportal.demo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "enterprise")

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Enterprise {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "enterprise_id_seq")
    @SequenceGenerator(name = "enterprise_id_seq", sequenceName = "enterprise_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "enterprise", cascade = CascadeType.ALL)
    private Set<Factory> factories = new LinkedHashSet<>();

    public Enterprise(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Enterprise(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Enterprise that = (Enterprise) o;
        return this.id.equals(that.id)
                && this.name.equals(that.name);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ")";
    }
}