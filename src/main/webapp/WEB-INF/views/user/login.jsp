<%--
  Created by IntelliJ IDEA.
  User: taedi
  Date: 2021/10/27
  Time: 8:40 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, maximum-scale=1">
    <title>Login</title>
    <link rel="stylesheet" href="${path}/css/user/login.css">
</head>
<body>
<div id="wrap">
    <div id="login_title">
        Login
    </div>

    <div id="login_form">

        <form action="login" method="post">
            <input type="text" class="login_input" name="username" placeholder="아이디">
            <input type="password" class="login_input" name="password" placeholder="비밀번호">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <input type="checkbox" name="remember-me">로그인 유지
            <button type="submit">로그인하기</button>
            <p>아직 회원이 아니신가요? <a href="register">회원가입</a></p>


        </form>

    </div>

    <div id="login_error">
        <p>${loginErrorMessage}</p>
    </div>

    <div id="from_social">
        <a href="oauth2/authorization/google">
            <div class="social_login_button">
                구글 계정으로 로그인
            </div>
        </a>
        <a href="oauth2/authorization/kakao">
            <div class="social_login_button">
                카카오 계정으로 로그인
            </div>
        </a>
    </div>


</div>

</body>
</html>
