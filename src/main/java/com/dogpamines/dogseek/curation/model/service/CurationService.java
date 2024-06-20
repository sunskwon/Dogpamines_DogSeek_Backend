package com.dogpamines.dogseek.curation.model.service;

import com.dogpamines.dogseek.curation.model.dao.CurationMapper;
import com.dogpamines.dogseek.curation.model.dto.CurationDTO;
import com.dogpamines.dogseek.products.model.dto.ProductsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
