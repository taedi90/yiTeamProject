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
    <link rel="stylesheet" href="${path}/css/common/main.css">
    <link rel="stylesheet" href="${path}/css/common/main_content.css">
    <title>메인페이지</title>
</head>
<body>

<div id="wrap">
    <%@ include file="division/common/header.jsp" %>
    <%@ include file="division/common/nav.jsp" %>


    <!-- content -->
    <%--    <div class="content">--%>

    <%--            <div class="event_banner">--%>

    <%--            </div>--%>

    <%--            <div class="best">--%>

    <%--                <div class="best_product_wrap">--%>
    <%--                    --%>
    <%--                    <div class="best_product">--%>
    <%--                        <div class="best_product_img">이미지영역</div>--%>
    <%--                        <div class="best_product_info_wrap">--%>
    <%--                            <a class="product" href="#">--%>
    <%--                                <div class="best_product_name">[EVENT] 11월 특가 롱원피스</div>--%>
    <%--                                <div class="best_product_price"> --%>
    <%--                                    <div style="display: inline-block;"><span>50,000원</span>--%>
    <%--                                        <span><strike>55,000원</strike></span>--%>
    <%--                                    </div>--%>
    <%--                                    <span style="display: inline-block;"> 500원 적립</span>--%>
    <%--                                </div>--%>
    <%--                                <div class="best_product_size"> S / M / L / XL</div>--%>
    <%--                                <div class="best_product_information"> 선선한 가을날 잘 어울리는 원피스</div>--%>
    <%--                                <div class="best_product_color">--%>
    <%--                                    <span style="background-color: rgba(255, 255, 255, 0.918);"></span>--%>
    <%--                                    <span style="background-color: lightpink;"></span>--%>
    <%--                                </div>--%>
    <%--                            </a>                --%>
    <%--                        </div>--%>
    <%--                    </div>--%>

    <%--                    <div class="best_product"></div>--%>
    <%--                    <div class="best_product"></div>--%>
    <%--                    <div class="best_product"></div>--%>
    <%--                    <div class="best_product"></div>--%>
    <%--                    <div class="best_product"></div>--%>
    <%--                    <div class="best_product"></div>--%>
    <%--                    <div class="best_product"></div>--%>
    <%--                    <div class="best_product"></div>--%>
    <%--                    <div class="best_product"></div>--%>
    <%--                    <div class="best_product"></div>--%>
    <%--                    <div class="best_product"></div>--%>

    <%--                </div>--%>

    <%--            </div>--%>

    <%--        </div>--%>

    <%--${result}--%>


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


    <div class="content">
        <div class="event_banner"></div>
        <div class="product_container">



            <c:forEach var="item" items="${result.records}" varStatus="status">
                <a href="detail?itemNo=${item.itemNo}">
                    <div class="product">
                        <div class="image_holder">
                            <img src="upload/${item.image}/thumb_350.png" alt="">
                        </div>
                        <div class="desc">
                            <p>${item.category.title}</p>
                            <p>${item.title}</p>
                            <p>${item.price}원</p>
                            <p>
                                <c:forEach var="option" items="${item.options}" varStatus="status">
                                    ${option.name}&nbsp;
                                </c:forEach>
                            </p>

                        </div>
                    </div>
                </a>
            </c:forEach>

        </div>
    </div>


    <%@ include file="division/common/footer.jsp" %>
</div>


<script src="${path}/js/payment/jquery-3.6.0.min.js"></script>
</body>
</html>
