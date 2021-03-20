package com.befree.adapter.custom;

import com.befree.adapter.DozerConverter;
import com.befree.data.model.Like;
import com.befree.data.model.User;
import com.befree.data.model.vo.LikeVO;
import com.befree.data.model.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeConverter {

    @Autowired
    private UserConverter userConverter;

    public LikeVO convertEntityToVO(Like likeObject) {
        LikeVO likeVO = new LikeVO();
        likeVO.setId(likeObject.getId());
        likeVO.setUserLiked(userConverter.convertUserToVO(likeObject.getUserLiked()));
        likeVO.setUserSendLike(userConverter.convertUserToVO(likeObject.getUserSendLike()));

        return likeVO;
    }

    public Like convertLikeVoToEntity(LikeVO voLike) {
        Like like = new Like();
        like.setId(voLike.getId());
        like.setUserLiked(userConverter.convertUserVoToUser(voLike.getUserLiked()));
        like.setUserSendLike(DozerConverter.parseObject(voLike.getUserSendLike(),User.class));
        return like;
    }



}
