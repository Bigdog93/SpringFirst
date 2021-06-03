<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>List</title>
</head>
<body>
<h1>리스트</h1>
<table>
    <tr>
        <th>번호</th>
        <th>제목</th>
        <th>글쓴이</th>
        <th>작성일</th>
    </tr>
    <c:forEach var="i" items="${requestScope.list}" varStatus="status">
        <tr class="record" onclick="moveToDetail(${i.iboard})">
            <td>${i.iboard}</td>
            <td>
                <c:choose>
                    <c:when test="${param.searchType==1 || param.searchType==2}">
                        ${i.title.replace(param.searchText, '<mark>' += param.searchText += '</mark>')}
                    </c:when>
                    <c:otherwise>${i.title}</c:otherwise>
                </c:choose>
            </td>
            <c:choose>
                <c:when test="${empty i.profileImg }">
                    <c:set var="img" value="/res/img/defaultprofile.jpg"/>
                </c:when>
                <c:otherwise>
                    <c:set var="img"
                           value="/res/img/user/${i.iuser}/${i.profileImg}"/>
                </c:otherwise>
            </c:choose>
            <td>
                <c:choose>
                    <c:when test="${param.searchType == 4}">
                        ${i.writerNm.replace(param.searchText, '<mark>' += param.searchText += '</mark>')}
                    </c:when>
                    <c:otherwise>
                        ${i.writerNm}
                    </c:otherwise>
                </c:choose>
                <img src="${img}" class="profileImg">
            </td>
            <td>${i.regdt}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
