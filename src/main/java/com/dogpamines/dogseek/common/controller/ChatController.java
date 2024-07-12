package com.dogpamines.dogseek.common.controller;

import com.dogpamines.dogseek.common.model.dto.ChatMessageDTO;
import com.dogpamines.dogseek.common.model.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.dogpamines.dogseek.common.model.dto.ChatMessageDTO.MessageType.JOIN;

@RestController
public class ChatController {

    private final ChatService chatService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public ChatController(ChatService chatService, SimpMessagingTemplate simpMessagingTemplate) {
        this.chatService = chatService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @GetMapping("/chat/prev")
    public ResponseEntity<Map<String, Object>> callPrevMessage(@RequestParam String roomId) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Map<String, Object> result = new HashMap<>();
        result.put("prevs", chatService.callPrevMessage(roomId));

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

    @GetMapping("/chat/check")
    public ResponseEntity<Map<String, Object>> callCheckList() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Map<String, Object> result = new HashMap<>();
        result.put("check", chatService.callCheckList());

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessageDTO sendMessage(ChatMessageDTO chatMessage) {

        chatService.savePrevMessage(chatMessage);

        return chatMessage;
    }

    @MessageMapping("/chat.sendMessage/room/{userCode}")
    @SendTo("/topic/room/{userCode}")
    public ChatMessageDTO sendPrivateMessage(@DestinationVariable int userCode, ChatMessageDTO chatMessage) {

        int code = userCode;

        chatService.savePrivateMessage(code, chatMessage);

        return chatMessage;
    }

    @PostMapping("/chat/comeandgo")
    public void comeAndLeaveMessage(@RequestBody ChatMessageDTO chatMessage) {

        System.out.println("comeandleave");
        System.out.println("chatMessage = " + chatMessage);

        if (chatMessage.getUserNick() != null) {

            simpMessagingTemplate.convertAndSend(chatMessage.getRoomId(), chatMessage);
        }
    }

    @PostMapping("/chat/adminleave")
    public void adminLeaveMessage(@RequestBody ChatMessageDTO chatMessage) {

        if (chatMessage.getUserNick() != null) {

            System.out.println("adminLeaveMessage");
            System.out.println("chatMessage = " + chatMessage);

            chatService.adminLeave(chatMessage);

            simpMessagingTemplate.convertAndSend(chatMessage.getRoomId(), chatMessage);
        }
    }
}

