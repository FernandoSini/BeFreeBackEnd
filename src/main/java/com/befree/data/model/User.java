package com.befree.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(generator = "uuid", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    @Column(name = "user_name", unique = true)
    @Size(min = 3)
    private String userName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "gender")
    private String gender;

//    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//    @JoinTable(name = "user_likes",
//            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
//            inverseJoinColumns = @JoinColumn(name = "like_id", referencedColumnName = "id"))


    //   @ManyToOne(targetEntity = Like.class)
   //@JoinColumn(name = "like_id")
    @OneToMany(mappedBy = "userSendLike")
    @JsonManagedReference
    private List<Like> likesSended;
//     @ManyToOne(targetEntity = Like.class)
//     @JoinColumn(name = "like_id")
//     private Like likesSended;

////      @ManyToOne(targetEntity = Like.class)
////  @JoinColumn(name = "like_received_id")
//    @OneToMany(mappedBy = "userLiked")
//    @JsonManagedReference
//    private List<Like> likeReceived;
////    @ManyToOne(targetEntity = Like.class)
////    @JoinColumn(name ="like_received_id" )
////    private Like likesReceived;


    public User() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<Like> getLikesSended() {
        return likesSended;
    }

    public void setLikesSended(List<Like> likesSended) {
        this.likesSended = likesSended;
    }
    private void addLikeSent(Like like){
        likesSended.add(like);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", likesSended=" + likesSended +
               // ", likeReceived=" + likeReceived +
                '}';
    }
}
