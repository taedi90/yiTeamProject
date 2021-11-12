<%--
  Created by IntelliJ IDEA.
  User: taedi
  Date: 2021/11/09
  Time: 2:08 오후
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
  <title>관리자 페이지</title>
  <link rel="stylesheet" href="${path}/css/manage/manage.css">
</head>
<body>
<div id="wrap">
  <div id="sidebar">
    <%@ include file="../division/manage/sidebar.jsp" %>
  </div>

  <div id="main">
<%--    <%@ include file="content/example.jsp" %>--%>
    <%@ include file="content/product/editor.jsp" %>
  </div>

</div>

<script src="${path}/js/common/ajax.js" defer></script>
<script src="${path}/js/manage/manage.js" defer></script>


</body>
</html>
