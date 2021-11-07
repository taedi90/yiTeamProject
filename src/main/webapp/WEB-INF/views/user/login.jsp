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
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>로그인 하기</h1>
<h2><c:out value="${error}" /> </h2>
<h2><c:out value="${logout}"/> </h2>
<form action="login" method="post">
    <input type="text" name="username" placeholder="아이디"><br>
    <input type="password" name="password" placeholder="비밀번호"><br>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <input type="checkbox" name="remember-me">로그인 유지<br>
    <button type="submit">로그인하기</button>
    <br>
    <a href="register">회원가입</a><br>
    <a href="email-confirm">이메일 인증 메일 발송</a>
</form>

<p>${loginErrorMessage}</p>

<h3>소셜 로그인 :</h3>

<a href="oauth2/authorization/google">
    <div>
        구글 로그인
    </div>
</a>

<script src="/shop/js/common/jquery-3.6.0.min.js"></script>
</body>
</html>
