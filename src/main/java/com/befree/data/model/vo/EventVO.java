package com.befree.data.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonPropertyOrder({"event_id", "event_name", "event_owner","event_cover", "users"})
public class EventVO extends RepresentationModel implements Serializable {

    @Mapping("id")
    @JsonProperty("event_id")
    private String eventId;
    @JsonProperty("event_owner")
    @JsonIgnoreProperties({"events","document_number"})
    private EventOwnerVO ownerVO;
    @JsonProperty("event_name")
    private String eventName;
    @JsonProperty("event_cover")
    private String eventCover;
    @JsonProperty("users")
    private List<UserVO> users;






}
