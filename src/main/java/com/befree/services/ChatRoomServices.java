package com.befree.services;

import com.befree.data.model.ChatRoom;
import com.befree.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChatRoomServices {

    @Autowired
    private ChatRoomRepository repository;

    public Optional<String> getChatId(
            String senderId, String receiverId, boolean createIfNotExists) {
        return repository.findBySenderIdAndReceiverId(senderId, receiverId)
                .map(ChatRoom::getChatId)
                .or(() -> {
                    if (!createIfNotExists) {
                        return Optional.empty();
                    }
                    var chatId = String.format("%s_%s", senderId, receiverId);
                    ChatRoom senderRecipient = ChatRoom.builder().
                            chatId(chatId)
                            .senderId(senderId)
                            .receiverId(receiverId)
                            .build();
                    ChatRoom recipientSender = ChatRoom.builder().
                            chatId(chatId)
                            .senderId(receiverId)
                            .receiverId(senderId)
                            .build();

                    repository.saveAndFlush(senderRecipient);
                    repository.saveAndFlush(recipientSender);
                    return Optional.of(chatId);

                });
    }

}
