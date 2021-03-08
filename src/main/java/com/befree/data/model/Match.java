package com.befree.data.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Matches")
public class Match implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "match_id")
        private Long id;

        @Column(name = "your_id")
        private User you;

//        @Column(name = "id_userMatched")
//        private User userMatched;
//
//        @Column("chat")
//        private Chat chat;



}
