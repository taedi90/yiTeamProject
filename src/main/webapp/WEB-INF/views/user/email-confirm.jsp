<%--
  Created by IntelliJ IDEA.
  User: taedi
  Date: 2021/11/07
  Time: 7:42 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="send_mail">
    <input type="text" name="username" placeholder="아이디">
    <input type="text" name="email" placeholder="이메일">
    <button type="button" onclick="sendMail()">인증 메일 재발송</button>
</form>


<script src="${path}/js/common/ajax.js" defer></script>
<script src="${path}/js/user/email-confirm.js" defer></script>
</body>
</html>
