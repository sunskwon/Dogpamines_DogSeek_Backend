package com.dogpamines.dogseek.board.model.dao;


import com.dogpamines.dogseek.board.model.dto.BoardDTO;
import com.dogpamines.dogseek.board.model.dto.ChatBoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface BoardMapper {
    List<BoardDTO> selectAllPosts();
    List<ChatBoardDTO> chatAllComment();

    BoardDTO selectPost(int postCode);

    int getLastPostCode();

    void newBoardPost(BoardDTO board);

}
