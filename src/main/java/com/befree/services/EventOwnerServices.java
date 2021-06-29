package com.befree.services;

import com.befree.adapter.DozerConverter;
import com.befree.data.model.EventOwner;
import com.befree.data.model.vo.EventOwnerVO;
import com.befree.exceptions.CreateUserException;
import com.befree.exceptions.ResourceNotFoundException;
import com.befree.exceptions.UserNotFoundException;
import com.befree.repository.EventOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventOwnerServices {

    @Autowired
    private EventOwnerRepository eventOwnerRepository;


    public EventOwnerVO createOwner(EventOwnerVO eventOwnerVO) {
//        var entity = DozerConverter.parseObject(eventOwnerVO, EventOwner.class);
//        var voEventOwner = DozerConverter.parseObject(eventOwnerRepository.save(entity), EventOwnerVO.class);
//        return voEventOwner;
        var entity = DozerConverter.parseObject(eventOwnerVO, EventOwner.class);
        Optional<EventOwner> eventOwnerExists = eventOwnerRepository.findByEventOwnerName(entity.getOwnerName());
        if (!eventOwnerExists.isPresent() || eventOwnerExists.isEmpty() || eventOwnerExists == null) {
            var voEventOwner = DozerConverter.parseObject(eventOwnerRepository.save(entity), EventOwnerVO.class);
            return voEventOwner;
        } else {
            throw new CreateUserException("Event owner already Exists");
        }
    }

    public EventOwnerVO getEventOwnerByUserName(String eventOwnerName) {
        var entity = eventOwnerRepository.findByEventOwnerName(eventOwnerName)
                .orElseThrow(() -> new UserNotFoundException("We not found in our database event owner with this name" + eventOwnerName));

        return DozerConverter.parseObject(entity, EventOwnerVO.class);
    }


    public EventOwnerVO getEventOwnerByName(String eventOwnerName) {
        var entity = eventOwnerRepository.findByEventOwnerName(eventOwnerName)
                .orElseThrow(() -> new UserNotFoundException("EventOwner not found! "));
        return DozerConverter.parseObject(entity, EventOwnerVO.class);
    }


    public List<EventOwnerVO> getAllOwners() {
        var entity = eventOwnerRepository.findAll();
        var voList = DozerConverter.parseListObjects(entity, EventOwnerVO.class);
        return voList;
    }

    public EventOwnerVO findEventOwnerById(String ownerId) {
        var entity = eventOwnerRepository.findById(ownerId)
                .orElseThrow(() -> new ResourceNotFoundException("We didn't not found Event Owner with this Id!"));
        var vo = DozerConverter.parseObject(entity, EventOwnerVO.class);
        return vo;
    }


    public EventOwnerVO updateOwner(EventOwnerVO eventOwnerVO) {
        var entity = eventOwnerRepository.findById(eventOwnerVO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Event owner not found"));
        if (eventOwnerVO.getOwnerName() == null || eventOwnerVO.getOwnerName().isEmpty()) {
            entity.setOwnerName(entity.getOwnerName());
        } else {
            entity.setOwnerName(eventOwnerVO.getOwnerName());
        }
        if (eventOwnerVO.getEmail() == null || eventOwnerVO.getEmail().isEmpty()) {
            entity.setEmail(entity.getEmail());
        } else {
            entity.setEmail(eventOwnerVO.getEmail());
        }
        if (eventOwnerVO.getDocumentNumber() == null) {
            entity.setDocumentNumber(entity.getDocumentNumber());
        } else {
            entity.setDocumentNumber(eventOwnerVO.getDocumentNumber());
        }
        var vo = DozerConverter.parseObject(eventOwnerRepository.save(entity), EventOwnerVO.class);
        return vo;
    }
}
