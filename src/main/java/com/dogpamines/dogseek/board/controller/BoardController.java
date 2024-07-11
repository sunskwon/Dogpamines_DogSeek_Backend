package com.dogpamines.dogseek.board.controller;

import com.dogpamines.dogseek.board.model.dto.BoardPostDTO;
import com.dogpamines.dogseek.board.model.dto.BoardDTO;
import com.dogpamines.dogseek.board.model.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
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
    public ResponseEntity<Map<String, Object>> selectPost(@PathVariable int postCode) {

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Map<String, Object> result = new HashMap<>();
        result.put("boards", boardService.selectPost(postCode));

        return new ResponseEntity<>(result, headers, HttpStatus.OK);

    }
    @GetMapping("/boards/search")
    public ResponseEntity<Map<String, Object>> searchBoards(@RequestParam("type") String postTitle) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Map<String, Object> result = new HashMap<>();
        result.put("boards", boardService.searchBoards(postTitle));

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

    @PostMapping("/boards")
    public ResponseEntity<?> newBoardPost(@RequestBody BoardPostDTO board) {
        LocalDateTime postDate = LocalDateTime.now();
        board.setPostDate(postDate);

        int postCode = boardService.findLastPostCode() + 1;

        board.setPostCode(postCode);

        boardService.newBoardPost(board);

        return ResponseEntity.noContent().build();
    }
    @PutMapping("/boards")
    public ResponseEntity<?> updateBoard(@RequestBody BoardPostDTO board) {
        LocalDateTime postDate = LocalDateTime.now();
        board.setPostDate(postDate);

        boardService.updateBoard(board);

        System.out.println("board = " + board);

        return ResponseEntity
                .created(URI.create("/boards/" + board.getPostCode()))
                .build();
    }

    @DeleteMapping("/boards/{postCode}")
    public ResponseEntity<?> deleteBoard(@PathVariable int postCode) {

        System.out.println("postCode = " + postCode);
        System.out.println("select One post delete");

        boardService.deleteBoard(postCode);

        System.out.println("postCode = " + postCode);
        System.out.println("select One post delete");

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/notice")
    public ResponseEntity<Map<String, Object>> selectAllNotices() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Map<String, Object> result = new HashMap<>();
        result.put("notice", boardService.selectAllNotices());

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

    @GetMapping("/notice/search")
    public ResponseEntity<Map<String, Object>> searchNotice(@RequestParam("type") String type, @RequestParam("input") String input) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Map<String, Object> result = new HashMap<>();
        result.put("notice", boardService.searchNotice(type, input));

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

    @GetMapping("/board")
    @Transactional
    public ResponseEntity<Map<String, Object>> selectAllBoards() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Map<String, Object> result = new HashMap<>();
        result.put("board", boardService.selectAllBoards());

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

    @GetMapping("/board/search")
    @Transactional
    public ResponseEntity<Map<String, Object>> selectBoard(@RequestParam("type") String type, @RequestParam("input") String input) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Map<String, Object> result = new HashMap<>();
        result.put("board", boardService.searchBoard(type, input));

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

    @GetMapping("/post/{postCode}")
    public ResponseEntity<Map<String, Object>> selectBoardByCode(@PathVariable int postCode) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Map<String, Object> result = new HashMap<>();
        result.put("notice", boardService.selectBoardByCode(postCode));

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

    @PostMapping("/post")
    public ResponseEntity<?> insertPost(@RequestBody BoardDTO notice) {

        int postCode = boardService.findLastPostCode() + 1;

        notice.setPostCode(postCode);

        boardService.insertPost(notice);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/post/{postCode}")
    public ResponseEntity<?> deletePost(@PathVariable int postCode) {

        String postStatus = boardService.findPostStatus(postCode);

        boardService.deletePost(postCode, postStatus);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/post")
    public ResponseEntity<?> updatePost(@RequestBody BoardDTO notice) {

        System.out.println("notice = " + notice);

        boardService.updatePost(notice);

        return ResponseEntity
                .created(URI.create("/post/" + notice.getPostCode()))
                .build();
    }

}
