package com.dogpamines.dogseek.dict.controller;

import com.dogpamines.dogseek.dict.model.dto.DictDTO;
import com.dogpamines.dogseek.dict.model.service.DictService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Tag(name = "Dict(견종백과) Controller")
@RestController
public class DictController {

    private DictService dictService;

    @Autowired
    public DictController(DictService dictService) {

        this.dictService = dictService;
    }
    @Operation(summary = "견종 전체 조회" , description = "모든 견종의 정보를 조회한다.")
    @GetMapping("/dict")
    public ResponseEntity<Map<String, Object>> selectAllDog() {

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Map<String, Object> result = new HashMap<>();
        result.put("dict", dictService.selectAllDog());

        System.out.println("AllDog");

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

    @Operation(summary = "견종 상세 페이지" , description = "견종의 이름으로 상세페이지에 들어간다.")
   @GetMapping("/dict/{dogName}")
   public ResponseEntity<Map<String, Object>> selectByName(@PathVariable String dogName) {

       HttpHeaders headers = new HttpHeaders();

       headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

       Map<String, Object> result = new HashMap<>();
       result.put("dict", dictService.selectByName(dogName));

       return new ResponseEntity<>(result, headers, HttpStatus.OK);
   }
    @Operation(summary = "관리자 견종 코드로 조회" , description = "관리자는 견종을 코드로 조회한다.")
     @GetMapping("/dict/get/{dogCode}")
     public ResponseEntity<Map<String, Object>> selectByCode(@PathVariable int dogCode) {

         HttpHeaders headers = new HttpHeaders();

         headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

         Map<String, Object> result = new HashMap<>();
         result.put("dict", dictService.selectByCode(dogCode));

         return new ResponseEntity<>(result, headers, HttpStatus.OK);
     }
    @Operation(summary = "견종 이름 조회" , description = "견종의 이름으로 정보를 조회한다.")
    @GetMapping("/dict/search")
    public ResponseEntity<Map<String, Object>> searchDog(@RequestParam("dogName") String dogName) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Map<String, Object> result = new HashMap<>();
        result.put("dict", dictService.searchDog(dogName));

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }
    @Operation(summary = "관리자 견종 조회" , description = "관리자는 견종의 이름과 크기로 정보를 조회한다.")
    @GetMapping("/dict/dictsearch")
    public ResponseEntity<Map<String, Object>> dictSearch(@RequestParam("type") String type, @RequestParam("input") String input) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Map<String, Object> result = new HashMap<>();
        result.put("dict", dictService.dictSearch(type, input));

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }
    @Operation(summary = "견종 정보 등록" , description = "관리자는 새로운 견종을 등록할 수 있다.")
    @PostMapping("/dict")
    public ResponseEntity<Map<String, Object>> insertDict(@RequestBody DictDTO dict) {

        int newDictCode = dictService.getLastDogCode() + 1;
        dict.setDogCode(newDictCode);

        dictService.insertDict(dict);

        return ResponseEntity
                .created(URI.create("/dict/get/" + newDictCode))
                .build();
    }
    @Operation(summary = "견종 정보 수정" , description = "관리자는 견종의 정보를 수정할 수 있다.")
    @PutMapping("/dict")
    public ResponseEntity<?> updateDict(@RequestBody DictDTO dict) {

        dictService.updateDict(dict);

        return ResponseEntity
                .created(URI.create("/dict/get/" + dict.getDogCode()))
                .build();
    }
    @Operation(summary = "견종 정보 삭제" , description = "관리자는 견종의 정보를 삭제할 수 있다.")
    @DeleteMapping("/dict/{dogCode}")
    public ResponseEntity<?> deleteDict(@PathVariable int dogCode) {

        dictService.deleteDict(dogCode);

        return ResponseEntity.noContent().build();
    }
}
