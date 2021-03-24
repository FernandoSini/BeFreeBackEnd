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
        imageVO.setUser(DozerConverter.parseObject(entity.getUser(), UserVO.class));
        imageVO.setImageLink(entity.getImageLink());
        imageVO.setId(entity.getId());
        return imageVO;
    }

    public Image convertVoToEntity(ImageVO vo){
        Image image  = new Image();
        image.setId(vo.getId());
        image.setImageLink(vo.getImageLink());
        image.setUser(DozerConverter.parseObject(vo.getUser(), User.class));
        return image;
    }
}
