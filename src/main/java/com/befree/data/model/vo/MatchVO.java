package com.befree.data.model.vo;

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
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"match_id","hisHer" ,"match_room","you_user"})
@JsonIgnoreProperties({"links"})
public class MatchVO extends RepresentationModel implements Serializable {

        private static final long serialVersionUID = 1L;

        @Mapping("id")
        @JsonProperty("match_id")
        private String id;

        @JsonProperty("you_user")
        @JsonIgnoreProperties({"matches", /*--> esse matches esta dando infinito de looping pra resolver vou manter ativo*/
                "likesSended",
                "likeReceived",
                "accountNonExpired","accountNonLocked","credentialsNonExpired", "enabled","username", "token"})
        private UserVO you;

        @JsonProperty("hisHer")
        @JsonIgnoreProperties({"matches", /*--> esse matches esta dando infinito de looping pra resolver vou manter ativo*/
                "likesSended",
                "likeReceived",
                "accountNonExpired","accountNonLocked","credentialsNonExpired", "enabled","username", "token"})
        private UserVO hisHer;


        @JsonProperty("messages")
        @JsonIgnoreProperties("match")
        @ToString.Exclude
        private List<MessageVO> messages;




}
