package com.dogpamines.dogseek.common.model.dto;

import java.sql.Date;

public class CountsDTO {

    private String countsDate;
    private int countsSignup;
    private int countsSignin;
    private int countsProducts;
    private int countsBoards;

    public CountsDTO() {}

    public CountsDTO(String countsDate, int countsSignup, int countsSignin, int countsProducts, int countsBoards) {
        this.countsDate = countsDate;
        this.countsSignup = countsSignup;
        this.countsSignin = countsSignin;
        this.countsProducts = countsProducts;
        this.countsBoards = countsBoards;
    }

    public String getCountsDate() {
        return countsDate;
    }

    public void setCountsDate(String countsDate) {
        this.countsDate = countsDate;
    }

    public int getCountsSignup() {
        return countsSignup;
    }

    public void setCountsSignup(int countsSignup) {
        this.countsSignup = countsSignup;
    }

    public int getCountsSignin() {
        return countsSignin;
    }

    public void setCountsSignin(int countsSignin) {
        this.countsSignin = countsSignin;
    }

    public int getCountsProducts() {
        return countsProducts;
    }

    public void setCountsProducts(int countsProducts) {
        this.countsProducts = countsProducts;
    }

    public int getCountsBoards() {
        return countsBoards;
    }

    public void setCountsBoards(int countsBoards) {
        this.countsBoards = countsBoards;
    }

    @Override
    public String toString() {
        return "CountsDTO{" +
                "countsDate=" + countsDate +
                ", countsSignup=" + countsSignup +
                ", countsSignin=" + countsSignin +
                ", countsProducts=" + countsProducts +
                ", countsBoards=" + countsBoards +
                '}';
    }
}
