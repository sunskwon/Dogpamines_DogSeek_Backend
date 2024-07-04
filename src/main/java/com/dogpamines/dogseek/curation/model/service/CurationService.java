package com.dogpamines.dogseek.curation.model.service;

import com.dogpamines.dogseek.curation.model.dao.CurationMapper;
import com.dogpamines.dogseek.curation.model.dto.CurationDTO;
import com.dogpamines.dogseek.curation.model.dto.HistoryDTO;
import com.dogpamines.dogseek.products.model.dto.ProductsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CurationService {

    private CurationMapper curationMapper;

    @Autowired
    public CurationService(CurationMapper curationMapper) {this.curationMapper = curationMapper;}

    public Integer lastCuration() {
        return curationMapper.lastCuration();
    }

    public void insertCuration(CurationDTO curationDTO) {
        curationMapper.insertCuration(curationDTO);
    }

    public List<ProductsDTO> curationProducts(String curationAge, String curationIngra, String curationDisease, String curationAllergy, String curationSize, String curationCook) {
        return curationMapper.curationProducts(curationAge, curationIngra, curationDisease, curationAllergy, curationSize, curationCook);
    }

    public List<CurationDTO> curationSelect(String curationAge, String curationIngra, String curationAllergy,
                                 String curationDisease, String curationBreed, String curationGender,
                                 String curationNeut, String curationWeight, String curationName,
                                 Date parsedDate, String curationSize, String curationCook, int userCode) {

        return curationMapper.curationSelect(curationAge, curationIngra, curationAllergy,
                curationDisease, curationBreed, curationGender,
                curationNeut, curationWeight, curationName,
                parsedDate, curationSize, curationCook, userCode);
    }

    public void insertHistory(HistoryDTO historyDTO) {
        curationMapper.insertHistory(historyDTO);
    }
}
