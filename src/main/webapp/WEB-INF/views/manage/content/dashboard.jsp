<%--
  Created by IntelliJ IDEA.
  User: taedi
  Date: 2021/11/28
  Time: 7:53 오전
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<div id="table_wrap">
    <svg id="users"></svg>

</div>

<script type="application/javascript" src="${path}/js/common/d3.min.js"></script>
<script type="application/javascript" src="${path}/js/manage/dashboard.js"></script>