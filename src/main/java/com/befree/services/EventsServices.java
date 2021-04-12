package com.befree.services;

import com.befree.adapter.DozerConverter;
import com.befree.adapter.custom.EventConverter;
import com.befree.data.model.Event;
import com.befree.data.model.vo.EventVO;
import com.befree.repository.EventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
