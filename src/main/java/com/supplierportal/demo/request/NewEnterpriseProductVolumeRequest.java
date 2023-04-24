package com.supplierportal.demo.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewEnterpriseProductVolumeRequest {

    int productId;
    int year;
    int planned;
    int occupied;
}
