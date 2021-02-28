package com.befree.services;

import com.befree.adapter.custom.LikeConverter;
import com.befree.data.model.Like;
import com.befree.data.model.vo.LikeVO;
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
    @Autowired
    private LikeConverter likeConverter;

    public List<Like> getAll() {
        return likeRepository.findAll();
    }

    public List<Like> getMyLikes(String userId) {
        return likeRepository.findAllMyLikes(userId);
    }

    public LikeVO setLike(LikeVO likeData) {
        var likeEntity = likeConverter.convertLikeVoToEntity(likeData);
        Optional<Like> likeExists = likeRepository.findIfUserWasLikedByMe(likeEntity.getUserSendLike().getId(), likeEntity.getUserLiked().getId());
        if (!likeExists.isPresent() || likeExists.isEmpty()) {
            var likeVo = likeConverter.convertEntityToVO(likeRepository.saveAndFlush(likeEntity));
            return likeVo;

        } else {
            throw new LikedException("This user was already liked by you");
        }


    }

    public List<Like> allMyLikesSent(String myId) {
        var entity = likeRepository.findAllMyLikesSent(myId);
        return entity.orElseThrow(() -> new ResourceNotFoundException("Like Not Found"));
    }
}
