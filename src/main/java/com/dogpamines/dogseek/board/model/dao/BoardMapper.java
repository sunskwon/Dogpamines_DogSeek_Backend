package com.dogpamines.dogseek.board.model.dao;


import com.dogpamines.dogseek.board.model.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface BoardMapper {
    List<BoardDTO> selectAllPosts();

    BoardDTO selectPost(int postCode);

    int getLastPostCode();

    void insertBoard(BoardDTO board);

    List<BoardDTO> selectBoardByCodeByAdmin(int userCode);

    int countCommentByPostCode(int postCode);
}
