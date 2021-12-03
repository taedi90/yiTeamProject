<%--
  Created by IntelliJ IDEA.
  User: taedi
  Date: 2021/11/09
  Time: 2:12 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<link rel="stylesheet" href="${path}/css/manage/sidebar.css">

<div class="sidebar_section">
    <div class="menu_item" onclick="toggleHide()">
        <div class="icon_holder" data-tooltip="펼치기">
            <img src="${path}/img/manage/icon/hide.png" id="hide_image" alt="">
        </div>
        <div class="description_holder">
            숨기기
        </div>
    </div>
    <sec:authorize access="hasRole('ADMIN')">
        <div class="menu_item" onclick="window.location.href = '?section=admin&func=list'">
            <div class="icon_holder" data-tooltip="관리자 설정">
                <img src="${path}/img/manage/icon/admin.png" alt="">
            </div>
            <div class="description_holder">
                관리자 설정
            </div>
        </div>
    </sec:authorize>

    <hr>
</div>

<div class="sidebar_section">
    <%--    <hr>--%>

    <div class="menu_item" onclick="window.location.href = '?section=member&func=list'">
        <div class="icon_holder" data-tooltip="회원 관리">
            <img src="${path}/img/manage/icon/member.png" alt="">
        </div>
        <div class="description_holder">
            회원 관리
        </div>
    </div>

    <div class="menu_item" onclick="window.location.href = '?section=product&func=list'">
        <div class="icon_holder" data-tooltip="상품 관리">
            <img src="${path}/img/manage/icon/item.png" alt="">
        </div>
        <div class="description_holder">
            상품 관리
        </div>
    </div>

    <div class="menu_item">
        <div class="icon_holder" data-tooltip="배송 관리">
            <img src="${path}/img/manage/icon/delivery.png" alt="">
        </div>
        <div class="description_holder">
            배송 관리
        </div>
    </div>


    <div class="menu_item">
        <div class="icon_holder" data-tooltip="반품 취소">
            <img src="${path}/img/manage/icon/return.png" alt="">
        </div>
        <div class="description_holder">
            반품 취소
        </div>
    </div>

    <div class="menu_item" onclick="window.location.href = '?section=order&func=list'">
        <div class="icon_holder" data-tooltip="주문 조회">
            <img src="${path}/img/manage/icon/sale.png" alt="">
        </div>
        <div class="description_holder">
            주문 조회
        </div>
    </div>

    <div class="menu_item">
        <div class="icon_holder" data-tooltip="리뷰 관리">
            <img src="${path}/img/manage/icon/review.png" alt="">
        </div>
        <div class="description_holder">
            리뷰 관리
        </div>
    </div>

    <div class="menu_item">
        <div class="icon_holder" data-tooltip="질문 답변">
            <img src="${path}/img/manage/icon/question.png" alt="">
        </div>
        <div class="description_holder">
            질문 답변
        </div>
    </div>

    <%--    <hr>--%>
</div>

<div id="user_info" class="sidebar_section" onclick="window.location.href = 'main'">
    <hr>
    <div class="menu_item">
        <div class="icon_holder" data-tooltip="<sec:authentication property="principal.member.name"/> 님">
            <img src="${path}/img/manage/icon/user.png" alt="">
        </div>
        <div class="description_holder">
            <sec:authentication property="principal.member.name"/> 님
        </div>
    </div>
</div>