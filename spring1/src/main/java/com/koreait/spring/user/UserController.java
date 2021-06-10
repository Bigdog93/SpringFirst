package com.koreait.spring.user;

import com.koreait.spring.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller // 컨트롤러, 서비스, 컴포넌트 등등(빈등록하는 애들)
// 빈 : 쉽게말해 스프링에서 관리하는 객체
// 컨트롤러 애노테이션 : 서블릿으로 연결을 시켜주는 아이
// @Controller : 고객이 어떤 요청을 해왔을때 연결 해주는 아이(RequestMapping 으로 연결)
@RequestMapping("/user") // 클래스위에다가 적는건 1차 주소값
public class UserController {

    @Autowired // 해당하는 타입의 객체를 스프링이 들고 있다면(@로 bean 등록이 되어 있다면),
    // service(변수)에다가 그 객체의 인스턴스 주소를 저장
//    @Qualifier("아이디값") 해당하는 타입으로 만들 수 있는 객체가 2개 이상일 경우(자식) 특정할 수 있는 어노테이션
    // @Service("아이디값") 으로 해당 Bean 등록할 클래스에 아이디 값을 부여 할 수 있다.
    private UserService service;

    @Autowired
    private MyUtils myUtils;

    @RequestMapping(value="/login", method= RequestMethod.GET) // 원래는 이렇게 적어줘야 하지만, GET 은 기본값이라 안써줘도 됨
    public String login(@RequestParam(value = "err", required = false, defaultValue = "0") int err, Model model) { // 파일경로와 서블렛경로가 같을 경우, void 가능
        // @RequestParam("Key") int value : int value = request.getParameter("Key"); (정수타입이면 알아서 변환), 없거나 int 가 아니면 에러남..
        // required = false 를 주면 값이 들어가야 하는 강제성 없어짐(int 라면 null 이라 에러는 터짐, 그래서 defaultValue 로 0 주면 됨)
        // Model model : request 에 담아주는 애
        // HttpServletRequest request 를 Parameter 에 적어줌으로써 원래 쓰던 request 처럼 쓸 수도 있다.
        switch (err) {
            case 1: // 아이디 없음
                model.addAttribute("errMsg", "아이디를 확인해 주세요.");
                break;
            case 2: // 비밀번호 틀림
                model.addAttribute("errMsg", "비밀번호를 확인해 주세요.");
                break;
        }
        myUtils.setTitle("로그인",model);
        return "user/login"; // 기본 디스패쳐 방식
        // 세팅해준 디스패쳐서블릿이 주소창에 쓰인 주소값으로 이 메소드(login())를 찾아 실행후,
        // prefix 값 + return 해준 String 값 + suffix 값 해서 request.getDispatch.forward(req,res) 해준다.
//      return "redirect:user/login"; // 리다이렉트 방식(서블릿 호출)
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(UserEntity param, HttpSession session) {
        return "redirect:" + service.login(param);
    }

    @RequestMapping("/join")
    public String join(Model model) {
        myUtils.setTitle("회원가입", model);
        return "user/join";
    }

    @RequestMapping(value = "/join", method = RequestMethod.POST) // 원래는 이렇게 적어줘야 하지만, GET 은 기본값이라 안써줘도 됨
    public String join(UserEntity param) {
        // 날릴때 멤버필드명으로 이름만 맞춰주면 알아서 UserEntity 객체에 넣어서 보내줌(dispatcher 가)
        service.join(param);
        return "redirect:/user/login"; // 기존에 response.sendRedirect()와 같은 역할. (서블릿을 호출)
    }

    @GetMapping("/profile")
    public void profile(Model model) {
        
    }  // 파일경로와 서블렛경로가 같을 경우, void 가능

//    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    @PostMapping("/profile")
    public String profile(@RequestParam("profileImg") MultipartFile profileImg) { // type 이 file 인 input 의 value 는 MultipartFile 로 간다.(변수명과 jsp 에서 name 이랑 맞춰줘야 한다.)
        return "redirect:" + service.updProfile(profileImg);
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletRequest request) {
        session.invalidate();
        String referer = request.getHeader("Referer");
        if(referer.contains("user/profile")) {
            return "redirect:/user/login";
        }
        return "redirect:" + referer;
    }
}