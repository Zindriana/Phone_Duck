package org.example.phoneduck.repository;

import org.example.phoneduck.model.ChatRoomModel;
import org.example.phoneduck.model.MessageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<MessageModel, Long> {

    MessageModel findById(int id);

    List<MessageModel> findAllByChatRoom(ChatRoomModel byTitle);
}
