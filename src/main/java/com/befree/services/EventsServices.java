package com.befree.services;

import com.befree.adapter.DozerConverter;
import com.befree.adapter.custom.EventConverter;
import com.befree.data.model.Event;
import com.befree.data.model.EventStatus;
import com.befree.data.model.vo.EventVO;
import com.befree.exceptions.ResourceNotFoundException;
import com.befree.repository.EventsRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

@Service
public class EventsServices {

    @Autowired
    private EventsRepository eventsRepository;
    @Autowired
    private EventConverter converter;

    public EventVO createEvent(EventVO eventVO) {
        var entity = converter.convertVOtoEntity(eventVO);
        var vo = converter.convertEntityToVo(eventsRepository.save(entity));
        return vo;
    }


    public List<EventVO> findEventsGoing(String userId) {
        var entity = eventsRepository.findEventsGoingByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));
//        var voList = DozerConverter.parseListObjects(entity, EventVO.class);
        var voList = entity.stream().map(event -> converter.convertEntityToVo(event))
                .collect(Collectors.toList());
        return voList;
    }


    public List<EventVO> findYourEvents(String eventOwnerId) {
        var entity = eventsRepository.findEventsByEventOwnerId(eventOwnerId)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found events with this eventOwnerId"));
//        var voList = DozerConverter.parseListObjects(entity, EventVO.class);
        var voList = entity.stream().map(event -> converter.convertEntityToVo(event))
                .collect(Collectors.toList());
        return voList;
    }

    public List<EventVO> findAllEvents() {
        var entity = eventsRepository.findAll();
//        var voList = DozerConverter.parseListObjects(entity, EventVO.class);
        var voList = entity.stream().map(event -> converter.convertEntityToVo(event)).collect(Collectors.toList());
        voList.stream().forEach(eventVO -> changeEventStatus(eventVO.getEventId()));
        return voList;
    }

    public List<EventVO> findEventsByStatus(EventStatus eventStatus) {
        var entity = eventsRepository.findEventByStatus(eventStatus)
                .orElseThrow(() -> new ResourceNotFoundException("Not found event with this status"));
//        var voList = DozerConverter.parseListObjects(entity, EventVO.class);
        var voList = entity.stream().map(event -> converter.convertEntityToVo(event))
                .collect(Collectors.toList());
        voList.stream().forEach(eventVO -> changeEventStatus(eventVO.getEventId()));
        return voList;
    }

    public void changeEventStatus(String eventId) {
        var event = eventsRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event doesn't exists"));
        //lembrar que se atualizar os dados e os dados forem modificados,
        // para mantê-los devemos usaros gets e setters igual fiz no update user
//
//        if (LocalDateTime.now().isBefore(event.getStartDate().atZone(ZoneId.systemDefault()).toLocalDateTime()) ==true) {
//            System.out.println("filho da puta");
//            event.setEventStatus(EventStatus.INCOMING);
//            eventsRepository.updateEventStatus(
//                    event.getEventStatus(), event.getId());
//
//        } else if (LocalDateTime.now().isAfter(event.getStartDate().atZone(ZoneId.systemDefault()).toLocalDateTime())
//                && LocalDateTime.now().isBefore(event.getEndDate().atZone(ZoneId.systemDefault()).toLocalDateTime())) {
//            System.out.println("pau no seu cu");
//            event.setEventStatus(EventStatus.HAPPENING);
//            eventsRepository.updateEventStatus(event.getEventStatus(), event.getId());
//
//        } else {
//            System.out.println("foda-se");
//            event.setEventStatus(EventStatus.ENDED);
//            eventsRepository.updateEventStatus(event.getEventStatus(), event.getId());
//
//        }
        if(Instant.now().atZone(ZoneId.of("America/Sao_Paulo")).toLocalDateTime().isEqual(event.getEndDate())||
                Instant.now().atZone(ZoneId.of("America/Sao_Paulo")).toLocalDateTime().isAfter(event.getEndDate())){
            event.setEventStatus(EventStatus.ENDED);
            eventsRepository.updateEventStatus(event.getEventStatus(),event.getId());
        }else if(Instant.now().atZone(ZoneId.of("America/Sao_Paulo")).toLocalDateTime().isAfter(event.getStartDate())
                && Instant.now().atZone(ZoneId.of("America/Sao_Paulo")).toLocalDateTime().isBefore(event.getEndDate())||
            Instant.now().atZone(ZoneId.of("America/Sao_Paulo")).toLocalDateTime().isEqual(event.getStartDate())) {
            event.setEventStatus(EventStatus.HAPPENING);
            eventsRepository.updateEventStatus(event.getEventStatus(),event.getId());
        }else{
            event.setEventStatus(EventStatus.INCOMING);
            eventsRepository.updateEventStatus(event.getEventStatus(), event.getId());
        }
    }

    public List<EventVO> findEventsByEventName(String eventName) {
        var list = eventsRepository.findEventsByEventName(eventName)
                .orElseThrow(() -> new ResourceNotFoundException("Event with this name: " + eventName + " not found!"));
        var listVo = list.stream().map(event -> converter.convertEntityToVo(event))
                .collect(Collectors.toList());
        return listVo;

    }

    public EventVO findEventById(String eventId) {
        var event = eventsRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event with this id not found"));
//        var vo = DozerConverter.parseObject(event, EventVO.class);
        var vo = converter.convertEntityToVo(event);
        return vo;
    }

    public EventVO gotoEventVO(EventVO eventVO) {
//        var event = DozerConverter.parseObject(eventVO, Event.class);
//        var vo = DozerConverter.parseObject(eventsRepository.saveAndFlush(event), EventVO.class);
        var event = converter.convertVOtoEntity(eventVO);
        var vo = converter.convertEntityToVo(eventsRepository.saveAndFlush(event));
        return vo;
    }

    public EventVO updateEvent(EventVO eventVO) {
        var entity = eventsRepository.findById(eventVO.getEventId())
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));

        if (eventVO.getEventName() == null || eventVO.getEventName().isEmpty()) {
            entity.setEventName(entity.getEventName());
        } else {
            entity.setEventName(eventVO.getEventName());
        }
        if (eventVO.getEventLocation() == null || eventVO.getEventLocation().isEmpty()) {
            entity.setEventLocation(entity.getEventLocation());
        } else {
            entity.setEventLocation(eventVO.getEventLocation());
        }
        if (eventVO.getEventCover() == null || eventVO.getEventCover().isEmpty()) {
            entity.setEventCover(entity.getEventCover());
        } else {
            entity.setEventCover(eventVO.getEventCover());
        }
        if (eventVO.getEventDescription() == null || eventVO.getEventDescription().isEmpty()) {
            entity.setEventDescription(entity.getEventDescription());
        } else {
            entity.setEventDescription(eventVO.getEventDescription());
        }
        if (eventVO.getStartDate().toString() == null || eventVO.getStartDate().toString().isEmpty()) {
            entity.setStartDate(entity.getStartDate());
        } else {
            entity.setStartDate(eventVO.getStartDate());
        }
        if (eventVO.getEndDate().toString() == null || eventVO.getEndDate().toString().isEmpty()) {
            entity.setEndDate(entity.getEndDate());

        } else {
            entity.setEndDate(eventVO.getEndDate());
        }

        var vo = converter.convertEntityToVo(eventsRepository.save(entity));
        changeEventStatus(vo.getEventId());
        return vo;
    }
}
