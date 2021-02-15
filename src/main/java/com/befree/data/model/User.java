package com.befree.data.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Table(name = "users")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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
    @OneToMany(mappedBy = "userSendLike",orphanRemoval = true)
    //@JsonManagedReference
    private List<Like> likesSended;

    @OneToMany(mappedBy = "userLiked", orphanRemoval = true)
    private List<Like> likeReceived;
//    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
//    private List<Graduation> userGraduations;


    @ManyToMany(targetEntity = Graduation.class, fetch = FetchType.LAZY/*, cascade = CascadeType.ALL*/)
    @JoinTable(name = "graduation_user",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "graduation_id", referencedColumnName = "graduation_id")})
    private List<Graduation> userGraduations;

    @Column(name = "age")
    private String age;

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

    public void addLikeSent(Like like) {
        likesSended.add(like);
        like.setUserSendLike(this);
    }

    public void removeLikeSent(Like like) {
        likesSended.remove(like);
        like.setUserSendLike(null);
    }

//    public List<Graduation> getUserGraduations() {
//        return userGraduations;
//    }
//
//    public void setUserGraduations(List<Graduation> userGraduations) {
//        this.userGraduations = userGraduations;
////        for(Graduation graduation: userGraduations){
////            graduation.setUser(this);
////        }
//    }


    public List<Like> getLikeReceived() {
        return likeReceived;
    }

    public void setLikeReceived(List<Like> likeReceived) {
        this.likeReceived = likeReceived;
    }

    public List<Graduation> getUserGraduations() {
        return userGraduations;
    }

    public void setUserGraduations(List<Graduation> userGraduations) {
        this.userGraduations = userGraduations;
    }

    public String getAge() {
//        LocalDate localDateTime =  LocalDate.now();
//        DateTimeFormatter dateFormat =  DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        localDateTime.format(dateFormat);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        int birthdayYear = LocalDate.parse(age, dateTimeFormatter).getYear();
        int yearNow = LocalDate.now().getYear();

        int data = (yearNow - birthdayYear);
        return String.valueOf(data);
    }

    public void setAge(String age) {
        this.age = age;
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
                ", userGraduations=" + userGraduations +
                '}';
    }
}
