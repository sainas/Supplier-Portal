package com.supplierportal.demo.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewFactoryRequest {
    private String name;
    private int enterpriseId;

    public NewFactoryRequest(int enterpriseId, String name) {
        this.enterpriseId = enterpriseId;
        this.name = name;
    }

}