<%--
  Created by IntelliJ IDEA.
  User: taedi
  Date: 2021/11/28
  Time: 8:00 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" href="${path}/css/user/mypage/edit.css">

<div id="table_wrap">
    <div id="member">
        <div class="row">
            <h2>회원정보 조회 & 변경</h2>
        </div>

        <div class="row">
            <h3>기본정보</h3>
        </div>

        <div class="row">
            <div class="key_col">아이디</div>
            <c:if test="${requestScope.result.social eq true}">
                <div class="value_col">소셜 회원</div>
            </c:if>
            <c:if test="${requestScope.result.social ne true}">
                <div class="value_col">${requestScope.result.username}</div>
            </c:if>
        </div>

        <div class="row">
            <div class="key_col">이름</div>
            <div class="value_col">${requestScope.result.name}</div>
        </div>

        <div class="row">
            <div class="key_col">이메일</div>
            <div class="value_col">${requestScope.result.email}</div>
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

        <div class="row">
            <div class="key_col">가입일</div>
            <div class="value_col">${requestScope.result.regDate}</div>
        </div>

        <sec:authentication property="principal.member.social" var="social"/>
        <c:if test="${social ne true}">
            <div class="row">
                <h3>비밀번호 변경</h3>
            </div>

            <div class="row">
                <div class="key_col">기존 비밀번호</div>
                <div class="value_col"><input type="password" class="register_input" name="password"></div>
            </div>

            <div class="row">
                <div class="key_col">변경 비밀번호</div>
                <div class="value_col"><input type="password" class="register_input" name="password"></div>
            </div>

            <div class="row">
                <div class="key_col">비밀번호 확인</div>
                <div class="value_col"><input type="password" class="register_input" name="password"></div>
            </div>
        </c:if>


        <div class="row">
            <h3>연락처 변경</h3>
        </div>

        <div class="row">
            <div class="key_col">연락처</div>
            <div class="value_col"><input type="text" maxlength="11" value="${requestScope.result.phone}"></div>
        </div>

    </div>
    <button>변경하기</button>
    <button onclick="history.go(-1)">뒤로가기</button>
</div>