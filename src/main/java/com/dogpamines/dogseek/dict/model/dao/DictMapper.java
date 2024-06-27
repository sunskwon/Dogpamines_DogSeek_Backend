package com.dogpamines.dogseek.dict.model.dao;

import com.dogpamines.dogseek.dict.model.dto.DictDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DictMapper {
    List<DictDTO> selectAllDog();

    DictDTO selectByCode(String dogName);

    List<DictDTO> searchDog(String dogName);

    List<DictDTO> dictSearch(String type, String input);

    int getLastDogCode();

    void deleteDict(int dogCode);

    void insertDict(DictDTO dict);

    void updateDict(DictDTO dict);
}
