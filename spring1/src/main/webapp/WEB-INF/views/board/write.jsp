<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>글쓰기</h1>
<form action="write" method="post">
    <input type="hidden" name="iboard" value="${not empty param.iboard ? param.iboard : 0}">
    <div><input type="text" name="title" placeholder="제목" value="${data.title}"></div>
    <div><textarea rows="30" cols="30" name="ctnt" placeholder="내용">${data.ctnt}</textarea></div>
    <div>
        <input type="submit" value="작성">
        <input type="reset" value="초기화">
    </div>
</form>