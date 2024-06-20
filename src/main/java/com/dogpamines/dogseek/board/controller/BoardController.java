package com.dogpamines.dogseek.board.controller;

import com.dogpamines.dogseek.board.model.service.BoardService;
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
import java.util.Map;

@RestController
public class BoardController {

    private BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/boards")
    public ResponseEntity<Map<String, Object>> selectAllPosts() {

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Map<String, Object> result = new HashMap<>();
        result.put("Post", boardService.selectAllPosts());

        System.out.println("select all post");

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

    @GetMapping("/boards/{postCode}")
    public ResponseEntity<Map<String, Object>>selectPost(@PathVariable int postCode) {

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Map<String, Object> result = new HashMap<>();
        result.put("Post", boardService.selectPost(postCode));

        return new ResponseEntity<>(result, headers, HttpStatus.OK);

    }
}
