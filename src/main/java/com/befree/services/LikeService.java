package com.befree.services;

import com.befree.data.model.Like;
import com.befree.exceptions.LikedException;
import com.befree.exceptions.ResourceNotFoundException;
import com.befree.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;

    public List<Like> getAll() {
        return likeRepository.findAll();
    }

    public List<Like> getMyLikes(String userId) {
        return likeRepository.findAllMyLikes(userId);
    }

    public Like setLike(Like likeData) {
        Optional<Like> likeExists = likeRepository.findIfUserWasLikedByMe(likeData.getUserSendLike().getId(), likeData.getUserLiked().getId());
        if (!likeExists.isPresent()) {
            var entity = likeRepository.saveAndFlush(likeData);
            return entity;

        } else {
            throw new LikedException("This user was already liked by you");
        }


    }

    public List<Like> allMyLikesSent(String myId) {
        var entity = likeRepository.findAllMyLikesSent(myId);
        return entity.orElseThrow(() -> new ResourceNotFoundException("Like Not Found"));
    }
}
