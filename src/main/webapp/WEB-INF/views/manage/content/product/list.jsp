<%--
  Created by IntelliJ IDEA.
  User: taedi
  Date: 2021/11/09
  Time: 2:32 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" href="${path}/css/manage/member.css">

<%--${requestScope.result}--%>

<div id="table_wrap">
    <div id="title">
        상품 관리
    </div>
    <div>
        <input type="text">
        <button>검색</button>
    </div>
    <div id="actions">
        <button onclick="window.location.href='?section=product&func=create'">신규 생성</button>
        <button onclick="deleteItem()">선택 삭제</button>

    </div>
    <div id="content">
        <table>
            <tr>
                <th onclick="selectAll()">선택</th>
                <th>No</th>
                <th>상품이미지</th>
                <th>카테고리</th>
                <th>상품명</th>
                <th>가격</th>
                <th>생성일</th>
                <th>숨김여부</th>
                <th>수정하기</th>
            </tr>

            <c:forEach var="item" items="${requestScope.result.records}">
                <tr>
                    <td><input type="checkbox" class="checkbox" name="${item.itemNo}"></td>
                    <td>${item.itemNo}</td>
                    <td>
                        <c:if test="${item.image ne null}">
                            <img src="upload/${item.image}/thumb_80.png" alt="">
                        </c:if>
                    </td>
                    <td>${item.category.title}</td>
                    <td>${item.name}</td>
                    <td>${item.price}</td>
                    <td>${item.regDate}</td>
                    <td>${item.hide}</td>
                    <td><button>수정</button></td>
                </tr>
            </c:forEach>

            



        </table>
    </div>
    <div id="link">

        <%-- 기본 링크 설정 --%>
        <c:set var="link" value="manage?section=product&func=list&amount=${requestScope.result.amount}&order=${requestScope.result.order}" />
        <c:if test="${requestScope.result.category ne null}" >
            <c:set var="link" value="${link}&category=${requestScope.result.category}"/>
        </c:if>
        <c:if test="${requestScope.result.keyword ne null}" >
            <c:set var="link" value="${link}&keyword=${requestScope.result.keyword}"/>
        </c:if>

            <%-- 이전 --%>
        <c:if test="${requestScope.result.prev > 0}">
            <a href="${link}&pageNo=${requestScope.result.prev}">이전</a>&nbsp;
        </c:if>

            <%-- 페이지번호 --%>
        <c:forEach begin="${requestScope.result.startPage}" end="${requestScope.result.endPage}" step="1" varStatus="i">
            &nbsp;<a href="${link}&pageNo=${requestScope.result.startPage + i.count - 1}">
                ${requestScope.result.startPage + i.count - 1}
            </a>&nbsp;
        </c:forEach>

            <%-- 다음 --%>
        <c:if test="${requestScope.result.next > 0}">
            &nbsp;<a href="${link}&pageNo=${requestScope.result.next}">다음</a>
        </c:if>
    </div>
</div>

<script src="${path}/js/manage/product-list.js"></script>