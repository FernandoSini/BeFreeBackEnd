package com.befree.data.model.vo;

import com.github.dozermapper.core.Mapping;


import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;


public class UserVO implements Serializable {

    @Mapping
    private String id;


    private String userName;


    private String firstName;

    private String lastName;


    private String gender;


    //private List<Like> yourLikes;

    public UserVO(){}
    public UserVO(String id, @Size(min = 3) String userName, String firstName, String lastName, String gender /*List<Like> yourLikes*/) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
     //   this.yourLikes = yourLikes;
    }

   // public List<Like> getYourLikes() {
   //     return yourLikes;
    //}

    //public void setYourLikes(List<Like> yourLikes) {
     //   this.yourLikes = yourLikes;
   //}

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
}

