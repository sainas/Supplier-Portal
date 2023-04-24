package com.supplierportal.demo.model;

public enum EmployeeType {
    PRODUCT_DEVELOPMENT("产品研发"),
    PROCESS_DEVELOPMENT("工艺研发"),
    MANUFACTURING("制造"),
    OTHER("其他");

    private final String value;

    EmployeeType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}




