package com.dogpamines.dogseek.curation.model.service;

import com.dogpamines.dogseek.curation.model.dao.CurationMapper;
import com.dogpamines.dogseek.curation.model.dto.CurationDTO;
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

    public Object curationSelect(String curationAge, String curationIngra, String curationAllergy,
                                 String curationDisease, String curationBreed, String curationGender,
                                 String curationNeut, String curationWeight, String curationName,
                                 Date curationDate, String curationSize, String curationCook, int userCode) {

        return curationMapper.curationSelect(curationAge, curationIngra, curationAllergy,
                curationDisease, curationBreed, curationGender,
                curationNeut, curationWeight, curationName,
                curationDate, curationSize, curationCook, userCode);
    }

    public List<String> findDogList(int userCode) {
        return curationMapper.findDogList(userCode);
    }

    public List<CurationDTO> selectDogsByCodeByAdmin(String dog) {

        return curationMapper.selectDogsByCodeByAdmin(dog);
    }
}
