package com.supplierportal.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "enterprise_product_volume", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "enterprise_id", "product_id", "year"})
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(EnterpriseProductVolumeId.class)
public class EnterpriseProductVolume {

    @Id
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "enterprise_id")
    private Enterprise enterprise;

    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIncludeProperties(value = {"id", "name"})
    private Product product;

    @Id
    @Column(name = "year")
    private Integer year;

    @Column(name = "planned")
    private Integer planned;

    @Column(name = "occupied")
    private Integer occupied;

    @Column(name = "last_update", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date lastUpdate;

    @PrePersist
    protected void onCreate() {
        lastUpdate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        lastUpdate = new Date();
    }

}