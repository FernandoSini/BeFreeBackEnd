package com.befree.controllers;

import com.befree.data.model.vo.MessageVO;
import com.befree.services.MatchServices;
import com.befree.services.MessageServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@RequestMapping("/api/chat")
@Controller
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
        var matchId = String.format("%s_%s", messageVO.getSender().getId(),
                messageVO.getReceiver().getId());
        messageVO.setMatch(matchVo);
        messageVO.setContent(messageVO.getContent());
//      messageVO.setTimestamp(Instant.now().atZone(ZoneId.of("America/Sao_Paulo")).toLocalDateTime());
        messageVO.setTimestamp(messageVO.getTimestamp());
        messageServices.saveMessage(messageVO);
        messagingTemplate.convertAndSendToUser(
                messageVO.getReceiver().getId(),
                "/queue/messages", // "/user/{recipientId}/queue/Messages
                messageVO
        );
        System.out.print(messagingTemplate.getUserDestinationPrefix());
        System.out.println(messagingTemplate.getDefaultDestination());
        System.out.println(messagingTemplate.getMessageChannel());
        System.out.println(messagingTemplate.getHeaderInitializer());
        System.out.println(messagingTemplate.getMessageConverter());
        System.out.println(messagingTemplate.toString());
        System.out.println("atingido");
    }

    @GetMapping(value="/messages/sent/{senderId}/{receiverId}",
            produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<List<MessageVO>>getSentMessages(@PathVariable("senderId") String senderId,
                                                          @PathVariable("receiverId")String receiverId){
        List<MessageVO> messages = messageServices.findSentMatchMessages(senderId, receiverId);
        return ResponseEntity.ok().body(messages);
    }
    @GetMapping(value="/messages/received/{heHerId}/{yourId}",
            produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<List<MessageVO>>getReceivedMessages(@PathVariable("heHerId") String heHerId,
                                                          @PathVariable("yourId")String yourId){
        List<MessageVO> messages = messageServices.findReceivedMatchMessages(heHerId, yourId);
        return ResponseEntity.ok().body(messages);
    }
    @GetMapping(value="/messages/all/by/{yourId}/{heHerId}",
            produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<List<MessageVO>>getAllMessagesMatch(@PathVariable("yourId") String yourId,
                                                          @PathVariable("heHerId")String heHerId){
        List<MessageVO> messages = messageServices.findAllChatMessages(yourId, heHerId);
        return ResponseEntity.ok().body(messages);
    }
    @GetMapping(value="/message/{yourId}",produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<MessageVO> getMessageById(@PathVariable("yourId") String yourId){
       MessageVO messages = messageServices.findMessageById(yourId);

        return ResponseEntity.ok().body(messages);
    }
}
