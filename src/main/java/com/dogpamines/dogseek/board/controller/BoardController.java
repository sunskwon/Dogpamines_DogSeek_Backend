package com.dogpamines.dogseek.board.controller;

import com.dogpamines.dogseek.board.model.dto.BoardPostDTO;
import com.dogpamines.dogseek.board.model.dto.BoardDTO;
import com.dogpamines.dogseek.board.model.dto.BoardReportDTO;
import com.dogpamines.dogseek.board.model.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "Board(게시판) Controller")
@RestController
public class BoardController {

    private BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }
    @Operation(summary = "전체 게시물 조회", description = "모든 게시물을 조회한다.")
    @GetMapping("/boards")
    public ResponseEntity<Map<String, Object>> selectAllPosts() {

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Map<String, Object> result = new HashMap<>();
        result.put("boards", boardService.selectAllPosts());

        System.out.println("select all post");

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }
    @Operation(summary = "게시물코드로 게시물 조회", description = "게시물 클릭 시 해당 게시물 상세 정보를 조회할 수 있다.")
    @GetMapping("/boards/{postCode}")
    public ResponseEntity<Map<String, Object>> selectPost(@PathVariable int postCode) {

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Map<String, Object> result = new HashMap<>();
        result.put("boards", boardService.selectPost(postCode));

        return new ResponseEntity<>(result, headers, HttpStatus.OK);

    }
    @Operation(summary = "게시물 검색", description = "게시물의 제목으로 게시물을 검색한다.")
    @GetMapping("/boards/search")
    public ResponseEntity<Map<String, Object>> searchBoards(@RequestParam("type") String postTitle) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Map<String, Object> result = new HashMap<>();
        result.put("boards", boardService.searchBoards(postTitle));

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }
    @Operation(summary = "게시물 입력", description = "회원은 게시물을 입력할 수 있다.")
    @PostMapping("/boards")
    public ResponseEntity<?> newBoardPost(@RequestBody BoardPostDTO board) {
        LocalDateTime postDate = LocalDateTime.now();
        board.setPostDate(postDate);

        int postCode = boardService.findLastPostCode() + 1;

        board.setPostCode(postCode);

        boardService.newBoardPost(board);

        return ResponseEntity.noContent().build();
    }
    @Operation(summary = "게시물 내용 수정", description = "회원은 본인이 작성한 게시물의 내용을 수정할 수 있다.")
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
    @Operation(summary = "게시물 삭제", description = "회원은 본인이 작성한 게시물을 삭제할 수 있다.")
    @DeleteMapping("/boards/{postCode}")
    public ResponseEntity<?> deleteBoard(@PathVariable int postCode) {

        System.out.println("postCode = " + postCode);
        System.out.println("select One post delete");

        boardService.deleteBoard(postCode);

        System.out.println("postCode = " + postCode);
        System.out.println("select One post delete");

        return ResponseEntity.noContent().build();
    }
    @Operation(summary = "게시물 신고", description = "회원은 다른 사용자가 작성한 게시물을 신고할 수 있다.")
    @PostMapping("/report/{postCode}")
    public ResponseEntity<String> reportPost(@PathVariable int postCode, @RequestBody BoardReportDTO report) {
        LocalDateTime reportDate = LocalDateTime.now();
        report.setPostCode(postCode);
        report.setReportDate(reportDate.toString());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
            boardService.reportPost(report);
            System.out.println("신고가 성공적으로 접수되었습니다.");
            return ResponseEntity.ok().body("신고가 성공적으로 접수되었습니다.");
        } catch (Exception e) {
            System.out.println("신고 접수 중 오류 발생: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("신고 접수 중 오류가 발생했습니다.");
        }
    }
    
    @Operation(summary = "관리자 전체 공지사항 조회", description = "관리자는 모든 공지사항을 조회할 수 있다.")
    @GetMapping("/notice")
    public ResponseEntity<Map<String, Object>> selectAllNotices() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Map<String, Object> result = new HashMap<>();
        result.put("notice", boardService.selectAllNotices());

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }
    @Operation(summary = "관리자 해당 공지사항 검색", description = "관리자는 제목,내용,닉네임으로 공지사항을 조회할 수 있다.")
    @GetMapping("/notice/search")
    public ResponseEntity<Map<String, Object>> searchNotice(@RequestParam("type") String type, @RequestParam("input") String input) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Map<String, Object> result = new HashMap<>();
        result.put("notice", boardService.searchNotice(type, input));

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }
    @Operation(summary = "관리자 전체 게시물 조회", description = "관리자는 모든 게시물을 조회할 수 있다.")
    @GetMapping("/board")
    @Transactional
    public ResponseEntity<Map<String, Object>> selectAllBoards() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Map<String, Object> result = new HashMap<>();
        result.put("board", boardService.selectAllBoards());

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }
    @Operation(summary = "관리자 해당 게시물 검색", description = "관리자는 제목,내용,닉네임으로 공지사항을 조회할 수 있다.")
    @GetMapping("/board/search")
    @Transactional
    public ResponseEntity<Map<String, Object>> selectBoard(@RequestParam("type") String type, @RequestParam("input") String input) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Map<String, Object> result = new HashMap<>();
        result.put("board", boardService.searchBoard(type, input));

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }
    @Operation(summary = "관리자 게시물코드로 게시물 조회", description = "관리자는 게시물 클릭 시 해당 게시물의 상세 정보를 조회할 수 있다.")
    @GetMapping("/post/{postCode}")
    public ResponseEntity<Map<String, Object>> selectBoardByCode(@PathVariable int postCode) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Map<String, Object> result = new HashMap<>();
        result.put("notice", boardService.selectBoardByCode(postCode));

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }
    @Operation(summary = "관리자 게시물 등록", description = "관리자는 게시물을 등록할 수 있다.")
    @PostMapping("/post")
    public ResponseEntity<?> insertPost(@RequestBody BoardDTO notice) {

        int postCode = boardService.findLastPostCode() + 1;

        notice.setPostCode(postCode);

        boardService.insertPost(notice);

        return ResponseEntity.noContent().build();
    }
    @Operation(summary = "관리자 게시물 삭제", description = "관리자는 게시물을 삭제할 수 있다.")
    @DeleteMapping("/post/{postCode}")
    public ResponseEntity<?> deletePost(@PathVariable int postCode) {

        String postStatus = boardService.findPostStatus(postCode);

        boardService.deletePost(postCode, postStatus);

        return ResponseEntity.noContent().build();
    }
    @Operation(summary = "관리자 게시물 수정", description = "관리자는 게시물을 수정할 수 있다.")
    @PutMapping("/post")
    public ResponseEntity<?> updatePost(@RequestBody BoardDTO notice) {

        System.out.println("notice = " + notice);

        boardService.updatePost(notice);

        return ResponseEntity
                .created(URI.create("/post/" + notice.getPostCode()))
                .build();
    }

}
