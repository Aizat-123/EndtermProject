package com.wastemanagement.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@DiscriminatorValue("electronic")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ElectronicWaste extends WasteItem {

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
