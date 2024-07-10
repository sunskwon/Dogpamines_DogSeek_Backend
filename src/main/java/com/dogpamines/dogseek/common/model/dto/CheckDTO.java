package com.dogpamines.dogseek.common.model.dto;

public class CheckDTO {

    private int userCode;
    private String userNick;
    private Boolean status;

    public CheckDTO() {}

    public CheckDTO(int userCode, String userNick, Boolean status) {
        this.userCode = userCode;
        this.userNick = userNick;
        this.status = status;
    }

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CheckDTO{" +
                "userCode=" + userCode +
                ", userNick='" + userNick + '\'' +
                ", status=" + status +
                '}';
    }
}
