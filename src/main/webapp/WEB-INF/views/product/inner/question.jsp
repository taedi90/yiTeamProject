<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<%-- 질문이 담길 테이블 --%>
<table id="question_table">
</table>

<%-- 로그인 안하면 작성 불가 --%>
<div>
    <form name="question_form">
        <div>
            <input name="title" type="text" placeholder="질문 제목">
        </div>
        <div>
            <textarea name="text" id="" cols="30" rows="10" placeholder="질문 내용"></textarea>
        </div>
        <div>
            <button type="button" onclick="postQuestion()">질문 등록</button>
        </div>
    </form>
</div>

<script src="${path}/js/common/ajax.js" defer></script>
<script src="${path}/js/product/question.js" defer></script>

