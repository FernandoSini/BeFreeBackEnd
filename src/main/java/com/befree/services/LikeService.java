package com.befree.services;

import com.befree.adapter.DozerConverter;
import com.befree.adapter.custom.LikeConverter;
import com.befree.data.model.Like;
import com.befree.data.model.vo.LikeVO;
import com.befree.data.model.vo.UserVO;
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
    @Autowired
    private MatchServices matchServices;


    public List<LikeVO> getAll() {
        var entity = likeRepository.findAll();
        var vo = DozerConverter.parseListObjects(entity, LikeVO.class);
        return vo;
    }

    public List<LikeVO> getMyLikes(String userId) {
        var entity = likeRepository.findAllMyLikes(userId);
        var vo = DozerConverter.parseListObjects(entity, LikeVO.class);
        return vo;
    }

    public LikeVO setLike(LikeVO likeData) {
        var likeEntity = likeConverter.convertLikeVoToEntity(likeData);
        Optional<Like> likeExists = likeRepository.findIfUserWasLikedByMe(
                likeEntity.getUserSendLike().getId(), likeEntity.getUserLiked().getId());
        if (!likeExists.isPresent() || likeExists.isEmpty()) {
            var likeVo = likeConverter.convertEntityToVO(likeRepository.saveAndFlush(likeEntity));
            //vamos verificar se o usuário que ele/ela curtiu já deu like nele, caso sim vamos realizar o match
            var himHerLikesYou = likeRepository.findIfIwasLikedByHim(
                    likeVo.getUserSendLike().getId(), likeVo.getUserLiked().getId());
            if(himHerLikesYou.isPresent() || !himHerLikesYou.isEmpty()){
                System.out.println("we have a match");
                matchServices.setMatch(likeVo.getUserSendLike(),likeVo.getUserLiked());
            }

            return likeVo;

        } else {

//                System.out.println("sucesso1");
//                Match match = new Match();
//                System.out.println("sucesso2");
//                match.setYourId(likeEntity.getUserSendLike().getId());
//                match.setYourMatchId(likeEntity.getUserLiked().getId());
//                match.setYourId(likeEntity.getUserLiked().getId());
//                match.setYourMatchId(likeEntity.getUserLiked().getId());
//                System.out.println(match.toString());
//                matchServices.saveMatch(match);
////                Match match2 = new Match();
////                match2.setYourMatchId(likeEntity.getUserSendLike().getId());
////                match2.setYourId(likeEntity.getUserLiked().getId());
////                ChatRoom chatRoom = new ChatRoom();
////                chatRoom.set
//              //  matchServices.saveMatch(match2);
//            matchServices.setMatch(likeData.getUserSendLike(),likeData.getUserLiked());
            throw new LikedException("This user was already liked by you");
        }


    }

    public List<Like> allMyLikesSent(String myId) {
        var entity = likeRepository.findAllMyLikesSent(myId);
        return entity.orElseThrow(() -> new ResourceNotFoundException("Like Not Found"));
    }
}
