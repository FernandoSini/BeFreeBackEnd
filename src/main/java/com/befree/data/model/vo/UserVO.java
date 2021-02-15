package com.befree.data.model.vo;

import com.befree.data.model.Graduation;
import com.befree.data.model.Like;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dozermapper.core.Mapping;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

public class UserVO implements Serializable {


    @Mapping("id")
    @JsonProperty("id")
    private String id;
    private String userName;
    private String firstName;
    private String lastName;
    private String gender;
    private String age;
    private List<Like> likes;
    private List<Graduation> graduations;



    //private List<Like> yourLikes;

    public UserVO() {
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
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

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public List<Graduation> getGraduations() {
        return graduations;
    }

    public void setGraduations(List<Graduation> graduations) {
        this.graduations = graduations;
    }
}

