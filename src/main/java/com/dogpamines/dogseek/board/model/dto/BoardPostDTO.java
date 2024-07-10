package com.dogpamines.dogseek.board.model.dto;

import java.time.LocalDateTime;

public class BoardPostDTO {

    private int postCode;
    private String postTitle;
    private String postContext;
    private LocalDateTime postDate;
    private String postCategory;
    private String postStatus;
    private int userCode;
    private String userNick;

    public BoardPostDTO() {}

    public BoardPostDTO(int postCode, String postTitle, String postContext, LocalDateTime postDate, String postCategory, String postStatus, int userCode, String userNick) {
        this.postCode = postCode;
        this.postTitle = postTitle;
        this.postContext = postContext;
        this.postDate = postDate;
        this.postCategory = postCategory;
        this.postStatus = postStatus;
        this.userCode = userCode;
        this.userNick = userNick;
    }

    public int getPostCode() {
        return postCode;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
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

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }
    @Override
    public String toString() {
        return "BoardPostDTO{" +
                "postCode=" + postCode +
                ", postTitle='" + postTitle + '\'' +
                ", postContext='" + postContext + '\'' +
                ", postDate=" + postDate +
                ", postCategory='" + postCategory + '\'' +
                ", postStatus='" + postStatus + '\'' +
                ", userCode=" + userCode +
                ", userNick='" + userNick + '\'' +
                '}';
    }
}
