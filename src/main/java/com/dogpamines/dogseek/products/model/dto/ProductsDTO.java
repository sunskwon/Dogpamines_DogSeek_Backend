package com.dogpamines.dogseek.products.model.dto;

public class ProductsDTO {

    private int prodCode;
    private String prodName;
    private String prodManufac;
    private Integer prodPrice;
    private String prodAge;
    private String prodEffi;
    private String prodRecom;
    private String prodSite;
    private String prodCook;
    private String prodVolume;
    private int prodGrade;
    private String prodIngra;
    private String prodSize;
    private String prodDate;
    private String prodStatus;
    private String prodImage;
    private int prodVisit;

    public ProductsDTO() {}

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
}
