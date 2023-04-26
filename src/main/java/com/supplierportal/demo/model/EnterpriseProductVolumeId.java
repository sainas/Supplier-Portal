package com.supplierportal.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EnterpriseProductVolumeId implements Serializable {

    private Enterprise enterprise;
    private Product product;
    private Integer year;

}