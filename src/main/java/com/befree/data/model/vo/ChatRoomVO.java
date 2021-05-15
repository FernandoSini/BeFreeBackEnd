//package com.befree.data.model.vo;
//
//
//
//import com.befree.data.model.Message;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.github.dozermapper.core.Mapping;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.hateoas.RepresentationModel;
//
//import java.io.Serializable;
//import java.util.List;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//public class ChatRoomVO extends RepresentationModel implements Serializable {
//    @Mapping("id")
//    @JsonProperty("chat_room_id")
//    private String idRoom;
//
////    private String chatId;
//    private String senderId;
//    private String receiverId;
//    private List<Message> messages;
//
//    @JsonProperty("match_chat_room")
//    private MatchVO matchVO;
//}
