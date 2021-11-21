<%--
  Created by IntelliJ IDEA.
  User: taedi
  Date: 2021/11/02
  Time: 2:27 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 스프링 시큐리티 태그 --%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, maximum-scale=1">
    <title>Register</title>
    <link rel="stylesheet" href="${path}/css/user/register.css">
</head>
<body>

<div id="wrap">
    <div id="register_title">
        Register
    </div>

    <div id="register_form">
        <div id="input_holder">
            <form action="register" method="post" name="form">
                <input type="text" class="register_input" name="username" placeholder="아이디">
                <input type="text" class="register_input" name="name" placeholder="이름" autocomplete="username">
                <input type="password" class="register_input" name="password" placeholder="비밀번호" autocomplete="new-password">
                <input type="password" class="register_input" name="passwordConfirm" placeholder="비밀번호 확인" autocomplete="new-password">
                <input type="email" class="register_input" name="email" placeholder="이메일">
                <input type="text" class="register_input" name="phone" placeholder="핸드폰번호" maxlength="11" required>
            </form>
        </div>
        <div id="error_holder">

        </div>
        <div id="button_holder">
            <button type="button" onclick="doRegister()">회원가입</button>
        </div>
        <div><a href="login">로그인으로 돌아가기</a></div>
    </div>



</div>


<script src="${path}/js/common/ajax.js" defer></script>
<script src="${path}/js/user/register.js" defer></script>

</body>
</html>
