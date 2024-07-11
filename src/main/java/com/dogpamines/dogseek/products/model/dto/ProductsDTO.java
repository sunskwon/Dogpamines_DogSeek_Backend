package com.dogpamines.dogseek.products.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
@Schema(description = "사료 정보 DTO")
public class ProductsDTO implements Comparable<ProductsDTO> {
    @Schema(description = "사료 코드(PK)")
    private int prodCode;
    @Schema(description = "사료 이름")
    private String prodName;
    @Schema(description = "사료 제조사")
    private String prodManufac;
    @Schema(description = "사료 가격")
    private Integer prodPrice;
    @Schema(description = "사료 연령대")
    private String prodAge;
    @Schema(description = "사료 기능")
    private String prodEffi;
    @Schema(description = "사료 추천견종")
    private String prodRecom;
    @Schema(description = "사료 사이트")
    private String prodSite;
    @Schema(description = "사료 제조방식")
    private String prodCook;
    @Schema(description = "사료 용량")
    private String prodVolume;
    @Schema(description = "사료 평점")
    private int prodGrade;
    @Schema(description = "사료 재료")
    private String prodIngra;
    @Schema(description = "사료 입자크기")
    private String prodSize;
    @Schema(description = "사료 등록일")
    private String prodDate;
    @Schema(description = "사료 게시여부")
    private String prodStatus;
    @Schema(description = "사료 이미지")
    private String prodImage;
    @Schema(description = "사료 누적조회수")
    private int prodVisit;

    public ProductsDTO() {
    }

    public ProductsDTO(int prodCode, String prodName, String prodManufac, Integer prodPrice, String prodAge, String prodEffi, String prodRecom, String prodSite, String prodCook, String prodVolume, int prodGrade, String prodIngra, String prodSize, String prodDate, String prodStatus, String prodImage, int prodVisit) {
        this.prodCode = prodCode;
        this.prodName = prodName;
        this.prodManufac = prodManufac;
        this.prodPrice = prodPrice;
        this.prodAge = prodAge;
        this.prodEffi = prodEffi;
        this.prodRecom = prodRecom;
        this.prodSite = prodSite;
        this.prodCook = prodCook;
        this.prodVolume = prodVolume;
        this.prodGrade = prodGrade;
        this.prodIngra = prodIngra;
        this.prodSize = prodSize;
        this.prodDate = prodDate;
        this.prodStatus = prodStatus;
        this.prodImage = prodImage;
        this.prodVisit = prodVisit;
    }

    public int getProdCode() {
        return prodCode;
    }

    public void setProdCode(int prodCode) {
        this.prodCode = prodCode;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdManufac() {
        return prodManufac;
    }

    public void setProdManufac(String prodManufac) {
        this.prodManufac = prodManufac;
    }

    public Integer getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(Integer prodPrice) {
        this.prodPrice = prodPrice;
    }

    public String getProdAge() {
        return prodAge;
    }

    public void setProdAge(String prodAge) {
        this.prodAge = prodAge;
    }

    public String getProdEffi() {
        return prodEffi;
    }

    public void setProdEffi(String prodEffi) {
        this.prodEffi = prodEffi;
    }

    public String getProdRecom() {
        return prodRecom;
    }

    public void setProdRecom(String prodRecom) {
        this.prodRecom = prodRecom;
    }

    public String getProdSite() {
        return prodSite;
    }

    public void setProdSite(String prodSite) {
        this.prodSite = prodSite;
    }

    public String getProdCook() {
        return prodCook;
    }

    public void setProdCook(String prodCook) {
        this.prodCook = prodCook;
    }

    public String getProdVolume() {
        return prodVolume;
    }

    public void setProdVolume(String prodVolume) {
        this.prodVolume = prodVolume;
    }

    public int getProdGrade() {
        return prodGrade;
    }

    public void setProdGrade(int prodGrade) {
        this.prodGrade = prodGrade;
    }

    public String getProdIngra() {
        return prodIngra;
    }

    public void setProdIngra(String prodIngra) {
        this.prodIngra = prodIngra;
    }

    public String getProdSize() {
        return prodSize;
    }

    public void setProdSize(String prodSize) {
        this.prodSize = prodSize;
    }

    public String getProdDate() {
        return prodDate;
    }

    public void setProdDate(String prodDate) {
        this.prodDate = prodDate;
    }

    public String getProdStatus() {
        return prodStatus;
    }

    public void setProdStatus(String prodStatus) {
        this.prodStatus = prodStatus;
    }

    public String getProdImage() {
        return prodImage;
    }

    public void setProdImage(String prodImage) {
        this.prodImage = prodImage;
    }

    public int getProdVisit() {
        return prodVisit;
    }

    public void setProdVisit(int prodVisit) {
        this.prodVisit = prodVisit;
    }

    @Override
    public String toString() {
        return "ProductsDTO{" +
                "prodCode=" + prodCode +
                ", prodName='" + prodName + '\'' +
                ", prodManufac='" + prodManufac + '\'' +
                ", prodPrice=" + prodPrice +
                ", prodAge='" + prodAge + '\'' +
                ", prodEffi='" + prodEffi + '\'' +
                ", prodRecom='" + prodRecom + '\'' +
                ", prodSite='" + prodSite + '\'' +
                ", prodCook='" + prodCook + '\'' +
                ", prodVolume='" + prodVolume + '\'' +
                ", prodGrade=" + prodGrade +
                ", prodIngra='" + prodIngra + '\'' +
                ", prodSize='" + prodSize + '\'' +
                ", prodDate='" + prodDate + '\'' +
                ", prodStatus='" + prodStatus + '\'' +
                ", prodImage='" + prodImage + '\'' +
                ", prodVisit=" + prodVisit +
                '}';
    }

    @Override
    public int compareTo(ProductsDTO product) {
        if (product.prodVisit > prodVisit) {
            return 1;
        } else if (product.prodVisit < prodVisit) {
            return -1;
        } else {
            return 0;
        }
    }
}
