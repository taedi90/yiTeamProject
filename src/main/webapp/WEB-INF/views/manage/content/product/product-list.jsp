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
<link rel="stylesheet" href="${path}/css/manage/product/product-list.css">

<%--${requestScope.result}--%>

<div id="table_wrap">
    <div id="title">
        상품 관리
    </div>
    <div id="actions">
        <input type="text" name="keyword" value="${requestScope.result.keyword}">
        <button onclick="search()">검색</button>
        <select onchange="publishOption(this)">
            <option value="" hidden>등록 여부</option>
            <option value="true" <c:if test="${requestScope.result.publish}">selected</c:if>>등록 완료</option>
            <option value="false" <c:if test="${requestScope.result.publish eq false}">selected</c:if>>작성 중</option>
        </select>
        <select onchange="hideOption(this)">
            <option value="" hidden>숨김 여부</option>
            <option value="true" <c:if test="${requestScope.result.hide}">selected</c:if>>숨김 상품</option>
            <option value="false" <c:if test="${requestScope.result.hide eq false}">selected</c:if>>공개 상품</option>
        </select>
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
                <th>작성자</th>
                <th>등록</th>
                <th>숨김</th>
            </tr>

            <c:forEach var="item" items="${requestScope.result.records}">
                <tr>
                    <td><input type="checkbox" class="checkbox" name="${item.itemNo}"></td>
                    <td onclick="window.location.href = 'manage?section=product&func=detail&itemNo=' + ${item.itemNo}">${item.itemNo}</td>
                    <td onclick="window.location.href = 'manage?section=product&func=detail&itemNo=' + ${item.itemNo}">
                        <c:if test="${item.image ne null}">
                            <img src="upload/${item.image}/thumb_80.png" alt="">
                        </c:if>
                    </td>
                    <td onclick="window.location.href = 'manage?section=product&func=detail&itemNo=' + ${item.itemNo}">${item.category.title}</td>
                    <td onclick="window.location.href = 'manage?section=product&func=detail&itemNo=' + ${item.itemNo}">${item.name}</td>
                    <td onclick="window.location.href = 'manage?section=product&func=detail&itemNo=' + ${item.itemNo}">${item.price}</td>
                    <td data-name="${item.member.name}"
                        data-reg-date="${item.member.regDate}">${item.member.username}</td>
                    <td>
                        <c:if test="${item.publish}">
                            ✅
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${item.hide}">
                            ✅
                        </c:if>
                    </td>
                </tr>
            </c:forEach>


        </table>
    </div>
    <div id="link">

        <%-- 기본 링크 설정 --%>
        <c:set var="link"
               value="manage?section=product&func=list&amount=${requestScope.result.amount}&order=${requestScope.result.order}"/>
        <c:if test="${requestScope.result.category ne null}">
            <c:set var="link" value="${link}&category=${requestScope.result.category}"/>
        </c:if>
        <c:if test="${requestScope.result.keyword ne null}">
            <c:set var="link" value="${link}&keyword=${requestScope.result.keyword}"/>
        </c:if>
        <c:if test="${requestScope.result.publish ne null}">
            <c:set var="link" value="${link}&publish=${requestScope.result.publish}"/>
        </c:if>
        <c:if test="${requestScope.result.hide ne null}">
            <c:set var="link" value="${link}&hide=${requestScope.result.hide}"/>
        </c:if>

        <%-- 이전 --%>
        <c:if test="${requestScope.result.prev > 0}">
            <a href="${link}&pageNo=${requestScope.result.prev}">이전</a>&nbsp;
        </c:if>

        <%-- 페이지번호 --%>
        <c:forEach begin="${requestScope.result.startPage}" end="${requestScope.result.endPage}" step="1" varStatus="i">
            <c:set var="pgNo" value="${requestScope.result.startPage + i.count - 1}"/>
            <c:choose>
                <c:when test="${pgNo ne requestScope.result.pageNo}">
                    &nbsp;<a href="${link}&pageNo=${pgNo}">${pgNo}</a>&nbsp;
                </c:when>
                <c:when test="${pgNo eq requestScope.result.pageNo}"><b style="color: #6e391a;">${pgNo}</b></c:when>
            </c:choose>
        </c:forEach>

        <%-- 다음 --%>
        <c:if test="${requestScope.result.next > 0}">
            &nbsp;<a href="${link}&pageNo=${requestScope.result.next}">다음</a>
        </c:if>
    </div>
</div>

<script src="${path}/js/manage/product/product-list.js"></script>