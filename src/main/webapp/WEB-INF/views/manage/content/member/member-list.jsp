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
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" href="${path}/css/manage/member/member-list.css">

<div id="table_wrap">
    <div id="title">
        회원 관리
    </div>
    <div>
        <input type="radio" name="type">
        아이디
        <input type="radio" name="type">
        이름
        <input type="radio" name="type">
        이메일
        <input type="text">
        <button>검색</button>
    </div>
    <div id="actions">
        <button>인증 메일 재발송</button>
        <button>탈퇴 처리</button>
        <button>엑셀 내려받기</button>
        <button>쿠폰 발행</button>
    </div>
    <div id="content">
        <table>
            <tr>
                <th>선택</th>
                <th>No</th>
                <th>아이디</th>
                <th>이름</th>
                <th>이메일</th>
                <th>연락처</th>
                <th>소셜 아이디</th>
                <th>가입일</th>
                <th>탈퇴일</th>
            </tr>

            <c:forEach var="member" items="${requestScope.result.records}" varStatus="i">
                <tr>
                    <td><input type="checkbox" class="checkbox" name="${member.username}"></td>
                    <td onclick="window.location.href = 'manage?section=product&func=detail&itemNo=' + ${member.username}">${i.count}</td>
                    <td onclick="window.location.href = 'manage?section=product&func=detail&itemNo=' + ${member.username}">
                            ${member.username}
                    </td>
                    <td onclick="window.location.href = 'manage?section=product&func=detail&itemNo=' + ${member.username}">${member.name}</td>
                    <td onclick="window.location.href = 'manage?section=product&func=detail&itemNo=' + ${member.username}">${member.email}</td>
                    <td onclick="window.location.href = 'manage?section=product&func=detail&itemNo=' + ${member.username}">${member.phone}</td>
                    <td>
                        <c:if test="${member.social}">
                            ✅
                        </c:if>
                    </td>
                    <fmt:formatDate var="regDate" value="${member.regDate}" pattern="yyyy-MM-dd"/>
                    <td>${regDate}</td>
                    <fmt:formatDate var="withdrawalDate" value="${member.withdrawalDate}" pattern="yyyy-MM-dd"/>
                    <td>${withdrawalDate}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>


<div id="link">

    <%-- 기본 링크 설정 --%>
    <c:set var="link"
           value="manage?section=member&func=list&amount=${requestScope.result.amount}&order=${requestScope.result.order}"/>

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

<%--${requestScope.result}--%>