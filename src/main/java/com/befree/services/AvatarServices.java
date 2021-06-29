package com.befree.services;

import com.befree.adapter.custom.AvatarConverter;
import com.befree.data.model.vo.AvatarVO;
import com.befree.data.model.vo.ImageVO;
import com.befree.data.model.vo.UserVO;
import com.befree.exceptions.ImageStoreException;
import com.befree.exceptions.ResourceNotFoundException;
import com.befree.repository.AvatarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@Service
public class AvatarServices {

    @Autowired
    private AvatarRepository avatarRepository;
    @Autowired
    private AvatarConverter converter;

    public AvatarVO storeAvatar(MultipartFile file, UserVO userVO) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if (filename.contains("..")) {
                throw new ImageStoreException("Sorry bad caracters" + filename);
            }

            AvatarVO avatarVO = new AvatarVO();
            avatarVO.setName(UUID.randomUUID().toString() + filename);
            avatarVO.setContentType(file.getContentType());
            avatarVO.setData(file.getBytes());
            avatarVO.setSize(file.getSize());
            avatarVO.setUserVO(userVO);
            String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/api/avatar/data/")
                    .path(avatarVO.getName())
                    .toUriString();
            avatarVO.setUrl(url);
            var entity = converter.convertVoToEntity(avatarVO);
            var vo = converter.convertEntitytoVO(avatarRepository.save(entity));
            return vo;

        } catch (Exception e) {
            throw new ImageStoreException("Could not store Avatar vo" + filename);
        }

    }

    public AvatarVO updateAvatar(MultipartFile file, UserVO userVO) {
        String newFileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if (newFileName.contains("..")) {
                throw new ImageStoreException("Sorry bad caracters" + newFileName);
            }

            AvatarVO avatarVO = converter.convertEntitytoVO(avatarRepository.findById(userVO.getAvatarProfile().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Avatar not found")));
            avatarVO.setName(UUID.randomUUID().toString() + newFileName);
            avatarVO.setContentType(file.getContentType());
            avatarVO.setData(file.getBytes());
            avatarVO.setSize(file.getSize());
            avatarVO.setUserVO(userVO);
            String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/api/avatar/data/")
                    .path(avatarVO.getName())
                    .toUriString();
            avatarVO.setUrl(url);
            var entity = converter.convertVoToEntity(avatarVO);
            var vo = converter.convertEntitytoVO(avatarRepository.save(entity));
            return vo;

        } catch (Exception e) {
            throw new ImageStoreException("Could not store Avatar vo" + newFileName);
        }

    }

    public AvatarVO getImageByName(String avatarName) {
        var avatar = avatarRepository.findByName(avatarName)
                .orElseThrow(() -> new ResourceNotFoundException("Image with this name not found: " + avatarName));
        AvatarVO avatarVO = converter.convertEntitytoVO(avatar);
        return avatarVO;
    }
}
