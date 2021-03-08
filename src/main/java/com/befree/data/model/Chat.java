package com.befree.data.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Chat")
public class Chat implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name="id_chat")
    private String idChat;

//    @Column(name = "userSendingMessage")
//    private User userSendingMessage;
//
//    @Column(name = "userReceivingMessage")
//    private User userReceivingMessage;

}
