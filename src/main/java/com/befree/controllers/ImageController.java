package com.befree.controllers;

import com.befree.adapter.DozerConverter;
import com.befree.data.model.Image;
import com.befree.data.model.vo.ImageVO;
import com.befree.data.model.vo.UserVO;
import com.befree.services.ImageServices;
import com.befree.services.UserServices;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    private ImageServices imageServices;
    @Autowired
    private UserServices userServices;


    @PostMapping(value = "/{yourId}/save",
            produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
        public ResponseEntity<ImageVO> saveImage(@PathVariable("yourId") String yourId,@RequestBody ImageVO imageVO) {
                UserVO userVO = userServices.getUserById(yourId);
                imageVO.setUser(userVO);

            return ResponseEntity.ok().body(imageServices.saveImage(imageVO));
        }
//    @PostMapping(value = "/{yourId}/",
//            produces = {"application/json", "application/xml", "application/x-yaml"},
//            consumes = {"application/json", "application/xml", "application/x-yaml"})
//    public ResponseEntity<ImageVO> saveImage(@PathVariable("yourId")String yourId,@RequestBody ImageVO imageVO) {
//        UserVO userVO = userServices.getUserById(yourId);
//
//        ImageVO imageToUpload = new ImageVO();
//        imageToUpload.setUserVO(userVO);
//        imageToUpload.setImageLink(imageVO.getImageLink());
//
//        return ResponseEntity.ok().body(imageServices.saveImage(imageToUpload));
//    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") String id) {
        imageServices.deleteImage(id);
        return ResponseEntity.ok().body("Image deleted successfully!");
    }
}
