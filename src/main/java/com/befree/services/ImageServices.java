package com.befree.services;

import com.befree.adapter.DozerConverter;
import com.befree.adapter.custom.ImageConverter;
import com.befree.data.model.Image;
import com.befree.data.model.vo.ImageVO;
import com.befree.data.model.vo.UserVO;
import com.befree.exceptions.ImageStoreException;
import com.befree.exceptions.ResourceNotFoundException;
import com.befree.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ImageServices {

    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ImageConverter converter;

    public ImageVO saveImage(ImageVO imageVO) {
        System.out.println(imageVO.getUserVO());
        var entity = imageRepository.save(converter.convertVoToEntity(imageVO));
        var vo = DozerConverter.parseObject(entity, ImageVO.class);
        return vo;
    }

    public ImageVO getImageById(String id) {
        var entity = imageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Image Not found"));
        var vo = converter.convertEntitytoVO(entity);
        return vo;
    }

    public void deleteImage(String id) {
        Image entity = imageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Image Not found"));
        imageRepository.delete(entity);

    }


    public ImageVO storeFile(MultipartFile file, UserVO userVO) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if (filename.contains("..")) {
                throw new ImageStoreException("Sorry bad caracters" + filename);
            }

            ImageVO imageVO = new ImageVO();
            imageVO.setName(UUID.randomUUID().toString() +filename);
            imageVO.setContentType(file.getContentType());
            imageVO.setData(file.getBytes());
            imageVO.setSize(file.getSize());
            imageVO.setUserVO(userVO);  String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/api/images/data/")
                    .path(imageVO.getName())
                    .toUriString();
            imageVO.setUrl(url);
            var entity = converter.convertVoToEntity(imageVO);
            var vo = converter.convertEntitytoVO(imageRepository.save(entity));
            return vo;

        } catch (Exception e) {
            throw new ImageStoreException("Could not store Image vo" + filename);
        }

    }

    public List<ImageVO> getImages() {

        List<ImageVO> imagesVo = imageRepository.findAll()
                .stream().
                        map(image -> converter.convertEntitytoVO(image))
                .collect(Collectors.toList());
        return imagesVo;
    }

    public ImageVO getImageByName(String imageName) {
        var image = imageRepository.findByName(imageName)
                .orElseThrow(() -> new ResourceNotFoundException("Image with this name not found: " + imageName));
        ImageVO imageVO = converter.convertEntitytoVO(image);
        return imageVO;
    }
}
