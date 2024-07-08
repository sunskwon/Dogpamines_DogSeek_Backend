package com.dogpamines.dogseek.mypage.model.dao;

import com.dogpamines.dogseek.curation.model.dto.CurationDTO;
import com.dogpamines.dogseek.curation.model.dto.HistoryDTO;
import com.dogpamines.dogseek.products.model.dto.ProductsDTO;
import com.dogpamines.dogseek.user.model.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MyPageMapper {
    List<CurationDTO> findAllCuration();

    List<CurationDTO> userCurationList(int userCode);

    List<CurationDTO> selectUserDogCurationList(int userCode, String curationName);

    List<UserDTO> selectUserDetail(int userCode);

    void updateUser(UserDTO updateInfo);
    
    String findUserAuth(int userCode);

    void deleteUser(int userCode);

    List<HistoryDTO> selectMyCurationResult(int curationCode);

    boolean checkInfo(String type, String info);

    List<Integer> findProdCodesByCurationCode(int curationCode);

    List<ProductsDTO> findProductsByProdCodes(List<Integer> prodCodes);

    void updateUserPwd(String id, String encodePwd);

    Optional<Object> findById(int userCode);
}
