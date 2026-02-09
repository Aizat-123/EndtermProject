package com.wastemanagement.controller;

import com.wastemanagement.model.RecyclingCenter;
import com.wastemanagement.service.RecyclingCenterService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recycling-centers")
public class RecyclingCenterController {

    private final RecyclingCenterService recyclingCenterService;

    public RecyclingCenterController(RecyclingCenterService recyclingCenterService) {
        this.recyclingCenterService = recyclingCenterService;
    }

    @GetMapping
    public ResponseEntity<List<RecyclingCenter>> getAllCenters() {
        return ResponseEntity.ok(recyclingCenterService.getAllCenters());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecyclingCenter> getCenterById(@PathVariable int id) {
        return recyclingCenterService.getCenterById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RecyclingCenter> createCenter(@Valid @RequestBody RecyclingCenter center) {
        RecyclingCenter created = recyclingCenterService.createCenter(center);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecyclingCenter> updateCenter(
            @PathVariable int id,
            @Valid @RequestBody RecyclingCenter center) {
        center.setId(id);
        return ResponseEntity.ok(recyclingCenterService.updateCenter(center));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCenter(@PathVariable int id) {
        recyclingCenterService.deleteCenter(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/waste-items")
    public ResponseEntity<?> getWasteItemsByCenterId(@PathVariable int id) {
        return ResponseEntity.ok(recyclingCenterService.getWasteItemsByCenterId(id));
    }

    @GetMapping("/{id}/total-weight")
    public ResponseEntity<Double> getTotalWeightByCenter(@PathVariable int id) {
        return ResponseEntity.ok(recyclingCenterService.getTotalWeightByCenter(id));
    }
}
