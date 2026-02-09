package com.wastemanagement.service.interfaces;

import com.wastemanagement.model.WasteItem;
import java.util.List;
import java.util.Optional;

public interface WasteServiceInterface {

    // CRUD operations
    WasteItem createWasteItem(WasteItem wasteItem);
    Optional<WasteItem> getWasteItemById(int id);
    List<WasteItem> getAllWasteItems();
    WasteItem updateWasteItem(WasteItem wasteItem);
    void deleteWasteItem(int id);

    List<WasteItem> getWasteItemsByType(String type);
    List<WasteItem> getRecyclableWasteItems();
    List<WasteItem> getWasteItemsByCenter(int centerId);
    List<WasteItem> getWasteItemsSortedByWeight();
    List<WasteItem> getWasteItemsSortedByRecyclingCost();

    // Fabric method
    WasteItem createWasteItemUsingFactory(String type, int id, String name,
                                          double weight, boolean recyclable, Integer centerId);
}