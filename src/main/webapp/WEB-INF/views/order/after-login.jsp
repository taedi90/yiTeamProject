<%--
  Created by IntelliJ IDEA.
  User: taedi
  Date: 2021/11/26
  Time: 9:57 오전
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- jstl 태그 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 스프링 시큐리티 태그 --%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>잠시만 기다려 주세요...</title>
</head>
<body>
<h1>잠시만 기다려 주세요...</h1>
<script type="text/javascript" src="${path}/js/common/ajax.js" defer></script>
<script type="text/javascript" src="${path}/js/common/order-util.js" defer></script>
<script type="text/javascript" src="${path}/js/order/after-login.js" defer></script>
</body>
</html>
