package com.koreait.spring.board;

import com.koreait.spring.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController // RestController 는 아래의 메소드들은 모두 JSON 을 리턴하는게 목적이 된다. 즉 @ResponseBody 를 적을 필요가 없다.
@RequestMapping("/board")
public class BoardLikeController {

    @Autowired
    private BoardLikeService service;

    @Autowired
    private MyUtils myUtils;

    @PostMapping("/like")
    public Map<String, Integer> insLike(@RequestBody BoardLikeEntity param) {
        Map<String, Integer> result = new HashMap<>();
        result.put("result", service.insLike(param));
        return result;
    }
    
    @GetMapping("/like")
    public Map<String, Integer> selLike(BoardLikeEntity param) {
        Map<String, Integer> result = new HashMap<>();
        result.put("result", service.selLike(param));
        return result;
    }
    
    @GetMapping("/likeList")
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
