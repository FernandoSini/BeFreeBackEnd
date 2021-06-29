package com.befree.services;

import com.befree.adapter.custom.MessageConverter;
import com.befree.data.model.MessageStatus;
import com.befree.data.model.vo.MessageVO;
import com.befree.exceptions.ResourceNotFoundException;
import com.befree.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageServices {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private MessageConverter converter;

    public MessageVO saveMessage(MessageVO messageVO) {
        messageVO.setMessageStatus(MessageStatus.RECEIVED);
        var entity = converter.convertVotoEntity(messageVO);
        var voMessage = converter.convertEntityTOvo(messageRepository.save(entity));
        return voMessage;
    }
    //caso de ruim lembrar de alterar pra dois metodos um de buscar mensagens do sender e do receiver
    public List<MessageVO> findSentMatchMessages(String senderId, String receiverId) {
        var messages = messageRepository.findMatchMessages(senderId, receiverId);
        System.out.println(messages.toString());
        if (messages.size() > 0) {
            messages.stream().forEach(message -> updateMessageStatus(message.getId()));
        }
        var voList = messages.stream().map(message -> converter.convertEntityTOvo(message))
                .collect(Collectors.toList());
        System.out.print(voList.toString());
        return voList;
    }
    public List<MessageVO> findReceivedMatchMessages(String heHerId, String yourId) {
        var messages = messageRepository.findMatchMessages(heHerId, yourId);
        System.out.println(messages.toString());
        if (messages.size() > 0) {
            messages.stream().forEach(message -> updateMessageStatus(message.getId()));
        }
        var voList = messages.stream().map(message -> converter.convertEntityTOvo(message))
                .collect(Collectors.toList());
        System.out.print(voList.toString());
        return voList;
    }
    public List<MessageVO> getAllMatchMessages(String yourId, String heHerId) {
        var messages = messageRepository.findAllMatchMessages(heHerId, yourId);
        if (messages.size() > 0) {
            messages.stream().forEach(message -> updateMessageStatus(message.getId()));
        }
        var voList = messages.stream().map(message -> converter.convertEntityTOvo(message))
                .collect(Collectors.toList());
        return voList;
    }

    public MessageVO findMessageById(String yourId){
        var messageVO = converter.convertEntityTOvo(messageRepository.findById(yourId).orElseThrow());
        return messageVO;
    }


    public void updateMessageStatus(String messageId) {
        var message = messageRepository.findById(messageId)
                .orElseThrow(() -> new ResourceNotFoundException("Message with this id not found"));
        messageRepository.updateMessageStatus(message.getId(), MessageStatus.DELIVERED);

    }

}
