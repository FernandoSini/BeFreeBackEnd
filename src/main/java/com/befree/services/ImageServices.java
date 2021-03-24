package com.befree.services;

import com.befree.adapter.DozerConverter;
import com.befree.adapter.custom.ImageConverter;
import com.befree.data.model.Image;
import com.befree.data.model.vo.ImageVO;
import com.befree.exceptions.ResourceNotFoundException;
import com.befree.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ImageServices {

    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ImageConverter converter;

    public ImageVO saveImage(ImageVO imageVO) {
        var entity = imageRepository.save(converter.convertVoToEntity(imageVO));
        var vo = DozerConverter.parseObject(entity, ImageVO.class);
        return vo;
    }




    public void deleteImage(String id) {

        Image entity = imageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Image Not found"));
        imageRepository.delete(entity);

    }


}
