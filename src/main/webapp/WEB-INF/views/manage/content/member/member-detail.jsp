<%--
  Created by IntelliJ IDEA.
  User: taedi
  Date: 2021/11/20
  Time: 1:53 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" href="${path}/css/manage/member/member-detail.css">

<%--${requestScope.result}--%>

<div id="table_wrap">
    <div id="member">
        <div class="row">
            <h2>회원 정보</h2>
        </div>

        <div class="row">
            <h3>기본정보</h3>
        </div>

        <div class="row">
            <div class="key_col">아이디</div>
            <div class="value_col">${requestScope.result.username}</div>
        </div>

        <div class="row">
            <div class="key_col">이름</div>
            <div class="value_col">${requestScope.result.name}</div>
        </div>

        <div class="row">
            <div class="key_col">이메일</div>
            <div class="value_col">${requestScope.result.email}</div>
        </div>

        <div class="row">
            <div class="key_col">이메일 인증</div>
            <div class="value_col">${requestScope.result.emailConfirm}</div>
        </div>

        <div class="row">
            <div class="key_col">소셜 여부</div>
            <div class="value_col">${requestScope.result.social}</div>
        </div>

        <div class="row">
            <div class="key_col">연락처</div>
            <div class="value_col">${requestScope.result.phone}</div>
        </div>

        <div class="row">
            <div class="key_col">가입일</div>
            <div class="value_col">${requestScope.result.regDate}</div>
        </div>

        <div class="row">
            <div class="key_col">탈퇴일</div>
            <div class="value_col">${requestScope.result.withdrawalDate}</div>
        </div>

        <div class="row">
            <div class="key_col">임시 비밀번호</div>
            <div class="value_col">${requestScope.result.tempPass}</div>
        </div>

        <c:set var="grade" value="0" scope="page" />
        <c:forEach var="auth" items="${requestScope.result.authority}">
        <c:if test="${auth.authority.grade > grade}">
            <c:set var="grade" value="${auth.authority.grade}" />
            <c:set var="title" value="${auth.authority.title}" />
        </c:if>
        </c:forEach>

        <div class="row">
            <div class="key_col">등급</div>
            <div class="value_col">${title}</div>
        </div>

    </div>
    <button onclick="history.go(-1)">뒤로가기</button>
</div>