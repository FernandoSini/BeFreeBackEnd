package com.befree.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "users")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
//@JsonIgnoreProperties({"hibernateLazyInitializer","handler", "likeReceived"})
public class User implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

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

    @OneToMany(mappedBy = "userSendLike",orphanRemoval = true, fetch = FetchType.EAGER)
    //@JsonBackReference
    private List<Like> likesSended;

    @OneToMany(mappedBy = "userLiked", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Like> likeReceived;

    @ManyToMany(targetEntity = Graduation.class,
            fetch = FetchType.LAZY
            /*, cascade = CascadeType.ALL*/)
    @JoinTable(name = "graduation_user",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "graduation_id", referencedColumnName = "graduation_id")})
    @JsonIgnoreProperties({"users"})
    private List<Graduation> userGraduations;

    @Enumerated(EnumType.ORDINAL)
    private Usertype usertype;

    @Column(name = "age")
    private String age;

    public User() {
    }

    public User(String id, @Size(min = 3) String userName, String firstName, String lastName, String gender, List<Like> likesSended, List<Like> likeReceived, List<Graduation> userGraduations, Usertype usertype, String age) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.likesSended = likesSended;
        this.likeReceived = likeReceived;
        this.userGraduations = userGraduations;
        this.usertype = usertype;
        this.age = age;
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

    public List<Like> getLikeReceived() {
        return likeReceived;
    }

    public void setLikeReceived(List<Like> likeReceived) {
        this.likeReceived = likeReceived;
    }

    public Usertype getUsertype() {
        return usertype;
    }

    public void setUsertype(Usertype usertype) {
        this.usertype = usertype;
    }

//    public void addLikeSended(Like like) {
//        this.likesSended.add(like);
//        like.setUserSendLike(this);
//    }
//
//
//    public void removeLikeSended(Like like) {
//        this.likesSended.remove(like);
//      //  like.setUserSendLike(null);
//    }
//    public void addLikeReceived(Like like) {
//        this.likeReceived.add(like);
//        like.setUserLiked(this);
//    }
//
//    public void removeLikeReceived(Like like) {
//       this.likeReceived.remove(like);
//      //  like.setUserLiked(null);
//    }
//
////    public void setLikesSended(List<Like> likes) {
////        for(Like l: likes){
////            this.likesSended.add(l);
////        }
////    }
////
////    public void setLikeReceived(List<Like> likes) {
////        for(Like l : likes){
////            this.likeReceived.add(l);
////        }
////    }


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
        // DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // int birthdayYear = LocalDate.parse(age, dateTimeFormatter).getYear();
        // int yearNow = LocalDate.now().getYear();

        // int data = (yearNow - birthdayYear);
        // return String.valueOf(data);
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }


}
