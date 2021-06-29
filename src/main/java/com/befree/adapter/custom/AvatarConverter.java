package com.befree.adapter.custom;

import com.befree.adapter.DozerConverter;
import com.befree.data.model.Avatar;
import com.befree.data.model.User;
import com.befree.data.model.vo.AvatarVO;
import com.befree.data.model.vo.UserVO;
import org.springframework.stereotype.Service;

@Service
public class AvatarConverter {

    public AvatarVO convertEntitytoVO(Avatar entity){
        AvatarVO avatarVO =new AvatarVO();
        avatarVO.setUserVO(DozerConverter.parseObject(entity.getUser(), UserVO.class));
        avatarVO.setName(entity.getName());
        avatarVO.setData(entity.getData());
        avatarVO.setSize(entity.getSize());
        avatarVO.setContentType(entity.getContentType());
        avatarVO.setId(entity.getId());
        avatarVO.setUrl(entity.getUrl());
        return avatarVO;
    }

    public Avatar convertVoToEntity(AvatarVO vo){
        Avatar avatar  = new Avatar();
        avatar.setId(vo.getId());
        avatar.setName(vo.getName());
        avatar.setContentType(vo.getContentType());
        avatar.setData(vo.getData());
        avatar.setSize(vo.getSize());
        avatar.setUser(DozerConverter.parseObject(vo.getUserVO(), User.class));
        avatar.setUrl(vo.getUrl());
        return avatar;
    }
}
