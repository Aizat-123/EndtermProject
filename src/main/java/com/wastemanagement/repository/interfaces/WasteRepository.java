package com.wastemanagement.repository.interfaces;

import com.wastemanagement.model.WasteItem;
import java.util.List;
import java.util.Optional;

public interface WasteRepository extends CrudRepository<WasteItem, Integer> {
    double getTotalWeightByCenter(int centerId);
    List<WasteItem> findByCriteria(Criteria<WasteItem> criteria); // WasteItem вместо WasteItems
    List<WasteItem> findRecyclableItems();
    List<WasteItem> findByWasteType(String wasteType);
    List<WasteItem> findByCenter(int centerId);
    void createBatch(List<WasteItem> items);
    Optional<WasteItem> findByIdOptional(Integer id);

    @FunctionalInterface
    interface Criteria<T> {
        boolean test(T item);
    }
}