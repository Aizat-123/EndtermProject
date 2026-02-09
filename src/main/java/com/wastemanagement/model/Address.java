package com.wastemanagement.model;

import jakarta.persistence.*;


@Embeddable
public class Address {

    private String street;
    private String city;
    private String postalCode;

    // Constructors
    public Address() {
    }

    public Address(String street, String city, String postalCode) {
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
    }

    // Getters and setters
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getFullAddress() {
        return String.format("%s, %s %s", street, city, postalCode);
    }

    @Override
    public String toString() {
        return getFullAddress();
    }
}