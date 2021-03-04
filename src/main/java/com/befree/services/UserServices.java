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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("userDetailsService")
public class UserServices implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserConverter userConverter;

    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //criar o user No banco
    //o problema é que o converter n esta convertendo a graduacao do vo
    public UserVO criandoUser(UserVO userVO) {
        var entity = DozerConverter.parseObject(userVO, User.class);
        Optional<User> userExists = userRepository.findOneUserByUserName(entity.getUsername());
        Optional<Boolean> emailExists = userRepository.findUserByEmail(entity.getEmail());
        if (!userExists.isPresent() || userExists.isEmpty() || userExists == null || !emailExists.isPresent()|| emailExists.isEmpty()) {
           if(!emailExists.isPresent() || emailExists.isEmpty() || emailExists ==null) {
                System.out.println(entity.getUserGraduations());
                var voUser = DozerConverter.parseObject(userRepository.saveAndFlush(entity), UserVO.class);
                System.out.println(entity.getUsername());

                return voUser;
            }else{
               throw new CreateUserException("Email already in use");
           }
        } else {
            throw new CreateUserException("User already exist");
        }
    }

    //buscando um usuário pelo id
    public User getUserById(String id) {
        var entity = userRepository.findUserById(id).orElseThrow(() -> new UserNotFoundException("User Not Found"));
        return entity;
    }

    //buscando um usuário pelo nome dele
    public UserVO getUserByUserName(String yourName) {
        var entity = userRepository.getUserByUserName(yourName).orElseThrow(() -> new UserNotFoundException("We not with this username not found in our database"));
        return convertToUserVo(entity);
    }

    public List<UserVO> getAllUsers() {
        var vo = DozerConverter.parseListObjects(userRepository.findAll(), UserVO.class);
        return vo;
    }

    public void deleteUser(String userName) {
        User entity = userRepository.findOneUserByUserName(userName)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userRepository.delete(entity);

    }

    public UserVO convertToUserVo(User entity) {
        if (entity == null) return null;
        return DozerConverter.parseObject(entity, UserVO.class);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username);
        if(user !=null){
            return user;
        } else{
            throw new UsernameNotFoundException("User with this username not found" +username);
        }
    }
}
