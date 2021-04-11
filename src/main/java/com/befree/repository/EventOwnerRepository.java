package com.befree.repository;

import com.befree.data.model.EventOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EventOwnerRepository extends JpaRepository<EventOwner, String> {
    @Query("Select eo from EventOwner eo where eo.ownerName=:eventOwnerName")
    Optional<EventOwner> findByEventOwnerName(@Param("eventOwnerName") String eventOwnerName);
}
