package com.dogpamines.dogseek.curation.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;
@Schema(description = "큐레이션 정보 DTO")
public class CurationDTO {
    @Schema(description = "큐레이션 코드(PK)")
    private Integer curationCode;
    @Schema(description = "연령대")
    private String curationAge;
    @Schema(description = "재료")
    private String curationIngra;
    @Schema(description = "질병")
    private String curationDisease;
    @Schema(description = "알러지")
    private String curationAllergy;
    @Schema(description = "견종")
    private String curationBreed;
    @Schema(description = "성별")
    private String curationGender;
    @Schema(description = "중성화 여부")
    private String curationNeut;
    @Schema(description = "몸무게")
    private String curationWeight;
    @Schema(description = "이름")
    private String curationName;
    @Schema(description = "큐레이션 날짜")
    private String curationDate;
    @Schema(description = "크기")
    private String curationSize;
    @Schema(description = "조리방식")
    private String curationCook;
    @Schema(description = "회원 코드(FK)")
    private int userCode;

    public CurationDTO() {}

    public CurationDTO(Integer curationCode, String curationAge, String curationIngra, String curationDisease, String curationAllergy, String curationBreed, String curationGender, String curationNeut, String curationWeight, String curationName, String curationDate, String curationSize, String curationCook, int userCode) {
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

    public String getCurationDate() {
        return curationDate;
    }

    public void setCurationDate(String curationDate) {
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
