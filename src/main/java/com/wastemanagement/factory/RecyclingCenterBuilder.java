package com.wastemanagement.factory;

import com.wastemanagement.model.Address;
import com.wastemanagement.model.RecyclingCenter;

public class RecyclingCenterBuilder {

    private int id;
    private String name;
    private int capacity;
    private Address address;

    public RecyclingCenterBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public RecyclingCenterBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public RecyclingCenterBuilder setCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    public RecyclingCenterBuilder setAddress(Address address) {
        this.address = address;
        return this;
    }

    public RecyclingCenterBuilder setAddress(String street, String city, String postalCode) {
        this.address = new Address(street, city, postalCode);
        return this;
    }

    public RecyclingCenter build() {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalStateException("Name is required");
        }
        if (capacity <= 0) {
            throw new IllegalStateException("Capacity must be positive");
        }

        return new RecyclingCenter(id, name, capacity, address);
    }
}