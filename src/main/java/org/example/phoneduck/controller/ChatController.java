package org.example.phoneduck.controller;

import org.example.phoneduck.model.ChatRoomModel;
import org.example.phoneduck.model.MessageModel;
import org.example.phoneduck.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping
    public void addNewChatRoom(@RequestBody ChatRoomModel chatRoomModel){
        chatService.createRoom(chatRoomModel);
    }

    @DeleteMapping("/{title}")
    public void deleteChatRoom(@PathVariable String title){
        chatService.deleteRoom(title);
    }

    @PatchMapping("/{title}")
    public void updateChatRoom(@PathVariable String title, @RequestBody ChatRoomModel newChatRoom){
        chatService.updateRoom(title, newChatRoom.getTitle());
    }

    @PutMapping("/{title}")
    public void createMessage(@PathVariable String title, @RequestBody MessageModel message){
        MessageModel newMessage = chatService.createMessage(title, message);


    }
}
