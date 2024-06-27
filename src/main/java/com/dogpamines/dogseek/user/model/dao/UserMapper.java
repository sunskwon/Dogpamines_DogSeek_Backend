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

    void updateUserByAdmin(String userCode);

    void deleteUserByAdmin(String userAuth, int userCode);


    boolean selectByEmail(String email);

    boolean selectByNickname(String nickname);
}
