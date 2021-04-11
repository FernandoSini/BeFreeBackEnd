package com.befree.data.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dozermapper.core.Mapping;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EventVO implements Serializable {

    @Mapping("id")
    @JsonProperty(value = "event_id")
    private String eventId;
    private EventOwnerVO ownerVO;
    private List<UserVO> users;
}
