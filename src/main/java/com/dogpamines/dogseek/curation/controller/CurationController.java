package com.dogpamines.dogseek.curation.controller;

import com.dogpamines.dogseek.curation.model.dto.CurationDTO;
import com.dogpamines.dogseek.curation.model.dto.HistoryDTO;
import com.dogpamines.dogseek.curation.model.service.CurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CurationController {

    private CurationService curationService;

    @Autowired
    public CurationController(CurationService curationService) {this.curationService = curationService;}

    @PostMapping("/curation")
    public ResponseEntity<?> curation(@RequestBody CurationDTO curationDTO) {

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(new MediaType("application", "JSON", Charset.forName("UTF-8")));

        Integer lastCuration = curationService.lastCuration();
        lastCuration = (lastCuration != null) ? lastCuration : 0;
        if (lastCuration == 0) {
            curationDTO.setCurationCode(lastCuration + 1);
            curationService.insertCuration(curationDTO);
            return ResponseEntity
                    .created(URI.create("/curation"))
                    .build();
        } else {
            int newCuration = lastCuration + 1;
            curationDTO.setCurationCode(newCuration);
            curationService.insertCuration(curationDTO);
            return ResponseEntity
                    .created(URI.create("/curation" + newCuration))
                    .build();
        }
    }

    @GetMapping("/curation")
    public ResponseEntity<Map<String, Object>> curationProducts(@RequestParam String curationAge, String curationIngra,
                                                                String curationDisease, String curationAllergy,
                                                                String curationSize, String curationCook) {
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(new MediaType("application", "JSON", Charset.forName("UTF-8")));

        Map<String, Object> result = new HashMap<>();
        result.put("curationProducts", curationService.curationProducts(curationAge, curationIngra, curationDisease, curationAllergy, curationSize, curationCook));

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

    @GetMapping("/curationSelect")
    public ResponseEntity<Map<String, Object>> curationSelect(@RequestParam String curationAge, String curationIngra, String curationDisease,
                                            String curationAllergy, String curationBreed, String curationGender,
                                            String curationNeut, String curationWeight, String curationName,
                                            String curationDate, String curationSize, String curationCook, int userCode ) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = dateFormat.parse(curationDate);

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(new MediaType("application","JSON", Charset.forName("UTF-8")));

        Map<String, Object> result = new HashMap<>();
        result.put("curationSelect", curationService.curationSelect(curationAge, curationIngra, curationAllergy,
                curationDisease, curationBreed, curationGender,
                curationNeut, curationWeight, curationName,
                parsedDate, curationSize, curationCook, userCode));

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

    @PostMapping("/curationProducts")
    public ResponseEntity<?> curationProductsInsert(@RequestBody HistoryDTO historyDTO) {

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(new MediaType("application", "JSON", Charset.forName("UTF-8")));

        curationService.insertHistory(historyDTO);

        return ResponseEntity
                .created(URI.create("/curation"))
                .build();
    }
}
