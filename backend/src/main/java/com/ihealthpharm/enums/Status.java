package com.ihealthpharm.enums;

/**
 * @author Vikas
 */
public enum Status {

    Active("Active"), Inactive("Inactive");

    private String id;

    private Status(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
