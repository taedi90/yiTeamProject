<%--
  Created by IntelliJ IDEA.
  User: taedi
  Date: 2021/11/21
  Time: 1:23 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" href="${path}/css/manage/admin/admin-list.css">

<div id="table_wrap">
    <div id="title">
        관리자 리스트
    </div>
    <div id="actions">
        <div id="search_wrap">
            <div id="searchbar">
                <input type="text" id="search_input" autocomplete="off">
                <button onclick="addManager()">추가</button>
            </div>
            <div id="autocomplete">
                <ol id="search_list">
                </ol>
            </div>
        </div>
    </div>
    <div id="content">
        <table>
            <tr>
<%--                <th>선택</th>--%>
                <th>No</th>
                <th>아이디</th>
                <th>이름</th>
                <th>이메일</th>
                <th>연락처</th>
                <th>가입일</th>
                <th>권한 삭제</th>
            </tr>

            <c:forEach var="member" items="${requestScope.result.records}" varStatus="i">
                <tr>
<%--                    <td><input type="checkbox" class="checkbox" name="${member.username}"></td>--%>
                    <td>${i.count}</td>
                    <td>${member.username}</td>
                    <td>${member.name}</td>
                    <td>${member.email}</td>
                    <td>${member.phone}</td>
                    <fmt:formatDate var="regDate" value="${member.regDate}" pattern="yyyy-MM-dd"/>
                    <td>${regDate}</td>
                    <fmt:formatDate var="withdrawalDate" value="${member.withdrawalDate}" pattern="yyyy-MM-dd"/>
                    <td><button onclick="deleteManager('${member.username}')">삭제하기</button></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>


<div id="link">

    <%-- 기본 링크 설정 --%>
    <c:set var="link"
           value="manage?section=admin&func=list&amount=${requestScope.result.amount}&order=${requestScope.result.order}"/>

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

<%--${requestScope.result}--%>

<script src="${path}/js/manage/admin/admin-list.js" defer></script>

