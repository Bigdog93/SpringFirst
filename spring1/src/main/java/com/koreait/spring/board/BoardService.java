package com.koreait.spring.board;

import com.koreait.spring.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardMapper mapper;

    @Autowired
    private BoardCmtMapper cmtMapper;

    @Autowired
    private HttpSession session;

    @Autowired
    private MyUtils myUtils;

    public List<BoardDomain> selBoardList(BoardDTO param) {
        if(param.getRecordCnt() == 0) {
            param.setRecordCnt(10);
        }
        param.setIuser(myUtils.getLoginUserPk());

        int startIdx = (param.getPage() - 1) * param.getRecordCnt();

        param.setStartIdx(startIdx);
        param.setRecordCnt(param.getRecordCnt());
        return mapper.selBoardList(param);
    }

    public BoardDomain selBoard(BoardDTO param) {
        return mapper.selBoard(param);
    }

    public int insBoardCmt(BoardCmtEntity param) {
        param.setIuser(myUtils.getLoginUserPk());
        return cmtMapper.insBoardCmt(param);
    }

    public List<BoardCmtDomain> selBoardCmtList(BoardDTO param) {
        return cmtMapper.selBoardCmtList(param);
    }

    public int delBoardCmt(BoardCmtEntity param) {
        param.setIuser(myUtils.getLoginUserPk());
        return cmtMapper.delBoardCmt(param);
    }

    public int updBoardCmt(BoardCmtEntity param) {
        param.setIuser(myUtils.getLoginUserPk());
        return cmtMapper.updBoardCmt(param);
    }

    public int insBoard(BoardEntity param) {
        param.setIuser(myUtils.getLoginUserPk());

        if(param.getIboard() == 0) {
            //등록
            mapper.insBoard(param);
        }else {
            //수정
            mapper.updBoard(param);
        }
        return param.getIboard();
    }

    public int delBoard(BoardEntity param) {
        param.setIuser(myUtils.getLoginUserPk());
        return mapper.delBoard(param);
    }

    public int selMaxPage(BoardDTO param) {
        return mapper.selMaxPage(param);
    }
}