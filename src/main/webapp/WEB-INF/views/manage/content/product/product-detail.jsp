<%--
  Created by IntelliJ IDEA.
  User: taedi
  Date: 2021/11/19
  Time: 2:26 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" href="${path}/css/manage/product.css">

<%--${requestScope.result}--%>


<p>itemNo : ${requestScope.result.itemNo}</p>
<p>title : ${requestScope.result.category.title}</p>
<p>name : ${requestScope.result.name}</p>
<p>price : ${requestScope.result.price}</p>
<p>discount : ${requestScope.result.discount}</p>
<p>start discount : ${requestScope.result.startDiscount}</p>
<p>end discount : ${requestScope.result.endDiscount}</p>
<p>title : ${requestScope.result.title}</p>
<p>text : ${requestScope.result.text}</p>
<p>productInfoAnnounce : ${requestScope.result.productInfoAnnounce}</p>
<p>couponAllow : ${requestScope.result.couponAllow}</p>
<p>hide : ${requestScope.result.hide}</p>
<p>regDate : ${requestScope.result.regDate}</p>
<p>lastMod : ${requestScope.result.modDate}</p>
<p>author : ${requestScope.result.member.username}, name : ${requestScope.result.member.name}, regDate : ${requestScope.result.member.regDate}</p>
<p>publish : ${requestScope.result.publish}</p>

<c:forEach var="option" items="${requestScope.result.options}">
    <p>optionNo : ${option.optionNo}, optionName : ${option.name}, additionalPrice : ${option.optionPrice}, stock : ${option.stock}</p>
</c:forEach>


<sec:authorize access="isAuthenticated()">

    <sec:authentication var="username" property="principal.username" />

    <c:if test="${username eq requestScope.result.member.username}">
        <p><a href="manage?section=product&func=edit&itemNo=${requestScope.result.itemNo}">수정하기</a></p>
    </c:if>

</sec:authorize>

<p><a href="javascript:history.go(-1);">뒤로가기</a></p>
