package com.befree.data.model.vo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.List;

@JsonPropertyOrder({"id", "courseName","users"})
@JsonIgnoreProperties({"users","links"})
public class GraduationVO extends RepresentationModel implements Serializable {

    @Mapping("id")
    @JsonProperty("id")
    private int id;
    private String courseName;
    @JsonIgnoreProperties({"userGraduations","token"})
    private List<UserVO> users;

    public GraduationVO(){}

    public GraduationVO(int id, String courseName, List<UserVO> users) {
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

    public List<UserVO> getUsers() {
        return users;
    }

    public void setUsers(List<UserVO> users) {
        this.users = users;
    }
}
