<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<h1>리스트</h1>
<form action="list" method="get" id="recordCntFrm">
    <input type="hidden" name="page" value="${requestScope.cPage.page}">
    <select name="recordCnt">
        <c:forEach begin="5" end="20" step="5" var="cnt">
            <c:choose>
                <c:when test="${cnt eq requestScope.cPage.recordCnt}">
                    <option value="${cnt}" selected>${cnt}개씩 보기</option>
                </c:when>
                <c:otherwise>
                    <option value="${cnt}">${cnt}개씩 보기</option>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </select>
</form>
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
                <c:if test="${i.isLike == 1}"><i id="likeIcon" class="fas fa-heart pointer"></i>
                </c:if>
            </td>
            <c:choose>
                <c:when test="${empty i.profileImg }">
                    <c:set var="img" value="/res/img/defaultprofile.jpg"/>
                </c:when>
                <c:otherwise>
                    <c:set var="img" value="/img/user/${i.iuser}/${i.profileImg}"/>
                </c:otherwise>
            </c:choose>
            <td>
                <img src="${img}" class="profileImg">
                <c:choose>
                    <c:when test="${param.searchType == 4}">
                        ${i.writerNm.replace(param.searchText, '<mark>' += param.searchText += '</mark>')}
                    </c:when>
                    <c:otherwise>
                        ${i.writerNm}
                    </c:otherwise>
                </c:choose>
            </td>
            <td>${i.regdt}</td>
        </tr>
    </c:forEach>
</table>
    <c:forEach var="i" begin="1" end="${requestScope.maxPage}">
        <c:choose>
            <c:when test="${i eq requestScope.cPage.page}">
                <span class="selected">${i}</span>
            </c:when>
            <c:otherwise>
                <span><a href="/board/list?page=${i}&recordCnt=${requestScope.cPage.recordCnt}">${i}</a></span>
            </c:otherwise>
        </c:choose>
    </c:forEach>

</body>
</html>