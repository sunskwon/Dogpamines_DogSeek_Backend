package com.dogpamines.dogseek.board.model.dao;

import com.dogpamines.dogseek.board.model.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardDTO> selectAllPosts();

    BoardDTO selectPost(int postCode);

    int getLastPostCode();

    void newBoardPost(BoardPostDTO board);

    List<BoardDTO> selectBoardByCodeByAdmin(int userCode);

    int countCommentByPostCode(int postCode);

    List<BoardDTO> selectAllNotices();

    List<BoardDTO> selectAllBoards();

    List<CommentDTO> selectCommentsByPostCode(int postCode);

    List<BoardReportDTO> selectBoardReportsByPostCode(int postCode);

    List<BoardDTO> searchNotice(String type, String input);

    List<BoardDTO> searchBoard(String type, String input);

    String findPostStatus(int postCode);

    void deletePost(int postCode, String postStatus);

    int findLastPostCode();

    void insertPost(BoardDTO notice);

    BoardDTO selectBoardByCode(int postCode);

    void updatePost(BoardDTO notice);

    List<BoardReportDTO> selectCommentReportByCommentCode(int commentCode);

    void updateBoard(BoardPostDTO board);

    void deleteBoard(int postCode);

    List<BoardPostDTO> searchBoards(String postTitle);

    void reportPost(BoardReportDTO report);
}
