package com.dogpamines.dogseek.curation.model.dto;

import java.util.Date;

public class CurationDTO {

    private Integer curationCode;
    private String curationAge;
    private String curationIngra;
    private String curationDisease;
    private String curationAllergy;
    private String curationBreed;
    private String curationGender;
    private String curationNeut;
    private String curationWeight;
    private String curationName;
    private Date curationDate;
    private String curationSize;
    private String curationCook;
    private int userCode;

    public CurationDTO() {}

    public CurationDTO(Integer curationCode, String curationAge, String curationIngra, String curationDisease, String curationAllergy, String curationBreed, String curationGender, String curationNeut, String curationWeight, String curationName, Date curationDate, String curationSize, String curationCook, int userCode) {
        this.curationCode = curationCode;
        this.curationAge = curationAge;
        this.curationIngra = curationIngra;
        this.curationDisease = curationDisease;
        this.curationAllergy = curationAllergy;
        this.curationBreed = curationBreed;
        this.curationGender = curationGender;
        this.curationNeut = curationNeut;
        this.curationWeight = curationWeight;
        this.curationName = curationName;
        this.curationDate = curationDate;
        this.curationSize = curationSize;
        this.curationCook = curationCook;
        this.userCode = userCode;
    }

    public Integer getCurationCode() {
        return curationCode;
    }

    public void setCurationCode(Integer curationCode) {
        this.curationCode = curationCode;
    }

    public String getCurationAge() {
        return curationAge;
    }

    public void setCurationAge(String curationAge) {
        this.curationAge = curationAge;
    }

    public String getCurationIngra() {
        return curationIngra;
    }

    public void setCurationIngra(String curationIngra) {
        this.curationIngra = curationIngra;
    }

    public String getCurationDisease() {
        return curationDisease;
    }

    public void setCurationDisease(String curationDisease) {
        this.curationDisease = curationDisease;
    }

    public String getCurationAllergy() {
        return curationAllergy;
    }

    public void setCurationAllergy(String curationAllergy) {
        this.curationAllergy = curationAllergy;
    }

    public String getCurationBreed() {
        return curationBreed;
    }

    public void setCurationBreed(String curationBreed) {
        this.curationBreed = curationBreed;
    }

    public String getCurationGender() {
        return curationGender;
    }

    public void setCurationGender(String curationGender) {
        this.curationGender = curationGender;
    }

    public String getCurationNeut() {
        return curationNeut;
    }

    public void setCurationNeut(String curationNeut) {
        this.curationNeut = curationNeut;
    }

    public String getCurationWeight() {
        return curationWeight;
    }

    public void setCurationWeight(String curationWeight) {
        this.curationWeight = curationWeight;
    }

    public String getCurationName() {
        return curationName;
    }

    public void setCurationName(String curationName) {
        this.curationName = curationName;
    }

    public Date getCurationDate() {
        return curationDate;
    }

    public void setCurationDate(Date curationDate) {
        this.curationDate = curationDate;
    }

    public String getCurationSize() {
        return curationSize;
    }

    public void setCurationSize(String curationSize) {
        this.curationSize = curationSize;
    }

    public String getCurationCook() {
        return curationCook;
    }

    public void setCurationCook(String curationCook) {
        this.curationCook = curationCook;
    }

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    @Override
    public String toString() {
        return "CurationDTO{" +
                "curationCode=" + curationCode +
                ", curationAge='" + curationAge + '\'' +
                ", curationIngra='" + curationIngra + '\'' +
                ", curationDisease='" + curationDisease + '\'' +
                ", curationAllergy='" + curationAllergy + '\'' +
                ", curationBreed='" + curationBreed + '\'' +
                ", curationGender='" + curationGender + '\'' +
                ", curationNeut='" + curationNeut + '\'' +
                ", curationWeight='" + curationWeight + '\'' +
                ", curationName='" + curationName + '\'' +
                ", curationDate=" + curationDate +
                ", curationSize='" + curationSize + '\'' +
                ", curationCook='" + curationCook + '\'' +
                ", userCode=" + userCode +
                '}';
    }
}
