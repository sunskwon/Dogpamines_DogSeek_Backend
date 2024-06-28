package com.dogpamines.dogseek.common.model.dao;

import com.dogpamines.dogseek.common.model.dto.CountsDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ScheduledMapper {
    CountsDTO selectCounts(String date);

    void updateCounts(CountsDTO countsDTO);

    void createNewRow();
}
