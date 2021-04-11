package com.befree.repository;

import com.befree.data.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventsRepository extends JpaRepository<Event, String> {
}
