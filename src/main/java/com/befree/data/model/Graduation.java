package com.befree.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "graduations")
public class Graduation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "graduation_id")
    private int id;

    @Column(name = "course")
    private String courseName;

    @ManyToMany(targetEntity = User.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "userGraduations")
    //@JoinColumn(name = "user_id")
//    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
//    @JoinTable(name = "graduation_user",
//            joinColumns = {@JoinColumn(name = "graduation_id", referencedColumnName = "graduation_id")},
//            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")})
   @JsonIgnore
    private List<User> users;

    public Graduation() {
    }

    public Graduation(int id, String courseName, List<User> users) {
        this.id = id;
        this.courseName = courseName;
        this.users = users;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
