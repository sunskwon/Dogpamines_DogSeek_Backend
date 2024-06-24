package com.dogpamines.dogseek.dict.controller;

import com.dogpamines.dogseek.dict.model.dto.DictDTO;
import com.dogpamines.dogseek.dict.model.service.DictService;
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

    @GetMapping("/dict/{dogCode}")
    public ResponseEntity<Map<String, Object>> selectByCode(@PathVariable int dogCode){

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Map<String, Object> result = new HashMap<>();
        result.put("dict", dictService.selectByCode(dogCode));

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

    @GetMapping("/dict/search")
    public ResponseEntity<Map<String, Object>> searchDog(@RequestParam("dogName") String dogName){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Map<String, Object> result = new HashMap<>();
        result.put("dict", dictService.searchDog(dogName));

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

    @GetMapping("/dict/dictsearch")
    public ResponseEntity<Map<String, Object>> dictSearch(@RequestParam("type") String type, @RequestParam("input") String input) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Map<String, Object> result = new HashMap<>();
        result.put("dict", dictService.dictSearch(type, input));

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

    @PostMapping("/dict")
    public ResponseEntity<Map<String, Object>> insertDict(@RequestBody DictDTO dict) {

        int newDictCode = dictService.getLastDogCode() + 1;
        dict.setDogCode(newDictCode);

        dictService.insertDict(dict);

        return ResponseEntity
                .created(URI.create("/dict/" + newDictCode))
                .build();
    }

    @PutMapping("/dict")
    public ResponseEntity<?> updateDict(@RequestBody DictDTO dict) {

        dictService.updateDict(dict);

        return ResponseEntity
                .created(URI.create("/dict/" + dict.getDogCode()))
                .build();
    }

    @DeleteMapping("/dict/{dogCode}")
    public ResponseEntity<?> deleteDict(@PathVariable int dogCode) {

        dictService.deleteDict(dogCode);

        return ResponseEntity.noContent().build();
    }
}
