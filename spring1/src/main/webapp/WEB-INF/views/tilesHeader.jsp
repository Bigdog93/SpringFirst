<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <nav class="navigation">
    <ul>
        <c:choose>
            <c:when test="${empty sessionScope.loginUser}">
                <li><a href="/user/login">로그인</a></li>
                <li><a href="/user/join">회원가입</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="/user/logout">로그아웃</a></li>
                <li><a href="/user/profile">프로필</a></li>
                <li><a href="/board/write">글쓰기</a></li>
                <li><a href="/board/likeList">좋아요</a></li>
            </c:otherwise>
        </c:choose>
        <li><a href="/board/list">게시판</a></li>
    </ul>
    </nav>
</header>