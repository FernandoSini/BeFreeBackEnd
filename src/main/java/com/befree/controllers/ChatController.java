package com.befree.controllers;

import com.befree.data.model.ChatMessage;
import com.befree.data.model.ChatNotification;
import com.befree.services.ChatMessageServices;
import com.befree.services.ChatRoomServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private ChatMessageServices chatMessageServices;
    @Autowired
    private ChatRoomServices chatRoomServices;


    @MessageMapping(value = "/send")
    //@MessageMapping garante que se a mensagem for enviada para o /app/chat/send o metodo process message é chamado
    public void processMessage(@Payload ChatMessage chatMessage) {
        var chatId = chatRoomServices.getChatId(chatMessage.getSenderId(), chatMessage.getReceiverId(), true);
        chatMessage.setChatId(chatId.get());

        //salvando a mensagem
        ChatMessage messageSaved = chatMessageServices.saveMessage(chatMessage);

        messagingTemplate.convertAndSendToUser(
                chatMessage.getReceiverId(),
                "/queue/messages", // "/user/{recipientId}/queue/Messages
                new ChatNotification(
                        messageSaved.getId(),
                        messageSaved.getSenderId(),
                        messageSaved.getSenderName()
                )
        );
    }

}
