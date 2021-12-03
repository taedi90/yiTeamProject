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
        질문 답변
    </div>
    <div id="actions">
        <input type="text" name="keyword" value="${requestScope.result.keyword}">
        <button onclick="search()">검색</button>
        <select onchange="hideOption(this)">
            <option value="" hidden>답변 여부</option>
            <option value="true" <c:if test="${requestScope.result.category}">selected</c:if>>답변완료</option>
            <option value="false" <c:if test="${requestScope.result.category ne null}">selected</c:if>>미답변</option>
        </select>
        <button type="button" onclick="">선택삭제</button>
    </div>
    <div id="content">
        <table>
            <tr>
                <th onclick="selectAll()">선택</th>
                <th>No</th>
                <th>상품 번호</th>
                <th>상품 이미지</th>
                <th>제목</th>
                <th>질문자</th>
                <th>작성일</th>
                <th>답변여부</th>
            </tr>

            <c:forEach var="item" items="${requestScope.result.records}">
                <tr>
                    <td><input type="checkbox" class="checkbox" name="${item.questionNo}"></td>
                    <td>${item.questionNo}</td>
                    <td>${item.item.itemNo}</td>
                    <td onclick="window.location.href = 'manage?section=product&func=detail&itemNo=' + ${item.item.itemNo}">
                        <c:if test="${item.item.image ne null}">
                            <img src="upload/${item.item.image}/thumb_80.png" alt="">
                        </c:if>
                    </td>
                    <td onclick="window.location.href = 'manage?section=question&func=detail&questionNo=' + ${item.questionNo}">${item.title}</td>
                    <td onclick="window.location.href = 'manage?section=question&func=detail&questionNo=' + ${item.questionNo}">${item.member.name}</td>
                    <td onclick="window.location.href = 'manage?section=question&func=detail&questionNo=' + ${item.questionNo}">${item.regDate}</td>
                    <td>
                        <c:if test="${item.answerRegDate ne null}">
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
               value="manage?section=question&func=list&amount=${requestScope.result.amount}&order=${requestScope.result.order}"/>
        <c:if test="${requestScope.result.category ne null}">
            <c:set var="link" value="${link}&category=${requestScope.result.category}"/>
        </c:if>
        <c:if test="${requestScope.result.keyword ne null}">
            <c:set var="link" value="${link}&keyword=${requestScope.result.keyword}"/>
        </c:if>
        <c:if test="${requestScope.result.param ne null}">
            <c:set var="link" value="${link}&param=${requestScope.result.hide}"/>
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
                <c:when test="${pgNo eq requestScope.result.pageNo}">&nbsp;<span id="now_page">${pgNo}</span>&nbsp;</c:when>
            </c:choose>
        </c:forEach>

        <%-- 다음 --%>
        <c:if test="${requestScope.result.next > 0}">
            &nbsp;<a href="${link}&pageNo=${requestScope.result.next}">다음</a>
        </c:if>
    </div>
</div>

<script src="${path}/js/manage/product/product-list.js"></script>