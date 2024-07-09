package com.dogpamines.dogseek.user.model.service;

import com.dogpamines.dogseek.board.model.dao.BoardMapper;
import com.dogpamines.dogseek.board.model.dto.BoardDTO;
import com.dogpamines.dogseek.common.UserRole;
import com.dogpamines.dogseek.curation.model.dao.CurationMapper;
import com.dogpamines.dogseek.user.model.dao.UserMapper;
import com.dogpamines.dogseek.user.model.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.*;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final CurationMapper curationMapper;
    private final BoardMapper boardMapper;
    private final RedisTemplate<String, String> redisTemplate;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final String REGIST_KEY = "regist";
    private final String VISITANT_KEY = "visitant";

    @Autowired
    public UserService(UserMapper userMapper, CurationMapper curationMapper, BoardMapper boardMapper, RedisTemplate redisTemplate, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userMapper = userMapper;
        this.curationMapper = curationMapper;
        this.boardMapper = boardMapper;
        this.redisTemplate = redisTemplate;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;

    }

    public void updateRefreshToken(String refreshToken, String userId) {

        userMapper.updateRefreshToken(refreshToken, userId);
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

        UserRole userRole = UserRole.valueOf("USER");
        System.out.println("여기?");

        if (userRole == null || userRole.name().isEmpty()) {
            return "회원 가입 실패: userRole이 설정되지 않았습니다.";   // 확인용으로만
        }

        user.setUserPass(bCryptPasswordEncoder.encode(user.getUserPass()));

        try {
            UserRole role = UserRole.valueOf(userRole.name().toUpperCase());
            System.out.println("role = " + role);
            user.setUserAuth(role);

        } catch (IllegalArgumentException e) {
            return "회원 가입 실패: 올바르지 않은 사용자 역할입니다.";
        }

        System.out.println("user = " + user);
        int result = userMapper.regist(user);
        System.out.println("result = " + result);

        // 회원가입 성공시 가입자 수 증가
        try {

            SetOperations<String, String> countRegist = redisTemplate.opsForSet();

            countRegist.add(REGIST_KEY, String.valueOf(user.getUserId()));
        } catch (RedisConnectionFailureException e) {
            System.out.println("redis와 연결되지 않음");
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (result == 1) {
            return "회원 가입 성공";
        } else {
            return "회원 가입 실패";
        }
    }

    public Map<String, Object> selectUserByCodeByAdmin(int userCode) {

        Map<String, Object> result = new HashMap<>();

        UserDTO user = userMapper.selectUserByCodeByAdmin(userCode);
        result.put("user", user);

        List<String> dogs = curationMapper.findDogList(userCode);

        if (dogs.size() > 0) {

            result.put("dogs", dogs);

            for (String dog : dogs) {

                result.put(dog, curationMapper.selectDogByCodeByAdmin(dog));
            }

        }

        List<BoardDTO> boardList = boardMapper.selectBoardByCodeByAdmin(userCode);

        if (boardList.size() > 0) {

            result.put("boardList", boardList);

            for (BoardDTO board : boardList) {

                int postCode = board.getPostCode();

                result.put(String.valueOf(postCode), boardMapper.selectBoardReportsByPostCode(postCode));
            }
        }

        return result;
    }

    public List<UserDTO> selectAllUsersByAdmin(Map<String, String> search) {

        String type = search.get("type");
        String input = search.get("input");

        return userMapper.selectAllUsersByAdmin(type, input);
    }

    public void updateUserByAdmin(int userCode) {

        String userAuth = userMapper.findUserAuth(userCode);

        userMapper.updateUserByAdmin(userCode, userAuth);
    }

    public void deleteUserByAdmin(int userCode) {

        String userAuth = userMapper.findUserAuth(userCode);

        userMapper.deleteUserByAdmin(userAuth, userCode);
    }

    public boolean checkInfo(String type, String info) {

        return userMapper.checkInfo(type, info);
    }

    @Transactional
    public void updateLogin(int userCode) {

        try {

            SetOperations<String, String> countVisit = redisTemplate.opsForSet();

            countVisit.add(VISITANT_KEY, String.valueOf(userCode));
        } catch (RedisConnectionFailureException e) {
            System.out.println("redis와 연결되지 않음");
        } catch (Exception e) {
            e.printStackTrace();
        }

        userMapper.updateLogin(userCode);
    }

    public UserDTO findEmailByPhone(String phoneNumber) {
        return userMapper.findEmailByPhone(phoneNumber);
    }

    public void updateUserPwd(String id, String pwd) {

        String encodePwd = bCryptPasswordEncoder.encode(pwd);

        userMapper.updateUserPwd(id, encodePwd);
    }

    public UserDTO selectUserByCode(int userCode) {

        return userMapper.selectUserByCode(userCode);
    }

    public void updateSleep(String id) {

        userMapper.updateSleep(id);
    }

    public boolean selectRefreshToken(String refreshToken) {

       return userMapper.selectRefreshToken(refreshToken);
    }
}
