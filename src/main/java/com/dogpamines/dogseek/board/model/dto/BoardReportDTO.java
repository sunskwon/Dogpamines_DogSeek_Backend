package com.dogpamines.dogseek.board.model.dto;

public class BoardReportDTO {

    private int reportCode;
    private String reportReason;
    private String reportDate;
    private int reportUser;
    private String reportNick;
    private int postCode;

    public BoardReportDTO() {}

    public BoardReportDTO(int reportCode, String reportReason, String reportDate, int reportUser, String reportNick, int postCode) {
        this.reportCode = reportCode;
        this.reportReason = reportReason;
        this.reportDate = reportDate;
        this.reportUser = reportUser;
        this.reportNick = reportNick;
        this.postCode = postCode;
    }

    public int getReportCode() {
        return reportCode;
    }

    public void setReportCode(int reportCode) {
        this.reportCode = reportCode;
    }

    public String getReportReason() {
        return reportReason;
    }

    public void setReportReason(String reportReason) {
        this.reportReason = reportReason;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public int getReportUser() {
        return reportUser;
    }

    public void setReportUser(int reportUser) {
        this.reportUser = reportUser;
    }

    public String getReportNick() {
        return reportNick;
    }

    public void setReportNick(String reportNick) {
        this.reportNick = reportNick;
    }

    public int getPostCode() {
        return postCode;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    @Override
    public String toString() {
        return "BoardReportDTO{" +
                "reportCode=" + reportCode +
                ", reportReason='" + reportReason + '\'' +
                ", reportDate='" + reportDate + '\'' +
                ", reportUser=" + reportUser +
                ", reportNick='" + reportNick + '\'' +
                ", postCode=" + postCode +
                '}';
    }
}
