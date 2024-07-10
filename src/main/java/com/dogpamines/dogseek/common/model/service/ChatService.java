package com.dogpamines.dogseek.common.model.service;

import com.dogpamines.dogseek.common.model.dto.ChatMessageDTO;
import com.dogpamines.dogseek.common.model.dto.CheckDTO;
import com.sun.tools.jconsole.JConsoleContext;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ChatService {

    private Map<String, Queue<ChatMessageDTO>> tempMessages;
    private Map<String, CheckDTO> checkList;

    public ChatService() {
        this.tempMessages = new HashMap<>();
        this.checkList = new HashMap<>();
    }

    public List<ChatMessageDTO> callPrevMessage(String roomId) {

        List<ChatMessageDTO> lastMessages = new ArrayList<>();

        try {

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

        if (roomId == "/topic/public") {

            try {

                if (tempMessages.containsKey(roomId)) {

                    Queue<ChatMessageDTO> tempMessage = tempMessages.get(roomId);

                    if (tempMessage.size() > 50) {

                        tempMessage.remove();
                        tempMessage.add(chatMessage);

                        tempMessages.put(roomId, tempMessage);
                    } else {

                        tempMessage.add(chatMessage);

                        tempMessages.put(roomId, tempMessage);
                    }

                } else {

                    Queue<ChatMessageDTO> tempMessage = new LinkedList<>();
                    tempMessage.add(chatMessage);

                    tempMessages.put(roomId, tempMessage);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {

            return;
        }
    }

    public void savePrivateMessage(int code, ChatMessageDTO chatMessage) {

        String roomId = chatMessage.getRoomId();
        int userCode = Integer.parseInt(chatMessage.getUserCode());
        String userNick = chatMessage.getUserNick();

        try {

            if (code == userCode) {

                Set<String> keyList = checkList.keySet();

                if (keyList.contains(roomId)) {

                    CheckDTO temp = checkList.get(roomId);
                    temp.setStatus(true);

                    checkList.put(roomId, temp);
                } else {

                    checkList.put(roomId, new CheckDTO(userCode, userNick, true));
                }
            }

            if (tempMessages.containsKey(roomId)) {

                Queue<ChatMessageDTO> tempMessage = tempMessages.get(roomId);

                tempMessage.add((chatMessage));

                tempMessages.put(roomId, tempMessage);
            } else {

                Queue<ChatMessageDTO> tempMessage = new LinkedList<>();
                tempMessage.add(chatMessage);

                tempMessages.put(roomId, tempMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object callCheckList() {

        Set<String> keyList = checkList.keySet();

        List<CheckDTO> list = new ArrayList<>();

        for (String key : keyList) {

            list.add(checkList.get(key));
        }

        return list;
    }

    public void adminLeave(ChatMessageDTO chatMessage) {

            String roomId = chatMessage.getRoomId();

            CheckDTO temp = checkList.get(roomId);
            temp.setStatus(false);

            checkList.put(roomId, temp);
    }
}
