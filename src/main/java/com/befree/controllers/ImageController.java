package com.befree.controllers;

import com.befree.data.model.vo.ImageVO;
import com.befree.data.model.vo.UserVO;
import com.befree.services.ImageServices;
import com.befree.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.print.attribute.standard.Media;
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
    public ResponseEntity<ImageVO> saveImage(@PathVariable("yourId") String yourId, @RequestBody ImageVO imageVO) {
        UserVO userVO = userServices.getUserById(yourId);
        imageVO.setUserVO(userVO);

        return ResponseEntity.ok().body(imageServices.saveImage(imageVO));
    }

    @PostMapping(value = "/{id}/saveImages",
            produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public List<ResponseEntity<ImageVO>> saveMultiImage(@PathVariable("id") String id, @RequestBody List<ImageVO> images) {
        UserVO userVO = userServices.getUserById(id);

// lembrar que para enviar um array de items por json deve comecar com colchetes só

        return images.stream().map(file -> saveImage(id, file)).collect(Collectors.toList());
    }

    @PostMapping(value = "/uploadimage/{yourId}")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file, @PathVariable("yourId") String yourId) {
        UserVO userVO = userServices.getUserById(yourId);
        ImageVO imageVO = imageServices.storeFile(file, userVO);

        return ResponseEntity.ok().body("Image " + imageVO.getName() + " uploaded Successfully");
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") String id) {
        imageServices.deleteImage(id);
        return ResponseEntity.ok().body("Image deleted successfully!");
    }

    @GetMapping(value = "/all",
            produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<List<ImageVO>> getImages() {
        List<ImageVO> imageList = imageServices.getImages();

        return ResponseEntity.ok(imageList);
    }

    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        ImageVO imageVO = imageServices.getImageById(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + imageVO.getName() + "\"")
                .contentType(MediaType.parseMediaType(imageVO.getContentType()))
                .body(imageVO.getData());
    }

    @GetMapping(value = "/data/{imageName}",
            produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<byte[]> getFileByName(@PathVariable String imageName) {
        ImageVO imageVO = imageServices.getImageByName(imageName);


//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + imageVO.getName() + "\"")
//                .contentType(MediaType.parseMediaType(imageVO.getContentType()))
//                .body(imageVO.getData());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(imageVO.getContentType()));
//        return new HttpEntity<byte[]>(imageVO.getData(),headers);
        return new ResponseEntity(imageVO.getData(),headers, HttpStatus.OK);
//        return ResponseEntity.ok(imageVO.getData());
    }
}

