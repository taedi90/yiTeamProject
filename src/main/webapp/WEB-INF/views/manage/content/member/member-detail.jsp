<%--
  Created by IntelliJ IDEA.
  User: taedi
  Date: 2021/11/20
  Time: 1:53 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" href="${path}/css/manage/product.css">

<%--${requestScope.result}--%>


<p>${requestScope.result.username}</p>
<p>${requestScope.result.name}</p>
<p>${requestScope.result.email}</p>
<p>${requestScope.result.emailConfirm}</p>
<p>${requestScope.result.phone}</p>
<p>${requestScope.result.regDate}</p>
<p>${requestScope.result.withdrawalDate}</p>
<p>${requestScope.result.tempPass}</p>
<p>${requestScope.result.enabled}</p>
<p>
    <c:set var="grade" value="0" scope="page" />
    <c:forEach var="auth" items="${requestScope.result.authority}">
        <c:if test="${auth.authority.grade > grade}">
            <c:set var="grade" value="${auth.authority.grade}" />
            <c:set var="title" value="${auth.authority.title}" />
        </c:if>
    </c:forEach>
    ${title}
</p>



<p><a href="javascript:history.go(-1);">뒤로가기</a></p>
