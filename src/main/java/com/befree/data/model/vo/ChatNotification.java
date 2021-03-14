package com.befree.data.model.vo;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ChatNotification")
public class ChatNotification {
    @Id
    private String id;
    private String senderId;
    private String senderName;
}
