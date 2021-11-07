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

<html>
<head>
    <title>회원가입</title>
</head>
<body>
<form action="register" method="post" name="form">
    <input type="text" name="username" placeholder="아이디"><br>
    <input type="text" name="name" placeholder="이름"><br>
    <input type="password" name="password" placeholder="비밀번호"><br>
    <input type="password" placeholder="비밀번호 확인"><br>
    <input type="email" name="email" placeholder="이메일"><br>
    <input type="text" name="phone" placeholder="핸드폰번호" maxlength="11" required><br>
    <button type="button" onclick="doRegister()">회원가입</button>
</form>

<script src="${path}/js/common/ajax.js" defer></script>
<script src="${path}/js/user/register.js" defer></script>

</body>
</html>
