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

    @ManyToOne(targetEntity = EventOwner.class)
    @JoinColumn(name = "event_owner_id")
    private EventOwner owner;

    @Column(name = "event_name")
    private String eventName;
    @Column(name = "event_cover")
    private String eventCover;

    @ManyToMany(mappedBy = "events")
    private List<User> users;



}
