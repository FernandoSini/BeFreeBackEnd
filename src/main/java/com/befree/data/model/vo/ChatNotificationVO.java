package com.befree.data.model.vo;


import com.befree.data.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dozermapper.core.Mapping;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatNotificationVO extends RepresentationModel implements Serializable {
    //antigo
//    @Id
//    private String id;
////    private String senderId;
////    private String senderName;

    @Mapping("id")
    @JsonProperty("notification_id")
    private String id;

    @JsonProperty("sender")
    private UserVO sender;
}
