package com.wastemanagement.repository;

import com.wastemanagement.model.WasteItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WasteItemRepository extends JpaRepository<WasteItem, Integer> {

    List<WasteItem> findByRecyclableTrue();

    @Query("SELECT w FROM WasteItem w WHERE w.wasteType = :type")
    List<WasteItem> findByWasteType(@Param("type") String wasteType);

    List<WasteItem> findByCenterId(Integer centerId);

    @Query("SELECT SUM(w.weight) FROM WasteItem w WHERE w.centerId = :centerId")
    Double getTotalWeightByCenter(@Param("centerId") int centerId);

    List<WasteItem> findAllByOrderByWeightAsc();

    @Query("SELECT w FROM WasteItem w ORDER BY " +
            "CASE WHEN TYPE(w) = PlasticWaste THEN w.weight * 40 " +
            "     WHEN TYPE(w) = ElectronicWaste THEN w.weight * 600 " +
            "     WHEN TYPE(w) = PaperWaste THEN w.weight * 50 " +
            "     ELSE 0 END")
    List<WasteItem> findAllOrderByRecyclingCost();
}
