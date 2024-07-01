package com.dogpamines.dogseek.mypage.model.dao;

import com.dogpamines.dogseek.curation.model.dto.CurationDTO;
import com.dogpamines.dogseek.curation.model.dto.HistoryDTO;
import com.dogpamines.dogseek.user.model.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyPageMapper {
    List<CurationDTO> findAllCuration();

    List<CurationDTO> userCurationList(int userCode);

    List<CurationDTO> selectUserDogCurationList(int userCode, String curationName);

    List<UserDTO> selectUserDetail(int userCode);

    void updateUser(UserDTO updateInfo);
    
    String findUserAuth(int userCode);

    void deleteUser(String userAuth, int userCode);

    List<HistoryDTO> selectMyCurationResult(int curationCode);
}
