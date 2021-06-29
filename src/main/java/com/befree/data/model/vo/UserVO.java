package com.befree.data.model.vo;

import com.befree.data.model.Gender;
import com.befree.data.model.Permission;
import com.befree.data.model.Usertype;
import com.fasterxml.jackson.annotation.*;
import com.github.dozermapper.core.Mapping;
import lombok.Data;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@JsonPropertyOrder({"id","avatar_profile", "userName", "firstName",
        "lastName", "gender", "birthday", "email", "usertype", "about", "job_title",
        "company", "school", "livesIn","createdAt",
         "matches", "events",
        "likesSended", "likeReceived", "images"})
@JsonIgnoreProperties({"accountNonExpired",
        "accountNonLocked", "credentialsNonExpired", "roles",
        "enabled", "username", "authorities", "permissions", "links"})
@JsonInclude(JsonInclude.Include.NON_NULL)
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
    private Gender gender;
    private String birthday;
    @JsonProperty("email")
    private String email;
    @JsonIgnoreProperties({"userSendLike", "matches", "links"})
    private List<LikeVO> likesSended;
    @JsonIgnoreProperties({"userLiked", "matches", "links"})
    private List<LikeVO> likeReceived;
    @JsonProperty(value = "usertype")
    private Usertype usertype;
    @JsonProperty(value = "password", access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private List<Permission> permissions;
    @JsonProperty(value = "matches")
    @ToString.Exclude
    @JsonIgnoreProperties({"userSendLike", "userLiked"})
    private List<MatchVO> matches;
    @ToString.Exclude
    @JsonIgnore
    private List<MatchVO> hisHerMatch;
    @JsonProperty(value = "token", access = JsonProperty.Access.READ_ONLY)
    private String token;
    @JsonProperty(value = "images")
    @JsonIgnoreProperties({"user", "links", "user_reference"})
    private List<ImageVO> images;
    @JsonProperty(value = "avatar_profile")
    private AvatarVO avatarProfile;
    @JsonProperty(value = "about")
    private String about;
    @JsonProperty(value = "events")
    @ToString.Exclude
    @JsonIgnoreProperties({"users"})
    private List<EventVO> events;
    @JsonProperty(value = "job_title")
    private String job;
    @JsonProperty(value = "company")
    private String company;
    @JsonProperty(value = "school")
    private String school;
    @JsonProperty(value = "livesIn")
    private String livesIn;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    public UserVO() {
    }

    public UserVO(String id, String userName,
                  String firstName,
                  String lastName, Gender gender,
                  String birthday,
                  String email,
                  List<LikeVO> likesSended,
                  List<LikeVO> likeReceived,
                  Usertype usertype, String password,
                  List<MatchVO> matches,
                  List<MatchVO> hisHerMatch,
                  AvatarVO avatarProfile,
                  List<ImageVO> images, String about,
                  List<EventVO> events,
                  String job, String company, String school, String livesIn,
                  LocalDateTime createdAt) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.birthday = birthday;
        this.likesSended = likesSended;
        this.likeReceived = likeReceived;
        this.usertype = usertype;
        this.password = password;
        this.matches = matches;
        this.avatarProfile = avatarProfile;
        this.hisHerMatch = hisHerMatch;
        this.images = images;
        this.about = about;
        this.events = events;
        this.job = job;
        this.company = company;
        this.school = school;
        this.livesIn = livesIn;
        this.createdAt = createdAt;

    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<EventVO> getEvents() {
        return events;
    }

    public void setEvents(List<EventVO> events) {
        this.events = events;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getLivesIn() {
        return livesIn;
    }

    public void setLivesIn(String livesIn) {
        this.livesIn = livesIn;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public List<MatchVO> getHisHerMatch() {
        return hisHerMatch;
    }

    public void setHisHerMatch(List<MatchVO> hisHerMatch) {
        this.hisHerMatch = hisHerMatch;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public void addImage(ImageVO images) {
        this.images.add(images);
        images.setUserVO(this);
    }

    public void remove(ImageVO images) {
        this.images.remove(images);
    }

    public List<ImageVO> getImages() {
        return images;
    }

    public void setImages(List<ImageVO> images) {
        this.images = images;
    }

    public AvatarVO getAvatarProfile() {
        return avatarProfile;
    }

    public void setAvatarProfile(AvatarVO avatarProfile) {
        this.avatarProfile = avatarProfile;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
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


    public Usertype getUsertype() {
        return usertype;
    }

    public void setUsertype(Usertype usertype) {
        this.usertype = usertype;
    }

    public List<MatchVO> getMatches() {
        return matches;
    }

    public void setMatches(List<MatchVO> matches) {
        this.matches = matches;
    }

    public void addMatch(MatchVO matchVO) {
        this.matches.add(matchVO);
        matchVO.setYou(this);
    }

    public void removeMatch(MatchVO matchVO) {
        this.matches.remove(matchVO);
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

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public Boolean getAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public Boolean getAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public Boolean getCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

}

