package com.koreait.spring.user;

import org.apache.commons.io.FilenameUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserMapper mapper;

    @Autowired
    private HttpSession session;

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
            session.setAttribute("loginUser", result);
            return "/board/list";
        } else { // 비밀번호 틀림
            return "/user/login?err=2";
        }
    }

    public String updProfile(MultipartFile img) {
        UserEntity loginUser = (UserEntity) session.getAttribute("loginUser");
        int loginUserPk = loginUser.getIuser();
        final String PATH = "C:/SpringRes/user/" + loginUser.getIuser();

        File folder = new File(PATH);
        folder.mkdirs();


        String ext = FilenameUtils.getExtension(img.getOriginalFilename()); // 업로드된 파일의 원래 이름의 확장자 얻어오기!!
        String fileNm = UUID.randomUUID().toString() + "." + ext; // 랜덤한 파일명 생성

        File target = new File(PATH + "/" + fileNm);

        try {
            img.transferTo(target);
        } catch (IOException e) {
            e.printStackTrace();
        }
        UserEntity param = new UserEntity();
        param.setIuser(loginUserPk);
        param.setProfileImg(fileNm);

        mapper.updUser(param);

        loginUser.setProfileImg(fileNm);

        return "/user/profile";
    }
}
