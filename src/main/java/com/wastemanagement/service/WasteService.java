package com.wastemanagement.service;

// ... импорты ...

@Service
@Transactional
public class WasteService implements WasteServiceInterface {

    // ... конструктор и другие методы ...

    public WasteItem createWasteItem(WasteItem wasteItem) {
        wasteItem.validate();

        if (wasteItemRepository.existsById(wasteItem.getId())) {
            throw new DuplicateResourceException("Waste item with id " + wasteItem.getId() + " already exists");
        }

        if (wasteItem.getCenterId() != null) {
            boolean centerExists = recyclingCenterRepository.existsById(wasteItem.getCenterId());
            if (!centerExists) {
                throw new ResourceNotFoundException("Recycling center not found with id: " + wasteItem.getCenterId());
            }

            Double totalWeight = wasteItemRepository.getTotalWeightByCenter(wasteItem.getCenterId()); // Double вместо double
            if (totalWeight != null && totalWeight > 1000) {
                throw new InvalidInputException("Center capacity exceeded");
            }
        }

        return wasteItemRepository.save(wasteItem);
    }

    // ... остальные методы ...
}
