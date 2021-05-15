package com.befree.adapter.custom;

import com.befree.adapter.DozerConverter;
import com.befree.data.model.Image;
import com.befree.data.model.User;
import com.befree.data.model.vo.ImageVO;
import com.befree.data.model.vo.UserVO;
import org.springframework.stereotype.Service;

@Service
public class ImageConverter {

    public ImageVO convertEntitytoVO(Image entity){
        ImageVO imageVO =new ImageVO();
        imageVO.setUserVO(DozerConverter.parseObject(entity.getUser(), UserVO.class));
        imageVO.setName(entity.getName());
        imageVO.setData(entity.getData());
        imageVO.setSize(entity.getSize());
        imageVO.setContentType(entity.getContentType());
        imageVO.setId(entity.getId());
        imageVO.setUrl(entity.getUrl());
        return imageVO;
    }

    public Image convertVoToEntity(ImageVO vo){
        Image image  = new Image();
        image.setId(vo.getId());
        image.setName(vo.getName());
        image.setContentType(vo.getContentType());
        image.setData(vo.getData());
        image.setSize(vo.getSize());
        image.setUser(DozerConverter.parseObject(vo.getUserVO(), User.class));
        image.setUrl(vo.getUrl());
        return image;
    }
}
