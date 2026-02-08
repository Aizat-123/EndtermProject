package com.wastemanagement.model.base;

import com.wastemanagement.exception.InvalidInputException;

public abstract class BaseEntity {
    private int id;
    private String name;

    public BaseEntity() {
    }

    public BaseEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public abstract String getType();
    public abstract void validate() throws InvalidInputException;

    public String getInfo() {
        return String.format("[%d] %s (%s)", id, name, getType());
    }

    // Getters/Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}