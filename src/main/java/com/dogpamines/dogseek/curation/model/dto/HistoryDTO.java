package com.dogpamines.dogseek.curation.model.dto;

import java.util.List;

public class HistoryDTO {

    private List<Integer>  prodCode;
    private int curationCode;

    public HistoryDTO() {}

    public HistoryDTO(List<Integer> prodCode, int curationCode) {
        this.prodCode = prodCode;
        this.curationCode = curationCode;
    }

    public List<Integer> getProdCode() {
        return prodCode;
    }

    public void setProdCode(List<Integer> prodCode) {
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
