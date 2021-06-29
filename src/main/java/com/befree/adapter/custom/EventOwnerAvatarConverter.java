package com.befree.adapter.custom;

import com.befree.adapter.DozerConverter;
import com.befree.data.model.Avatar;
import com.befree.data.model.EventOwner;
import com.befree.data.model.EventOwnerAvatar;
import com.befree.data.model.User;
import com.befree.data.model.vo.AvatarVO;
import com.befree.data.model.vo.EventOwnerAvatarVO;
import com.befree.data.model.vo.EventOwnerVO;
import com.befree.data.model.vo.UserVO;
import org.springframework.stereotype.Service;

@Service
public class EventOwnerAvatarConverter {

    public EventOwnerAvatarVO convertEntitytoVO(EventOwnerAvatar entity){
        EventOwnerAvatarVO eventOwnerAvatarVO =new EventOwnerAvatarVO();
        eventOwnerAvatarVO.setEventOwnerVO(DozerConverter.parseObject(entity.getEventOwner(), EventOwnerVO.class));
        eventOwnerAvatarVO.setName(entity.getName());
        eventOwnerAvatarVO.setData(entity.getData());
        eventOwnerAvatarVO.setSize(entity.getSize());
        eventOwnerAvatarVO.setContentType(entity.getContentType());
        eventOwnerAvatarVO.setId(entity.getId());
        eventOwnerAvatarVO.setUrl(entity.getUrl());
        return eventOwnerAvatarVO;
    }

    public EventOwnerAvatar convertVoToEntity(EventOwnerAvatarVO vo){
        EventOwnerAvatar avatar  = new EventOwnerAvatar();
        avatar.setId(vo.getId());
        avatar.setName(vo.getName());
        avatar.setContentType(vo.getContentType());
        avatar.setData(vo.getData());
        avatar.setSize(vo.getSize());
        avatar.setEventOwner(DozerConverter.parseObject(vo.getEventOwnerVO(), EventOwner.class));
        avatar.setUrl(vo.getUrl());
        return avatar;
    }
}
