package com.dogpamines.dogseek.common.controller;

import com.dogpamines.dogseek.common.model.dto.ChatMessageDTO;
import com.dogpamines.dogseek.common.model.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    private final ChatService chatService;

    @Autowired
    public ChatController (ChatService chatService) {
        this.chatService = chatService;
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessageDTO sendMessage(ChatMessageDTO chatMessageDTO) {
        return chatMessageDTO;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessageDTO addUser(ChatMessageDTO chatMessageDTO, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessageDTO.getSender());
        return chatMessageDTO;
    }
}
