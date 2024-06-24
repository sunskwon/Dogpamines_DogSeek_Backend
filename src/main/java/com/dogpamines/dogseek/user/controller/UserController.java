package com.dogpamines.dogseek.user.controller;

import com.dogpamines.dogseek.curation.model.service.CurationService;
import com.dogpamines.dogseek.user.model.dto.UserDTO;
import com.dogpamines.dogseek.user.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    private UserService userService;        // 생성자 주입으로 하기!!!
    private CurationService curationService;

    @Autowired
    public UserController(UserService userService, CurationService curationService) {
        this.userService = userService;
        this.curationService = curationService;
    }

    @PostMapping("/signup")
    public String signup(@RequestBody UserDTO user) {
        return userService.signUp(user);
    }

    @GetMapping("/admin/users/{userCode}")
    @Transactional
    public ResponseEntity<Map<String, Object>> selectUserByCodeByAdmin(@PathVariable int userCode) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Map<String, Object> result = new HashMap<>();
        result.put("user", userService.selectUserByCodeByAdmin(userCode));

        List<String> dogList = curationService.findDogList(userCode);

        if (dogList.size() > 0) {

            result.put("dogList", dogList);

            for (String dog : dogList) {

                result.put(dog, curationService.selectDogsByCodeByAdmin(dog));
            }
        }



        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

    @PostMapping("/admin/users")
    public ResponseEntity<Map<String, Object>> selectAllUsersByAdmin(@RequestBody Map<String, String> search) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Map<String, Object> result = new HashMap<>();
        result.put("users", userService.selectAllUsersByAdmin(search));

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

    @DeleteMapping("/admin/users/{userCode}")
    public ResponseEntity<?> deleteUserByAdmin(@PathVariable int userCode) {

        String userAuth = userService.findUserAuth(userCode);

        userService.deleteUserByAdmin(userAuth, userCode);

        return ResponseEntity.noContent().build();
    }
}
