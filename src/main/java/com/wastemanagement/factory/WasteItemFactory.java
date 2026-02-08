package com.wastemanagement.factory;

import com.wastemanagement.model.*;
import org.springframework.stereotype.Component;

@Component
public class WasteItemFactory {

    public WasteItem createWasteItem(String type, int id, String name, double weight, boolean recyclable) {
        return switch (type.toLowerCase()) {
            case "plastic" -> new PlasticWaste(id, name, weight, recyclable);
            case "electronic" -> new ElectronicWaste(id, name, weight, recyclable);
            case "paper" -> new PaperWaste(id, name, weight, recyclable);
            default -> throw new IllegalArgumentException("Unknown waste type: " + type);
        };
    }

    public WasteItem createWasteItem(String type, int id, String name, double weight,
                                     boolean recyclable, int centerId) {
        WasteItem item = createWasteItem(type, id, name, weight, recyclable);
        item.setCenterId(centerId);
        return item;
    }
}