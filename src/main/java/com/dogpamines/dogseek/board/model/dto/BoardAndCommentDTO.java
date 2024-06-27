package com.dogpamines.dogseek.board.model.dto;

import java.sql.Date;

public class BoardAndCommentDTO {

    private int postCode;
    private String postTitle;
    private String postContext;
    private Date postDate;
    private String postCategory;
    private String postStatus;
    private int userCode;
    private CommentDTO commentDTO;

    public BoardAndCommentDTO() {}

    public BoardAndCommentDTO(int postCode, String postTitle, String postContext, Date postDate, String postCategory, String postStatus, int userCode, CommentDTO commentDTO) {
        this.postCode = postCode;
        this.postTitle = postTitle;
        this.postContext = postContext;
        this.postDate = postDate;
        this.postCategory = postCategory;
        this.postStatus = postStatus;
        this.userCode = userCode;
        this.commentDTO = commentDTO;
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

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
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

    public CommentDTO getCommentDTO() {
        return commentDTO;
    }

    public void setCommentDTO(CommentDTO commentDTO) {
        this.commentDTO = commentDTO;
    }

    @Override
    public String toString() {
        return "BoardAndCommentDTO{" +
                "postCode=" + postCode +
                ", postTitle='" + postTitle + '\'' +
                ", postContext='" + postContext + '\'' +
                ", postDate=" + postDate +
                ", postCategory='" + postCategory + '\'' +
                ", postStatus='" + postStatus + '\'' +
                ", userCode=" + userCode +
                ", commentDTO=" + commentDTO +
                '}';
    }
}
