package com.befree.data.model.vo;

import com.befree.data.model.ChatRoomVO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonPropertyOrder({"match_id","hisHerId" ,"match_room","you_user"})
//@JsonIgnoreProperties({"matchRoom"})
public class MatchVO extends RepresentationModel implements Serializable {


        private static final long serialVersionUID = 1L;

        @Mapping("id")
        @JsonProperty("match_id")
        private String id;

        @JsonProperty("you_user")
        @JsonIgnoreProperties({"matches", /*--> esse matches esta dando infinito de looping pra resolver vou manter ativo*/
                "likesSended",
                "likeReceived",
//            "matchRoom",
                "accountNonExpired","accountNonLocked","credentialsNonExpired", "enabled","username", "token"})
        private UserVO you;

        @JsonProperty("hisHer_id")
        private String hisHerId;

        @JsonProperty("match_room")
        private ChatRoomVO matchRoomVO;


//        @Column(name = "id_userMatched")
//        private User userMatched;
//
//        @Column("chat")
//        private Chat chat;



}
