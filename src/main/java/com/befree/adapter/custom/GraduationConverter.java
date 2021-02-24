package com.befree.adapter.custom;

import com.befree.adapter.DozerConverter;
import com.befree.data.model.Graduation;
import com.befree.data.model.User;
import com.befree.data.model.vo.GraduationVO;
import com.befree.data.model.vo.UserVO;
import org.springframework.stereotype.Service;

@Service
public class GraduationConverter {

    public GraduationVO convertEntityToVO(Graduation graduation) {
        GraduationVO graduationVO = new GraduationVO();
        graduationVO.setId(graduation.getId());
        graduationVO.setCourseName(graduation.getCourseName());
        graduationVO.setUsers(DozerConverter.parseListObjects(graduation.getUsers(),UserVO.class));

        return graduationVO;
    }

    public Graduation convertVoToEntity(GraduationVO graduationVO){
        Graduation graduation = new Graduation();
        graduation.setId(graduationVO.getId());
        graduation.setCourseName(graduationVO.getCourseName());
        graduation.setUsers(DozerConverter.parseListObjects(graduationVO.getUsers(),User.class));
        return graduation;
    }

}
