package com.dogpamines.dogseek.user.model.dao;

import com.dogpamines.dogseek.user.model.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    UserDTO findByUsername(String username);

    int regist(UserDTO user);

    UserDTO selectUserByCodeByAdmin(int userCode);

    List<UserDTO> selectAllUsersByAdmin(String type, String input);

    String findUserAuth(int userCode);

    void updateUserByAdmin(int userCode, String userAuth);

    void deleteUserByAdmin(String userAuth, int userCode);

    boolean checkInfo(String type, String info);

    void updateLogin(int userCode);

    UserDTO findEmailByPhone(String phoneNumber);

    void updateUserPwd(String id, String encodePwd);

    UserDTO selectUserByCode(int userCode);

    void updateSleep(String id);
}
