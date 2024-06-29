package com.dogpamines.dogseek.admin.model.dao;

import com.dogpamines.dogseek.common.model.dto.CountsDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {
    List<CountsDTO> selectAllCounts();
}
