package org.example.phoneduck.controller;

import org.example.phoneduck.model.ChatRoomModel;
import org.example.phoneduck.model.MessageModel;
import org.example.phoneduck.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/phoneduck")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @GetMapping
    public List<ChatRoomModel> chatRoomList(){
        return chatService.getAllRooms();
    }

    @GetMapping("/{title}")
    public List<MessageModel> getMessages(@PathVariable String title){
        return chatService.getAllMessages(title);
    }

    @PostMapping
    public void addNewChatRoom(@RequestBody ChatRoomModel chatRoomModel){
        chatService.createRoom(chatRoomModel);
    }

    @DeleteMapping("/{title}")
    public void deleteChatRoom(@PathVariable String title){
        chatService.deleteRoom(title);
    }

    @DeleteMapping("/{title}/{id}")
    public void deleteMessage(@PathVariable int id){
        chatService.deleteMessage(id);
    }

    @PatchMapping("/{title}")
    public void updateChatRoom(@PathVariable String title, @RequestBody ChatRoomModel newChatRoom){
        chatService.updateRoom(title, newChatRoom.getTitle());
    }

    @PatchMapping("/{title}/{id}")
    public void updateMessage(@PathVariable String title, @PathVariable int id, @RequestBody MessageModel newMessage){
        chatService.updateMessage(title, id, newMessage);
    }


    @PutMapping("/{title}")
    public ResponseEntity<Object> createMessage(@PathVariable String title, @RequestBody MessageModel message){
        ChatRoomModel chatRoom = chatService.findRoomByTitle(title);
        if(chatRoom != null) {
            message.setChatRoom(chatRoom);
            chatService.saveMessage(message);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
