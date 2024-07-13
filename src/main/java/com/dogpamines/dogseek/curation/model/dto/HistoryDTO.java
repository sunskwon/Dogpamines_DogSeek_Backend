package com.dogpamines.dogseek.curation.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
@Schema(description = "큐레이션 히스토리 DTO")
public class HistoryDTO {
    @Schema(description = "사료 코드(FK)")
    private List<Integer>  prodCode;
    @Schema(description = "큐레이셔 코드(FK)")
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
