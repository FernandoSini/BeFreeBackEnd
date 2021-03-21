package com.befree.repository;

import com.befree.data.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, String> {

    @Query("Select l from Like l where l.userLiked.id =:userId")
    List<Like> findAllMyLikes(@Param("userId") String userId);

    @Query("Select l from Like l where l.userSendLike.id =:myId")
    Optional<List<Like>> findAllMyLikesSent(String myId);

    @Query("Select l from Like l where l.userSendLike.id =:myId AND l.userLiked.id =:hisId")
    Optional<Like> findIfUserWasLikedByMe(@Param("myId") String myId, @Param("hisId") String hisId);

    @Query("SELECT l FROM Like l WHERE l.userLiked.id =:myId AND l.userSendLike.id =:hisHerId")
    Optional<Like> findIfIwasLikedByHim(@Param("myId") String myId, @Param("hisHerId") String hisHerId);
}
