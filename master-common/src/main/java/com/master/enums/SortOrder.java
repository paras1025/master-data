package com.master.enums;

public enum SortOrder {
    ASC("asc"), DESC("desc");
    private final String code;

    SortOrder(String code) {
        this.code = code;
    }
    public String getCode() {
        return code;
    }
}
