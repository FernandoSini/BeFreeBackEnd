package com.befree.data.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "ChatNotification")
public class ChatNotification {
    //antigo
//    @Id
//    private String id;
////    private String senderId;
////    private String senderName;

    @Id
    private String id;

    @OneToOne
    private User sender;
}
