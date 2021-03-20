package com.befree.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

        /**
         *
         */
        private static final long serialVersionUID = 1L;


        @Id
        @GeneratedValue(generator = "uuid",strategy = GenerationType.IDENTITY)
        @GenericGenerator(name = "uuid", strategy = "uuid")
        @Column(name = "match_id")
        private String id;


        @ManyToOne(targetEntity = User.class)
        @JsonIgnoreProperties({"likesSended","likeReceived","accountNonLocked",
                "accountNonExpired","credentialsNonExpired", "enabled","username"})
        private User you;

        @Column(name = "hisHer_id")
        private String hisHerId;

        @OneToOne(cascade = CascadeType.PERSIST, targetEntity = ChatRoom.class)
        @JoinColumn(name="chat_room_id")
        private ChatRoom matchRoom;

//        @Column(name = "id_userMatched")
//        private User userMatched;
//
//        @Column("chat")
//        private Chat chat;



}
