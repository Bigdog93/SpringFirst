package com.koreait.spring.user;

import org.apache.ibatis.annotations.Mapper;

@Mapper // MyBatis 에서 DAO 만들어주는 애
// 얘랑 xml 이랑 세트
public interface UserMapper {
    int insUser(UserEntity param);
    // UserMapper.xml 파일에서 <insert 태그의 id 값으로 준 아이로 메소드명
    // MyBatis 가 알아서 DAO 만들고 sql 문 완성, 실행, bean 등록까지 해줌

    UserEntity selUser(UserEntity param);
}
