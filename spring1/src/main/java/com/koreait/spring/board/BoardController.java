package com.koreait.spring.board;

import com.koreait.spring.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService service;

    @Autowired
    private MyUtils myUtils;

    @RequestMapping("/list")
    public String list(Model model) {
        List<BoardDomain> list = service.selBoardList();
        model.addAttribute("list", list);
        myUtils.setTitle("주's 게시판", model);
        return "board/list";
    }

    @RequestMapping("/detail")
    public String detail(BoardDTO param, Model model) {
        BoardDomain data = service.selBoard(param);
        model.addAttribute("data", data);
        myUtils.setTitle(data.getTitle(), model);
        return "board/detail";
    }

    // return 할 때 JSON 형태의 문자열로 날려줌, Gson 이 하던 역할 -> spring 에서는 jackson
    // 원래 Controller 의 역할은 jsp 파일을 여는 것.
    // 그래서 항상 jsp 파일의 주소나 서블렛의 주소를 스트링으로 return 해줬었는데
    // @ResponseBody 어노테이션을 주면 얘를 JSON 형태로 바꿔서 return 해줌
    @ResponseBody
    @RequestMapping(value = "/cmt", method = RequestMethod.POST)
    public Map<String, Integer> cmtIns(@RequestBody BoardCmtEntity param) { // @RequestBody : js로 부터 날아온 json 형태의 문자열(body:JSON.stringify())로부터 알아서 class 멤버필드에 넣어줌
        Map<String, Integer> data = new HashMap<>();
        int result = service.insBoardCmt(param);
        data.put("result", result);
        return data;
    }

    @ResponseBody
    @RequestMapping("/cmt/{iboard}") // 패스 밸류어블(RESTful 방식)
    public List<BoardCmtDomain> cmtSel(@PathVariable("iboard") int iboard) { // 쿼리스트링, GET 방식으로 넘어온 애 받을때는 @RequestBody 안적는다.
        BoardDTO param = new BoardDTO();
        param.setIboard(iboard);
        return service.selBoardCmtList(param);
    }

    @ResponseBody
    @RequestMapping(value = "/cmt", method = RequestMethod.PUT)
    public Map<String, Integer> cmtUpd(@RequestBody BoardCmtEntity param) {
        Map<String, Integer> result = new HashMap<>();
        result.put("result", service.updBoardCmt(param));
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/cmt/{icmt}", method = RequestMethod.DELETE)
    public Map<String, Integer> cmtDel(@PathVariable int icmt) {
        BoardCmtEntity param = new BoardCmtEntity();
        param.setIcmt(icmt);
        Map<String, Integer> result = new HashMap<>();
        result.put("result", service.delBoardCmt(param));
        return result;
    }

    @GetMapping("/write")
    public String insBoard(BoardDTO param, Model model) {
        if(param.getIboard() != 0) {
            // 글 수정
            BoardDomain data = service.selBoard(param);
            model.addAttribute("data", data);
            myUtils.setTitle(data.getTitle(), model);
        }
        myUtils.setTitle("글쓰기", model);
        return "board/write";
    }

    @PostMapping("/write")
    public String insBoard(BoardEntity param) {
            return "redirect:/board/detail?iboard=" + service.insBoard(param);
    }

    @GetMapping("/delete")
    public String delBoard(BoardEntity param) {
        service.delBoard(param);
        return "redirect:/board/list";
    }
}