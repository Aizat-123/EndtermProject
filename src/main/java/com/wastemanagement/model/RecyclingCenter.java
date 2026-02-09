package com.wastemanagement.model;

import com.wastemanagement.model.base.BaseEntity;
import com.wastemanagement.exception.InvalidInputException;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "recycling_center")
public class RecyclingCenter extends BaseEntity {

    @Column(nullable = false)
    private int capacity;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "center", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<WasteItem> wasteItems = new ArrayList<>();

    // Constructors
    public RecyclingCenter() {
        super();
    }

    public RecyclingCenter(int id, String name, int capacity, Address address) {
        super(id, name);
        this.capacity = capacity;
        this.address = address;
    }

    // Getters and setters
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<WasteItem> getWasteItems() {
        return wasteItems;
    }

    public void setWasteItems(List<WasteItem> wasteItems) {
        this.wasteItems = wasteItems;
    }

    @Override
    public String getType() {
        return "RecyclingCenter";
    }

    @Override
    public void validate() throws InvalidInputException {
        if (getName() == null || getName().trim().isEmpty()) {
            throw new InvalidInputException("Center name cannot be empty");
        }
        if (capacity <= 0) {
            throw new InvalidInputException("Capacity must be positive");
        }
    }

    public String getLocationInfo() {
        if (address != null) {
            return getName() + " located at " + address.getFullAddress();
        }
        return getName() + " (no address specified)";
    }

    @Override
    public String toString() {
        return "RecyclingCenter{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", capacity=" + capacity +
                ", address=" + address +
                '}';
    }
}