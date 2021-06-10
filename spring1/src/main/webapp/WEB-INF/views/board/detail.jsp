<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>detail</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="/res/css/board/detail.css">
    <link rel="stylesheet" href="/res/css/common.css">
    <script defer src="/res/js/board/detail.js"></script> <!-- <태그/>는 xml 문법 -->
    <script defer src="/res/js/common.js"></script>
</head>
<body>
<c:choose>
    <c:when test="${empty i.profileImg }">
        <c:set var="img" value="/res/img/defaultprofile.jpg"/>
    </c:when>
    <c:otherwise>
        <c:set var="img"
               value="/img/user/${i.iuser}/${i.profileImg}"/>
    </c:otherwise>
</c:choose>

<h1>${ data.title }<i id="likeIcon" class="far fa-heart pointer"></i></h1>
<div>
    <c:out value="${ data.ctnt }" /> <!-- 스크립트 실행문이 아니라 그 결과(내용)가 나온다. -->
</div>
<div>글쓴이 : ${ data.writerNm }</div>
<div>작성일시 : ${ data.regdt }</div>
<button onclick="goBack()">목록</button>
<c:if test="${sessionScope.loginUser.iuser == data.iuser}">
    <div>
        <a href="write?iboard=${ data.iboard }"><button>수정</button></a>
        <a href="delete?iboard=${ data.iboard }"><button>삭제</button></a>
    </div>
</c:if>
<c:if test="${not empty sessionScope.loginUser}">
    <div>
        <form id="cmtFrm" onsubmit="return false;">
            <input type="text" id="cmt" placeholder="댓글">
            <input type="button" value="댓글달기" onclick="regCmt();">
        </form>
    </div>
</c:if>
<div id="cmtList" data-login-user-pk="${sessionScope.loginUser.iuser}" data-iboard="${param.iboard}"></div>
                <!-- 이렇게 하면 js 에서 loginUserPk로 받는다. -->

<div id="modal" class="displayNone">
    <div class="modal_content">
        <form id="cmtModFrm" onsubmit="return false;">
            <input type="hidden" id="icmt">
            <input type="text" id="modCmt">
        </form>
        <input type="button" value="댓글 수정" onclick="modAjax();">
        <input type="button" value="취소" onclick="closeModModal();">
    </div>
</div>
</body>
</html>
