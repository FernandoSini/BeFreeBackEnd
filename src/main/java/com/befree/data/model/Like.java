package com.befree.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "likes")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Like implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "like_id")
    @GeneratedValue(generator = "uuid",strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

   @ManyToOne(/*targetEntity = User.class, fetch = FetchType.EAGER*//*, cascade = CascadeType.ALL*/)
   @JoinColumn(name = "user_send_like_id")
   @JsonIgnoreProperties({"likesSended","likeReceived"})
    private User userSendLike;

   @ManyToOne
   @JoinColumn(name = "user_received_like_id")
   @JsonIgnoreProperties({"likesSended","likeReceived"})
   private User userLiked;


    public Like() {
    }

    public Like(String id, User userSendLike, User userLiked) {
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

    public User getUserSendLike() {
        return userSendLike;
    }

    public void setUserSendLike(User userSendLike) {
        this.userSendLike = userSendLike;
    }

    public User getUserLiked() {
        return userLiked;
    }

    public void setUserLiked(User userLiked) {
        this.userLiked = userLiked;
    }

//    @Override
//    public String toString() {
//        return "Like{" +
//                "id='" + id + '\'' +
//                ", userSendLike=" + userSendLike +
//                ", idUserLiked='" + userLiked + '\'' +
//                '}';
//    }
}
