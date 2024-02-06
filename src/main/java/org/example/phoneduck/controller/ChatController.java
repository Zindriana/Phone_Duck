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
@RequestMapping("/api/v1/phoneduck/channels")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @GetMapping
    public List<String> chatRoomList(){
        return chatService.getAllRooms();
    }

    @GetMapping("/{title}")
    public List<MessageModel> getMessages(@PathVariable String title){
        return chatService.getAllMessages(title);

    }

    @PostMapping
    public ResponseEntity<Object> addNewChatRoom(@RequestBody ChatRoomModel chatRoomModel){
        if (chatRoomModel.getTitle() != null) {
            return chatService.createRoom(chatRoomModel);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{title}")
    public ResponseEntity<Object> deleteChatRoom(@PathVariable String title){
        return chatService.deleteRoom(title);
    }

    @DeleteMapping("/{title}/{id}")
    public ResponseEntity<Object> deleteMessage(@PathVariable String title, @PathVariable int id){
        return chatService.deleteMessage(id);
    }

    @PatchMapping("/{title}")
    public ResponseEntity<Object> updateChatRoom(@PathVariable String title, @RequestBody ChatRoomModel newChatRoom){
        return chatService.updateRoom(title, newChatRoom.getTitle());
    }

    @PatchMapping("/{title}/{id}")
    public ResponseEntity<Object> updateMessage(@PathVariable String title, @PathVariable int id, @RequestBody MessageModel newMessage){
        return chatService.updateMessage(title, id, newMessage);
    }


    @PutMapping("/{title}")
    public ResponseEntity<Object> createMessage(@PathVariable String title, @RequestBody MessageModel message){
        ChatRoomModel chatRoom = chatService.findRoomByTitle(title);
        if(chatRoom != null && message != null) {
            message.setChatRoom(chatRoom);
            return chatService.saveMessage(message);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
