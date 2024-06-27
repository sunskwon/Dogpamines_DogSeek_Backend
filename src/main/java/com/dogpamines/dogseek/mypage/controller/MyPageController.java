package com.dogpamines.dogseek.mypage.controller;

import com.dogpamines.dogseek.curation.model.dto.CurationDTO;
import com.dogpamines.dogseek.curation.model.dto.HistoryDTO;
import com.dogpamines.dogseek.mypage.model.service.MyPageService;
import com.dogpamines.dogseek.user.model.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class MyPageController {

    private final MyPageService myPageService;

    @Autowired
    public MyPageController(MyPageService myPageService) {
        this.myPageService = myPageService;
    }

    /* 큐레이션 전체 조회 */
    @GetMapping("/curationList")
    public ResponseEntity<Map<String, Object>> allCurationList(){

        List<CurationDTO> curationDTOList = myPageService.findAllCuration();

        /* 응답 헤더 설정 */
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        /* 응답 데이터 설정 */
        Map<String, Object> result = new HashMap<>();
        result.put("curations", curationDTOList);

        return new ResponseEntity<>(result,headers, HttpStatus.OK);
    }

    /* 큐레이션 유저별 상세 조회 */
    @GetMapping("/curations")
    public ResponseEntity<Map<String, Object>> userCurationList(@RequestParam int userCode){

        List<CurationDTO> curationDTOList = myPageService.userCurationList(userCode);

        /* 응답 헤더 설정 */
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        /* 응답 데이터 설정 */
        Map<String, Object> result = new HashMap<>();
        result.put("curations", curationDTOList);

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

    /* 큐레이션 유저별 반려견별 상세 조회 */
    @GetMapping("/curationsDog")
    public ResponseEntity<Map<String, Object>> selectUserDogCurationList(@RequestParam int userCode, String curationName){
        List<CurationDTO> curationDTOList = myPageService.selectUserDogCurationList(userCode, curationName);

        /* 응답 헤더 설정 */
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        /* 응답 데이터 설정 */
        Map<String, Object> result = new HashMap<>();
        result.put("curationsDog", curationDTOList);

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

    /* 큐레이션 코드로 맞춤 사료 추천 페이지 넘어가기 */
//    @GetMapping("/mycurationresult")
//    public  ResponseEntity<Map<String, Object>> selectMyCurationResult(@RequestParam int curationCode) {
//        List<HistoryDTO> historyDTOS = myPageService
//                .selectMyCurationResult(curationCode);
//    }

    /* 회원 상세 정보 조회 */
    @GetMapping("/mypage")
    public ResponseEntity<Map<String, Object>> selectUserDetail(@RequestParam int userCode) {
        List<UserDTO> userDTOS = myPageService.selectUserDetail(userCode);

        /* 응답 헤더 설정 */
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        /* 응답 데이터 설정 */
        Map<String, Object> result = new HashMap<>();
        result.put("users", userDTOS);

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

    /* 회원 정보 수정 */
    @PutMapping("/mypage")
    public ResponseEntity<?> updateUser(@RequestParam(value = "userCode", required = false, defaultValue = "1") int userCode,@RequestBody UserDTO updateInfo){

        myPageService.updateUser(updateInfo);

        updateInfo.setUserPass(updateInfo.getUserPass());
        updateInfo.setUserNick(updateInfo.getUserNick());

        return ResponseEntity
                .created(URI.create("/mypage/" + updateInfo.getUserCode()))
                .build();
    }

    /* 회원 탈퇴(휴면 변경) */
    @DeleteMapping("/mypage")
    public ResponseEntity<?> deleteUser(@RequestParam(value = "userCode", required = false, defaultValue = "2") int userCode) {

        String userAuth = myPageService.findUserAuth(userCode);

        myPageService.deleteUser(userAuth, userCode);

        return ResponseEntity.noContent().build();
    }
}
