package com.dogpamines.dogseek.board.model.dto;

import java.time.LocalDateTime;
import java.util.Date;

public class BoardChatDTO {

    private int postCode;
    private String postContext;
    private LocalDateTime postDate;
    private String postCategory;
    private String postStatus;
    private int userCode;

    public BoardChatDTO() {}

    public BoardChatDTO(int postCode, String postContext, LocalDateTime postDate, String postCategory, String postStatus, int userCode) {
        this.postCode = postCode;
        this.postContext = postContext;
        this.postDate = postDate;
        this.postCategory = postCategory;
        this.postStatus = postStatus;
        this.userCode = userCode;
    }

    public int getPostCode() {
        return postCode;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    public String getPostContext() {
        return postContext;
    }

    public void setPostContext(String postContext) {
        this.postContext = postContext;
    }

    public LocalDateTime getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDateTime postDate) {
        this.postDate = postDate;
    }

    public String getPostCategory() {
        return postCategory;
    }

    public void setPostCategory(String postCategory) {
        this.postCategory = postCategory;
    }

    public String getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(String postStatus) {
        this.postStatus = postStatus;
    }

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    @Override
    public String toString() {
        return "BoardChatDTO{" +
                "postCode=" + postCode +
                ", postContext='" + postContext + '\'' +
                ", postDate=" + postDate +
                ", postCategory='" + postCategory + '\'' +
                ", postStatus='" + postStatus + '\'' +
                ", userCode=" + userCode +
                '}';
    }
}
