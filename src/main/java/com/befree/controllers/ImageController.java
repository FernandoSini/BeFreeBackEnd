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

@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    private ImageServices imageServices;
    @Autowired
    private UserServices userServices;


    @PostMapping(value = "/save",
            produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<ImageVO> saveImage(@RequestBody ImageVO imageVO) {



        return ResponseEntity.ok().body(imageServices.saveImage(imageVO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") String id) {
        imageServices.deleteImage(id);
        return ResponseEntity.ok().body("Image deleted successfully!");
    }
}
