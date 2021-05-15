package com.befree.controllers;

import com.befree.data.model.vo.ChatNotificationVO;
import com.befree.data.model.vo.MatchVO;
import com.befree.data.model.vo.MessageVO;
import com.befree.services.MatchServices;
import com.befree.services.MessageServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
public class MessageController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private MessageServices messageServices;
    @Autowired
    private MatchServices matchServices;

    @MessageMapping(value = "/send")
    //@MessageMapping garante que se a mensagem for enviada
    // para /api/chat/app/send o metodo process message é chamado
    public void sendMessage(@Payload MessageVO messageVO) {
//      var chatId = chatRoomServices.getChatId(message.getSender().getId(), message.getReceiver().getId(), true);
//        var chatRoomVO = chatRoomServices.getChatRoom(messageVO.getSender(),messageVO.getReceiver());
////        message.setChatId(chatId.get());
//        messageVO.setChatRoom(chatRoomVO);
//     MatchVO match = messageServices.saveMessage(messageVO);
        //salvando a mensagem
        var matchVo = matchServices
                .getMatchByMessageSenderAndReceiver(
                        messageVO.getSender().getId(),
                        messageVO.getReceiver().getId()
                );
        messageVO.setMatch(matchVo);
        messageServices.saveMessage(messageVO);
        messagingTemplate.convertAndSendToUser(
                messageVO.getSender().getId(),
                "/queue/messages", // "/user/{recipientId}/queue/Messages
                messageVO.getReceiver().getId()

        );

    }

}
