package com.dogpamines.dogseek.user.controller;

import com.dogpamines.dogseek.user.model.dto.UserDTO;
import com.dogpamines.dogseek.user.model.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "USER(회원) Controller")
@RestController
public class UserController {

    private UserService userService;        // 생성자 주입으로 하기!!!

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "회원 가입" , description = "회원 정보를 입력하여 회원 가입을한다.")
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
    @Operation(summary = "관리자 회원 코드로 조회" , description = "관리자는 회원을 코드로 조회한다.")
    @GetMapping("/admin/users/{userCode}")
    @Transactional
    public ResponseEntity<Map<String, Object>> selectUserByCodeByAdmin(@PathVariable int userCode) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Map<String, Object> result = new HashMap<>();
        result.put("user", userService.selectUserByCodeByAdmin(userCode));

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }
    @Operation(summary = "회원 전체 조회" , description = "관리자는 전체 회원을 조회한다.")
    @PostMapping("/admin/users")
    public ResponseEntity<Map<String, Object>> selectAllUsersByAdmin(@RequestBody Map<String, String> search) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Map<String, Object> result = new HashMap<>();
        result.put("users", userService.selectAllUsersByAdmin(search));

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }
    @Operation(summary = "회원 정보 수정" , description = "관리자는 회원의 정보를 수정한다.")
    @PutMapping("/admin/users")
    public ResponseEntity<?> updateUserByAdmin(@RequestBody Map<String, String> object) {

        int userCode = Integer.parseInt(object.get("userCode"));

        userService.updateUserByAdmin(userCode);

        return ResponseEntity
                .created(URI.create("/admin/users/" + userCode))
                .build();
    }
    @Operation(summary = "회원 휴면 처리" , description = "관리자는 회원을 휴면회원 처리한다.")
    @DeleteMapping("/admin/users/{userCode}")
    public ResponseEntity<?> deleteUserByAdmin(@PathVariable int userCode) {

        userService.deleteUserByAdmin(userCode);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "회원 정보 중복확인" , description = "회원 정보 변경 시 중복 값을 확인한다.")
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
    @Operation(summary = "이메일 찾기" , description = "가입 시 입력한 휴대폰 번호로 아이디(이메일)을 확인한다.")
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
    @Operation(summary = "비밀번호 수정" , description = "회원은 비밀번호를 수정한다.")
    @PutMapping("/user/change/pwd")
    public ResponseEntity<?> updateUserPwd(@RequestBody Map<String, String> requestBody) {

        String id = requestBody.get("id");
        String pwd = requestBody.get("pwd");
        userService.updateUserPwd(id, pwd);


        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @Operation(summary = "휴면 회원 정상 회원으로 변경" , description = "휴면회원에서 정보 입력을 한 뒤 정상회원으로 권한이 수정된다.")
    @PutMapping("/user/release/sleep")
    public ResponseEntity<?> updateSleep(@RequestBody Map<String, String> requestBody) {

        String id = requestBody.get("id");

        userService.updateSleep(id);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
