package com.dogpamines.dogseek.animalRegist.model.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Service
public class AnimalRegistService {

    public String getAnimalInfo (String dogRegNo, String rfidCd, String ownerNm, String ownerBirth) throws IOException {

        final String key = " "; //보안을 위하여 yml로 이동 필요
        /*String dogRegNo = "410000001513331";
        String rfidCd = "";
        String ownerNm = "홍길동";
        String ownerBirth = "";*/

        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1543061/animalInfoSrvc/animalInfo"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + key); /*Service Key*/

        if(dogRegNo != null && !dogRegNo.isEmpty()) {
            urlBuilder.append("&" + URLEncoder.encode("dog_reg_no","UTF-8") + "=" + URLEncoder.encode(dogRegNo, "UTF-8")); /*동물등록번호 또는 RFID코드 필수*/
        }
        if(rfidCd != null && !rfidCd.isEmpty()){
            urlBuilder.append("&" + URLEncoder.encode("rfid_cd","UTF-8") + "=" + URLEncoder.encode(rfidCd, "UTF-8")); /*동물등록번호 또는 RFID코드 필수*/
        }
        if(ownerNm != null && !ownerNm.isEmpty()){
            urlBuilder.append("&" + URLEncoder.encode("owner_nm","UTF-8") + "=" + URLEncoder.encode(ownerNm, "UTF-8")); /*소유자 성명 또는 생년월일 필수*/
        }
        if(ownerBirth != null && !ownerBirth.isEmpty()){
            urlBuilder.append("&" + URLEncoder.encode("owner_birth","UTF-8") + "=" + URLEncoder.encode(ownerBirth, "UTF-8")); /*소유자 성명 또는 생년월일 필수*/
        }
        urlBuilder.append("&" + URLEncoder.encode("_type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*xml(기본값) 또는 json*/

        URL url = new URL(urlBuilder.toString());

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        System.out.println("Response code: " + conn.getResponseCode());

        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }

        rd.close();
        conn.disconnect();
        return sb.toString();
    }
}
