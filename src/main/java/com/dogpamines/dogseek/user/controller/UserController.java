package com.dogpamines.dogseek.user.controller;

import com.dogpamines.dogseek.board.model.dto.BoardDTO;
import com.dogpamines.dogseek.board.model.service.BoardService;
import com.dogpamines.dogseek.curation.model.service.CurationService;
import com.dogpamines.dogseek.user.model.dto.UserDTO;
import com.dogpamines.dogseek.user.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    private UserService userService;        // 생성자 주입으로 하기!!!
    private CurationService curationService;
    private BoardService boardService;

    @Autowired
    public UserController(UserService userService, CurationService curationService, BoardService boardService) {
        this.userService = userService;
        this.curationService = curationService;
        this.boardService = boardService;
    }

    @PostMapping("/signup")
    public String signup(@RequestBody UserDTO user) {
        return userService.signUp(user);
    }

    @GetMapping("/admin/users/{userCode}")
    @Transactional
    public ResponseEntity<Map<String, Object>> selectUserByCodeByAdmin(@PathVariable int userCode) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Map<String, Object> result = new HashMap<>();
        result.put("user", userService.selectUserByCodeByAdmin(userCode));

        List<String> dogList = curationService.findDogList(userCode);

        if (dogList.size() > 0) {

            result.put("dogList", dogList);

            for (String dog : dogList) {

                result.put(dog, curationService.selectDogsByCodeByAdmin(dog));
            }
        }

        List<BoardDTO> boardList = boardService.selectBoardByCodeByAdmin(userCode);

        if (boardList.size() > 0) {

            result.put("boardList", boardList);

            Map<String, String> commentCount = new HashMap<>();

            for (BoardDTO board : boardList) {

                int postCode = board.getPostCode();

                commentCount.put(Integer.toString(postCode), Integer.toString(boardService.countCommentByPostCode(postCode)));
            }

            result.put("countList", commentCount);
        }

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

    @PostMapping("/admin/users")
    public ResponseEntity<Map<String, Object>> selectAllUsersByAdmin(@RequestBody Map<String, String> search) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Map<String, Object> result = new HashMap<>();
        result.put("users", userService.selectAllUsersByAdmin(search));

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

    @PutMapping("/admin/users")
    public ResponseEntity<?> updateUserByAdmin(@RequestBody Map<String, String> object) {

        String userCode = (String) object.get("userCode");

        userService.updateUserByAdmin(userCode);

        return ResponseEntity
                .created(URI.create("/admin/users/" + userCode))
                .build();
    }

    @DeleteMapping("/admin/users/{userCode}")
    public ResponseEntity<?> deleteUserByAdmin(@PathVariable int userCode) {

        String userAuth = userService.findUserAuth(userCode);

        userService.deleteUserByAdmin(userAuth, userCode);

        return ResponseEntity.noContent().build();
    }
}
