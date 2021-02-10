package com.befree.repository;

import com.befree.data.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, String> {

    @Query("Select l from Like l where l.idUserLiked =:userId")
    List<Like> findAllMyLikes(@Param("userId") String userId);

    @Query("Select l from Like l where l.userSendLike.id =:myId")
    List<Like> findAllMyLikesSent(String myId);
}
