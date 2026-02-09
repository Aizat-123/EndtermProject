package com.wastemanagement.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("electronic")
public class ElectronicWaste extends WasteItem {

    public ElectronicWaste() {
    }

    public ElectronicWaste(int id, String name, double weight, boolean recyclable) {
        super(id, name, weight, recyclable);
    }

    @Override
    public String getWasteType() {
        return "electronic";
    }

    @Override
    public double calculateRecyclingCost() {
        return weight * 600;
    }
}
