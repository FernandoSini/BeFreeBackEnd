package com.befree.services;

import com.befree.data.model.Graduation;
import com.befree.repository.GraduationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GraduationServices {

    @Autowired
    GraduationRepository graduationRepository;


    //buscar todas as graduações no banco
    public List<Graduation> getAllGraduations(){
        return graduationRepository.findAll();
    }

    //buscando a graduação pelo id
    public Graduation getGraduationById(int id){
        var entity =graduationRepository.findById(id);
        return  entity;
    }

    public Graduation getGraduationByName(String graduationName) {
        var entity = graduationRepository.findByName(graduationName);
        return entity;
    }
}
