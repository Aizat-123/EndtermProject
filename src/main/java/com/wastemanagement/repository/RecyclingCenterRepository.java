package com.wastemanagement.repository;

import com.wastemanagement.model.RecyclingCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecyclingCenterRepository extends JpaRepository<RecyclingCenter, Integer> {
}