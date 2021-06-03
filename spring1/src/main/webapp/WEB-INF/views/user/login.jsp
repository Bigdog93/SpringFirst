<%--
  Created by IntelliJ IDEA.
  User: 이수찬
  Date: 2021-06-01
  Time: 오후 4:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<div>로그인</div>
<div>${requestScope.errMsg}</div>
<form action="login" method="post">
    <input type="text" name="uid" placeholder="아이디">
    <input type="password" name="upw" placeholder="비밀번호">
    <div>
        <input type="submit" value="login">
    </div>
</form>
<div>
    <a href="join">join</a>
</div>
</form>
</body>
</html>
