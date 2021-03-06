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
        final String PATH = "C:/SpringRes/user/" + loginUser.getIuser(); // 리소스 폴더 내 유저 pk 로 경로 설정

        File folder = new File(PATH); // 그 경로로 파일 객체 생성
        folder.mkdirs(); // 경로 생성


        String ext = FilenameUtils.getExtension(img.getOriginalFilename()); // 업로드된 파일의 원래 이름의 확장자 얻어오기!!
        String fileNm = UUID.randomUUID().toString() + "." + ext; // 랜덤한 파일명 생성

        File target = new File(PATH + "/" + fileNm); // 경로 내에 파일 이름으로 파일 객체 생성

        try {
            img.transferTo(target); // 메모리상에 있는 파일(img)을 원하는 디렉토리로 옮기기

            // 이전 이미지 삭제
            File delFile = new File(PATH + "/" + loginUser.getProfileImg());
            if(delFile.exists()) {
                delFile.delete();
            }
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
