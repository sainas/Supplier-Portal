package com.supplierportal.demo.request;

import lombok.*;

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
