<%--
  Created by IntelliJ IDEA.
  User: taedi
  Date: 2021/11/11
  Time: 3:49 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" href="${path}/css/manage/product.css">

<div id="table_wrap">
    <div id="product">
        <div class="row">
            <h2>기본정보</h2>
        </div>

        <%-- 상품번호 --%>
        <input type="hidden" class="item_input" name="itemNo" value="${requestScope.result.itemNo}">


        <div class="row">
            <div class="key_col"><label for="">상품명</label></div>
            <div class="value_col"><input type="text" class="item_input" name="name"></div>
        </div>
        <div class="row">
            <div class="key_col"><label for="">판매금액</label></div>
            <div class="value_col"><input type="number" class="item_input" name="price"></div>
        </div>
        <div class="row">
            <div class="key_col"><label for="">카테고리</label></div>
            <div class="value_col">
                <select name="categoryNo" id="category_no" value="${requestScope.result.category.categoryNo}">
                    <option value="1">아우터</option>
                    <option value="2">상의</option>
                    <option value="3">하의</option>
                    <option value="4">원피스</option>
                    <option value="5">배송비</option>
                </select>
            </div>
        </div>

        <div class="row">
            <div class="key_col"><label for="">상품 숨기기</label></div>
            <div class="value_col">
                <select name="hide" class="item_input">
                    <option value="true">적용</option>
                    <option value="false" selected>미적용</option>
                </select>
            </div>
        </div>

    </div>

    <%-- 옵션 기본 1개 이상, 추가 가능 --%>
    <div id="option">
        <div class="row">
            <h2>옵션</h2>
        </div>

        <div class="option_input">
            <input type="hidden" name="optionNo" value="${requestScope.result.options[0].optionNo}">
            <input type="text" name="name" placeholder="옵션명">
            <input type="number" name="optionPrice" placeholder="추가금액">
            <input type="number" name="stock" placeholder="재고수량">
            <button onclick="addOption()">+</button>
        </div>

    </div>

    <%-- 할인 --%>
    <div id="discount">
        <div class="row">
            <h2>할인</h2>
        </div>
        <h3>기간 할인</h3>
        <div class="row">
            <div class="key_col"><label for="">할인 금액</label></div>
            <div class="value_col"><input type="number" class="item_input" name="discount"></div>
        </div>

        <div class="row">
            <div class="key_col"><label for="">할인 시작</label></div>
            <div class="value_col"><input type="date" class="item_input" name="startDiscount"></div>
        </div>

        <div class="row">
            <div class="key_col"><label for="">할인 종료</label></div>
            <div class="value_col"><input type="date" class="item_input" name="endDiscount"></div>
        </div>

        <h3>쿠폰 할인</h3>
        <div class="row">
            <div class="key_col"><label for="">쿠폰 할인 여부</label></div>
            <div class="value_col">
                <select name="couponAllow" class="item_input">
                    <option value="true" selected>적용</option>
                    <option value="false">미적용</option>
                </select>
            </div>
        </div>

    </div>

    <%-- 상품 상세 --%>
    <div id="detail">
        <div class="row">
            <h2>상품 상세</h2>
        </div>

        <div class="row">
            <div class="key_col"><label for="">제목</label></div>
            <div class="value_col"><input type="text" class="item_input" name="title"></div>
        </div>

        <div class="row">
            <div class="key_col"><label for="">썸네일</label></div>
            <div class="value_col">
                <input type="hidden" class="item_input" name="image">
                <input type="file" name="uploadThumb">
            </div>
        </div>


        <div class="row">
            <div class="key_col"><label for="">정보제공고시</label></div>
            <div class="value_col"><button>선택하기</button></div>
        </div>
        <div class="row">
            <!-- SmartEditor2 -->
            <textarea name="smartEditor" id="smartEditor" style="width: 100%; height: 412px;"></textarea>
        </div>


    </div>
        <%-- publish --%>
        <button onclick="">등록하기</button>
        <%-- non-publish --%>
        <button onclick="tempSave()">임시저장</button>
        <button>취소하기</button>

</div>

<script src="${path}/js/manage/editor.js"></script>
<script type="text/javascript" src="${path}/se2/js/HuskyEZCreator.js" defer></script>
<script type="text/javascript" src="${path}/js/common/smart-editor.js" defer></script>