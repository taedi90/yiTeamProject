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
    <title>에러페이지</title>
</head>
<body>

<h2>${errorTitle}</h2>
<p>${errorDescription}</p>
<p>이용에 불편을 드려 죄송합니다.</p>
<p>(에러 상세 : ${exception})</p>

<a href="${path}">HOME</a>

</body>
</html>
