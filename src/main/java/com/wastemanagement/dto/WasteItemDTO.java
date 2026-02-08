package com.wastemanagement.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

    // Можно добавить методы преобразования
    public static WasteItemDTO fromModel(com.wastemanagement.model.WasteItem item) {
        return new WasteItemDTO(
                item.getId(),
                item.getName(),
                item.getWasteType(),
                item.getWeight(),
                item.isRecyclable(),
                item.getCenterId()
        );
    }
}