package com.befree.adapter.custom;

import com.befree.adapter.DozerConverter;
import com.befree.data.model.*;
import com.befree.data.model.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserConverter {

    private GraduationConverter graduationConverter;

    public UserVO convertUserToVO(User user) {
        UserVO userVO = new UserVO();
        userVO.setUserName(user.getUsername());
        userVO.setBirthday(user.getBirthDay());
        userVO.setEmail(user.getEmail());
        userVO.setFirstName(user.getFirstName());
        userVO.setLastName(user.getLastName());
        userVO.setGender(user.getGender());
        userVO.setId(user.getId());
        userVO.setUsertype(user.getUsertype());
        userVO.setUserGraduations(DozerConverter.parseListObjects(user.getUserGraduations(), GraduationVO.class));
        userVO.setLikesSended(DozerConverter.parseListObjects(user.getLikesSended(), LikeVO.class));
        userVO.setLikeReceived(DozerConverter.parseListObjects(user.getLikeReceived(), LikeVO.class));
        userVO.setPermissions(DozerConverter.parseListObjects(user.getPermissions(), Permission.class));
        userVO.setAccountNonExpired(user.getAccountNonExpired());
        userVO.setCredentialsNonExpired(user.getCredentialsNonExpired());
        userVO.setAccountNonLocked(user.getAccountNonLocked());
        userVO.setEnabled(user.getEnabled());
        userVO.setMatches(DozerConverter.parseListObjects(user.getMatches(), MatchVO.class));
        userVO.setImages(DozerConverter.parseListObjects(user.getImages(), ImageVO.class));
        userVO.setAvatar(user.getAvatar());
        return userVO;
    }

    public User convertUserVoToUser(UserVO voUser) {
        User userObject = new User();
        userObject.setId(voUser.getId());
        userObject.setUserName(voUser.getUsername());
        userObject.setFirstName(voUser.getFirstName());
        userObject.setLastName(voUser.getLastName());
        userObject.setEmail(voUser.getEmail());
        userObject.setGender(voUser.getGender());
        userObject.setUserGraduations(DozerConverter.parseListObjects(voUser.getUserGraduations(), Graduation.class)/*voUser.getUserGraduations().stream().map(data-> graduationConverter.convertVoToEntity(data)).collect(Collectors.toList())*/);
        userObject.setUsertype(voUser.getUsertype());
        userObject.setBirthday(voUser.getBirthday());
        userObject.setLikesSended(DozerConverter.parseListObjects(voUser.getLikesSended(), Like.class));
        userObject.setLikeReceived(DozerConverter.parseListObjects(voUser.getLikeReceived(), Like.class));
        userObject.setAccountNonExpired(voUser.getAccountNonExpired());
        userObject.setAccountNonLocked(voUser.getAccountNonLocked());
        userObject.setCredentialsNonExpired(voUser.getCredentialsNonExpired());
        userObject.setEnabled(voUser.getEnabled());
        userObject.setMatches(DozerConverter.parseListObjects(voUser.getMatches(), Match.class));
        userObject.setAvatar(voUser.getAvatar());
        userObject.setImages(DozerConverter.parseListObjects(voUser.getImages(),Image.class));
        return userObject;
    }


}
