<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- jstl 태그 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 스프링 시큐리티 태그 --%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>


<div class="side_all">
    <h1 class="side_title">MY PAGE</h1>
    <div class="side_content_all">
        <div class="mypage_menu">
            <P><sec:authentication property="principal.member.name"/> 님 어서오세요.</P>
            <h3>쇼핑정보</h3>
            <ul>
                <li><a href=""> 장바구니 </a></li>
                <li><a href='mypage?section=order&func=detail'> 주문 내역 </a></li>
                <li><a href=""> 쿠폰 내역 </a></li>
                <li><a href=""> 포인트 내역 </a></li>
                <li><a href=""> 배송현황 </a></li>
            </ul>

            <h3>리뷰관리</h3>
            <ul>
                <li><a href=""> 리뷰목록 </a></li>
            </ul>

            <h3>회원정보관리</h3>
            <ul>
                <li><a href=""> 문의 내역 </a></li>

                <li><a href="?section=edit"> 회원정보 변경 </a></li>
                <sec:authentication property="principal.member.social" var="social"/>
                <c:if test="${social ne true}">
                    <li><a href="javascript:deactivateMember()"> 회원탈퇴 </a></li>
                </c:if>

            </ul>
        </div>
    </div>
</div>