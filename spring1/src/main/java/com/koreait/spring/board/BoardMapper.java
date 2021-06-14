package com.koreait.spring.board;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardDomain> selBoardList(BoardDTO param);
    int selMaxPage(BoardDTO param);
    BoardDomain selBoard(BoardDTO param);
    int insBoard(BoardEntity param);
    int selLastBoardPk();
    int updBoard(BoardEntity param);
    int delBoard(BoardEntity param);
}
