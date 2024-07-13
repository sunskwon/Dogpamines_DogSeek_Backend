package com.dogpamines.dogseek.common.controller;

import com.dogpamines.dogseek.common.model.dto.ChatMessageDTO;
import com.dogpamines.dogseek.common.model.service.ChatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "Chat(채팅) Controller")
@RestController
public class ChatController {

    private final ChatService chatService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public ChatController(ChatService chatService, SimpMessagingTemplate simpMessagingTemplate) {
        this.chatService = chatService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }
  
    @Operation(summary = "이전 채팅 내용 조회", description = "사용자는 해당 채팅방의 이전 채팅 내용을 조회할 수 있다.")
    @GetMapping("/chat/prev")
    public ResponseEntity<Map<String, Object>> callPrevMessage(@RequestParam String roomId) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Map<String, Object> result = new HashMap<>();
        result.put("prevs", chatService.callPrevMessage(roomId));

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }
  
    @Operation(summary = "관리자 1:1 문의 조회", description = "관리자는 1:1 실시간 문의 조회를 할 수 있다(조회 여부 확인 가능).")
    @GetMapping("/chat/check")
    public ResponseEntity<Map<String, Object>> callCheckList() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Map<String, Object> result = new HashMap<>();
        result.put("check", chatService.callCheckList());

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }
  
    @Operation(summary = "다중 채팅방 메세지 전송", description = "다중 채팅방의 메세지 전송을 위해 사용된다.")
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessageDTO sendMessage(ChatMessageDTO chatMessage) {

        chatService.savePrevMessage(chatMessage);

        return chatMessage;
    }
  
    @Operation(summary = "1:1 관리자 문의방 메세지 전송", description = "관리자와의 1:1 문의 메세지 전송을 위해 사용된다.")
    @MessageMapping("/chat.sendMessage/room/{userCode}")
    @SendTo("/topic/room/{userCode}")
    public ChatMessageDTO sendPrivateMessage(@DestinationVariable int userCode, ChatMessageDTO chatMessage) {

        int code = userCode;

        chatService.savePrivateMessage(code, chatMessage);

        return chatMessage;
    }

    @Operation(summary = "채팅방 출입", description = "채팅방 출입시 안내로 사용된다.")
    @PostMapping("/chat/comeandgo")
    public void comeAndLeaveMessage(@RequestBody ChatMessageDTO chatMessage) {

        System.out.println("comeandleave");
        System.out.println("chatMessage = " + chatMessage);

        if (chatMessage.getUserNick() != null) {

            simpMessagingTemplate.convertAndSend(chatMessage.getRoomId(), chatMessage);
        }
    }

    @Operation(summary = "관리자 채팅방 나가기", description = "관리자가 채팅방을 나가면 채팅방을 확인했다고 처리된다.")
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

