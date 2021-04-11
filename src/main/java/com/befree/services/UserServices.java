package com.befree.services;


import com.befree.adapter.DozerConverter;
import com.befree.adapter.custom.UserConverter;
import com.befree.data.model.*;
import com.befree.data.model.vo.EventOwnerVO;
import com.befree.data.model.vo.UserVO;
import com.befree.exceptions.CreateUserException;
import com.befree.exceptions.ResourceNotFoundException;
import com.befree.exceptions.UserNotFoundException;
import com.befree.repository.EventOwnerRepository;
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
    @Autowired
    private EventOwnerRepository eventOwnerRepository;

    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //criar o user No banco
    //o problema é que o converter n esta convertendo a graduacao do vo
    public UserVO criandoUser(UserVO userVO) {
        var entity = DozerConverter.parseObject(userVO, User.class);
        Optional<User> userExists = userRepository.findOneUserByUserName(entity.getUsername());
        Optional<Boolean> emailExists = userRepository.findUserByEmail(entity.getEmail());
        if (!userExists.isPresent() || userExists.isEmpty() || userExists == null || !emailExists.isPresent() || emailExists.isEmpty()) {
            if (!emailExists.isPresent() || emailExists.isEmpty() || emailExists == null) {
                System.out.println(entity.getUserGraduations());
                var voUser = DozerConverter.parseObject(userRepository.saveAndFlush(entity), UserVO.class);
                System.out.println(entity.getUsername());

                return voUser;
            } else {
                throw new CreateUserException("Email already in use");
            }
        } else {
            throw new CreateUserException("User already exist");
        }
    }

    //buscando um usuário pelo id
    public UserVO getUserById(String id) {
        var entity = userRepository.findUserById(id).orElseThrow(() -> new UserNotFoundException("User Not Found"));
        var vo = DozerConverter.parseObject(entity, UserVO.class);
        return vo;
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

    public List<UserVO> getUsersByDiferentThenYourGender(Gender gender) {

        var entity = userRepository.findUserByGenders(gender)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        var vo = DozerConverter.parseListObjects(entity, UserVO.class);
        return vo;
    }

    public List<UserVO> getUsersByOneGender(Gender gender) {
        var entity = userRepository.findUsersByOneGender(gender)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        var vo = DozerConverter.parseListObjects(entity, UserVO.class);
        return vo;
    }


    public void deleteUser(String userName) {
        User entity = userRepository.findOneUserByUserName(userName)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userRepository.delete(entity);

    }

    public void deleteById(String id) {
        User entity = userRepository.findUserById(id).
                orElseThrow(() -> new UserNotFoundException("User Not found"));
        userRepository.deleteById(entity.getId());
    }

    public UserVO update(UserVO u) {
        var entity = userRepository.findUserById(u.getId())
                .orElseThrow(() -> new UserNotFoundException("User not found!"));

        entity.setAvatar(u.getAvatar());
//        entity.setMatches(DozerConverter.parseListObjects(u.getMatches(), Match.class));
        if (u.getUserName() == null || u.getUserName().isEmpty()) {
            entity.setUserName(entity.getUserName());
        } else {
            entity.setUserName(u.getUserName());
        }
        if (u.getBirthday() == null || u.getBirthday().isEmpty()) {
            entity.setBirthday(entity.getBirthday());
        } else {
            entity.setBirthday(u.getBirthday());
        }
        if (u.getEmail() == null || u.getEmail().isEmpty()) {
            entity.setEmail(entity.getEmail());
        } else {
            entity.setEmail(u.getEmail());
        }
        if (u.getGender() == null) {
            entity.setGender(entity.getGender());
        } else {
            entity.setGender(u.getGender());
        }
        if (u.getLastName() == null || u.getLastName().isEmpty()) {
            entity.setLastName(entity.getLastName());
        } else {
            entity.setLastName(u.getLastName());
        }
        if (u.getFirstName() == null || u.getFirstName().isEmpty()) {
            entity.setFirstName(entity.getFirstName());
        } else {
            entity.setFirstName(u.getFirstName());
        }
        if (u.getAbout() == null || u.getAbout().isEmpty()) {
            entity.setAbout(entity.getAbout());
        } else {
            entity.setAbout(u.getAbout());
        }


//        entity.setLikeReceived(DozerConverter.parseListObjects(u.getLikeReceived(), Like.class));
//        entity.setLikesSended(DozerConverter.parseListObjects(u.getLikesSended(),Like.class));
//        entity.setImages(DozerConverter.parseListObjects(u.getImages(), Image.class));

        var vo = DozerConverter.parseObject(userRepository.save(entity), UserVO.class);
        return vo;

    }

    public UserVO convertToUserVo(User entity) {
        if (entity == null) return null;
        return DozerConverter.parseObject(entity, UserVO.class);
    }


//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        var user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new UserNotFoundException("User " + username + " not found"));
//
//        if (user != null) {
//            return user;
//        } else {
//            throw new UsernameNotFoundException("User with this username not found" + username);
//        }
//    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.contains(":")) {
            int colonIndex = username.indexOf(":");
//            int event_ownerIndex = username.indexOf("event_owner");
            String eventOwnerName = username.substring(0,colonIndex);
            System.out.println(eventOwnerName);
            var eventOwner = eventOwnerRepository.findByEventOwnerName(eventOwnerName)
                    .orElseThrow(() -> new UserNotFoundException("EventOwner:" + eventOwnerName + " not found"));
            if (eventOwner != null) {
                return eventOwner;
            }else {
                throw new UsernameNotFoundException("EventOwner with this username not found " + eventOwnerName);
            }
        }

        if(!username.contains(":")) {
            var user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UserNotFoundException("User " + username + " not found"));
            if (user != null) {
                return user;
            } else {
                throw new UsernameNotFoundException("User with this username not found" + username);
            }
        }
        return null;
    }
}
