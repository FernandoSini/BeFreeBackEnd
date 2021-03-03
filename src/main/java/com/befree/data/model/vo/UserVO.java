package com.befree.data.model.vo;

import com.befree.data.model.Permission;
import com.befree.data.model.Usertype;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@JsonPropertyOrder({"id", "userName", "firstName",
        "lastName", "gender", "birthday","email","userGraduations", "usertype",
        "likesSended", "likeReceived"})
@JsonIgnoreProperties({"accountNonExpired",
        "accountNonLocked", "credentialsNonExpired",
        "enabled","username","authorities", "roles"})
@Data
public class UserVO extends RepresentationModel implements UserDetails, Serializable {


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
    @JsonProperty("birthday")
    private String birthday;
    @JsonProperty("email")
    private String email;
    @JsonIgnoreProperties({"userSendLike"})
    private List<LikeVO> likesSended;
    @JsonIgnoreProperties({"userLiked"})
    private List<LikeVO> likeReceived;
    @JsonProperty("userGraduations")
    @JsonIgnoreProperties("users")
    private List<GraduationVO> userGraduations;
    @JsonProperty(value = "usertype")
    private Usertype usertype;
    @JsonProperty(value = "password", access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private Boolean credentialsNonExpired;
    private Boolean enabled;
    private List<Permission> permissions;


    public UserVO() {
    }

    public UserVO(String id, String userName,
                  String firstName,
                  String lastName, String gender,
                  String birthday,
                  List<LikeVO> likesSended,
                  List<LikeVO> likeReceived,
                  List<GraduationVO> userGraduations,
                  Usertype usertype, String password) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthday = birthday;
        this.likesSended = likesSended;
        this.likeReceived = likeReceived;
        this.userGraduations = userGraduations;
        this.usertype = usertype;
        this.password = password;
    }

    public List<String> getRoles() {
        List<String> roles = new ArrayList<>();
        for (Permission permission : this.permissions) {
            roles.add(permission.getDescription());
        }
        return roles;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.permissions;
    }


    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

}

