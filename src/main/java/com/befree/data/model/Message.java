package com.befree.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;


@Entity
@Table(name = "Messages")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Message implements Serializable {
    //recomendacao do sensato tirar o chatroom
    @Id
    @GeneratedValue(generator = "uuid", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "message_id")
    private String id;
//    private String chatId;
    @ManyToOne
    private User sender;
    @ManyToOne
    private User receiver;
    @ManyToOne
    private Match match;
    @Column(name = "content")
    private String content;
    private LocalDateTime timestamp;
    private MessageStatus messageStatus;

}
