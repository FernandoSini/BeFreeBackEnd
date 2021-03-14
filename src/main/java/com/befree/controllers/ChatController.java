/*
package com.befree.controllers;

import com.befree.data.model.ChatMessage;
import com.befree.data.model.ChatRoom;
import com.befree.data.model.vo.ChatNotification;
import com.befree.services.ChatMessageServices;
import com.befree.services.ChatRoomServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private ChatMessageServices chatMessageServices;
    @Autowired
    private ChatRoomServices chatRoomServices;

    @MessageMapping("/send")
    //@MessageMapping garante que se a mensagem for enviada para o /app/chat o metodo process message é chamado
    public void processMessage(@Payload ChatMessage chatMessage) {
        var chatId = chatRoomServices.getChatId(chatMessage.getSenderId(), chatMessage.getReceiverId(), true);
        chatMessage.setChatId(chatId.get());

        //salvando a mensagem
        ChatMessage messageSaved = chatMessageServices.saveMessage(chatMessage);

        messagingTemplate.convertAndSendToUser(
                chatMessage.getReceiverId(),
                "/queue/messages",
                new ChatNotification(
                        messageSaved.getId(),
                        messageSaved.getSenderId(),
                        messageSaved.getSenderName()
                )
                );
    }

}
*/
