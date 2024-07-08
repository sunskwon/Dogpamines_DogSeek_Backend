package com.dogpamines.dogseek.user.controller;

import com.dogpamines.dogseek.user.model.dto.UserDTO;
import com.dogpamines.dogseek.user.model.service.UserService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    private UserService userService;        // 생성자 주입으로 하기!!!

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Boolean> signup(@Valid @RequestBody UserDTO user) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        headers.set("Access-Control-Expose-Headers", "Result"); // CORS 설정 추가

        userService.signUp(user);
        boolean result = true;

        headers.set("Result", String.valueOf(result));

        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    @GetMapping("/admin/users/{userCode}")
    @Transactional
    public ResponseEntity<Map<String, Object>> selectUserByCodeByAdmin(@PathVariable int userCode) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Map<String, Object> result = new HashMap<>();
        result.put("user", userService.selectUserByCodeByAdmin(userCode));

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

    @PutMapping("/admin/users")
    public ResponseEntity<?> updateUserByAdmin(@RequestBody Map<String, String> object) {

        int userCode = Integer.parseInt(object.get("userCode"));

        userService.updateUserByAdmin(userCode);

        return ResponseEntity
                .created(URI.create("/admin/users/" + userCode))
                .build();
    }

    @DeleteMapping("/admin/users/{userCode}")
    public ResponseEntity<?> deleteUserByAdmin(@PathVariable int userCode) {

        userService.deleteUserByAdmin(userCode);

        return ResponseEntity.noContent().build();
    }


    @PostMapping("/user/check")
    public ResponseEntity<?> checkInfo(@RequestBody Map<String, String> user) {

        String type = user.get("type");
        String info = user.get("info");

        System.out.println("type = " + type);
        System.out.println("info = " + info);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        headers.set("Access-Control-Expose-Headers", "Result"); // CORS 설정 추가

        boolean result = true;

        if (info.trim().isEmpty()) {
            result = false;
        } else {
            if (userService.checkInfo(type, info)) {
                result = false;
            } else {
                result = true;
            }
        }
        headers.set("Result", String.valueOf(result));
        System.out.println("result = " + result);

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

    @PostMapping("/user/find/email")
    public ResponseEntity<?> findEmailByPhone(@RequestBody Map<String, String> requestBody) {
        String phoneNumber = requestBody.get("phoneNumber");
        UserDTO user = userService.findEmailByPhone(phoneNumber);  // 조회된 userId
        Map<String, String> result = new HashMap<>();
        result.put("userId", user.getUserId());
        result.put("signupDate", user.getUserSignup());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        headers.set("Access-Control-Expose-Headers", "Result");

        headers.set("Result", result.toString());

        if (user.getUserId() != null) {
                return new ResponseEntity<>(headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/user/change/pwd")
    public ResponseEntity<?> updateUserPwd(@RequestBody Map<String, String> requestBody) {

        String id = requestBody.get("id");
        String pwd = requestBody.get("pwd");
        userService.updateUserPwd(id, pwd);


        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/user/release/sleep")
    public ResponseEntity<?> updateSleep(@RequestBody Map<String, String> requestBody) {

        String id = requestBody.get("id");

        userService.updateSleep(id);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
