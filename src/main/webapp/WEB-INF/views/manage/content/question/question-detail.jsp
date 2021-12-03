<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" href="${path}/css/manage/product/product-detail.css">


<div id="table_wrap">
    <div id="product">
        <div class="row">
            <h2>질문 답변</h2>
        </div>

        <div class="row">
            <h3>질문 내용</h3>
        </div>

        <div class="row">
            <div class="key_col">제목</div>
            <div class="value_col">${requestScope.result.title}</div>
        </div>
        <div class="row">
            <div class="key_col">작성자</div>
            <div class="value_col">${requestScope.result.member.name} (${requestScope.result.member.username})</div>
        </div>
        <div class="row">
            <div class="key_col">작성일</div>
            <div class="value_col">${requestScope.result.regDate}</div>
        </div>
        <div class="row">
            <div class="key_col">내용</div>
            <div class="value_col">${requestScope.result.text}</div>
        </div>

        <br>

        <div class="row">
            <h3>답변 작성</h3>
        </div>
        <form name="reply_form">
            <div class="row">
                <div class="key_col">제목</div>
                <div class="value_col"><input name="title" type="text" value="${requestScope.result.answerTitle}" style="width: 100%"></div>
            </div>
            <div class="row">
                <div class="key_col">내용</div>
                <div class="value_col">
                    <textarea name="text" id=""  style="width: 100%; height: 15rem; resize:none;">${requestScope.result.answerText}</textarea>
                </div>
            </div>
        </form>



    </div>

    <button onclick="ajax('item/question',{questionNo:'${requestScope.result.questionNo}', answerTitle:document.reply_form.title.value,answerText:document.reply_form.text.value},()=>{history.go(-1)},()=>{},'put');">답변등록</button>
    <button onclick="history.go(-1)">뒤로가기</button>

</div>