package com.dogpamines.dogseek.board.model.dto;

import java.sql.Date;

public class CommentDTO {

    private int commentCode;
    private String commentContext;
    private String commentDate;
    private String commentStatus;
    private int postCode;
    private int userCode;
    private String userNick;

    public CommentDTO() {
    }

    public CommentDTO(int commentCode, String commentContext, String commentDate, String commentStatus, int postCode, int userCode, String userNick) {
        this.commentCode = commentCode;
        this.commentContext = commentContext;
        this.commentDate = commentDate;
        this.commentStatus = commentStatus;
        this.postCode = postCode;
        this.userCode = userCode;
        this.userNick = userNick;
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

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
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

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    @Override
    public String toString() {
        return "CommentDTO{" +
                "commentCode=" + commentCode +
                ", commentContext='" + commentContext + '\'' +
                ", commentDate=" + commentDate +
                ", commentStatus='" + commentStatus + '\'' +
                ", postCode=" + postCode +
                ", userCode=" + userCode +
                ", userNick='" + userNick + '\'' +
                '}';
    }
}
