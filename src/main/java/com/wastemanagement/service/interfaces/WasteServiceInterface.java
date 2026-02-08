package com.wastemanagement.service.interfaces;

import com.wastemanagement.model.WasteItem;
import java.util.List;
import java.util.Optional;

public interface WasteServiceInterface {

    // CRUD операции
    WasteItem createWasteItem(WasteItem wasteItem);
    Optional<WasteItem> getWasteItemById(int id);
    List<WasteItem> getAllWasteItems();
    WasteItem updateWasteItem(WasteItem wasteItem);
    void deleteWasteItem(int id);

    // Специальные операции
    List<WasteItem> getWasteItemsByType(String type);
    List<WasteItem> getRecyclableWasteItems();
    List<WasteItem> getWasteItemsByCenter(int centerId);
    List<WasteItem> getWasteItemsSortedByWeight();
    List<WasteItem> getWasteItemsSortedByRecyclingCost();

    // Фабричный метод
    WasteItem createWasteItemUsingFactory(String type, int id, String name,
                                          double weight, boolean recyclable, Integer centerId);
}