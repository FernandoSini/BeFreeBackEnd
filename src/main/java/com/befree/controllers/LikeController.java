package com.befree.controllers;

import com.befree.data.model.Like;
import com.befree.data.model.User;
import com.befree.repository.UserRepository;
import com.befree.services.LikeService;
import com.befree.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/like")
public class LikeController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LikeService likeService;

    @GetMapping("/all")
    public List<Like> getAllLikes() {
        return likeService.getAll();
    }

    @GetMapping("/getLikes/{userId}")
    public List<Like> getMyLikes(@PathVariable("userId") String userId){
        return likeService.getMyLikes(userId);
    }

    @GetMapping("/likesSent/{myId}")
    public List<Like> getLikesSentByMe(@PathVariable("myId") String myId){
        return likeService.allMyLikesSent(myId);
    }


    @PostMapping("/{userId}")
    public Like likeUser(@PathVariable("userId") String userId, @RequestBody User userSendLike) {
        //pegando o usuário likado
        User userLiked = userRepository.findUserById(userId);


        //criando um array de usuários que receberam o like
        List<User> usersLiked = new ArrayList<>();
        //Criando um array de usuarios que deram o like
        List<User> usersSendLike = new ArrayList<>();

        System.out.println("teste" + userSendLike.toString());
        System.out.println("teste" + userLiked.toString());
        //adicionando o usuario likados na lista de usuarios que receberam o like
        usersLiked.add(userLiked);
        //adicionando o usuario que  deram um like para a lista de quem enviou um like
        usersSendLike.add(userSendLike);
        System.out.println("teste"+ usersLiked.get(0).getUserName());
        System.out.println("teste"+ usersSendLike.get(0).getUserName());


        Like like = new Like();
        like.setUserSendLike(userSendLike);
        like.setIdUserLiked(userLiked.getId());



        System.out.println(like.getUserSendLike());
        System.out.println(like.toString());




        return likeService.setLike(like);


    }
}
