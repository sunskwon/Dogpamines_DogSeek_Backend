package com.dogpamines.dogseek.common.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@Schema(description = "채팅 정보 DTO")
public class ChatMessageDTO {
    @Schema(description = "채팅방 아이디(PK)")
    private String roomId;
    @Schema(description = "회원 코드(FK)")
    private String userCode;
    @Schema(description = "회원 닉네임(FK)")
    private String userNick;
    @Schema(description = "메세지 종류:'입장/메세지/떠나기'")
    private MessageType type;
    @Schema(description = "메세지 내용")
    private String message;
    @Schema(description = "메세지 전송 날짜,시간")
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
