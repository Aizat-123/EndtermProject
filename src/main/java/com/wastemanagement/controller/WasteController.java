package com.wastemanagement.controller;

import com.wastemanagement.model.WasteItem;
import com.wastemanagement.dto.WasteItemDTO;
import com.wastemanagement.service.WasteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/waste-items")
public class WasteController {

    private final WasteService wasteService;

    public WasteController(WasteService wasteService) {
        this.wasteService = wasteService;
    }

    @GetMapping
    public ResponseEntity<List<WasteItem>> getAllWasteItems() {
        List<WasteItem> wasteItems = wasteService.getAllWasteItems();
        return ResponseEntity.ok(wasteItems);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WasteItem> getWasteItemById(@PathVariable int id) {
        Optional<WasteItem> wasteItem = wasteService.getWasteItemById(id);

        if (wasteItem.isPresent()) {
            return ResponseEntity.ok(wasteItem.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<WasteItem>> getWasteItemsByType(@PathVariable String type) {
        List<WasteItem> wasteItems = wasteService.getWasteItemsByType(type);
        return ResponseEntity.ok(wasteItems);
    }

    @GetMapping("/recyclable")
    public ResponseEntity<List<WasteItem>> getRecyclableWasteItems() {
        List<WasteItem> wasteItems = wasteService.getRecyclableWasteItems();
        return ResponseEntity.ok(wasteItems);
    }

    @GetMapping("/center/{centerId}")
    public ResponseEntity<List<WasteItem>> getWasteItemsByCenter(@PathVariable int centerId) {
        List<WasteItem> wasteItems = wasteService.getWasteItemsByCenter(centerId);
        return ResponseEntity.ok(wasteItems);
    }

    @PostMapping
    public ResponseEntity<WasteItem> createWasteItem(@Valid @RequestBody WasteItem wasteItem) {
        WasteItem createdWasteItem = wasteService.createWasteItem(wasteItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdWasteItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WasteItem> updateWasteItem(
            @PathVariable int id,
            @Valid @RequestBody WasteItem wasteItem) {
        wasteItem.setId(id);
        WasteItem updatedWasteItem = wasteService.updateWasteItem(wasteItem);
        return ResponseEntity.ok(updatedWasteItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWasteItem(@PathVariable int id) {
        wasteService.deleteWasteItem(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/sorted/weight")
    public ResponseEntity<List<WasteItem>> getWasteItemsSortedByWeight() {
        List<WasteItem> wasteItems = wasteService.getWasteItemsSortedByWeight();
        return ResponseEntity.ok(wasteItems);
    }

    @GetMapping("/sorted/cost")
    public ResponseEntity<List<WasteItem>> getWasteItemsSortedByRecyclingCost() {
        List<WasteItem> wasteItems = wasteService.getWasteItemsSortedByRecyclingCost();
        return ResponseEntity.ok(wasteItems);
    }

    @PostMapping("/dto")
    public ResponseEntity<WasteItem> createWasteItemFromDTO(@Valid @RequestBody WasteItemDTO wasteItemDTO) {
        // Просто используем фабрику напрямую
        WasteItem created = wasteService.createWasteItemUsingFactory(
                wasteItemDTO.getWasteType(),
                wasteItemDTO.getId(),
                wasteItemDTO.getName(),
                wasteItemDTO.getWeight(),
                wasteItemDTO.getRecyclable() != null ? wasteItemDTO.getRecyclable() : false,
                wasteItemDTO.getCenterId()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}/dto")
    public ResponseEntity<WasteItemDTO> getWasteItemDTOById(@PathVariable int id) {
        Optional<WasteItem> wasteItem = wasteService.getWasteItemById(id);

        if (wasteItem.isPresent()) {
            WasteItemDTO dto = WasteItemDTO.fromModel(wasteItem.get());
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/factory")
    public ResponseEntity<WasteItem> createUsingFactory(
            @RequestParam String type,
            @RequestParam int id,
            @RequestParam String name,
            @RequestParam double weight,
            @RequestParam boolean recyclable,
            @RequestParam(required = false) Integer centerId) {

        WasteItem item = wasteService.createWasteItemUsingFactory(
                type, id, name, weight, recyclable, centerId
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(item);
    }

    @GetMapping("/demo/polymorphism")
    public ResponseEntity<String> demonstratePolymorphism() {
        StringBuilder result = new StringBuilder();
        result.append("=== Polymorphism Demonstration ===\n\n");

        List<WasteItem> items = wasteService.getAllWasteItems();

        for (WasteItem item : items) {
            result.append("Item: ").append(item.getName()).append("\n");
            result.append("  Type: ").append(item.getWasteType()).append("\n");
            result.append("  Disposal: ").append(item.disposalInfo()).append("\n");
            result.append("  Recycling Cost: $").append(item.calculateRecyclingCost()).append("\n");
            result.append("  Environmental Impact: ").append(item.calculateEnvironmentalImpact()).append("\n");
            result.append("  Valid: ").append(item.isValid()).append("\n\n");
        }

        return ResponseEntity.ok(result.toString());
    }
}