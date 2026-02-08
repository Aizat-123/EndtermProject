package com.wastemanagement.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@DiscriminatorValue("plastic")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class PlasticWaste extends WasteItem {

    public PlasticWaste(int id, String name, double weight, boolean recyclable) {
        super(id, name, weight, recyclable);
        // УДАЛИТЬ: this.centerId = centerId; ← ЭТОЙ СТРОКИ НЕ ДОЛЖНО БЫТЬ!
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