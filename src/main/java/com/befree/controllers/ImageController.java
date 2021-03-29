package com.befree.controllers;

import com.befree.data.model.vo.ImageVO;
import com.befree.data.model.vo.UserVO;
import com.befree.services.ImageServices;
import com.befree.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    @PostMapping(value = "/{id}/saveImages",
            produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public List<ResponseEntity<ImageVO>> saveMultiImage(@PathVariable("id")String id, @RequestBody List<ImageVO> images) {
        UserVO userVO = userServices.getUserById(id);

// lembrar que para enviar um array de items por json deve comecar com colchetes só

        return images.stream().map(file -> saveImage(id,file)).collect(Collectors.toList());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") String id) {
        imageServices.deleteImage(id);
        return ResponseEntity.ok().body("Image deleted successfully!");
    }
}
