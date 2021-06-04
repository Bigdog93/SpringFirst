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

    @ResponseBody // return 할 때 JSON 형태의 문자열로 날려줌, Gson 이 하던 역할
    // 원래 Controller 의 역할은 jsp 파일을 여는 것.
    // 그래서 항상 jsp 파일의 주소나 서블렛의 주소를 스트링으로 return 해줬었는데
    // @ResponseBody 어노테이션을 주면 얘를 JSON 형태로 바꿔서 return 해줌
    @RequestMapping(value = "/cmtInsSel", method = RequestMethod.POST)
    public Map<String, Integer> cmtInsSel(@RequestBody BoardCmtEntity param) {
        Map<String, Integer> data = new HashMap<>();

        data.put("result", 1);
        return data;
    }
}
