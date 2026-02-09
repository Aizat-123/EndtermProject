package com.wastemanagement.model;

import com.wastemanagement.model.base.BaseEntity;
import com.wastemanagement.model.interfaces.Chargeable;
import com.wastemanagement.model.interfaces.Searchable;
import com.wastemanagement.model.interfaces.Validatable;
import com.wastemanagement.exception.InvalidInputException;
import jakarta.persistence.*;


@Entity
@Table(name = "waste_items")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "waste_type", discriminatorType = DiscriminatorType.STRING)
public abstract class WasteItem extends BaseEntity implements Chargeable, Validatable, Searchable<String> {

    @Column(nullable = false)
    protected double weight;

    @Column(nullable = false)
    protected boolean recyclable;

    @Column(name = "center_id")
    protected Integer centerId;

    @Transient
    private String wasteType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "center_id", insertable = false, updatable = false)
    protected RecyclingCenter center;

    // Constructors
    protected WasteItem() {
        super();
    }

    public WasteItem(int id, String name, double weight, boolean recyclable) {
        super(id, name);
        this.weight = weight;
        this.recyclable = recyclable;
    }

    // Getters and setters
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isRecyclable() {
        return recyclable;
    }

    public void setRecyclable(boolean recyclable) {
        this.recyclable = recyclable;
    }

    public Integer getCenterId() {
        return centerId;
    }

    public void setCenterId(Integer centerId) {
        this.centerId = centerId;
    }

    public RecyclingCenter getCenter() {
        return center;
    }

    public void setCenter(RecyclingCenter center) {
        this.center = center;
    }

    @Override
    public String getType() {
        return getWasteType();
    }

    @Override
    public void validate() {
        if (getName() == null || getName().trim().isEmpty()) {
            throw new InvalidInputException("Waste item name cannot be empty");
        }
        if (weight <= 0) {
            throw new InvalidInputException("Weight must be positive");
        }
    }

    @Override
    public boolean matches(String criteria) {
        return getName().toLowerCase().contains(criteria.toLowerCase()) ||
                getWasteType().toLowerCase().contains(criteria.toLowerCase());
    }

    public double calculateEnvironmentalImpact() {
        return weight * (recyclable ? 0.1 : 1.0);
    }

    public abstract String getWasteType();

    public String disposalInfo() {
        return recyclable ? "Recycle" : "Dispose";
    }

    @Override
    public String toString() {
        return "WasteItem{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", wasteType='" + getWasteType() + '\'' +
                ", weight=" + weight +
                ", recyclable=" + recyclable +
                ", centerId=" + centerId +
                '}';
    }
}
