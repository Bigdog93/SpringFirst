package com.koreait.spring.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService service;

    @RequestMapping("/list")
    public String list(Model model) {
        List<BoardDomain> list = service.selBoardList();
        model.addAttribute("list", list);
        return "board/list";
    }

    @RequestMapping("/detail")
    public String detail(BoardDTO param, Model model) {
        BoardDomain data = service.selBoard(param);
        model.addAttribute("data", data);
        return "board/detail";
    }

    // return 할 때 JSON 형태의 문자열로 날려줌, Gson 이 하던 역할 -> spring 에서는 jackson
    // 원래 Controller 의 역할은 jsp 파일을 여는 것.
    // 그래서 항상 jsp 파일의 주소나 서블렛의 주소를 스트링으로 return 해줬었는데
    // @ResponseBody 어노테이션을 주면 얘를 JSON 형태로 바꿔서 return 해줌
    @ResponseBody
    @RequestMapping(value = "/cmtIns", method = RequestMethod.POST)
    public Map<String, Integer> cmtIns(@RequestBody BoardCmtEntity param) { // @RequestBody : js로 부터 날아온 json 형태의 문자열로부터 알아서 class 멤버필드에 넣어줌
        Map<String, Integer> data = new HashMap<>();
        int result = service.insBoardCmt(param);
        data.put("result", result);
        return data;
    }

    @ResponseBody
    @RequestMapping("/cmtSel")
    public List<BoardCmtDomain> cmtSel(BoardDTO param) { // 쿼리스트링, GET 방식으로 넘어온 애 받을때는 @RequestBody 안적는다.
        return service.selBoardCmtList(param);
    }
}
