package com.befree.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Images")
@JsonIgnoreProperties("links")
public class Image implements Serializable {

    @Id
    @Column(name = "image_id")
    @GeneratedValue(generator = "uuid", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    @Column(name = "image_link")
    private String imageLink;

    @ManyToOne
    @JsonIgnoreProperties({"likesSended","likeReceived","accountNonLocked",
            "accountNonExpired","credentialsNonExpired", "enabled","username", "token","images"})
    private User user;

    public Image(String id, String imageLink, User user) {
        this.id = id;
        this.imageLink = imageLink;
        this.user = user;
    }

    public Image() {

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
