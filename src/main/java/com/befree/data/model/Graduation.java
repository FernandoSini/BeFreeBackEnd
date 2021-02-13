package com.befree.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "graduations")
public class Graduation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "graduation_id")
    private int id;

    @Column(name = "course")
    private String courseName;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //@JoinColumn(name = "user_id")
    @JoinTable(name = "graduation_user",
            joinColumns = {@JoinColumn(name = "graduation_id", referencedColumnName = "graduation_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")})
    @JsonIgnore
    private User user;

    public Graduation() {
    }

    public Graduation(int id, String courseName, User user) {
        this.id = id;
        this.courseName = courseName;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
