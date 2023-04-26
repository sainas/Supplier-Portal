package com.supplierportal.demo.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewKeyprocessEquipmentRequest {

    int productStepKeyprocessId;
    int equipmentId;
    double capacityPerHour;
    int quantity;
    int purchaseCycle;

}
