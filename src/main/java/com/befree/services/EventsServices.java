package com.befree.services;

import com.befree.repository.EventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventsServices {

    @Autowired
    private EventsRepository eventsRepository;




}
