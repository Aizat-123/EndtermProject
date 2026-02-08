package com.wastemanagement.model;

import com.wastemanagement.model.base.BaseEntity;
import com.wastemanagement.exception.InvalidInputException;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "recycling_center")
@EqualsAndHashCode(callSuper = true)
public class RecyclingCenter extends BaseEntity {

    @Column(nullable = false)
    private int capacity;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "center", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<WasteItem> wasteItems = new ArrayList<>();

    public RecyclingCenter() {
        super();
    }

    public RecyclingCenter(int id, String name, int capacity, Address address) {
        super(id, name);
        this.capacity = capacity;
        this.address = address;
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
}