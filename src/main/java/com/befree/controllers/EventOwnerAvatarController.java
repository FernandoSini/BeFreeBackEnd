package com.befree.controllers;

import com.befree.data.model.vo.AvatarVO;
import com.befree.data.model.vo.EventOwnerAvatarVO;
import com.befree.data.model.vo.EventOwnerVO;
import com.befree.data.model.vo.UserVO;
import com.befree.services.AvatarServices;
import com.befree.services.EventOwnerAvatarServices;
import com.befree.services.EventOwnerServices;
import com.befree.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/avatarowner")
public class EventOwnerAvatarController {

    @Autowired
    private EventOwnerAvatarServices eventOwnerAvatarServices;
    @Autowired
    private EventOwnerServices eventOwnerServices;

    @PostMapping(value = "/upload/{yourId}")
    public ResponseEntity<String> uploadAvatar(@RequestParam("file") MultipartFile file, @PathVariable("yourId") String yourId) {
        EventOwnerVO eventOwnerVO = eventOwnerServices.findEventOwnerById(yourId);
        EventOwnerAvatarVO avatar = eventOwnerAvatarServices.storeAvatar(file, eventOwnerVO);

        return ResponseEntity.ok().body("Event Owner Avatar " + avatar.getName() + " uploaded Successfully");
    }

    @PutMapping(value = "/update/{yourId}")
    public ResponseEntity<String> updateAvatar(@RequestParam("file") MultipartFile file, @PathVariable("yourId") String yourId) {
        EventOwnerVO eventOwnerVO = eventOwnerServices.findEventOwnerById(yourId);
        EventOwnerAvatarVO avatar = eventOwnerAvatarServices.updateAvatar(file, eventOwnerVO);

        return ResponseEntity.ok().body("Avatar " + avatar.getName() + " updated Successfully!");
    }

    @GetMapping(value = "/data/{imageName}",
            produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<byte[]> getFileByName(@PathVariable String imageName) {
        EventOwnerAvatarVO avatarVO = eventOwnerAvatarServices.getImageByName(imageName);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(avatarVO.getContentType()));
        return new ResponseEntity(avatarVO.getData(), headers, HttpStatus.OK);

    }
}
