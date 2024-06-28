package com.dogpamines.dogseek.main.controller;

import com.dogpamines.dogseek.main.model.service.MainService;
import com.dogpamines.dogseek.products.model.dto.ProductsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MainController {

    private final MainService mainService;

    @Autowired
    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    /* 최신 사료 정보 가져오기 */
    @GetMapping("/lastProds")
    public ResponseEntity<Map<String, Object>> selectLastProds(){

        List<ProductsDTO> productsDTOS = mainService.selectLastProds();

        /* 응답 헤더 설정 */
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        /* 응답 데이터 설정 */
        Map<String, Object> result = new HashMap<>();
        result.put("lastProds", productsDTOS);

        return new ResponseEntity<>(result,headers, HttpStatus.OK);
    }

}
