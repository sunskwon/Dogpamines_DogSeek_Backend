package com.dogpamines.dogseek.animalRegist.controller;

import com.dogpamines.dogseek.animalRegist.model.service.AnimalRegistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

@RestController
public class AnimalRegistController {

    private final AnimalRegistService animalRegistService;

    @Autowired
    public AnimalRegistController(AnimalRegistService animalRegistService){
        this.animalRegistService = animalRegistService;
    }

    @GetMapping("/animalRegist")
    public String registNumber (@RequestParam(required = false) String dogRegNo,
                                @RequestParam(required = false) String rfidCd,
                                @RequestParam(required = false) String ownerNm,
                                @RequestParam(required = false) String ownerBirth
                                ) throws IOException {

        return animalRegistService.getAnimalInfo(dogRegNo, rfidCd, ownerNm, ownerBirth);

    }
}