//package com.befree.data.model;
//
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.hibernate.annotations.GenericGenerator;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.util.List;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Table(name = "ChatRoom")
//public class ChatRoom implements Serializable {
//    @Id
//    @GeneratedValue(generator = "uuid",strategy = GenerationType.IDENTITY)
//    @GenericGenerator(name = "uuid", strategy = "uuid")
//    @Column(name = "chat_room_id")
//    private String id;
////    private String chatId;
//    private String senderId;
//    private String receiverId;
//
//    @OneToMany
//    private List<Message> messages;
//
//    @OneToOne(mappedBy = "matchRoom",cascade = CascadeType.ALL)
////    @JoinColumn(name = "chat_room_id", referencedColumnName = "chat_room_id")
////    @JsonIgnoreProperties("matchRoom")
//    private Match match;
//}
