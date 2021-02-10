package com.befree.services;

import com.befree.data.model.Like;
import com.befree.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;

    public List<Like> getAll(){

        return likeRepository.findAll();
    }

    public List<Like> getMyLikes(String userId){
        return likeRepository.findAllMyLikes(userId);
    }

    public Like setLike(Like likeData) {
        var entity = likeRepository.saveAndFlush(likeData);
        return entity;
    }

    public List<Like> allMyLikesSent(String myId) {
        var entity = likeRepository.findAllMyLikesSent(myId);
        return entity;
    }
}
