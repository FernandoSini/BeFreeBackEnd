package com.befree.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "AvatarEventOwner")
@JsonIgnoreProperties({"links"})
public class EventOwnerAvatar implements Serializable {

    @Id
    @Column(name = "avatar_id")
    @GeneratedValue(generator = "uuid", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    @Column(name = "name")
    private String name;
    @Column(name="contentType")
    private String contentType;
    @Column(name="size")
    private Long size;
    @Lob
    private byte[] data;
    @Column(name = "url")
    private String url;

    @OneToOne(targetEntity = EventOwner.class)
    @JsonIgnoreProperties({"likesSended","likeReceived","accountNonLocked",
            "accountNonExpired","credentialsNonExpired", "enabled","username", "token","images"})
    private EventOwner eventOwner;

    public EventOwnerAvatar(String id, String name, String contentType, Long size, byte[] data, EventOwner eventOwner, String url) {
        this.id = id;
        this.name = name;
        this.contentType = contentType;
        this.size = size;
        this.data = data;
        this.eventOwner = eventOwner;
        this.url = url;
    }


    public EventOwnerAvatar() {

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

    public EventOwner getEventOwner() {
        return eventOwner;
    }

    public void setEventOwner(EventOwner eventOwner) {
        this.eventOwner = eventOwner;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
