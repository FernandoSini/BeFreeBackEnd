package com.befree.data.model.vo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Lob;
import java.io.Serializable;

@JsonPropertyOrder({"avatar_id", "name", "contentType", "size"})
@JsonIgnoreProperties({"links", "user_reference", "data"})
public class AvatarVO extends RepresentationModel implements Serializable {

    @Mapping("id")
    @JsonProperty("avatar_id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("contentType")
    private String contentType;
    @JsonProperty("size")
    private Long size;
    @Lob
    @JsonIgnore
    private byte[] data;

    @JsonProperty(value = "user_reference", required = false)
    @JsonIgnoreProperties({
            "accountNonExpired",
            "accountNonLocked",
            "credentialsNonExpired",
            "roles",
            "enabled", "username", "authorities", "permissions", "token", "links",
            "images", "matches", "likesSended", "likeReceived"})
    private UserVO userVO;
     @JsonProperty("url")
     private String url;

    public AvatarVO(String id, String name, String contentType, Long size, byte[] data, UserVO userVO, String url) {
        this.id = id;
        this.name = name;
        this.contentType = contentType;
        this.size = size;
        this.data = data;
        this.userVO = userVO;
        this.url = url;
    }


    public AvatarVO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public UserVO getUserVO() {
        return userVO;
    }

    public void setUserVO(UserVO userVO) {
        this.userVO = userVO;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
