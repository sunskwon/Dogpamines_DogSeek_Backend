package com.dogpamines.dogseek.common.model.dto;

public class ChatMessageDTO {

    private String content;
    private String sender;
    private MessageType type;

    public enum MessageType {
        CHAT, JOIN, LEAVE
    }

    public ChatMessageDTO() {}

    public ChatMessageDTO(String content, String sender, MessageType type) {
        this.content = content;
        this.sender = sender;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ChatMessageDTO{" +
                "content='" + content + '\'' +
                ", sender='" + sender + '\'' +
                ", type=" + type +
                '}';
    }
}
