package com.pnu.servicebike.model.enums;

public enum StatusEnum {
    AVAILABLE("AVAILABLE"),
    IN_USE("IN_USE"),
    MAINTENANCE("MAINTENANCE"),
    BROKEN("BROKEN");

    private final String status;

    StatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
