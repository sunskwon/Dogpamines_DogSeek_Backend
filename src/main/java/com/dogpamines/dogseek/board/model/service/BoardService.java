package com.dogpamines.dogseek.board.model.service;

import com.dogpamines.dogseek.board.model.dao.BoardMapper;
import com.dogpamines.dogseek.board.model.dto.BoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    private BoardMapper boardMapper;

    @Autowired
    public BoardService(BoardMapper boardMapper) {
        this .boardMapper = boardMapper;
    }

    public List<BoardDTO> selectAllPosts() {

        return boardMapper.selectAllPosts();
    }


    public BoardDTO selectPost(int postCode) {

        return boardMapper.selectPost();
    }
}
