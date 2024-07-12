package com.dogpamines.dogseek.board.model.service;

import com.dogpamines.dogseek.board.model.dao.BoardMapper;
import com.dogpamines.dogseek.board.model.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BoardService {

    private static BoardMapper boardMapper;
    private final RedisTemplate redisTemplate;
    private final String BOARD_KEY = "board";

    @Autowired
    public BoardService(BoardMapper boardMapper, RedisTemplate redisTemplate) {
        this.boardMapper = boardMapper;
        this.redisTemplate = redisTemplate;
    }


    public List<BoardDTO> selectAllPosts() {

        return boardMapper.selectAllPosts();
    }

    public BoardDTO selectPost(int postCode) {

        return boardMapper.selectPost(postCode);
    }
    public int getLastPostCode() {

        return boardMapper.getLastPostCode();
    }

    @Transactional
    public void newBoardPost(BoardPostDTO board) {

        // 새 게시물 작성시 게시물 작성 수 +1

        try {

            ValueOperations<String, String> countBoard = redisTemplate.opsForValue();

            Optional<String> tempVisit = Optional.ofNullable(countBoard.get(BOARD_KEY));

            if (!tempVisit.isEmpty()) {

                int count = Integer.parseInt(countBoard.get(BOARD_KEY));

                String updatedCount = String.valueOf(count + 1);

                countBoard.set(BOARD_KEY, updatedCount);
            } else {

                String firstBoard = String.valueOf(1);

                countBoard.set(BOARD_KEY, firstBoard);
            }

        } catch (RedisConnectionFailureException e) {
            System.out.println("redis와 연결되지 않음");
        } catch (Exception e) {
            e.printStackTrace();
        }

        boardMapper.newBoardPost(board);
    }

    public List<BoardDTO> selectBoardByCodeByAdmin(int userCode) {

        return boardMapper.selectBoardByCodeByAdmin(userCode);
    }

    public int countCommentByPostCode(int postCode) {

        return boardMapper.countCommentByPostCode(postCode);
    }

    public List<BoardDTO> selectAllNotices() {

        return boardMapper.selectAllNotices();
    }

    public Map<String, Object> selectAllBoards() {

        Map<String, Object> result = new HashMap<>();

        List<BoardDTO> boardList = boardMapper.selectAllBoards();
        result.put("boardList", boardList);

        for (BoardDTO board : boardList) {

            int postCode = board.getPostCode();

            List<BoardReportDTO> reportList = boardMapper.selectBoardReportsByPostCode(postCode);

            if (reportList.size() > 0) {

                result.put(String.valueOf(postCode), reportList);
            }
        }

        return result;
    }

    public List<CommentDTO> selectCommentsByPostCode(int postCode) {

        return boardMapper.selectCommentsByPostCode(postCode);
    }

    public List<BoardReportDTO> selectBoardReportsByPostCode(int postCode) {

        return boardMapper.selectBoardReportsByPostCode(postCode);
    }

    public List<BoardDTO> searchNotice(String type, String input) {

        return boardMapper.searchNotice(type, input);
    }

    public Map<String, Object> searchBoard(String type, String input) {

        Map<String, Object> result = new HashMap<>();

        List<BoardDTO> boardList = boardMapper.searchBoard(type, input);
        result.put("boardList", boardList);

        for (BoardDTO board : boardList) {

            int postCode = board.getPostCode();

            List<BoardReportDTO> reportList = boardMapper.selectBoardReportsByPostCode(postCode);

            if (reportList.size() > 0) {

                result.put(String.valueOf(postCode), reportList);
            }
        }

        return result;
    }

    public String findPostStatus(int postCode) {

        return boardMapper.findPostStatus(postCode);
    }

    public void deletePost(int postCode, String postStatus) {

        boardMapper.deletePost(postCode, postStatus);
    }

    public int findLastPostCode() {

        return boardMapper.findLastPostCode();
    }

    public void insertPost(BoardDTO notice) {

        boardMapper.insertPost(notice);
    }

    public BoardDTO selectBoardByCode(int postCode) {

        return boardMapper.selectBoardByCode(postCode);
    }

    public void updatePost(BoardDTO notice) {

        boardMapper.updatePost(notice);
    }

    public List<BoardReportDTO> selectCommentReportByCommentCode(int commentCode) {

        return boardMapper.selectCommentReportByCommentCode(commentCode);

    }

    public void updateBoard(BoardPostDTO board) {

         boardMapper.updateBoard(board);
    }


    public void deleteBoard(int postCode) {

        System.out.println("postCode = " + postCode);
        boardMapper.deleteBoard(postCode);
    }

    @Transactional
    public void reportPost(BoardReportDTO report) {

        int postCode = report.getPostCode();
        boardMapper.reportPost(postCode);
        incrementReportCount(postCode); // 증가
        historyReport(report); // 저장
    }

    // 게시물 신고 횟수 증가 메서드
    public void incrementReportCount(int postCode) {
        boardMapper.incrementReportCount(postCode);
    }
    // 게시물 신고 내역 저장 메서드
    public void historyReport(BoardReportDTO report) {
        boardMapper.historyReport(report);
    }
}
