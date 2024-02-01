package org.example.phoneduck.controller;

import org.example.phoneduck.model.ChatRoomModel;
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
}
