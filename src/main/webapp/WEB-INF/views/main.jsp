<%--
  Created by IntelliJ IDEA.
  User: taedi
  Date: 2021/10/31
  Time: 5:58 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- jstl 태그 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 스프링 시큐리티 태그 --%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, maximum-scale=1">
    <link rel="stylesheet" href="${path}/css/common/main.css">
    <link rel="stylesheet" href="${path}/css/common/main_content.css">
    <title>메인페이지</title>
</head>
<body>

<div id="wrap">
    <%@ include file="division/common/header.jsp" %>
    <%@ include file="division/common/nav.jsp" %>


    <div class="content">
        <div class="event_banner"></div>
        <div class="product_container">



            <c:forEach var="item" items="${result.records}" varStatus="status">
                <a href="detail?itemNo=${item.itemNo}">
                    <div class="product">
                        <div class="image_holder">
                            <img src="upload/${item.image}/thumb_350.png" onerror="this.src='${path}/img/common/lazy.svg'" alt="">
<%--                            <img class="lazy" src="${path}/img/common/lazy.svg" data-src="upload/${item.image}/thumb_350.png" onerror="this.src='${path}/img/common/lazy.svg'" alt="">--%>
                        </div>
                        <div class="desc">
                            <p class="product_category">${item.category.title}</p>
                            <p class="product_title">${item.title}</p>

                            <p class="product_price"><fmt:formatNumber value="${item.price}" pattern="#,###원"/></p>
                            <p class="product_option">
                                <c:forEach var="option" items="${item.options}" varStatus="status">
                                    ${option.name}&nbsp;
                                </c:forEach>
                            </p>

                        </div>
                    </div>
                </a>
            </c:forEach>

        </div>


        <div id="more" onclick="viewMore()">
            더보기
        </div>


    </div>


    <%@ include file="division/common/footer.jsp" %>
</div>


<%--<script src="${path}/js/payment/jquery-3.6.0.min.js"></script>--%>
<script src="${path}/js/common/lazy.js"></script>
<script src="${path}/js/common/main.js"></script>
<script src="${path}/js/common/ajax.js"></script>
</body>
</html>
