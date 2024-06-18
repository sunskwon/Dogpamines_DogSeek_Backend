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
    private int prodGrade;
    private String prodIngra;
    private String prodSize;
    private String prodImage;

    public ProductsDTO() {}

    public ProductsDTO(int prodCode, String prodName, String prodManufac, int prodPrice, String prodAge, String prodEffi, String prodRecom, String prodSite, String prodCook, int prodGrade, String prodIngra, String prodSize, String prodImage) {
        this.prodCode = prodCode;
        this.prodName = prodName;
        this.prodManufac = prodManufac;
        this.prodPrice = prodPrice;
        this.prodAge = prodAge;
        this.prodEffi = prodEffi;
        this.prodRecom = prodRecom;
        this.prodSite = prodSite;
        this.prodCook = prodCook;
        this.prodGrade = prodGrade;
        this.prodIngra = prodIngra;
        this.prodSize = prodSize;
        this.prodImage = prodImage;
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

    public String getProdImage() {
        return prodImage;
    }

    public void setProdImage(String prodImage) {
        this.prodImage = prodImage;
    }

    @Override
    public String toString() {
        return "ProductsDTO{" +
                "prodCode=" + prodCode +
                ", prodName='" + prodName + '\'' +
                ", prodManufac='" + prodManufac + '\'' +
                ", prodPrice='" + prodPrice + '\'' +
                ", prodAge='" + prodAge + '\'' +
                ", prodEffi='" + prodEffi + '\'' +
                ", prodRecom='" + prodRecom + '\'' +
                ", prodSite='" + prodSite + '\'' +
                ", prodCook='" + prodCook + '\'' +
                ", prodGrade='" + prodGrade + '\'' +
                ", prodIngra='" + prodIngra + '\'' +
                ", prodSize='" + prodSize + '\'' +
                ", prodImage='" + prodImage + '\'' +
                '}';
    }
}
