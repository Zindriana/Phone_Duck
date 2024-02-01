package org.example.phoneduck.service;

import org.example.phoneduck.model.ChatRoomModel;
import org.example.phoneduck.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    public List<ChatRoomModel> getAllRooms(){
       return chatRepository.findAll();
    }

    public String createRoom(ChatRoomModel chatRoomModel){
        chatRepository.save(chatRoomModel);
        return "Success";
    }

    public void deleteRoom(String title){
        ChatRoomModel room = chatRepository.findByTitle(title);
        if(room.getId()!=1){
            chatRepository.delete(room);
        }
    }

    public ChatRoomModel updateRoom(String title, String newTitle){
        ChatRoomModel room = chatRepository.findByTitle(title);
        if (room.getTitle() != null) {
            room.setTitle(newTitle);
        }
        return chatRepository.save(room);
    }
}
