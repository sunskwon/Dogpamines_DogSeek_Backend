package com.dogpamines.dogseek.mypage.controller;

import com.dogpamines.dogseek.curation.model.dto.CurationDTO;
import com.dogpamines.dogseek.mypage.model.service.MyPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Map<String, Object>> userCurationList(@RequestParam(value = "userCode", required = false, defaultValue = "1") int userCode){

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
    @GetMapping("/curationsdog")
    public ResponseEntity<Map<String, Object>> selectUserDogCurationList(@RequestParam(value = "userCode", required = false, defaultValue = "1") int userCode, @RequestParam(value = "curationName", required = false, defaultValue = "나나") String curationName){
        List<CurationDTO> curationDTOList = myPageService.selectUserDogCurationList(userCode,curationName);

        /* 응답 헤더 설정 */
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        /* 응답 데이터 설정 */
        Map<String, Object> result = new HashMap<>();
        result.put("curations", curationDTOList);

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }


}
