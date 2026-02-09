package com.wastemanagement.dto;

import com.wastemanagement.model.RecyclingCenter;
import jakarta.validation.constraints.*;

public class RecyclingCenterDTO {

    @NotNull(message = "ID is required")
    private Integer id;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @Positive(message = "Capacity must be positive")
    @Max(value = 10000, message = "Capacity cannot exceed 10000")
    private Integer capacity;

    private String street;
    private String city;
    private String postalCode;

    // Constructors
    public RecyclingCenterDTO() {
    }

    public RecyclingCenterDTO(Integer id, String name, Integer capacity, String street, String city, String postalCode) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
    }

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public static RecyclingCenterDTO fromModel(RecyclingCenter center) {
        RecyclingCenterDTO dto = new RecyclingCenterDTO();
        dto.setId(center.getId());
        dto.setName(center.getName());
        dto.setCapacity(center.getCapacity());

        if (center.getAddress() != null) {
            dto.setStreet(center.getAddress().getStreet());
            dto.setCity(center.getAddress().getCity());
            dto.setPostalCode(center.getAddress().getPostalCode());
        }

        return dto;
    }

    @Override
    public String toString() {
        return "RecyclingCenterDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }
}