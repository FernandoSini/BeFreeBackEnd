package com.befree.data.model.vo;

import com.befree.data.model.User;
import com.fasterxml.jackson.annotation.*;
import com.github.dozermapper.core.Mapping;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.io.Serializable;


//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonPropertyOrder({"id","userSendLike", "userLiked"})
public class LikeVO extends RepresentationModel implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Mapping("id")
    @JsonProperty("id_like")
    private String id;

    @JsonProperty("userSendLike")
    @JsonIgnoreProperties({"likesSended", "likeReceived"})
    private UserVO userSendLike;

    @JsonProperty("userLiked")
    @JsonIgnoreProperties({"likesSended", "likeReceived"})
    private UserVO userLiked;


    public LikeVO() {
    }

    public LikeVO(String id, UserVO userSendLike, UserVO userLiked) {
        this.id = id;
        this.userSendLike = userSendLike;
        this.userLiked = userLiked;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserVO getUserSendLike() {
        return userSendLike;
    }

    public void setUserSendLike(UserVO userSendLike) {
        this.userSendLike = userSendLike;
    }

    public UserVO getUserLiked() {
        return userLiked;
    }

    public void setUserLiked(UserVO userLiked) {
        this.userLiked = userLiked;
    }

    @Override
    public String toString() {
        return "Like{" +
                "id='" + id + '\'' +
                ", userSendLike=" + userSendLike +
                ", idUserLiked='" + userLiked + '\'' +
                '}';
    }
}
