package com.dogpamines.dogseek.common.model.dto;

public class ChatMessageDTO {

    private String roomId;
    private String userCode;
    private String userNick;
    private MessageType type;
    private String message;
    private String date;

    public enum MessageType {
        JOIN, CHAT, LEAVE
    }

    public ChatMessageDTO() {}

    public ChatMessageDTO(String roomId, String userCode, String userNick, MessageType type, String message, String date) {
        this.roomId = roomId;
        this.userCode = userCode;
        this.userNick = userNick;
        this.type = type;
        this.message = message;
        this.date = date;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ChatMessageDTO{" +
                "roomId='" + roomId + '\'' +
                ", userCode='" + userCode + '\'' +
                ", userNick='" + userNick + '\'' +
                ", type=" + type +
                ", message='" + message + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
