package com.befree.data.model.vo;

import com.befree.data.model.MessageStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonPropertyOrder({"message_id","content", "messageStatus","sender","receiver","match", "timestamp"})
@JsonIgnoreProperties({"links"})
public class MessageVO extends RepresentationModel implements Serializable {
    @Mapping("id")
    @JsonProperty("message_id")
    private String id;
    @JsonProperty("sender")
    @JsonIgnoreProperties({
            "avatar_profile",
            "email","birthday", "gender","first_name", "last_name",
            "images","matches", /*--> esse matches esta dando infinito de looping pra resolver vou manter ativo*/
            "likesSended",
            "likeReceived",
            "createdAt",
            "accountNonExpired", "accountNonLocked", "credentialsNonExpired", "enabled", "username", "token", "events"})
    private UserVO sender;
    @JsonProperty("receiver")
    @JsonIgnoreProperties({
            "avatar_profile",
            "email","birthday", "gender","first_name", "last_name",
            "createdAt",
            "images","matches", /*--> esse matches esta dando infinito de looping pra resolver vou manter ativo*/
            "likesSended",
            "likeReceived",
            "accountNonExpired", "accountNonLocked", "credentialsNonExpired", "enabled", "username", "token", "events"})
    private UserVO receiver;
    @JsonProperty("match")
    @ToString.Exclude
    private MatchVO match;
    @JsonProperty("content")
    private String content;
    @JsonProperty("timestamp")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", timezone = "UTC")
    private LocalDateTime timestamp;
    private MessageStatus messageStatus;
}
