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
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" href="${path}/css/manage/product/product-editor.css">

<div id="table_wrap">
    <div id="product">
        <div class="row">
            <h2>기본정보 <span style="color: red">*</span></h2>
        </div>

        <%-- 상품번호 --%>
        <input type="hidden" class="item_input" name="itemNo" value="${requestScope.result.itemNo}">


        <div class="row">
            <div class="key_col"><label for="">상품명</label></div>
            <div class="value_col"><input type="text" class="item_input" name="name"
                                          value="${requestScope.result.name}"></div>
        </div>
        <div class="row">
            <div class="key_col"><label for="">판매금액</label></div>
            <div class="value_col"><input type="number" class="item_input" name="price"
                                          value="${requestScope.result.price}" min="0"></div>
        </div>
        <div class="row">
            <div class="key_col"><label for="">카테고리</label></div>
            <div class="value_col">
                <select name="categoryNo" id="category_no">
                    <option value="1" <c:if test="${requestScope.result.category.categoryNo eq 1}">selected</c:if>>아우터
                    </option>
                    <option value="2" <c:if test="${requestScope.result.category.categoryNo eq 2}">selected</c:if>>원피스
                    </option>
                    <option value="3" <c:if test="${requestScope.result.category.categoryNo eq 3}">selected</c:if>>상의
                    </option>
                    <option value="4" <c:if test="${requestScope.result.category.categoryNo eq 4}">selected</c:if>>하의
                    </option>
                    <option value="5" <c:if test="${requestScope.result.category.categoryNo eq 5}">selected</c:if>>배송비
                    </option>
                </select>
            </div>
        </div>

        <div class="row">
            <div class="key_col"><label for="">상품 숨기기</label></div>
            <div class="value_col">
                <select name="hide" class="item_input">
                    <option value="true" <c:if test="${requestScope.result.hide}">selected</c:if>>적용</option>
                    <option value="false" <c:if test="${!requestScope.result.hide}">selected</c:if>>미적용</option>
                </select>
            </div>
        </div>

    </div>

    <%-- 옵션 기본 1개 이상, 추가 가능 --%>
    <div id="option">
        <div class="row">
            <h2>옵션 <span style="color: red">*</span></h2>
        </div>
        <c:forEach var="option" items="${requestScope.result.options}" varStatus="i">
            <div class="option_input">
                <input type="hidden" name="optionNo" value="${option.optionNo}">
                <input type="text" name="name" placeholder="옵션명" value="${option.name}">
                <input type="number" name="optionPrice" placeholder="추가금액" value="${option.optionPrice}" min="0">
                <input type="number" name="stock" placeholder="재고수량" value="${option.stock}" min="0">
                <button onclick="addOption()">+</button>
                <c:if test="${i.count > 1}">
                    <button onclick="removeOption(this.parentNode)">-</button>
                </c:if>
            </div>
        </c:forEach>


    </div>

    <%-- 할인 --%>
    <div id="discount">
        <div class="row">
            <h2>할인</h2>
        </div>
        <h3>기간 할인</h3>
        <div class="row">
            <div class="key_col"><label for="">할인 금액</label></div>
            <div class="value_col"><input type="number" class="item_input" name="discount"
                                          value="${requestScope.result.discount}" min="0"></div>
        </div>

        <div class="row">
            <div class="key_col"><label for="">할인 시작</label></div>
            <fmt:formatDate var="startDiscount" value="${requestScope.result.startDiscount}" pattern="yyyy-MM-dd"/>
            <div class="value_col"><input type="date" class="item_input" name="startDiscount" value="${startDiscount}">
            </div>
        </div>

        <div class="row">
            <div class="key_col"><label for="">할인 종료</label></div>
            <fmt:formatDate var="endDiscount" value="${requestScope.result.endDiscount}" pattern="yyyy-MM-dd"/>
            <div class="value_col"><input type="date" class="item_input" name="endDiscount" value="${endDiscount}">
            </div>
        </div>

        <h3>쿠폰 할인</h3>
        <div class="row">
            <div class="key_col"><label for="">쿠폰 할인 여부</label></div>
            <div class="value_col">
                <select name="couponAllow" class="item_input">
                    <option value="true" <c:if test="${requestScope.result.couponAllow}">selected</c:if>>적용</option>
                    <option value="false" <c:if test="${!requestScope.result.couponAllow}">selected</c:if>>미적용</option>
                </select>
            </div>
        </div>

    </div>

    <%-- 상품 상세 --%>
    <div id="detail">
        <div id="detail_info">
            <div class="info_holder">
                <div class="row">
                    <h2>상품 상세</h2>
                </div>

                <div class="row">
                    <div class="key_col"><label for="">제목</label></div>
                    <div class="value_col"><input type="text" class="item_input" name="title"
                                                  value="${requestScope.result.title}"></div>
                </div>

                <div class="row">
                    <div class="key_col"><label for="">썸네일</label></div>
                    <div class="value_col">
                        <input type="hidden" class="item_input" name="image" value="${requestScope.result.image}">
                        <input type="file" name="uploadThumb">
                    </div>
                </div>


                <div class="row">
                    <div class="key_col"><label for="">정보제공고시</label></div>
                    <div class="value_col">
                        <button>선택하기</button>
                    </div>
                </div>

            </div>

            <div class="image_holder">

                <c:if test="${requestScope.result.image ne null}">
                    <img src="upload/${requestScope.result.image}/thumb_130.png" alt="썸네일 이미지"/>
                </c:if>

            </div>
        </div>


        <div class="row">
            <!-- SmartEditor2 -->
            <input id="seUpload" type="file" style="display: none" multiple accept="image/jpeg, image/png, image/gif">
            <%--            <input type="hidden" class="item_input" name="text">--%>
            <textarea name="smartEditor" id="smartEditor"
                      style="width: 100%; height: 412px;">${requestScope.result.text}</textarea>
        </div>

    </div>


    <%-- publish --%>
    <button onclick="save()">등록하기</button>
    <%-- non-publish --%>
    <c:if test="${requestScope.result.publish ne true}">
        <button onclick="tempSave()">임시저장</button>
        <button onclick="cancel()">삭제하기</button>
    </c:if>


</div>

<script src="${path}/js/manage/product/product-editor.js"></script>
<script type="text/javascript" src="${path}/se2/js/HuskyEZCreator.js" defer></script>
<script type="text/javascript" src="${path}/js/common/smart-editor.js" defer></script>