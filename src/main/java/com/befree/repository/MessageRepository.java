package com.befree.repository;

import com.befree.data.model.Message;

import com.befree.data.model.MessageStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, String> {
    @Query("Select m from Message m join m.match ma where ma.id =:matchId")
    List<Message> findMessagesByMatchId(@Param("matchId") String matchId);

    @Query("Select m from Message m where m.sender.id=:senderId and m.receiver.id =:receiverId")
    List<Message> findMatchMessages(@Param("senderId") String senderId, @Param("receiverId") String receiverId);


    @Query("select m from Message m where m.sender.id =:senderId and m.receiver.id =:receiverId")
    List<Message> findMessagesSent(@Param("senderId") String senderId, @Param("receiverId")String receiverId);

    @Query("select m from Message m where m.sender.id =:receiverId and m.receiver.id =:senderId")
    List<Message> findMessagesReceived(@Param("senderId") String senderId, @Param("receiverId")String receiverId);

    @Transactional
    @Query(value = "Select m from Message m where m.sender.id=:senderId and m.receiver.id =:receiverId or m.sender.id=:receiverId and m.receiver=:senderId")
    List<Message> findAllMatchMessages(@Param("senderId") String senderId, @Param("receiverId") String receiverId);


    @Transactional
    @Modifying
    @Query("Update Message m set m.messageStatus=:status where m.id =:messageId")
    void updateMessageStatus(@Param("messageId") String messageId, @Param("status") MessageStatus status);
}
