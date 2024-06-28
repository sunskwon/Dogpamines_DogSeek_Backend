package com.dogpamines.dogseek.user.model.service;

import com.dogpamines.dogseek.common.UserRole;
import com.dogpamines.dogseek.user.model.dao.UserMapper;
import com.dogpamines.dogseek.user.model.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UserService {


    private final UserMapper userMapper;


    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserMapper userMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userMapper = userMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;

    }

    public UserDTO findByUsername(String username) {

        UserDTO login = userMapper.findByUsername(username);

        if (!Objects.isNull(login)) {
            return login;
        } else {
            return null;
        }
    }

    public String signUp(UserDTO user) {

        UserRole userRole = user.getUserAuth();
        System.out.println("여기?");

        if (userRole == null || userRole.name().isEmpty()) {
            return "회원 가입 실패: userRole이 설정되지 않았습니다.";   // 확인용으로만
        }

        user.setUserPass(bCryptPasswordEncoder.encode(user.getUserPass()));
        System.out.println("여기 오나?");

        try {
            UserRole role = UserRole.valueOf(userRole.name().toUpperCase());
            System.out.println("role = " + role);
            user.setUserAuth(role);
            System.out.println("여기 오나??");
        } catch (IllegalArgumentException e) {
            return "회원 가입 실패: 올바르지 않은 사용자 역할입니다.";
        }

        System.out.println("user = " + user);
        int result = userMapper.regist(user);
        System.out.println("result = " + result);

        if (result == 1) {
            return "회원 가입 성공";
        } else {
            return "회원 가입 실패";
        }
    }

    public UserDTO selectUserByCodeByAdmin(int userCode) {

        return userMapper.selectUserByCodeByAdmin(userCode);
    }

    public List<UserDTO> selectAllUsersByAdmin(Map<String, String> search) {

        String type = search.get("type");
        String input = search.get("input");

        return userMapper.selectAllUsersByAdmin(type, input);
    }

    public String findUserAuth(int userCode) {

        return userMapper.findUserAuth(userCode);
    }

    public void updateUserByAdmin(String userCode) {

        userMapper.updateUserByAdmin(userCode);
    }

    public void deleteUserByAdmin(String userAuth, int userCode) {

        userMapper.deleteUserByAdmin(userAuth, userCode);
    }

    public boolean checkInfo(String type, String info) {

        return userMapper.checkInfo(type, info);
    }

}
