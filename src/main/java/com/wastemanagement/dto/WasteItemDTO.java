package com.wastemanagement.dto;

import com.wastemanagement.model.WasteItem;
import jakarta.validation.constraints.*;

public class WasteItemDTO {

    @NotNull(message = "ID is required")
    private Integer id;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotBlank(message = "Waste type is required")
    @Pattern(regexp = "plastic|electronic|paper", message = "Waste type must be plastic, electronic, or paper")
    private String wasteType;

    @Positive(message = "Weight must be positive")
    @Max(value = 1000, message = "Weight cannot exceed 1000 kg")
    private Double weight;

    private Boolean recyclable;

    private Integer centerId;

    // Constructors
    public WasteItemDTO() {
    }

    public WasteItemDTO(Integer id, String name, String wasteType, Double weight, Boolean recyclable, Integer centerId) {
        this.id = id;
        this.name = name;
        this.wasteType = wasteType;
        this.weight = weight;
        this.recyclable = recyclable;
        this.centerId = centerId;
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

    public String getWasteType() {
        return wasteType;
    }

    public void setWasteType(String wasteType) {
        this.wasteType = wasteType;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Boolean getRecyclable() {
        return recyclable;
    }

    public void setRecyclable(Boolean recyclable) {
        this.recyclable = recyclable;
    }

    public Integer getCenterId() {
        return centerId;
    }

    public void setCenterId(Integer centerId) {
        this.centerId = centerId;
    }

    public static WasteItemDTO fromModel(WasteItem item) {
        return new WasteItemDTO(
                item.getId(),
                item.getName(),
                item.getWasteType(),
                item.getWeight(),
                item.isRecyclable(),
                item.getCenterId()
        );
    }

    @Override
    public String toString() {
        return "WasteItemDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", wasteType='" + wasteType + '\'' +
                ", weight=" + weight +
                ", recyclable=" + recyclable +
                ", centerId=" + centerId +
                '}';
    }
}