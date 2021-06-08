package com.koreait.spring.board;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardCmtMapper {
    int insBoardCmt(BoardCmtEntity param);
    List<BoardCmtDomain> selBoardCmtList(BoardDTO param);
    int updBoardCmt(BoardCmtEntity param);
    int delBoardCmt(BoardCmtEntity param);
}
