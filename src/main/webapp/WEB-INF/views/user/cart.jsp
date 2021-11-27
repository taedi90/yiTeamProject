<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, maximum-scale=1">
    <title>장바구니</title>
    <link rel="stylesheet" href="${path}/css/common/main.css">
    <link rel="stylesheet" href="${path}/css/user/cart.css">
    <link rel="stylesheet" href="${path}/css/user/mypage_publish.css">

    <script src="${path}/js/common/ajax.js"></script>
    <script src="${path}/js/user/cart.js"></script>
</head>
<body>
	
	<div id="wrap">
	<%@ include file="../division/common/header.jsp" %>
	<%@ include file="../division/common/nav.jsp" %>

	
<%--	<nav class="mypage_nav">--%>
<%--            <div class="mypage_nav_left">--%>
<%--                <ul>--%>
<%--                    <li>MY PAGE</li> |--%>
<%--                    <li>LOGOUT</li>--%>
<%--                </ul>--%>
<%--            </div>--%>

<%--            <div class="welcome">--%>
<%--                <P><sec:authentication property="principal.member.name"/> 님 어서오세요.</P>--%>
<%--            </div>--%>

<%--            <div class="mypage_nav_menu">--%>
<%--                <ul>--%>
<%--                    <li>Q&A</li>--%>
<%--                    <li>NOTICE</li>--%>
<%--                    <li>FAQ</li>--%>
<%--                    <li>EVENT</li>--%>
<%--                </ul>--%>
<%--            </div>--%>


<%--        </nav>--%>
	
	
	<!-- content -->
        <div class="content">
            <div class="content_wrap">
<%--                <div class="side_wrap">--%>
<%--                    <div class="side_all">--%>
<%--                        <h1 class="side_title">MY PAGE</h1>--%>
<%--                        <div class="side_content_all">--%>
<%--                            <div class="mypage_menu">--%>
<%--                                <h3>쇼핑정보</h3>--%>
<%--                                    <ul>--%>
<%--                                        <li><a href=""> 장바구니 </a></li>--%>
<%--                                        <li><a href=""> 주문 내역 </a></li>--%>
<%--                                        <li><a href=""> 쿠폰 내역 </a></li>--%>
<%--                                        <li><a href=""> 포인트 내역 </a></li>--%>
<%--                                        <li><a href=""> 배송현황 </a></li>--%>
<%--                                    </ul>--%>
<%--                            --%>
<%--                                <h3>리뷰관리</h3>--%>
<%--                                    <ul>--%>
<%--                                        <li><a href=""> 리뷰목록 </a></li>--%>
<%--                                    </ul>--%>

<%--                                <h3>회원정보관리</h3>--%>
<%--                                    <ul>--%>
<%--                                        <li><a href=""> 문의 내역 </a></li>--%>
<%--                                        <li><a href=""> 회원정보 변경 </a></li>--%>
<%--                                        <li><a href=""> 회원탈퇴 </a></li>--%>
<%--                                    </ul>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>

<%--                <div class="mypage_content_wrap">--%>
<%--                    <div class="shopping_bag_wrap">--%>
<%--                        <h1> shopping bag </h1>--%>
<%--                        <div class="shopping_bag_list">--%>
<%--                            <div class="no_shopping_bag">--%>
<%--                                장바구니에 담긴 상품이 없습니다.--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>

<%--                </div>--%>


                <table id="cart_list">
                    <tr>
                        <td>상품이 존재하지 않습니다.</td>
                    </tr>
                </table>

                <div>
                    <div id="item_price">
                        상품 금액
                    </div>
                    <div id="delivery_fee">
                        배송비
                    </div>
                    <div id="total_price">
                        총 합계
                    </div>
                </div>

                <div>
                    <button id="purchase_button">주문하기</button>
                </div>



            </div>

        </div>




    
    	<%@ include file="../division/common/footer.jsp" %>
    </div>


    <script src="${path}/js/common/jquery-3.6.0.min.js"></script>
	<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.8.js"></script>

	<script type="text/javascript" src="${path}/js/common/nav.js"></script>
	<script type="text/javascript" src="${path}/js/common/order-util.js"></script>
	<script type="text/javascript" src="${path}/js/common/modal.js"></script>

</body>
</html>