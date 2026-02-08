package com.wastemanagement.service;

import com.wastemanagement.model.RecyclingCenter;
import com.wastemanagement.model.WasteItem;
import com.wastemanagement.repository.RecyclingCenterRepository;
import com.wastemanagement.repository.WasteItemRepository;
import com.wastemanagement.exception.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RecyclingCenterService {

    private final RecyclingCenterRepository recyclingCenterRepository;
    private final WasteItemRepository wasteItemRepository;

    public RecyclingCenterService(RecyclingCenterRepository recyclingCenterRepository,
                                  WasteItemRepository wasteItemRepository) {
        this.recyclingCenterRepository = recyclingCenterRepository;
        this.wasteItemRepository = wasteItemRepository;
    }

    public List<RecyclingCenter> getAllCenters() {
        return recyclingCenterRepository.findAll();
    }

    public Optional<RecyclingCenter> getCenterById(int id) {
        return recyclingCenterRepository.findById(id);
    }

    public RecyclingCenter createCenter(RecyclingCenter center) {
        center.validate();

        if (recyclingCenterRepository.existsById(center.getId())) {
            throw new DuplicateResourceException("Recycling center with id " + center.getId() + " already exists");
        }

        return recyclingCenterRepository.save(center);
    }

    public RecyclingCenter updateCenter(RecyclingCenter center) {
        if (!recyclingCenterRepository.existsById(center.getId())) {
            throw new ResourceNotFoundException("Recycling center not found with id: " + center.getId());
        }

        center.validate();
        return recyclingCenterRepository.save(center);
    }

    public void deleteCenter(int id) {
        if (!recyclingCenterRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recycling center not found with id: " + id);
        }

        // Check if center has waste items
        List<WasteItem> wasteItems = wasteItemRepository.findByCenterId(id);
        if (!wasteItems.isEmpty()) {
            throw new InvalidInputException("Cannot delete center with existing waste items");
        }

        recyclingCenterRepository.deleteById(id);
    }

    public List<WasteItem> getWasteItemsByCenterId(int centerId) {
        if (!recyclingCenterRepository.existsById(centerId)) {
            throw new ResourceNotFoundException("Recycling center not found with id: " + centerId);
        }
        return wasteItemRepository.findByCenterId(centerId);
    }

    public Double getTotalWeightByCenter(int centerId) {
        if (!recyclingCenterRepository.existsById(centerId)) {
            throw new ResourceNotFoundException("Recycling center not found with id: " + centerId);
        }
        return wasteItemRepository.getTotalWeightByCenter(centerId);
    }
}