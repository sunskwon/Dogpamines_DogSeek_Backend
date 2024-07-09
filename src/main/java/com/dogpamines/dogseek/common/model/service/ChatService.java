package com.dogpamines.dogseek.common.model.service;

import com.dogpamines.dogseek.common.model.dto.ChatMessageDTO;
import com.sun.tools.jconsole.JConsoleContext;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ChatService {

    private Map<String, Queue<ChatMessageDTO>> tempMessages;

    public ChatService() {
        this.tempMessages = new HashMap<>();
    }

    public List<ChatMessageDTO> callPrevMessage(String roomId) {

        List<ChatMessageDTO> lastMessages = new ArrayList<>();

        try {

            System.out.println(tempMessages.containsKey(roomId));

            if (tempMessages.containsKey(roomId)) {

                Queue<ChatMessageDTO> tempMessage = tempMessages.get(roomId);

                lastMessages = tempMessage.stream().toList();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lastMessages;
    }

    public void savePrevMessage(ChatMessageDTO chatMessage) {

        String roomId = chatMessage.getRoomId();

        try {

            if (tempMessages.containsKey(roomId)) {

                Queue<ChatMessageDTO> tempMessage = tempMessages.get(roomId);

                if (tempMessage.size() > 30) {

                    tempMessage.remove();
                    tempMessage.add(chatMessage);

                    System.out.println("tempMessage = " + tempMessage);

                    tempMessages.put(roomId, tempMessage);
                } else {

                    tempMessage.add(chatMessage);

                    System.out.println("tempMessage = " + tempMessage);

                    tempMessages.put(roomId, tempMessage);
                }

            } else {

                Queue<ChatMessageDTO> tempMessage = new LinkedList<>();
                tempMessage.add(chatMessage);

                System.out.println("tempMessage = " + tempMessage);

                tempMessages.put(roomId, tempMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
