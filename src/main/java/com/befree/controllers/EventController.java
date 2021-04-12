package com.befree.controllers;

import com.befree.adapter.DozerConverter;
import com.befree.data.model.vo.EventOwnerVO;
import com.befree.data.model.vo.EventVO;
import com.befree.services.EventOwnerServices;
import com.befree.services.EventsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {
    @Autowired
    private EventsServices eventsServices;
    @Autowired
    private EventOwnerServices eventOwnerServices;


    @PostMapping(value = "/create/{ownerName}",
            produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public EventVO createEvent(@PathVariable("ownerName") String ownerName, @RequestBody EventVO eventVO){
        var ownerVO = eventOwnerServices.getEventOwnerByName(ownerName);
        eventVO.setOwnerVO(ownerVO);
        eventVO.setEventCover("flemis");
        System.out.println(eventVO.getOwnerVO());
        List<EventVO> events = new ArrayList<>();
        events.add(eventVO);
        ownerVO.setEvents(events);



        return eventsServices.createEvent(eventVO);
    }



}
