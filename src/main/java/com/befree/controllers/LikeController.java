package com.befree.controllers;

import com.befree.adapter.custom.UserConverter;
import com.befree.data.model.Like;
import com.befree.data.model.vo.LikeVO;
import com.befree.data.model.vo.UserVO;
import com.befree.exceptions.UserNotFoundException;
import com.befree.repository.UserRepository;
import com.befree.services.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/like")
public class LikeController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserConverter userConverter;

    @Autowired
    private LikeService likeService;
   

    @GetMapping("/all")
    public List<LikeVO> getAllLikes() {

        return likeService.getAll();
    }

    @GetMapping(value = "/getLikes/{userId}",
            produces = {"application/json", "application/xml", "application/x-yaml"})
    public List<LikeVO> getMyLikes(@PathVariable("userId") String userId){
        return likeService.getMyLikes(userId);
    }

    @GetMapping(value = "/likesSent/{myId}",
            produces = {"application/json","application/xml", "application/x-yaml"})
    public List<Like> getLikesSentByMe(@PathVariable("myId") String myId){
        return likeService.allMyLikesSent(myId);
    }


    @PostMapping(value = "/sendLikeTo/{userId}/{yourId}",
            produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<LikeVO> likeUser(@PathVariable("userId") String userId, @PathVariable("yourId") String yourId) {
        //pegando o usuário likado
        UserVO userLiked = userConverter.convertUserToVO(userRepository.findUserById(userId).orElseThrow(()-> new UserNotFoundException("User not found!")));
        UserVO userSendLike = userConverter.convertUserToVO(userRepository.findUserById(yourId).orElseThrow(() -> new UserNotFoundException("User not found!")));
        

//        //criando um array de usuários que receberam o like
//        List<User> usersLiked = new ArrayList<>();
//        //Criando um array de usuarios que deram o like
//        List<User> usersSendLike = new ArrayList<>();
//
//        //adicionando o usuario likados na lista de usuarios que receberam o like
//        usersLiked.add(userLiked);
//        //adicionando o usuario que  deram um like para a lista de quem enviou um like
////        usersSendLike.add(userSendLike);
//        System.out.println("teste"+ usersLiked.get(0).getUserName());
//        System.out.println("teste"+ usersSendLike.get(0).getUserName());



        LikeVO likeVO = new LikeVO();
//        likeVO.setUserSendLike(userSendLike);
//        likeVO.setUserLiked(userLiked);

//        userLiked.getLikeReceived().add(likeVO);
//        userSendLike.getLikesSended().add(likeVO);
        userSendLike.addLikeSended(likeVO);
        userLiked.addLikeReceived(likeVO);


        return ResponseEntity.ok().body(likeService.setLike(likeVO));


    }
}
