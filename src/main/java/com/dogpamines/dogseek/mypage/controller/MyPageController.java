package com.dogpamines.dogseek.mypage.controller;

import com.dogpamines.dogseek.curation.model.dto.CurationDTO;
import com.dogpamines.dogseek.curation.model.dto.HistoryDTO;
import com.dogpamines.dogseek.mypage.model.service.MyPageService;
import com.dogpamines.dogseek.products.model.dto.ProductsDTO;
import com.dogpamines.dogseek.user.model.dto.UserDTO;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
//        System.out.println("curationCode = " + curationCode);
//        /* 응답 헤더 설정 */
//        HttpHeaders headers = new HttpHeaders();
//
//        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
//
//        /* 응답 데이터 설정 */
//        Map<String, Object> result = new HashMap<>();
//        result.put("mycurationresult", historyDTOS);
//        System.out.println("result = " + result);
//
//        return new ResponseEntity<>(result, headers, HttpStatus.OK);
//
//    }

    @GetMapping("/mycurationresult")
    public ResponseEntity<Map<String, Object>> selectMyCurationResult(@RequestParam int curationCode) throws NotFoundException {
        List<Integer> prodCodes = myPageService.selectProdCodesByCurationCode(curationCode);
        if (prodCodes == null || prodCodes.isEmpty()) {
            throw new NotFoundException("No history found for curationCode: " + curationCode);
        }

        List<ProductsDTO> products = myPageService.selectProductsByProdCodes(prodCodes);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Map<String, Object> result = new HashMap<>();
        result.put("mycurationresult", products);

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

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
    public ResponseEntity<?> updateUser(@RequestBody UserDTO updateInfo){
        try {
            myPageService.updateUser(updateInfo);

            updateInfo.setUserPass(updateInfo.getUserPass());
            updateInfo.setUserNick(updateInfo.getUserNick());

            return ResponseEntity
                    .created(URI.create("/mypage/" + updateInfo.getUserCode()))
                    .build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating user info: " +e.getMessage());
        }
    }

    /* 유저 닉네임 조회 */
    @PostMapping("/mypage/check")
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
            if (myPageService.checkInfo(type, info)) {
                result = false; // 중복된 닉네임이 있는 경우 false
            } else {
                result = true;
            }
        }
        headers.set("Result", String.valueOf(result));
        System.out.println("result = " + result);

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }


    /* 회원 탈퇴(휴면 변경) */
    @DeleteMapping("/mypage/{userCode}")
    public ResponseEntity<?> deleteUser(@PathVariable int userCode) {

        myPageService.deleteUser(userCode);

        return ResponseEntity.noContent().build();
    }
}
