package com.koreait.spring.board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class BoardCmtDomain extends BoardCmtEntity {
    private String writerNm;
    private String profileImg;
}
