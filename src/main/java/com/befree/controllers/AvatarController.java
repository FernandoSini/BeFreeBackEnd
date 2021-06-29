package com.befree.controllers;

import com.befree.data.model.vo.AvatarVO;
import com.befree.data.model.vo.ImageVO;
import com.befree.data.model.vo.UserVO;
import com.befree.services.AvatarServices;
import com.befree.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/avatar")
public class AvatarController {

    @Autowired
    private AvatarServices avatarServices;
    @Autowired
    private UserServices userServices;

    @PostMapping(value = "/upload/{yourId}")
    public ResponseEntity<String> uploadAvatar(@RequestParam("file") MultipartFile file, @PathVariable("yourId") String yourId) {
        UserVO userVO = userServices.getUserById(yourId);
        AvatarVO avatar = avatarServices.storeAvatar(file, userVO);

        return ResponseEntity.ok().body("Avatar " + avatar.getName() + " uploaded Successfully");
    }

    @PutMapping(value = "/update/{yourId}")
    public ResponseEntity<String> updateAvatar(@RequestParam("file") MultipartFile file, @PathVariable("yourId") String yourId) {
        UserVO userVO = userServices.getUserById(yourId);
        AvatarVO avatar = avatarServices.updateAvatar(file, userVO);

        return ResponseEntity.ok().body("Avatar " + avatar.getName() + " updated Successfully!");
    }

    @GetMapping(value = "/data/{imageName}",
            produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<byte[]> getFileByName(@PathVariable String imageName) {
        AvatarVO avatarVO = avatarServices.getImageByName(imageName);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(avatarVO.getContentType()));
        return new ResponseEntity(avatarVO.getData(), headers, HttpStatus.OK);

    }
}
