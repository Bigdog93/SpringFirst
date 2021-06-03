package com.koreait.spring.board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardEntity { // DB의 개체에 딱 맞게 사용할 녀석
    private int iboard;
    private String title;
    private String ctnt;
    private int iuser;
    private String regdt;
    private int viewcnt;
}
