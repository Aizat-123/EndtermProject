package com.wastemanagement.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("paper")
public class PaperWaste extends WasteItem {

    public PaperWaste() {
    }

    public PaperWaste(int id, String name, double weight, boolean recyclable) {
        super(id, name, weight, recyclable);
    }

    @Override
    public String getWasteType() {
        return "paper";
    }

    @Override
    public double calculateRecyclingCost() {
        return weight * 50;
    }
}