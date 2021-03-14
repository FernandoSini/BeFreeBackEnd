package com.befree.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="ChatMessage")
public class ChatMessage {

    @Id
    private String id;
    private String chatId;
    private String senderId;
    private String receiverId;
    private String senderName;
    private String receiverName;
    private String content;
    private Date timestamp;
    private MessageStatus messageStatus;

}
