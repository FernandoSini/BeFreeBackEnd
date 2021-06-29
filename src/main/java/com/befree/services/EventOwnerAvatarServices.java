package com.befree.services;

import com.befree.adapter.custom.EventOwnerAvatarConverter;
import com.befree.data.model.vo.EventOwnerAvatarVO;
import com.befree.data.model.vo.EventOwnerVO;
import com.befree.exceptions.ImageStoreException;
import com.befree.exceptions.ResourceNotFoundException;
import com.befree.repository.EventOwnerAvatarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@Service
public class EventOwnerAvatarServices {

    @Autowired
    private EventOwnerAvatarRepository eventOwnerAvatarRepository;
    @Autowired
    private EventOwnerAvatarConverter converter;

    public EventOwnerAvatarVO storeAvatar(MultipartFile file, EventOwnerVO eventOwnerVO) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if (filename.contains("..")) {
                throw new ImageStoreException("Sorry bad caracters" + filename);
            }

            EventOwnerAvatarVO avatarVO = new EventOwnerAvatarVO();
            avatarVO.setName(UUID.randomUUID().toString() + filename);
            avatarVO.setContentType(file.getContentType());
            avatarVO.setData(file.getBytes());
            avatarVO.setSize(file.getSize());
            avatarVO.setEventOwnerVO(eventOwnerVO);
            String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/api/avatarowner/data/")
                    .path(avatarVO.getName())
                    .toUriString();
            avatarVO.setUrl(url);
            var entity = converter.convertVoToEntity(avatarVO);
            var vo = converter.convertEntitytoVO(eventOwnerAvatarRepository.save(entity));
            return vo;

        } catch (Exception e) {
            throw new ImageStoreException("Could not store EventOwnerAvatar vo" + filename);
        }

    }

    public EventOwnerAvatarVO updateAvatar(MultipartFile file, EventOwnerVO eventOwnerVO) {
        String newFileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if (newFileName.contains("..")) {
                throw new ImageStoreException("Sorry bad caracters" + newFileName);
            }

            EventOwnerAvatarVO avatarVO = converter.convertEntitytoVO(eventOwnerAvatarRepository.findById(eventOwnerVO.getAvatarProfile().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Avatar not found")));
            avatarVO.setName(UUID.randomUUID().toString() + newFileName);
            avatarVO.setContentType(file.getContentType());
            avatarVO.setData(file.getBytes());
            avatarVO.setSize(file.getSize());
            avatarVO.setEventOwnerVO(eventOwnerVO);
            String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/api/avatarowner/data/")
                    .path(avatarVO.getName())
                    .toUriString();
            avatarVO.setUrl(url);
            var entity = converter.convertVoToEntity(avatarVO);
            var vo = converter.convertEntitytoVO(eventOwnerAvatarRepository.save(entity));
            return vo;

        } catch (Exception e) {
            throw new ImageStoreException("Could not store Avatar vo" + newFileName);
        }

    }

    public EventOwnerAvatarVO getImageByName(String avatarName) {
        var avatar = eventOwnerAvatarRepository.findByName(avatarName)
                .orElseThrow(() -> new ResourceNotFoundException("Image with this name not found: " + avatarName));
        EventOwnerAvatarVO avatarVO = converter.convertEntitytoVO(avatar);
        return avatarVO;
    }
}
