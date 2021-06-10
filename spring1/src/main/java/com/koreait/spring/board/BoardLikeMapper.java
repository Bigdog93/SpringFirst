package com.koreait.spring.board;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardLikeMapper {
    int insBoardLike(BoardLikeEntity param);
    int selBoardLike(BoardLikeEntity param);
    int delBoardLike(BoardLikeEntity param);
    int selBoardLikeCnt(BoardLikeEntity param);
}
