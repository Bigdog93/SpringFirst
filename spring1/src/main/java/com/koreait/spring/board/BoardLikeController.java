package com.koreait.spring.board;

import com.koreait.spring.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController // RestController 는 아래의 메소드들은 모두 JSON(data) 을 리턴하는게 목적이 된다. 즉 @ResponseBody 를 적을 필요가 없다.
// Client side 렌더링. 데이터만 줄테니까 클라이언트에서 렌더링 해라.
@RequestMapping("/board")
public class BoardLikeController {

    @Autowired
    private BoardLikeService service;

    @Autowired
    private BoardService boardService;

    @Autowired
    private MyUtils myUtils;

    @GetMapping("/like")
    public Map<String, Object> selLike(BoardDTO param) {
        Map<String, Object> result = new HashMap<>();

        param.setSelType(1);
        param.setIuser(myUtils.getLoginUserPk());
        result.put("list", boardService.selBoardList(param));
        result.put("maxPage", boardService.selMaxPage(param));
        result.put("cPage", param);

        return result;
    }

    @PostMapping("/like")
    public Map<String, Integer> insLike(@RequestBody BoardLikeEntity param) { // @RequestParam : 하나의 값 받을 때
        Map<String, Integer> result = new HashMap<>();
        result.put("result", service.insLike(param));
        return result;
    }

    /*public List<BoardDomain> selLikeBoardLike(BoardDTO param) {
        param.setSelType(1); // 좋아요 리스트
        return boardService.selBoardList(param);
    }*/
    
    @GetMapping("/like/{iboard}")
    public Map<String, Integer> selLike(BoardLikeEntity param, @PathVariable int iboard) {
        param.setIboard(iboard);
        Map<String, Integer> result = new HashMap<>();
        result.put("result", service.selLike(param));
        return result;
    }
    
    @GetMapping("/likeCnt")
    public Map<String, Integer> selLikeList(BoardLikeEntity param) {
        Map<String, Integer> result = new HashMap<>();
        result.put("likeCnt", service.selLikeCnt(param));
        return result;
    }
    
    @DeleteMapping("/like")
    public Map<String, Integer> delLike(@RequestBody BoardLikeEntity param) {
        Map<String, Integer> result = new HashMap<>();
        result.put("result", service.delLike(param));
        return result;
    }
}
