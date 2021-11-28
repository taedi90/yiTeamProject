<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- jstl 태그 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 스프링 시큐리티 태그 --%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, maximum-scale=1">
    <title>마이페이지</title>
    <link rel="stylesheet" href="${path}/css/common/main.css">
    <link rel="stylesheet" href="${path}/css/user/mypage_content.css">
    <link rel="stylesheet" href="${path}/css/user/mypage_publish.css">
</head>
<body>

<div id="wrap">
    <%@ include file="../division/common/header.jsp" %>
    <%@ include file="../division/common/nav.jsp" %>


    <nav class="mypage_nav">
        <div class="mypage_nav_left">
            <ul>
                <li>MY PAGE</li>
                |
                <li>LOGOUT</li>
            </ul>
        </div>

        <div class="welcome">
            <P><sec:authentication property="principal.member.name"/> 님 어서오세요.</P>
        </div>

        <div class="mypage_nav_menu">
            <ul>
                <li>Q&A</li>
                <li>NOTICE</li>
                <li>FAQ</li>
                <li>EVENT</li>
            </ul>
        </div>
    </nav>


    <!-- content -->
    <div class="content">
        <div class="content_wrap">
            <div class="side_wrap">
                <%@ include file="mypage/sidebar.jsp" %>
            </div>

            <div class="mypage_content_wrap">
                <c:if test="${url ne null}">
                    <c:set var="result" value="${result}" scope="request" />
                    <c:import url="${url}" />
                </c:if>
            </div>

        </div>

    </div>


    <%@ include file="../division/common/footer.jsp" %>
</div>


<script src="${path}/js/common/jquery-3.6.0.min.js"></script>

<script type="text/javascript" src="${path}/js/common/modal.js" defer></script>
<script type="text/javascript" src="${path}/js/common/ajax.js" defer></script>
<script type="text/javascript" src="${path}/js/common/nav.js" defer></script>
<script type="text/javascript" src="${path}/js/user/mypage.js" defer></script>

<script type="text/javascript" src="${path}/js/payment/iamport.js"></script>

</body>
</html>