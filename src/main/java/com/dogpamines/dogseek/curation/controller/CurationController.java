package com.dogpamines.dogseek.curation.controller;

import com.dogpamines.dogseek.curation.model.dto.CurationDTO;
import com.dogpamines.dogseek.curation.model.service.CurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.nio.charset.Charset;

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
}
