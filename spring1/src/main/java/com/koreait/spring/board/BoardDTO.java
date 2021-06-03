package com.koreait.spring.board;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BoardDTO { // 파라미터로 주고 받을 아이
    private int iboard;
    private int startIdx;
    private int recordCnt;
    private int searchType;
    private String searchText;
}
