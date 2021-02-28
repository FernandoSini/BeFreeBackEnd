package com.befree.services;

import com.befree.adapter.DozerConverter;
import com.befree.data.model.Graduation;
import com.befree.data.model.vo.GraduationVO;
import com.befree.exceptions.ResourceNotFoundException;
import com.befree.repository.GraduationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GraduationServices {

    @Autowired
    GraduationRepository graduationRepository;


    //buscar todas as graduações no banco
    public List<GraduationVO> getAllGraduations() {
        var graduations = graduationRepository.findAll();
        return DozerConverter.parseListObjects(graduations, GraduationVO.class);
    }

    //buscando a graduação pelo id
    public GraduationVO getGraduationById(int id) {
        var entity = graduationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not records found for this ID"));
        return convertEntityToGraduationVo(entity);
    }

    public Graduation getGraduationByName(String graduationName) {
        var entity = graduationRepository.findByName(graduationName);
        return entity;
    }

    public GraduationVO convertEntityToGraduationVo(Graduation entity) {
        if (entity == null) return null;
        return DozerConverter.parseObject(entity, GraduationVO.class);
    }

    public List<GraduationVO> getGraduationsByuserName(String userName) {
        var entity = graduationRepository.findGraduationByUserName(userName);

        return DozerConverter.parseListObjects(entity, GraduationVO.class);
    }

}
