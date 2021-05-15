package com.befree.repository;

import com.befree.data.model.Event;
import com.befree.data.model.EventStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventsRepository extends JpaRepository<Event, String> {
    @Query("Select e from Event e join e.users u where u.id =:userId")
    Optional<List<Event>> findEventsGoingByUserId(@Param("userId") String userId);

    @Query("Select e from Event e where e.owner.id =:eventOwnerId")
    Optional<List<Event>> findEventsByEventOwnerId(@Param("eventOwnerId") String eventOwnerId);
    @Query("Select e from Event e where e.eventStatus =:eventStatus")
    Optional<List<Event>> findEventByStatus(@Param("eventStatus")EventStatus eventStatus);

    @Transactional
    @Modifying
    @Query("Update Event e set e.eventStatus =:eventStatus where e.id =:eventId")
    void updateEventStatus(@Param("eventStatus") EventStatus eventStatus, @Param("eventId")String eventId);

    @Transactional
    @Modifying
    @Query(value = "Insert into (user_id,event_id) events_users eu VALUES(:userId, :eventId)", nativeQuery = true)
    Event insertUsersOnEvent(@Param("user") String userId,  @Param("eventId") String eventId);
}
