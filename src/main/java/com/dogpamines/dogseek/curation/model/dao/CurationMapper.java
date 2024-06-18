package com.dogpamines.dogseek.curation.model.dao;

import com.dogpamines.dogseek.curation.model.dto.CurationDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CurationMapper {
    Integer lastCuration();
    void insertCuration(CurationDTO curationDTO);
}
