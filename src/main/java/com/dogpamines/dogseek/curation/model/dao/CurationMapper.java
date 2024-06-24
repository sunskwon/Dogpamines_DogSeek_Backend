package com.dogpamines.dogseek.curation.model.dao;

import com.dogpamines.dogseek.curation.model.dto.CurationDTO;
import com.dogpamines.dogseek.products.model.dto.ProductsDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface CurationMapper {
    Integer lastCuration();
    void insertCuration(CurationDTO curationDTO);
    List<ProductsDTO> curationProducts(String curationAge, String curationIngra, String curationDisease, String curationAllergy, String curationSize, String curationCook);
    Object curationSelect(String curationAge, String curationIngra, String curationAllergy, String curationDisease, String curationBreed, String curationGender, String curationNeut, String curationWeight, String curationName, Date curationDate, String curationSize, String curationCook, int userCode);

    List<String> findDogList(int userCode);

    List<CurationDTO> selectDogsByCodeByAdmin(String dog);
}
