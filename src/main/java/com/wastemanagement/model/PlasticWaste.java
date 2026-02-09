package com.wastemanagement.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("plastic")
public class PlasticWaste extends WasteItem {

    // Constructors
    public PlasticWaste() {
    }

    public PlasticWaste(int id, String name, double weight, boolean recyclable) {
        super(id, name, weight, recyclable);
    }

    @Override
    public String getWasteType() {
        return "plastic";
    }

    @Override
    public double calculateRecyclingCost() {
        return weight * 40;
    }
}