package com.befree.controllers;

import com.befree.adapter.DozerConverter;
import com.befree.data.model.Event;
import com.befree.data.model.EventStatus;
import com.befree.data.model.vo.EventOwnerVO;
import com.befree.data.model.vo.EventVO;
import com.befree.data.model.vo.UserVO;
import com.befree.services.EventOwnerServices;
import com.befree.services.EventsServices;
import com.befree.services.UserServices;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

@RestController
@RequestMapping("/api/events")
public class EventController {
    @Autowired
    private EventsServices eventsServices;
    @Autowired
    private EventOwnerServices eventOwnerServices;
    @Autowired
    private UserServices userServices;

    @PostMapping(value = "/create/{ownerId}",
            produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<EventVO> createEvent(
            @PathVariable("ownerId") String ownerId, @RequestBody EventVO voEvent) {
            System.out.println(TimeZone.getDefault());
        var ownerVO = eventOwnerServices.findEventOwnerById(ownerId);
//        EventVO voEvent = new EventVO();
        voEvent.setOwnerVO(ownerVO);
        voEvent.setEventName(voEvent.getEventName());
        voEvent.setEventCover(voEvent.getEventCover());
//        voEvent.setEventStatus(voEvent.getEventStatus());
        //caso queiramos converter uma string em date usaremos DateTimeFormatter
//        DateTimeFormatter df =  DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        voEvent.setStartDate(voEvent.getStartDate());
        voEvent.setEndDate(voEvent.getEndDate());
        System.out.println(voEvent.getStartDate().toString());
        System.out.println(voEvent.getEndDate().toString());
        voEvent.setEventLocation(voEvent.getEventLocation());
        voEvent.setEventStatus(EventStatus.INCOMING); //adicionei essa linha
        voEvent.setEventDescription(voEvent.getEventDescription());
        List<EventVO> events = new ArrayList<>();
        events.add(voEvent);
        ownerVO.setEvents(events);
        return ResponseEntity.ok().body(eventsServices.createEvent(voEvent));
    }

    @GetMapping(value = "/yourEvents/{eventOwnerId}",
            produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<List<EventVO>> getYourEvents(@PathVariable("eventOwnerId") String eventOwnerId) {
        List<EventVO> yourEvents = eventsServices.findYourEvents(eventOwnerId);
        return ResponseEntity.ok().body(yourEvents);
    }

    @GetMapping(value = "/all",
            produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<List<EventVO>> getAllEvents() {
        List<EventVO> allEvents = eventsServices.findAllEvents();
        return ResponseEntity.ok().body(allEvents);
    }

    @GetMapping(value = "/{eventStatus}",
            produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<List<EventVO>> getEventsByStatus(@PathVariable("eventStatus") EventStatus eventStatus) {
        List<EventVO> eventsByStatus = eventsServices.findEventsByStatus(eventStatus);
        return ResponseEntity.ok().body(eventsByStatus);
    }

    @GetMapping(value = "/goingEvents",
            produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<List<EventVO>> getGoingEvents(@RequestBody UserVO userVO) {
        List<EventVO> eventsYouGoing = eventsServices.findEventsGoing(userVO.getId());
        return ResponseEntity.ok().body(eventsYouGoing);
    }

    @GetMapping(value = "/goingEvents/{userId}",
            produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<List<EventVO>> goingEvents(@PathVariable("userId") String userId) {
        List<EventVO> eventsGoing = eventsServices.findEventsGoing(userId);
        return ResponseEntity.ok().body(eventsGoing);
    }


    @PostMapping(value = "/go/{eventId}",
            produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<EventVO> goToEvent(
            @PathVariable("eventId") String eventId,
            @RequestBody ObjectNode objectNode) {
        UserVO user = userServices.getUserById(objectNode.get("id_user").asText());
        EventVO event = eventsServices.findEventById(eventId);
        List<EventVO> events = new ArrayList<>();
        events.add(event);
        user.setEvents(events);
        List<UserVO> users = new ArrayList<>();
        users.add(user);
        event.setUsers(users);
        return ResponseEntity.ok().body(eventsServices.gotoEventVO(event));
    }

    @PutMapping(value = "/updateEvent/{eventId}",
            produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<EventVO> updateEventData(
            @PathVariable("eventId") String eventId,
            @RequestBody EventVO eventData) {
        EventVO event = eventsServices.updateEvent(eventData);
        return ResponseEntity.ok().body(event);
    }

    @GetMapping(value = "/find/{eventName}",
            produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<List<EventVO>> findEventsByName(@PathVariable("eventName") String eventName) {
        List<EventVO> events = eventsServices.findEventsByEventName(eventName);
        return ResponseEntity.ok().body(events);
    }

}
