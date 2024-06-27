package com.dogpamines.dogseek.board.model.dto;

import java.util.Date;

public class ChatBoardDTO {

    private int commentCode;
    private String commentContext;
    private Date commentDate;
    private String commentStatus;
    private int postCode;
    private int userCode;

    public ChatBoardDTO() {}

    public ChatBoardDTO(int commentCode, String commentContext, Date commentDate, String commentStatus, int postCode, int userCode) {
        this.commentCode = commentCode;
        this.commentContext = commentContext;
        this.commentDate = commentDate;
        this.commentStatus = commentStatus;
        this.postCode = postCode;
        this.userCode = userCode;
    }

    public int getCommentCode() {
        return commentCode;
    }

    public void setCommentCode(int commentCode) {
        this.commentCode = commentCode;
    }

    public String getCommentContext() {
        return commentContext;
    }

    public void setCommentContext(String commentContext) {
        this.commentContext = commentContext;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public String getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(String commentStatus) {
        this.commentStatus = commentStatus;
    }

    public int getPostCode() {
        return postCode;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    @Override
    public String toString() {
        return "ChatBoardDTO{" +
                "commentCode=" + commentCode +
                ", commentContext='" + commentContext + '\'' +
                ", commentDate=" + commentDate +
                ", commentStatus='" + commentStatus + '\'' +
                ", postCode=" + postCode +
                ", userCode=" + userCode +
                '}';
    }
}
