package com.befree.adapter.custom;

import com.befree.adapter.DozerConverter;
import com.befree.data.model.Match;
import com.befree.data.model.Message;
import com.befree.data.model.User;
import com.befree.data.model.vo.MatchVO;
import com.befree.data.model.vo.MessageVO;
import com.befree.data.model.vo.UserVO;
import org.springframework.stereotype.Service;

@Service
public class MessageConverter {


    public MessageVO convertEntityTOvo(Message message){
        MessageVO vo = new MessageVO();
        vo.setId(message.getId());
        vo.setContent(message.getContent());
        vo.setMatch(DozerConverter.parseObject(message.getMatch(), MatchVO.class));
        vo.setSender(DozerConverter.parseObject(message.getSender(), UserVO.class));
        vo.setReceiver(DozerConverter.parseObject(message.getReceiver(), UserVO.class));
        vo.setMessageStatus(message.getMessageStatus());
        vo.setTimestamp(message.getTimestamp());
        return vo;
    }

    public Message convertVotoEntity(MessageVO messageVO){
        Message entity = new Message();
        entity.setId(messageVO.getId());
        entity.setContent(messageVO.getContent());
        entity.setMatch(DozerConverter.parseObject(messageVO.getMatch(), Match.class));
        entity.setSender(DozerConverter.parseObject(messageVO.getSender(), User.class));
        entity.setReceiver(DozerConverter.parseObject(messageVO.getReceiver(), User.class));
        entity.setMessageStatus(messageVO.getMessageStatus());
        entity.setTimestamp(messageVO.getTimestamp());
        return entity;
    }
}
