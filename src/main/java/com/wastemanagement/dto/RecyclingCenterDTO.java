package com.wastemanagement.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

    // Можно добавить методы преобразования
    public static RecyclingCenterDTO fromModel(com.wastemanagement.model.RecyclingCenter center) {
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
}