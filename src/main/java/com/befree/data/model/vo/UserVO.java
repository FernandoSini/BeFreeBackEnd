package com.befree.data.model.vo;

import com.befree.data.model.Graduation;
import com.befree.data.model.Like;
import com.befree.data.model.Usertype;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@JsonPropertyOrder({"id", "userName", "firstName",
        "lastName", "gender", "age", "userGraduations","usertype",
        "likesSended", "likeReceived"})
public class UserVO extends RepresentationModel implements Serializable {


    @Mapping("id")
    @JsonProperty("id_user")
    private String id;
    @JsonProperty("user_name")
    private String userName;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    private String gender;
    private String age;
    private List<LikeVO> likesSended;
    private List<LikeVO> likeReceived;
    @JsonProperty("userGraduations")
    @JsonIgnoreProperties("users")
    private List<GraduationVO> userGraduations;
    @JsonProperty(value = "usertype")
    private Usertype usertype;



    public UserVO() {
    }

    public UserVO(String id, String userName,
                  String firstName,
                  String lastName, String gender,
                  String age,
                  List<LikeVO> likesSended,
                  List<LikeVO> likeReceived,
                  List<GraduationVO> userGraduations,
                  Usertype usertype) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.likesSended = likesSended;
        this.likeReceived = likeReceived;
        this.userGraduations = userGraduations;
        this.usertype = usertype;
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

    public List<LikeVO> getLikesSended() {
        return likesSended;
    }

    public void setLikesSended(List<LikeVO> likesSended) {
        this.likesSended = likesSended;
    }

    public List<LikeVO> getLikeReceived() {
        return likeReceived;
    }

    public void setLikeReceived(List<LikeVO> likeReceived) {
        this.likeReceived = likeReceived;
    }

    public List<GraduationVO> getUserGraduations() {
        return userGraduations;
    }

    public void setUserGraduations(List<GraduationVO> userGraduations) {
        this.userGraduations = userGraduations;
    }

    public Usertype getUsertype() {
        return usertype;
    }

    public void setUsertype(Usertype usertype) {
        this.usertype = usertype;
    }

    public void addLikeSended(LikeVO likeVO) {
        this.likesSended.add(likeVO);
        likeVO.setUserSendLike(this);
    }


    public void removeLikeSended(LikeVO likeVO) {
        this.likesSended.remove(likeVO);
        //  like.setUserSendLike(null);
    }
    public void addLikeReceived(LikeVO likeVO) {
        this.likeReceived.add(likeVO);
        likeVO.setUserLiked(this);
    }


    public void removeLikeReceived(LikeVO likeVO) {
        this.likeReceived.remove(likeVO);
        //  like.setUserSendLike(null);
    }
}

