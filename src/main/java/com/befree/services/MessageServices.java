package com.befree.services;

import com.befree.adapter.DozerConverter;
import com.befree.data.model.Message;
import com.befree.data.model.MessageStatus;
import com.befree.data.model.vo.MessageVO;
import com.befree.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServices {
    @Autowired
    private MessageRepository messageRepository;
//    @Autowired
//    private ChatRoomServices chatRoomServices;


    public MessageVO saveMessage(MessageVO messageVO){
        messageVO.setMessageStatus(MessageStatus.RECEIVED);
        var entity = DozerConverter.parseObject(messageVO, Message.class);
        var voMessage = DozerConverter.parseObject(messageRepository.save(entity),MessageVO.class);
        return voMessage;
    }
}
