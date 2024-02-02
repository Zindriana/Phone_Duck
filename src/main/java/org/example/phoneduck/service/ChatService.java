package org.example.phoneduck.service;

import org.example.phoneduck.model.ChatRoomModel;
import org.example.phoneduck.model.MessageModel;
import org.example.phoneduck.repository.ChatRepository;
import org.example.phoneduck.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;
    private MessageRepository messageRepository;

    public List<ChatRoomModel> getAllRooms(){
       return chatRepository.findAll();
    }

    public ChatRoomModel getIdByTitle(String title){
        return chatRepository.findByTitle(title);
    }

    public String createRoom(ChatRoomModel chatRoomModel){
        if (chatRepository.findByTitle(chatRoomModel.getTitle()) == null) {
            chatRepository.save(chatRoomModel);
            return "Success";
        }
        return "Failure";
    }


    public void deleteRoom(String title){
        ChatRoomModel room = chatRepository.findByTitle(title);
        if(room.getId()!=1){
            chatRepository.delete(room);
        }
    }

    public ChatRoomModel updateRoom(String title, String newTitle){
        ChatRoomModel room = chatRepository.findByTitle(title);
        if (room.getTitle() != null && room.getId()!=1) {
            room.setTitle(newTitle);
        }
        return chatRepository.save(room);
    }

    public MessageModel createMessage(String title, MessageModel messageModel){
        MessageModel message = messageModel;
        ChatRoomModel room = chatRepository.findByTitle(title);
        message.setRoomId(room.getId());
        return messageRepository.save(message);
    }
}
