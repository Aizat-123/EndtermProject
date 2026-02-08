package com.wastemanagement.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@DiscriminatorValue("paper")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class PaperWaste extends WasteItem {

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