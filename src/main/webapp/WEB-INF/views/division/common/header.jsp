<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/common/header.css">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- header -->
    <div class="header">


        <div class="user">
            <sec:authorize access="isAnonymous()">
                <a href="login">Login</a>&nbsp;|&nbsp;<a href="policy">Register</a>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <a href="logout">Logout</a>
            </sec:authorize>
            <sec:authorize access="hasAnyRole('ADMIN','MANAGER')">
                &nbsp;|&nbsp;<a href="manage">Manager</a>
            </sec:authorize>
        </div>

        <!-- 모바일 메뉴 -->
<%--        <div class="mobile_menu">--%>

<%--            <img class="mobile_button" src="${path}/img/common/menu.svg" alt="목록">--%>

<%--        </div>--%>


        <!-- logo 데스크탑, 모바일 공통사용 -->
        <div class="logo">
            <a href="main"><img src="${path}/img/common/logo.svg" class="logo_img" alt="logo"></a>
        </div>

        <!-- 모바일 상단바 -->
        <div class="mobile_user">
            <img src="${path}/img/common/search.svg" alt="검색">
            <img src="${path}/img/common/cart.svg" alt="장바구니">
        </div>

    </div>
 