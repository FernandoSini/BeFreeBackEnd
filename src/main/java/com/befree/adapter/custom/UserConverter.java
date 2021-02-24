package com.befree.adapter.custom;

import com.befree.adapter.DozerConverter;
import com.befree.data.model.Graduation;
import com.befree.data.model.User;
import com.befree.data.model.vo.GraduationVO;
import com.befree.data.model.vo.UserVO;
import org.springframework.stereotype.Service;

@Service
public class UserConverter {

    private GraduationConverter graduationConverter;

    public UserVO convertUserToVO(User user) {
        UserVO userVO = new UserVO();
        userVO.setUserName(user.getUserName());
        userVO.setAge(user.getAge());
        userVO.setFirstName(user.getFirstName());
        userVO.setGender(user.getGender());
        userVO.setId(user.getId());
        userVO.setLikesSended(user.getLikesSended());
        userVO.setLikeReceived(user.getLikeReceived());
        userVO.setUsertype(user.getUsertype());
        userVO.setGraduations(DozerConverter.parseListObjects(user.getUserGraduations(), GraduationVO.class));
        return userVO;
    }

    public User convertUserVoToUser(UserVO voUser) {
        User userObject = new User();
        userObject.setId(voUser.getId());
        userObject.setUserName(voUser.getUserName());
        userObject.setFirstName(voUser.getFirstName());
        userObject.setLastName(voUser.getLastName());
        userObject.setGender(voUser.getGender());
        userObject.setUserGraduations(DozerConverter.parseListObjects(voUser.getGraduations(), Graduation.class));
        userObject.setUsertype(voUser.getUsertype());
        userObject.setAge(voUser.getAge());
//        userObject.setLikesSended(voUser.getLikesSended());
//        userObject.setLikeReceived(voUser.getLikeReceived());



        return userObject;
    }

}
