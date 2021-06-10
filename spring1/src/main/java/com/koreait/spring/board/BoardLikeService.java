package com.koreait.spring.board;

import com.koreait.spring.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardLikeService {

    @Autowired
    private BoardLikeMapper likeMapper;

    @Autowired
    private MyUtils myUtils;

    public int insLike(BoardLikeEntity param) {
        param.setIuser(myUtils.getLoginUserPk());
        return likeMapper.insBoardLike(param);
    }

    public int selLike(BoardLikeEntity param) {
        param.setIuser(myUtils.getLoginUserPk());
        return likeMapper.selBoardLike(param);
    }

    public int delLike(BoardLikeEntity param) {
        param.setIuser(myUtils.getLoginUserPk());
        return likeMapper.delBoardLike(param);
    }

    public int selLikeCnt(BoardLikeEntity param) {
        return likeMapper.selBoardLikeCnt(param);
    }
}
