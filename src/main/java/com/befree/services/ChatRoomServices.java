package com.befree.services;

import com.befree.adapter.DozerConverter;
import com.befree.data.model.ChatRoom;
import com.befree.data.model.ChatRoomVO;
import com.befree.data.model.vo.MatchVO;
import com.befree.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChatRoomServices {

    @Autowired
    private ChatRoomRepository repository;
    @Autowired
    private MatchServices matchServices;

    //original sem VO
//    public Optional<String> getChatId(
//            String senderId, String receiverId, boolean createIfNotExists) {
//        return repository.findBySenderIdAndReceiverId(senderId, receiverId)
//                .map(ChatRoom::getChatId)
//                .or(() -> {
//                    if (!createIfNotExists) {
//                        return Optional.empty();
//                    }
//                    var chatId = String.format("%s_%s", senderId, receiverId);
//                    ChatRoom senderRecipient = ChatRoom.builder().
//                            chatId(chatId)
//                            .senderId(senderId)
//                            .receiverId(receiverId)
//                            .build();
//                    ChatRoom recipientSender = ChatRoom.builder().
//                            chatId(chatId)
//                            .senderId(receiverId)
//                            .receiverId(senderId)
//                            .build();
//
//                    repository.saveAndFlush(senderRecipient);
//                    repository.saveAndFlush(recipientSender);
//                    return Optional.of(chatId);
//
//                });
//    }

    public ChatRoomVO createChatRoom(ChatRoomVO room){
        var entityChatRoom = DozerConverter.parseObject(room, ChatRoom.class);
        var vo = DozerConverter.parseObject(repository.saveAndFlush(entityChatRoom), ChatRoomVO.class);
        return vo;
    }
    public Optional<String> getChatId(
            String senderId, String receiverId, boolean createIfNotExists) {
        return repository.findBySenderIdAndReceiverId(senderId, receiverId)
                .map(ChatRoom::getChatId)
                .or(() -> {
                            if (!createIfNotExists) {
                                return Optional.empty();
                            }

                            var chatId = String.format("%s_%s", senderId, receiverId);
                            //criando a sala de chat para o usuário que vai enviar a mensagem e quem vai receber a mensagem

                            ChatRoomVO senderRecipientVO = ChatRoomVO.builder().
                                    chatId(chatId)
                                    .senderId(senderId)
                                    .receiverId(receiverId)

                                    .build();
                            ChatRoomVO recipientSenderVO = ChatRoomVO.builder().
                                    chatId(chatId)
                                    .senderId(receiverId)
                                    .receiverId(senderId)
                                    .build();
                            //convertendo o VO para entitade;
                            var senderRecipientEntity = DozerConverter.parseObject(senderRecipientVO, ChatRoom.class);
                            var recipientSenderEntity = DozerConverter.parseObject(recipientSenderVO, ChatRoom.class);

                            //salvando as entidades
//                            repository.saveAndFlush(senderRecipientEntity);
//                            repository.saveAndFlush(recipientSenderEntity);
                    createChatRoom(senderRecipientVO);
                    createChatRoom(senderRecipientVO);
                            return Optional.of(chatId);

                        }
                );
    }


    public ChatRoomVO getChatRoom(String senderId, String receiverId) {
        Optional<MatchVO> matchVO = Optional.ofNullable(matchServices.getMatchToChatRoom(senderId, receiverId));


            var chatId = String.format("%s_%s", senderId, receiverId);
            ChatRoomVO senderRecipientVO = new ChatRoomVO();
            senderRecipientVO.setSenderId(senderId);
            senderRecipientVO.setChatId(chatId);
            senderRecipientVO.setReceiverId(receiverId);
            senderRecipientVO.setMatchVO(matchVO.get());
            ChatRoomVO recipientSenderVO = new ChatRoomVO();
            recipientSenderVO.setSenderId(receiverId);
            senderRecipientVO.setChatId(chatId);
            senderRecipientVO.setMatchVO(matchVO.get());
            var senderRecipientEntity = DozerConverter.parseObject(senderRecipientVO, ChatRoom.class);
            var recipientSenderEntity = DozerConverter.parseObject(recipientSenderVO, ChatRoom.class);
            var dataSender = DozerConverter.parseObject(repository.save(senderRecipientEntity), ChatRoomVO.class);
            var dataReceiver = DozerConverter.parseObject(repository.save(recipientSenderEntity), ChatRoomVO.class);

            return dataSender;
        }



}
