package com.befree.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Matches")
public class Match implements Serializable {

        @Id
        @GeneratedValue(generator = "uuid",strategy = GenerationType.IDENTITY)
        @GenericGenerator(name = "uuid", strategy = "uuid")
        @Column(name = "match_id")
        private String id;


        @Column(name = "your_id")
        private String yourId;

        @Column(name = "your_match_id")
        private String yourMatchId;

        @OneToOne(cascade = CascadeType.PERSIST)
        private ChatRoom matchRoom;

//        @Column(name = "id_userMatched")
//        private User userMatched;
//
//        @Column("chat")
//        private Chat chat;



}
