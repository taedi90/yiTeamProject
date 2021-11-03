<%--
  Created by IntelliJ IDEA.
  User: taedi
  Date: 2021/11/03
  Time: 11:14 오전
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>에러페이지</title>
</head>
<body>

<c:if test="${requestScope['javax.servlet.error.status_code'] == 400}">
    <p>잘못 된 요청입니다.</p>
</c:if>

<c:if test="${requestScope['javax.servlet.error.status_code'] == 404}">
    <p>요청하신 페이지를 찾을 수 없습니다.</p>
</c:if>

<c:if test="${requestScope['javax.servlet.error.status_code'] == 405}">
    <p>요청된 메소드가 허용되지 않습니다.</p>
</c:if>

<c:if test="${requestScope['javax.servlet.error.status_code'] == 500}">
    <p>서버에 오류가 발생하여 요청을 수행할 수 없습니다.</p>
</c:if>

<c:if test="${requestScope['javax.servlet.error.status_code'] == 503}">
    <p>서비스를 사용할 수 없습니다.</p>
</c:if>

<a href="/">HOME</a>

</body>
</html>
