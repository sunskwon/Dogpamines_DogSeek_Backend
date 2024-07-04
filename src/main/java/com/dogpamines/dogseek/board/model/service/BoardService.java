package com.dogpamines.dogseek.board.model.service;

import com.dogpamines.dogseek.board.model.dao.BoardMapper;
import com.dogpamines.dogseek.board.model.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardService {

    private static BoardMapper boardMapper;

    @Autowired
    public BoardService(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
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

    public void newBoardPost(BoardChatDTO board) {

        boardMapper.newBoardPost(board);
    }

    public List<ChatBoardDTO> chatAllComment() {

        return boardMapper.chatAllComment();
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

    public List<BoardDTO> selectAllBoards() {

        return boardMapper.selectAllBoards();
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

    public List<BoardDTO> searchBoard(String type, String input) {

        return boardMapper.searchBoard(type, input);
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

    public void updateBoard(BoardChatDTO board) {

         boardMapper.updateBoard(board);
    }


    public void deleteBoard(int postCode) {

        System.out.println("postCode = " + postCode);
        boardMapper.deleteBoard(postCode);
    }
}
