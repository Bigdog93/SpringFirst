package com.koreait.spring.board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardCmtEntity {
    private int icmt;
    private int iuser;
    private int iboard;
    private String cmt;
    private String regdt;
    private int likecnt;
    private int dislike;
}
