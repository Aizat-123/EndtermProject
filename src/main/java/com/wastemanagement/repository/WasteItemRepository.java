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

    @Query(value = "SELECT * FROM waste_items WHERE LOWER(waste_type) = LOWER(:type)",
            nativeQuery = true)
    List<WasteItem> findByWasteTypeNative(@Param("type") String type);

    List<WasteItem> findByCenterId(Integer centerId);

    @Query("SELECT COALESCE(SUM(w.weight), 0) FROM WasteItem w WHERE w.centerId = :centerId")
    Double getTotalWeightByCenter(@Param("centerId") Integer centerId);

    List<WasteItem> findAllByOrderByWeightAsc();

    @Query("SELECT w FROM WasteItem w")
    List<WasteItem> findAllOrderByRecyclingCost();
}