package com.dogpamines.dogseek.board.controller;

import com.dogpamines.dogseek.board.model.dto.BoardDTO;
import com.dogpamines.dogseek.board.model.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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
        result.put("boards", boardService.selectAllPosts());

        System.out.println("select all post");

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

    @GetMapping("/boards/{postCode}")
    public ResponseEntity<Map<String, Object>>selectPost(@PathVariable int postCode) {

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Map<String, Object> result = new HashMap<>();
        result.put("boards", boardService.selectPost(postCode));

        return new ResponseEntity<>(result, headers, HttpStatus.OK);

    }

    @PostMapping("/boards")
    public ResponseEntity<?> newBoardPost(@RequestBody BoardDTO board) {

        int newPostCode = BoardService.getLastPostCode() + 1;
        board.setPostCode(newPostCode);

        BoardService.newBoardPost(board);

        return ResponseEntity
                .created(URI.create("/boards/" + newPostCode))
                .build();
    }
}
