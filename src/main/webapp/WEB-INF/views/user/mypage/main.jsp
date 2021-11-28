<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<div class="point_and_coupon">
    <ul>
        <li>
            <h6>point</h6>
            <a href=""> 0 </a>
        </li>

        <li>
            <h6>쿠폰</h6>
            <a href=""> 0 </a>
        </li>
    </ul>
</div>

<div class="mypage_order_list">
    <h1> 최근 주문 내역 </h1>
    <div class="order_list"> 주문 내역이 없습니다.</div>
</div>

<div class="mypage_deliver_list">
    <h1> 현재 배송 현황 </h1>
    <div class="deliver_list"> 배송중인 상품이 없습니다.</div>
</div>


