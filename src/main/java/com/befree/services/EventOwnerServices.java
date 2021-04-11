package com.befree.services;

import com.befree.adapter.DozerConverter;
import com.befree.data.model.EventOwner;
import com.befree.data.model.vo.EventOwnerVO;
import com.befree.exceptions.UserNotFoundException;
import com.befree.repository.EventOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class EventOwnerServices {

    @Autowired
    private EventOwnerRepository eventOwnerRepository;


    public EventOwnerVO createOwner(EventOwnerVO eventOwnerVO) {
        var entity = DozerConverter.parseObject(eventOwnerVO, EventOwner.class);
        var voEventOwner = DozerConverter.parseObject(eventOwnerRepository.save(entity), EventOwnerVO.class);
        return voEventOwner;
    }

    public EventOwnerVO getEventOwnerByUserName(String eventOwnerName) {
        var entity = eventOwnerRepository.findByEventOwnerName(eventOwnerName)
                .orElseThrow(() -> new UserNotFoundException("We not found in our database event owner with this name" +eventOwnerName));

        return DozerConverter.parseObject(entity, EventOwnerVO.class);
    }



}
