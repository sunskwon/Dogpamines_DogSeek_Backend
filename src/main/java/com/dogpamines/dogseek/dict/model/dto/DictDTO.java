package com.dogpamines.dogseek.dict.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "견종 정보 DTO")
public class DictDTO {
    @Schema(description = "견종 코드(PK)")
    private int dogCode;           // 견종코드
    @Schema(description = "견종 이름")
    private String dogName;        // 이름
    @Schema(description = "견종 크기")
    private String dogSize;        // 크기
    @Schema(description = "견종 개요")
    private String dogSummary;     // 개요
    @Schema(description = "견종 수컷 체고")
    private String dogHeightM;     // 수체고
    @Schema(description = "견종 수컷 체중")
    private String dogWeightM;     // 수체중
    @Schema(description = "견종 암컷 체고")
    private String dogHeightF;     // 암체고
    @Schema(description = "견종 암컷 체중")
    private String dogWeightF;     // 암체중
    @Schema(description = "견종 유아기")
    private String dogChild;       // 유아기
    @Schema(description = "견종 청년기")
    private String dogYouth;       // 청년기
    @Schema(description = "견종 노년기")
    private String dogEld;         // 노년기
    @Schema(description = "견종 취약질병")
    private String dogDisease;     // 취약질병
    @Schema(description = "견종 침흘림")
    private int dogDrool;          // 침흘림
    @Schema(description = "견종 공동생활")
    private int dogSocial;         // 다른동물과의 공동생활
    @Schema(description = "견종 털빠짐")
    private int dogShed;           // 털빠짐
    @Schema(description = "견종 짖음")
    private int dogBark;           // 짖음
    @Schema(description = "견종 가족반려동물")
    private int dogPet;            // 가족반려동물
    @Schema(description = "견종 더위 적응")
    private int dogHot;            // 더위 적응
    @Schema(description = "견종 추위 적응")
    private int dogCold;           // 추위 적응
    @Schema(description = "견종 실내 적합성")
    private int dogHouse;          // 실내 적합성
    @Schema(description = "견종 그루밍 요구")
    private int dogGroom;          // 그루밍 요구
    @Schema(description = "견종 활동량")
    private int dogActi;           // 활동량
    @Schema(description = "견종 이미지")
    private String dogImage;       // 이미지
    @Schema(description = "견종 상세 이미지")
    private String dogDetail;      // 상세 이미지

    public DictDTO(){};

    public DictDTO(int dogCode, String dogName, String dogSize, String dogSummary, String dogHeightM, String dogWeightM, String dogHeightF, String dogWeightF, String dogChild, String dogYouth, String dogEld, String dogDisease, int dogDrool, int dogSocial, int dogShed, int dogBark, int dogPet, int dogHot, int dogCold, int dogHouse, int dogGroom, int dogActi, String dogImage, String dogDetail) {
        this.dogCode = dogCode;
        this.dogName = dogName;
        this.dogSize = dogSize;
        this.dogSummary = dogSummary;
        this.dogHeightM = dogHeightM;
        this.dogWeightM = dogWeightM;
        this.dogHeightF = dogHeightF;
        this.dogWeightF = dogWeightF;
        this.dogChild = dogChild;
        this.dogYouth = dogYouth;
        this.dogEld = dogEld;
        this.dogDisease = dogDisease;
        this.dogDrool = dogDrool;
        this.dogSocial = dogSocial;
        this.dogShed = dogShed;
        this.dogBark = dogBark;
        this.dogPet = dogPet;
        this.dogHot = dogHot;
        this.dogCold = dogCold;
        this.dogHouse = dogHouse;
        this.dogGroom = dogGroom;
        this.dogActi = dogActi;
        this.dogImage = dogImage;
        this.dogDetail = dogDetail;
    }

    @Override
    public String toString() {
        return "DictDTO{" +
                "dogCode=" + dogCode +
                ", dogName='" + dogName + '\'' +
                ", dogSize='" + dogSize + '\'' +
                ", dogSummary='" + dogSummary + '\'' +
                ", dogHeightM='" + dogHeightM + '\'' +
                ", dogWeightM='" + dogWeightM + '\'' +
                ", dogHeightF='" + dogHeightF + '\'' +
                ", dogWeightF='" + dogWeightF + '\'' +
                ", dogChild='" + dogChild + '\'' +
                ", dogYouth='" + dogYouth + '\'' +
                ", dogEld='" + dogEld + '\'' +
                ", dogDisease='" + dogDisease + '\'' +
                ", dogDrool=" + dogDrool +
                ", dogSocial=" + dogSocial +
                ", dogShed=" + dogShed +
                ", dogBark=" + dogBark +
                ", dogPet=" + dogPet +
                ", dogHot=" + dogHot +
                ", dogCold=" + dogCold +
                ", dogHouse=" + dogHouse +
                ", dogGroom=" + dogGroom +
                ", dogActi=" + dogActi +
                ", dogImage='" + dogImage + '\'' +
                ", dogDetail='" + dogDetail + '\'' +
                '}';
    }

    public int getDogCode() {
        return dogCode;
    }

    public void setDogCode(int dogCode) {
        this.dogCode = dogCode;
    }

    public String getDogName() {
        return dogName;
    }

    public void setDogName(String dogName) {
        this.dogName = dogName;
    }

    public String getDogSize() {
        return dogSize;
    }

    public void setDogSize(String dogSize) {
        this.dogSize = dogSize;
    }

    public String getDogSummary() {
        return dogSummary;
    }

    public void setDogSummary(String dogSummary) {
        this.dogSummary = dogSummary;
    }

    public String getDogHeightM() {
        return dogHeightM;
    }

    public void setDogHeightM(String dogHeightM) {
        this.dogHeightM = dogHeightM;
    }

    public String getDogWeightM() {
        return dogWeightM;
    }

    public void setDogWeightM(String dogWeightM) {
        this.dogWeightM = dogWeightM;
    }

    public String getDogHeightF() {
        return dogHeightF;
    }

    public void setDogHeightF(String dogHeightF) {
        this.dogHeightF = dogHeightF;
    }

    public String getDogWeightF() {
        return dogWeightF;
    }

    public void setDogWeightF(String dogWeightF) {
        this.dogWeightF = dogWeightF;
    }

    public String getDogChild() {
        return dogChild;
    }

    public void setDogChild(String dogChild) {
        this.dogChild = dogChild;
    }

    public String getDogYouth() {
        return dogYouth;
    }

    public void setDogYouth(String dogYouth) {
        this.dogYouth = dogYouth;
    }

    public String getDogEld() {
        return dogEld;
    }

    public void setDogEld(String dogEld) {
        this.dogEld = dogEld;
    }

    public String getDogDisease() {
        return dogDisease;
    }

    public void setDogDisease(String dogDisease) {
        this.dogDisease = dogDisease;
    }

    public int getDogDrool() {
        return dogDrool;
    }

    public void setDogDrool(int dogDrool) {
        this.dogDrool = dogDrool;
    }

    public int getDogSocial() {
        return dogSocial;
    }

    public void setDogSocial(int dogSocial) {
        this.dogSocial = dogSocial;
    }

    public int getDogShed() {
        return dogShed;
    }

    public void setDogShed(int dogShed) {
        this.dogShed = dogShed;
    }

    public int getDogBark() {
        return dogBark;
    }

    public void setDogBark(int dogBark) {
        this.dogBark = dogBark;
    }

    public int getDogPet() {
        return dogPet;
    }

    public void setDogPet(int dogPet) {
        this.dogPet = dogPet;
    }

    public int getDogHot() {
        return dogHot;
    }

    public void setDogHot(int dogHot) {
        this.dogHot = dogHot;
    }

    public int getDogCold() {
        return dogCold;
    }

    public void setDogCold(int dogCold) {
        this.dogCold = dogCold;
    }

    public int getDogHouse() {
        return dogHouse;
    }

    public void setDogHouse(int dogHouse) {
        this.dogHouse = dogHouse;
    }

    public int getDogGroom() {
        return dogGroom;
    }

    public void setDogGroom(int dogGroom) {
        this.dogGroom = dogGroom;
    }

    public int getDogActi() {
        return dogActi;
    }

    public void setDogActi(int dogActi) {
        this.dogActi = dogActi;
    }

    public String getDogImage() {
        return dogImage;
    }

    public void setDogImage(String dogImage) {
        this.dogImage = dogImage;
    }

    public String getDogDetail() {
        return dogDetail;
    }

    public void setDogDetail(String dogDetail) {
        this.dogDetail = dogDetail;
    }
}
