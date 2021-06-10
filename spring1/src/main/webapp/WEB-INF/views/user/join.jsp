<%--
  Created by IntelliJ IDEA.
  User: 이수찬
  Date: 2021-06-02
  Time: 오전 11:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

    <h1>회원가입</h1>
    <form action="/user/join" method="post">
        <div>
            <input type="text" name="uid" placeholder="아이디">
            <input type="password" name="upw" placeholder="비밀번호">
            <input type="password" name="chkUpw" placeholder="비밀번호 확인">
        </div>
        <div>
            <input type="text" name="unm" placeholder="이름">
        </div>
        <div>
            <label>남<input type="radio" name="gender" value="1" checked></label>
            <label>여<input type="radio" name="gender" value="0"></label>
        </div>
    <input type="submit" value="join">
    </form>

