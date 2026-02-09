package com.wastemanagement.service;

import com.wastemanagement.factory.WasteItemFactory;
import com.wastemanagement.model.WasteItem;
import com.wastemanagement.repository.WasteItemRepository;
import com.wastemanagement.repository.RecyclingCenterRepository;
import com.wastemanagement.exception.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class WasteService {

    private final WasteItemRepository wasteItemRepository;
    private final RecyclingCenterRepository recyclingCenterRepository;
    private final WasteItemFactory wasteItemFactory;

    public WasteService(WasteItemRepository wasteItemRepository,
                        RecyclingCenterRepository recyclingCenterRepository,
                        WasteItemFactory wasteItemFactory) {
        this.wasteItemRepository = wasteItemRepository;
        this.recyclingCenterRepository = recyclingCenterRepository;
        this.wasteItemFactory = wasteItemFactory;
    }

    public List<WasteItem> getAllWasteItems() {
        return wasteItemRepository.findAll();
    }

    public Optional<WasteItem> getWasteItemById(int id) {
        return wasteItemRepository.findById(id);
    }

    public List<WasteItem> getWasteItemsByType(String type) {
        return wasteItemRepository.findByWasteTypeNative(type);
    }

    public List<WasteItem> getRecyclableWasteItems() {
        return wasteItemRepository.findByRecyclableTrue();
    }

    public List<WasteItem> getWasteItemsByCenter(int centerId) {
        return wasteItemRepository.findByCenterId(centerId);
    }

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

            double totalWeight = wasteItemRepository.getTotalWeightByCenter(wasteItem.getCenterId());
            if (totalWeight > 1000) {
                throw new InvalidInputException("Center capacity exceeded");
            }
        }

        return wasteItemRepository.save(wasteItem);
    }

    public WasteItem updateWasteItem(WasteItem wasteItem) {
        if (!wasteItemRepository.existsById(wasteItem.getId())) {
            throw new ResourceNotFoundException("Waste item not found with id: " + wasteItem.getId());
        }

        wasteItem.validate();
        return wasteItemRepository.save(wasteItem);
    }

    public void deleteWasteItem(int id) {
        if (!wasteItemRepository.existsById(id)) {
            throw new ResourceNotFoundException("Waste item not found with id: " + id);
        }
        wasteItemRepository.deleteById(id);
    }

    public List<WasteItem> getWasteItemsSortedByWeight() {
        return wasteItemRepository.findAllByOrderByWeightAsc();
    }

    public List<WasteItem> getWasteItemsSortedByRecyclingCost() {
        return wasteItemRepository.findAllOrderByRecyclingCost();
    }

    public WasteItem createWasteItemUsingFactory(String type, int id, String name, double weight, boolean recyclable, Integer centerId) {
        WasteItem item = wasteItemFactory.createWasteItem(type, id, name, weight, recyclable);
        if (centerId != null) {
            item.setCenterId(centerId);
        }
        return createWasteItem(item);
    }
}