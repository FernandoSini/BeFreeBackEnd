package com.befree.data.model.vo;


import com.befree.data.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@JsonPropertyOrder({"id", "course"})
public class GraduationVO extends RepresentationModel implements Serializable {

    @Mapping("id")
    @JsonProperty("id")
    private int id;
    private String courseName;
    private List<UserVO> users;

    public GraduationVO(){}

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

    public List<UserVO> getUsers() {
        return users;
    }

    public void setUsers(List<UserVO> users) {
        this.users = users;
    }
}
