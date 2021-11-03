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
<c:set var="path" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>메인페이지</title>
</head>
<body>

    <header>
        <jsp:include page="division/main/header.jsp" />
    </header>

    <nav>
        <jsp:include page="division/main/nav.jsp" />
    </nav>

    <main>

        <a href="login">로그인</a>
        <a href="logout">로그아웃</a>


        <sec:authorize access="isAuthenticated()">
            <p>principal : <sec:authentication property="principal.member"/></p>
            <p>아이디 : <sec:authentication property="principal.member.username"/></p>
            <p>이름 : <sec:authentication property="principal.member.name"/></p>
            <p>이메일 : <sec:authentication property="principal.member.email"/></p>
            <p>권한 : <sec:authentication property="principal.member.authority"/></p>
        </sec:authorize>

    </main>

    <footer>
        <jsp:include page="division/main/footer.jsp" />
    </footer>

    <script src="${path}/js/jquery-3.6.0.min.js"></script>
</body>
</html>
