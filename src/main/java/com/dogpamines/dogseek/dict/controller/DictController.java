package com.dogpamines.dogseek.dict.controller;

import com.dogpamines.dogseek.dict.model.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

@RestController
public class DictController {

    private DictService dictService;

    @Autowired
    public DictController(DictService dictService){

            this.dictService = dictService;
    }

    @GetMapping("/dict")
    public ResponseEntity<Map<String, Object>> selectAllDog() {

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Map<String, Object> result = new HashMap<>();
        result.put("dict", dictService.selectAllDog());

        System.out.println("AllDog");

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }


}
