package com.befree.services;

import com.befree.data.model.ChatMessage;
import com.befree.data.model.MessageStatus;
import com.befree.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatMessageServices {
    @Autowired
    private ChatMessageRepository chatMessageRepository;
//    @Autowired
//    private ChatRoomServices chatRoomServices;


    public ChatMessage saveMessage(ChatMessage chatMessage){
        chatMessage.setMessageStatus(MessageStatus.RECEIVED);
        chatMessageRepository.save(chatMessage);
        return chatMessage;
    }
}
