package com.supplierportal.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Table(name = "factory")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Factory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "factory_id_seq")
    @SequenceGenerator(name = "factory_id_seq", sequenceName = "factory_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enterprise_id", nullable = false)
    private Enterprise enterprise;

    @Column(name = "name")
    private String name;

    public Factory(String name, Enterprise enterprise) {
        this.name = name;
        this.enterprise = enterprise;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Factory factory = (Factory) o;
        return getId() != null && Objects.equals(getId(), factory.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ")";
    }
}