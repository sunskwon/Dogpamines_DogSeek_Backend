package com.dogpamines.dogseek.mypage.model.dao;

import com.dogpamines.dogseek.curation.model.dto.CurationDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyPageMapper {
    List<CurationDTO> findAllCuration();

    List<CurationDTO> userCurationList(int userCode);

    List<CurationDTO> selectUserDogCurationList(int userCode, String curationName);
}
