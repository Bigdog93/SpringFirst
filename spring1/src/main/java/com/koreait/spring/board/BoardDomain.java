package com.koreait.spring.board;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDomain extends BoardEntity{ // Join 했을때 t_board 의 컬럼 외의 속성을 받기 위해 쓰는 아이
    private String writerNm;
    private String profileImg;
}
