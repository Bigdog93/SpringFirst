package com.koreait.spring.user;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper mapper;

    public int join(UserEntity param) {
        String cryptPw = BCrypt.hashpw(param.getUpw(), BCrypt.gensalt());
        param.setUpw(cryptPw);
        return mapper.insUser(param);
    }

    public String login(UserEntity param) {
        UserEntity result = mapper.selUser(param);
        if(result == null) { // 아이디 없을때
            return "/user/login?err=1";
        } else  if(BCrypt.checkpw(param.getUpw(), result.getUpw())) { // 로그인 성공
            result.setUpw(null);

            return "/board/list";
        } else { // 비밀번호 틀림
            return "/user/login?err=2";
        }




    }
}
