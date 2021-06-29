package com.befree.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Matches")
public class Match implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(generator = "uuid", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "match_id")
    private String id;


    @ManyToOne(targetEntity = User.class)
    private User you;


    @ManyToOne(targetEntity = User.class)
    private User hisHer;


    @OneToMany(mappedBy = "match", orphanRemoval = true)
    private List<Message> messages;


}
