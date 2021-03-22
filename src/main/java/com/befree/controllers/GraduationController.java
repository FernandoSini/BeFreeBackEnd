package com.befree.controllers;

import com.befree.data.model.vo.GraduationVO;
import com.befree.services.GraduationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/graduations")
public class GraduationController {

    @Autowired
    private GraduationServices graduationServices;


    @GetMapping(value = "/all",
            produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<List<GraduationVO>> getAllGraduations() {
        List<GraduationVO> graduations = graduationServices.getAllGraduations();
        return ResponseEntity.ok().body(graduations);

    }

    @GetMapping("/{graduationId}")
    public ResponseEntity<GraduationVO> getGraduationByID(
            @PathVariable("graduationId") int graduationId) {
        GraduationVO graduationVO = graduationServices.getGraduationById(graduationId);
        return ResponseEntity.ok(graduationVO);
    }


    @GetMapping("/userName/{nomeDoUsuario}")
    public ResponseEntity<List<GraduationVO>> getUserGraduations(
            @PathVariable("nomeDoUsuario") String nomeDoUsuario) {
        List<GraduationVO> graduationsVO = graduationServices.getGraduationsByuserName(nomeDoUsuario);
        return ResponseEntity.ok(graduationsVO);
    }

}
