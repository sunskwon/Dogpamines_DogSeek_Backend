package com.dogpamines.dogseek.dict.model.dao;

import com.dogpamines.dogseek.dict.model.dto.DictDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DictMapper {
    List<DictDTO> selectAllDog();
}
