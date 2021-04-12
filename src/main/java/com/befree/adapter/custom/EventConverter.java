package com.befree.adapter.custom;

import com.befree.adapter.DozerConverter;
import com.befree.data.model.Event;
import com.befree.data.model.EventOwner;
import com.befree.data.model.User;
import com.befree.data.model.vo.EventOwnerVO;
import com.befree.data.model.vo.EventVO;
import com.befree.data.model.vo.UserVO;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;

@Service
public class EventConverter {

    public Event convertVOtoEntity(EventVO vo){
        Event event = new Event();
        event.setId(vo.getEventId());
        event.setEventName(vo.getEventName());
        event.setUsers(DozerConverter.parseListObjects(vo.getUsers(), User.class));
        event.setOwner(DozerConverter.parseObject(vo.getOwnerVO(), EventOwner.class));
        event.setEventCover(vo.getEventCover());
        return event;
    }
    public EventVO convertEntityToVo(Event entity){
        EventVO eventVo = new EventVO();
       eventVo.setEventId(entity.getId());
        eventVo.setEventName(entity.getEventName());
        eventVo.setUsers(DozerConverter.parseListObjects(entity.getUsers(), UserVO.class));
        eventVo.setOwnerVO(DozerConverter.parseObject(entity.getOwner(), EventOwnerVO.class));
        eventVo.setEventCover(entity.getEventCover());
        return eventVo;
    }
}
