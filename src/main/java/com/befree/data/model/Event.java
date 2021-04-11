package com.befree.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Events")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Event implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid",strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "event_id")
    private String id;

    @ManyToOne
    private EventOwner owner;

    @ManyToMany
    private List<User> users;
    @Transient
    private String token;

}
