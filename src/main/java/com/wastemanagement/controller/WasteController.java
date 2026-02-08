package com.wastemanagement.controller;

import com.wastemanagement.model.WasteItem;
import com.wastemanagement.dto.WasteItemDTO; // ДОБАВИТЬ ЭТОТ ИМПОРТ!
import com.wastemanagement.service.WasteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/waste-items")
public class WasteController {

    // ... остальной код ...

    @PostMapping("/dto")
    public ResponseEntity<WasteItem> createWasteItemFromDTO(@Valid @RequestBody WasteItemDTO wasteItemDTO) {
        // ... код метода ...
    }

    @GetMapping("/{id}/dto")
    public ResponseEntity<WasteItemDTO> getWasteItemDTOById(@PathVariable int id) {
        // ... код метода ...
    }
}