package com.dogpamines.dogseek.curation.controller;

import com.dogpamines.dogseek.curation.model.dto.CurationDTO;
import com.dogpamines.dogseek.curation.model.dto.HistoryDTO;
import com.dogpamines.dogseek.curation.model.service.CurationService;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Tag(name = "Curation(큐레이션) Controller")
@RestController
public class CurationController {

    private CurationService curationService;

    @Autowired
    public CurationController(CurationService curationService) {this.curationService = curationService;}
    @Operation(summary = "큐레이션 내용 저장", description = "회원이 큐레이션 한 내용을 DB에 저장한다.")
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
    @Operation(summary = "맞춤 사료 추천", description = "회원이 큐레이션 한 내용을 바탕으로 맞춤 사료를 추천한다.")
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
    @Operation(summary = "해당 큐레이션 코드 조회", description = "해당 큐레이션 히스토리 DB에 맞춤 사료 코드를 저장하기 위해 큐레이션 코드 조회")
    @GetMapping("/curation/search")
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
    @Operation(summary = "맞춤사료 내용 저장", description = "해당 큐레이션 코드를 가져와 히스토리 DB에 맞춤 사료 코드를 저장한다.")
    @PostMapping("/curation/products")
    public ResponseEntity<?> curationProductsInsert(@RequestBody HistoryDTO historyDTO) {

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(new MediaType("application", "JSON", Charset.forName("UTF-8")));

        curationService.insertHistory(historyDTO);

        return ResponseEntity
                .created(URI.create("/curation"))
                .build();
    }
}
