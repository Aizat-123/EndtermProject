package com.wastemanagement.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String street;
    private String city;
    private String postalCode;

    public String getFullAddress() {
        return String.format("%s, %s %s", street, city, postalCode);
    }
}