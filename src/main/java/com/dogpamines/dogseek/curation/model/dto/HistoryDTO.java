package com.dogpamines.dogseek.curation.model.dto;

public class HistoryDTO {

    private int prodCode;
    private int curationCode;

    public HistoryDTO() {}

    public HistoryDTO(int prodCode, int curationCode) {
        this.prodCode = prodCode;
        this.curationCode = curationCode;
    }

    public int getProdCode() {
        return prodCode;
    }

    public void setProdCode(int prodCode) {
        this.prodCode = prodCode;
    }

    public int getCurationCode() {
        return curationCode;
    }

    public void setCurationCode(int curationCode) {
        this.curationCode = curationCode;
    }

    @Override
    public String toString() {
        return "HistoryDTO{" +
                "prodCode=" + prodCode +
                ", curationCode=" + curationCode +
                '}';
    }
}
