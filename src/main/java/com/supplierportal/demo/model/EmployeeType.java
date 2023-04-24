package com.supplierportal.demo.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EmployeeType {
    PRODUCT_DEVELOPMENT("产品研发"),
    PROCESS_DEVELOPMENT("工艺研发"),
    MANUFACTURING("制造"),
    OTHER("其他");

    private final String value;

    EmployeeType(String value) {
        this.value = value;
    }

    public static String getAllOptions() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (EmployeeType option : EmployeeType.values()) {
            sb.append(option.toString()).append(": ").append(option.getValue())
                    .append(", ");
        }
        return sb.substring(0, sb.length() - 2) + "]";
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}




