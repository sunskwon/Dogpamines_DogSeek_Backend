package com.dogpamines.dogseek.board.model.dao;


import com.dogpamines.dogseek.board.model.dto.BoardDTO;
import com.dogpamines.dogseek.board.model.dto.ChatBoardDTO;
import com.dogpamines.dogseek.board.model.dto.CommentDTO;
import com.dogpamines.dogseek.board.model.dto.BoardReportDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface BoardMapper {
    List<BoardDTO> selectAllPosts();
    List<ChatBoardDTO> chatAllComment();

    BoardDTO selectPost(int postCode);

    int getLastPostCode();

    void newBoardPost(BoardDTO board);

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
}
