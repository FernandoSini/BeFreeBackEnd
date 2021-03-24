package com.befree.data.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;


public class ImageVO extends RepresentationModel implements Serializable {

    @Mapping("id")
    @JsonProperty("image_id")
    private String id;

    @JsonProperty("image_link")
    private String imageLink;

    @JsonProperty("user_reference_id")
    private UserVO userVO;

    public ImageVO(String id, String imageLink, UserVO userVO) {
        this.id = id;
        this.imageLink = imageLink;
        this.userVO = userVO;
    }

    public ImageVO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public UserVO getUser() {
        return userVO;
    }

    public void setUser(UserVO userVO) {
        this.userVO = userVO;
    }
}
