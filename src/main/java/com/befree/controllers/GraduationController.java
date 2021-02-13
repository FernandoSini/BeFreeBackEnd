package com.befree.controllers;

import com.befree.data.model.Graduation;
import com.befree.services.GraduationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/graduations")
public class GraduationController {

    @Autowired
    private GraduationServices graduationServices;


    @GetMapping("/all")
    public ResponseEntity<List<Graduation>> getAllGraduations(){
        List<Graduation> graduations = graduationServices.getAllGraduations();
        return ResponseEntity.ok().body(graduations);

    }

}
