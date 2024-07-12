package com.dogpamines.dogseek.admin.controller;

import com.dogpamines.dogseek.admin.model.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
@Tag(name = "Admin(관리자) Controller")
@RestController
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
    @Operation(summary = "관리자 페이지 조회", description = "관리자는 관리자 대쉬보드 페이지를 조회할 수 있다.")
    @GetMapping("dashboard")
    public ResponseEntity<Map<String, Object>> selectAllCounts() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Map<String, Object> result = new HashMap<>();
        result.put("Counts", adminService.selectAllCounts());

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }
}
