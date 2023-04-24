package com.supplierportal.demo.request;

import com.supplierportal.demo.model.EmployeeType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewEmployeeRequest {

    String employeeType;
    int number;

}
