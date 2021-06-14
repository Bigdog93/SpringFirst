package com.koreait.spring.board;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BoardDTO { // 파라미터로 주고 받을 아이
    private int iboard;
    private int iuser;
    private int selType; // 0:기본 리스트, 1: 좋아요 리스트
    private int page = 1;
    private int startIdx;
    private int recordCnt;
    private int searchType;
    private String searchText;
}
