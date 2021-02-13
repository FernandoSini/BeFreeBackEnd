package com.befree.data.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "likes")
public class Like implements Serializable {

    @Id
    @Column(name = "like_id")
    @GeneratedValue(generator = "uuid",strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

   @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
////    @JsonIgnore
    //@OneToMany(targetEntity =User.class, mappedBy = "likesSended")
   @JoinColumn(name = "user_id")
   @JsonBackReference
    private User userSendLike;

   @Column(name = "user_received_like_id")
   private String idUserLiked;

//   @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
//////    @JsonIgnore
// //   @OneToMany(targetEntity =User.class , mappedBy = "likeReceived")
//   @JoinColumn(name = "user_received_liked_id")
//   @JsonBackReference
//    private User userLiked;
////    @OneToMany(mappedBy = "likesSended")
////    @Column(name = "who_are_sending_like")
////    private List<User> whoAreSendingLike;
////    @OneToMany(mappedBy = "likesReceived")
////    @Column(name = "who_are_receiving_like")
////    private List<User> whoAreReceivingLike;

    public Like() {
    }

    public Like(String id, User userSendLike, String idUserLiked) {
        this.id = id;
        this.userSendLike = userSendLike;
        this.idUserLiked = idUserLiked;
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

    public String getIdUserLiked() {
        return idUserLiked;
    }

    public void setIdUserLiked(String idUserLiked) {
        this.idUserLiked = idUserLiked;
    }

    @Override
    public String toString() {
        return "Like{" +
                "id='" + id + '\'' +
                ", userSendLike=" + userSendLike +
                ", idUserLiked='" + idUserLiked + '\'' +
                '}';
    }
}
