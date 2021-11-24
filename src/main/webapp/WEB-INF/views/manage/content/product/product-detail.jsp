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
<link rel="stylesheet" href="${path}/css/manage/product/product-detail.css">

<%--${requestScope.result}--%>

<div id="table_wrap">
    <div id="product">
        <div class="row">
            <h2>상품 상세</h2>
        </div>

        <div class="row">
            <h3>기본정보</h3>
        </div>

        <div class="row">
            <div class="key_col">상품 번호</div>
            <div class="value_col">${requestScope.result.itemNo}</div>
        </div>
        <div class="row">
            <div class="key_col">상품명</div>
            <div class="value_col">${requestScope.result.name}</div>
        </div>
        <div class="row">
            <div class="key_col">판매 금액</div>
            <div class="value_col">${requestScope.result.price}</div>
        </div>
        <div class="row">
            <div class="key_col">카테고리</div>
            <div class="value_col">${requestScope.result.category.title}</div>
        </div>
        <br>

        <div class="row">
            <h3>할인</h3>
        </div>
        <div class="row">
            <div class="key_col">할인 금액</div>
            <div class="value_col">${requestScope.result.discount}</div>
        </div>
        <div class="row">
            <div class="key_col">할인 시작</div>
            <div class="value_col">${requestScope.result.startDiscount}</div>
        </div>
        <div class="row">
            <div class="key_col">할인 종료</div>
            <div class="value_col">${requestScope.result.endDiscount}</div>
        </div>
        <div class="row">
            <div class="key_col">쿠폰 할인 여부</div>
            <div class="value_col">${requestScope.result.couponAllow}</div>
        </div>

        <br>
        <div class="row">
            <h3>작성정보</h3>
        </div>
        <div class="row">
            <div class="key_col">작성일자</div>
            <div class="value_col">${requestScope.result.regDate}</div>
        </div>
        <div class="row">
            <div class="key_col">최종수정</div>
            <div class="value_col">${requestScope.result.modDate}</div>
        </div>
        <div class="row">
            <div class="key_col">작성자</div>
            <div class="value_col">${requestScope.result.member.username} (${requestScope.result.member.name})</div>
        </div>
        <div class="row">
            <div class="key_col">작성완료</div>
            <div class="value_col">${requestScope.result.publish}</div>
        </div>
        <div class="row">
            <div class="key_col">숨김처리</div>
            <div class="value_col">${requestScope.result.hide}</div>
        </div>

        <br>

        <div class="row">
            <h3>옵션</h3>
        </div>

        <c:forEach var="option" items="${requestScope.result.options}">

            <div style="border: 0.1rem solid #dadada;">
                <div class="row">
                    <div class="key_col">번호</div>
                    <div class="value_col">${option.optionNo}</div>
                </div>
                <div class="row">
                    <div class="key_col">이름</div>
                    <div class="value_col">${option.name}</div>
                </div>
                <div class="row">
                    <div class="key_col">추가금액</div>
                    <div class="value_col">${option.optionPrice}</div>
                </div>
                <div class="row">
                    <div class="key_col">재고</div>
                    <div class="value_col">${option.stock}</div>
                </div>
            </div>

        </c:forEach>

    </div>

    <sec:authorize access="isAuthenticated()">
        <sec:authentication var="username" property="principal.username"/>
        <c:if test="${username eq requestScope.result.member.username}">
            <button onclick="window.location.href = 'manage?section=product&func=edit&itemNo=${requestScope.result.itemNo}'">수정하기</button>
        </c:if>
    </sec:authorize>

    <button onclick="history.go(-1)">뒤로가기</button>

</div>



