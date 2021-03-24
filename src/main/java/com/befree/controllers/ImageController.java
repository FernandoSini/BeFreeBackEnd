package com.befree.controllers;

import com.befree.data.model.vo.ImageVO;
import com.befree.services.ImageServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    private ImageServices imageServices;

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") String id){
        imageServices.deleteImage(id);
        return ResponseEntity.ok().body("Image deleted successfully!");
    }
}
