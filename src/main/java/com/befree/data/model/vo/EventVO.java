package com.befree.data.model.vo;

import com.befree.data.model.EventStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonPropertyOrder({"event_id", "event_name", "event_owner", "event_description", "event_cover", "event_location",
        "users", "event_status", "start_date", "end_date"})
@JsonIgnoreProperties({"links"})
public class EventVO extends RepresentationModel implements Serializable {

    @Mapping("id")
    @JsonProperty("event_id")
    private String eventId;
    @JsonProperty("event_owner")
    @ToString.Exclude
    @JsonIgnoreProperties({"events", "document_number"})
    private EventOwnerVO ownerVO;
    @JsonProperty("event_name")
    private String eventName;
    @JsonProperty("event_cover")
    private String eventCover;
    @JsonProperty("event_status")
    private EventStatus eventStatus;
    @JsonProperty("event_location")
    private String eventLocation;
    @JsonProperty("start_date")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss",timezone = "UTC")
    private LocalDateTime startDate;
    @JsonProperty("end_date")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", timezone = "UTC")
    private LocalDateTime endDate;
    @JsonProperty("event_description")
    private String eventDescription;
    @JsonProperty("users")
    @ToString.Exclude
    @JsonIgnoreProperties({"email","birthday", "gender","first_name", "last_name",
            "images","matches", /*--> esse matches esta dando infinito de looping pra resolver vou manter ativo*/
            "likesSended",
            "likeReceived",
            "accountNonExpired", "accountNonLocked", "credentialsNonExpired", "enabled", "username", "token", "events"})
    private List<UserVO> users;

}
