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
@Table(name = "message")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Message implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "message_id")
    private String id;
    @ManyToOne
    private User sender;
    @ManyToOne
    private User receiver;
    @ManyToOne
    @JoinColumn(name = "match_id",referencedColumnName = "match_id")
    private Match match;
    @Column(name = "content")
    private String content;
    @Column(name="timestamp")
    private LocalDateTime timestamp;
    private MessageStatus messageStatus;

}
