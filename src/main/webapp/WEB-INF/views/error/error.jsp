<%--
  Created by IntelliJ IDEA.
  User: taedi
  Date: 2021/11/03
  Time: 11:14 오전
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<c:set var="response" value="<%= response.getStatus() %>"/>
<html>
<head>
    <title>에러 페이지</title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, maximum-scale=1">
    <link rel="stylesheet" href="${path}/css/common/error.css">
</head>
<body>

<div id="wrap">
    <img id="error_img" src="${path}/img/common/error.png" alt="에러 이미지">
    <h1>${errorTitle}</h1>
    <h3>${errorDescription}<br>이용에 불편을 드려 죄송합니다.</h3>
    <p id="exception">(Cause - ${exception})</p>
    <br>
    <h3><a href="${path}">HOME</a></h3>
</div>



</body>
</html>
