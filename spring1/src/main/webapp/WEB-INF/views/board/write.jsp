<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>글쓰기</h1>
<c:choose>
    <c:when test="${empty requestScope.data}">
        <c:set var="iboard" value="0"/>
    </c:when>
    <c:otherwise>
        <c:set var="iboard" value="${requestScope.data.iboard}" />
    </c:otherwise>
</c:choose>
<form action="write" method="post">
    <input type="hidden" name="iboard" value="${iboard}"> <%--not empty param.iboard ? param.iboard : 0 --%>
    <div><input type="text" name="title" placeholder="제목" value="${data.title}"></div>
    <div><textarea rows="30" cols="30" name="ctnt" placeholder="내용">${data.ctnt}</textarea></div>
    <div>
        <input type="submit" value="작성">
        <input type="reset" value="초기화">
    </div>
</form>