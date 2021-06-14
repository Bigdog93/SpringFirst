package com.koreait.spring;

import com.koreait.spring.user.UserEntity;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AuthCheckInterceptor implements HandlerInterceptor { // 인증 인터셉터
//    private final String[] needLoginUriArr = {"/board/write", "/board/likeList", "/user/profile"}; // 이걸 xml 파일 에서 부터 설정해줄 수도 있다. 훨씬 좋으니까 실제로 했다

    @Override // 컨트롤러 도착하기 전에 뭔가 하고 싶다. return 이 false 면 차단, true 면 controller 로
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return true;
    }

    @Override // 일단 컨트롤러 가서 리턴 받은 상태(화면 열기 직전)
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
//        String uri = httpServletRequest.getRequestURI();
//        System.out.println("uri : " + uri);
//        if(Arrays.asList(needLoginUriArr).contains(uri)) {
            UserEntity loginUser = (UserEntity) httpServletRequest.getSession().getAttribute("loginUser");
            if(loginUser == null) {
                System.out.println("OriginViewName : " + modelAndView.getViewName());
                modelAndView.addObject("errMsg", "로그인이 필요합니다."); // 값 넣을 수 있고, jsp 등등
                modelAndView.setViewName("/user/login"); // 이게 사실 원래 jsp 띄워주는 아이였던 것.
                // view 에 있는 Name 을 뷰리졸버가 앞 뒤 붙여서 jsp 열어주고, model 부분에 있는 애들은 Request 에 담아준다.
            }
//        }
    }

    @Override // view 까지 처리가 끝난 후에 처리
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
