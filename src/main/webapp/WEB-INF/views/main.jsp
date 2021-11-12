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

<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, maximum-scale=1">
    <link rel="stylesheet" href="${path}/css/common/common.css">
    <title>메인페이지</title>
</head>
<body>
	
	<div id="wrap">
	<%@ include file="division/common/header.jsp" %>
	<%@ include file="division/common/nav.jsp" %>


	<!-- content -->
    <div class="content">

        <div class="event_banner">

        </div>

        <div class="best">

            <div>

                <h1>지금 가장 인기있는 상품</h1><br><br><br>

            </div>

            <table border="1" class="best_product_table">

                <tr class="best_product_img">
                    <td> img 1 </td>
                    <td> img 2 </td>
                    <td> img 3 </td>
                    <td> img 4 </td>
                </tr>

                <tr class="best_product_name">
                    <td> con 1 </td>
                    <td> con 2 </td>
                    <td> con 3 </td>
                    <td> con 4 </td>
                </tr>

                <tr class="best_product_img">
                    <td> img 5 </td>
                    <td> img 6 </td>
                    <td> img 7 </td>
                    <td> img 8 </td>
                </tr>

                <tr class="best_product_name">
                    <td> con 5 </td>
                    <td> con 6 </td>
                    <td> con 7 </td>
                    <td> con 8 </td>
                </tr>

            </table>

            <table border="1" class="mobile_best_product_table">

                <tr class="best_product_img">
                    <td> img 1 </td>
                    <td> img 2 </td>
                </tr>

                <tr class="best_product_name">
                    <td> 이름 <br>
                        가격 </td>
                    <td> con 2 </td>
                </tr>

                <tr class="best_product_img">
                    <td> img 3 </td>
                    <td> img 4 </td>
                </tr>

                <tr class="best_product_name">
                    <td> con 3 </td>
                    <td> con 4 </td>
                </tr>

                <tr class="best_product_img">
                    <td> img 5 </td>
                    <td> img 6 </td>
                </tr>

                <tr class="best_product_name">
                    <td> con 5 </td>
                    <td> con 6 </td>
                </tr>

                <tr class="best_product_img">
                    <td> img 7 </td>
                    <td> img 8 </td>
                </tr>

                <tr class="best_product_name">
                    <td> con 7 </td>
                    <td> con 8 </td>
                </tr>


            </table>

        </div>

    </div>

${items}
<c:forEach var="item" items="${items}" varStatus="status">
    <p>${item.image}</p>
    <h2>${item.title}</h2>
    <h4>${item.category.title}</h4>
    <p>${item.price}원</p>
    <c:forEach var="option" items="${item.options}" varStatus="status">
        <p>${option.name}</p>
    </c:forEach>

</c:forEach>





   <%--  <main>

        <sec:authorize access="isAnonymous()">
            <a href="login">로그인</a>
        </sec:authorize>

        <sec:authorize access="isAuthenticated()">
            <a href="logout">로그아웃</a>
            <p>principal : <sec:authentication property="principal.member"/></p>
            <p>아이디 : <sec:authentication property="principal.username"/></p>
            <p>이름 : <sec:authentication property="principal.member.name"/></p>
            <p>이메일 : <sec:authentication property="principal.member.email"/></p>
            <p>권한 : <sec:authentication property="principal.member.authority"/></p>
        </sec:authorize>

    </main> --%>
    
    
    <div class="content" style="height:1000px">
    
    </div>
    
    
    
    	<%@ include file="division/common/footer.jsp" %>
    </div>


    <script src="${path}/js/payment/jquery-3.6.0.min.js"></script>
</body>
</html>
