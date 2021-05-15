package com.befree.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
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
    @JoinColumn(name = "event_owner_id",referencedColumnName = "event_owner_id")
    private EventOwner owner;
    @Column(name = "event_name")
    private String eventName;
    @Column(name = "event_cover")
    private String eventCover;
    @Enumerated(EnumType.ORDINAL)
    private EventStatus eventStatus;
    @Column(name = "event_location")
    private String eventLocation;
    @Column(name = "start_date")
    private LocalDateTime startDate;
    @Column(name = "end_date")
    private LocalDateTime endDate;
    @Column(name="event_description")
    private String eventDescription;
    @ManyToMany
//    @JsonIgnoreProperties({"likesSended","likeReceived","accountNonLocked",
//            "accountNonExpired","credentialsNonExpired", "enabled","username", "token","images"})
    @JoinTable(name = "events_users", joinColumns = {@JoinColumn(name = "event_id", referencedColumnName = "event_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")})
    private List<User> users;



}
