package org.example.phoneduck.service;

import org.example.phoneduck.model.ChatRoomModel;
import org.example.phoneduck.model.MessageModel;
import org.example.phoneduck.repository.ChatRepository;
import org.example.phoneduck.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private MessageRepository messageRepository;

    public List<ChatRoomModel> getAllRooms() {
       return chatRepository.findAll();
    }

    public ChatRoomModel findRoomByTitle(String title){
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

    public void deleteMessage(int id){
        MessageModel message = messageRepository.findById(id);
        messageRepository.delete(message);
    }

    public ChatRoomModel updateRoom(String title, String newTitle){
        ChatRoomModel room = chatRepository.findByTitle(title);
        if (room.getTitle() != null && room.getId()!=1) {
            room.setTitle(newTitle);
        }
        return chatRepository.save(room);
    }

    public MessageModel updateMessage(String title, int id, MessageModel newMessage){
        MessageModel message = messageRepository.findById(id);
        ChatRoomModel room = chatRepository.findByTitle(title);
        newMessage.setId((long) id);
        newMessage.setChatRoom(room);
        if (room != null && message != null){
            message = newMessage;
        }
        return messageRepository.save(message);
    }
    public MessageModel saveMessage(MessageModel message){
         return messageRepository.save(message);
    }

    /*public List<MessageModel> getAllMessages(String title){
        ChatRoomModel room = chatRepository.findByTitle(title);
        List<MessageModel> messages = (List<MessageModel>) messageRepository.findByChatRoom(room);
        return messages;
    }*/
    public List<MessageModel> getAllMessages(String title) {
        ChatRoomModel room = chatRepository.findByTitle(title);
        return messageRepository.findAllByChatRoom(room);
    }
}
