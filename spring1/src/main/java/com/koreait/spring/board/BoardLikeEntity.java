package com.koreait.spring.board;

import lombok.Data;

@Data
public class BoardLikeEntity {
    private int iboard;
    private int iuser;
    private String regdt;
}
