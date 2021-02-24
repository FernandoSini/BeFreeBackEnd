package com.befree.services;


import com.befree.adapter.DozerConverter;
import com.befree.adapter.custom.UserConverter;
import com.befree.data.model.User;
import com.befree.data.model.vo.UserVO;
import com.befree.exceptions.CreateUserException;
import com.befree.exceptions.ResourceNotFoundException;
import com.befree.exceptions.UserNotFoundException;
import com.befree.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServices {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserConverter userConverter;

    //criar o user No banco
    //o problema é que o converter n esta convertendo a graduacao do vo
    public UserVO criandoUser(UserVO userVO) {
        var user = userConverter.convertUserVoToUser(userVO);
        var vo = userRepository.findOneUserByUserName(user.getUserName());
        Optional<UserVO> userExists = Optional.ofNullable(convertToUserVo(vo));
        if (!userExists.isPresent()|| userExists.isEmpty() || userExists ==null) {
            System.out.println(user.getUserGraduations());
            var entity = DozerConverter.parseObject(userRepository.saveAndFlush(user),UserVO.class);
            System.out.println(entity.getUserName());
            return entity;
        } else {
            throw new CreateUserException("User already exist");
        }
    }

    //buscando um usuário pelo id
    public User getUserById(String id) {
        var entity = userRepository.findUserById(id).orElseThrow(() -> new UserNotFoundException("User Not Found"));
        return entity;
    }

    public List<UserVO> getAllUsers() {
        var entity = DozerConverter.parseListObjects(userRepository.findAll(),UserVO.class);
        return entity;
    }

    public void deleteUser(String id) {
        User entity = userRepository.findUserById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userRepository.delete(entity);

    }
    public UserVO convertToUserVo(User entity){
        if(entity == null) return null;
        return DozerConverter.parseObject(entity,UserVO.class);
    }
    public User convertVoToUser(UserVO entity){
        if(entity == null) return null;
        return DozerConverter.parseObject(entity,User.class);
    }


}
