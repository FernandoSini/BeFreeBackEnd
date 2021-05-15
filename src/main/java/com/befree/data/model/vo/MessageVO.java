package com.befree.data.model.vo;

import com.befree.data.model.MessageStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dozermapper.core.Mapping;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MessageVO extends RepresentationModel implements Serializable {
    @Mapping("id")
    @JsonProperty("message_id")
    private String id;
//    private String chatId;
    @JsonProperty("sender")
    private UserVO sender;
    @JsonProperty("receiver")
    private UserVO receiver;
    @JsonProperty("match")
    private MatchVO match;
    private String content;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime timestamp;
    private MessageStatus messageStatus;
}
