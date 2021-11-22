<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- jstl 태그 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 스프링 시큐리티 태그 --%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, maximum-scale=1">
    <link rel="stylesheet" href="${path}/resources/css/common/main.css">
    <link rel="stylesheet" href="${path}/resources/css/common/product.css">
    <title>상품 상세페이지</title>
</head>
<body>
	
	<div id="wrap">
	<%@ include file="../division/common/header.jsp" %>
	<%@ include file="../division/common/nav.jsp" %>


	<!-- content -->
    <div class="content">

            <div id="product_detail">
                
                <div class="fix_wrap">
                    <!-- 상품 상세페이지 시작 -->
                    <div class="detail_top container">
                        <div class="product_img" style="height: 70rem;">
                            <img src="upload/${result.image}/thumb.png" alt="">
                        </div>

                        <div class="product_info">
                            <!-- form 태그와 input type=hidden태그로 값넘겨주는게 일반적인듯? -->
                            <H3 class="product_title">${result.title}</H3>
                            <!-- 금액시작 -->
                            <div class="product_price">
                                <!-- 판매가 -->
                                <div class="price dcpricet">
                                    <span id="price_span">
                                        ${result.price}
                                    </span>
                                    <span class="won">원</span>
                                </div>
                                <!-- 적립금 -->
                                <div class="point">
                                    <img src="img/icon_point3.svg" alt="point" class="point_img"> 500
                                </div>
                                <div class="product_option_table">
                                    <table>
                                        <caption>상품 옵션</caption>
                                        <colgroup>
                                            <col width="90">
                                            <col width="*">
                                        </colgroup>
                                        <tbody>
                                            <tr>
                                                <td colspan="2">
                                                    <div class="option_wrap">
                                                        <div class="option_title">
                                                            <strong>기본옵션</strong>
                                                        </div>
                                                        <!-- 옵션 선택 -->
                                                        <dl>
                                                            <dt>색상&사이즈</dt>
                                                            <dd>
                                                                <select name="option" id="option">
                                                                    <option value="null">옵션선택</option>
                                                                    <option value="1">화이트 S (+2000)</option>
                                                                    <option value="2">화이트 M</option>
                                                                    <option value="3">화이트 L</option>
                                                                    <option value="4">화이트 XL</option>
                                                                    <option value="5">핑크 S</option>
                                                                    <option value="6">핑크 M</option>
                                                                    <option value="7">핑크 L</option>
                                                                    <option value="8">핑크 XL</option>
                                                                </select>
                                                            </dd>
                                                        </dl>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td colspan="2">
                                                    <div class="selected_option_wrap">
                                                                                                             

                                                        <div id="selected_option" class="selected_option" >
                                                        
                                                            

                                                        </div>

                                                        
                                                                
                                                        
                                                        <div class="option_total">
                                                            <p class="total">
                                                                <span class="total_txt"> 총 상품 금액</span>
                                                                <strong class="total_price" id="total_price">  </strong>
                                                                <span class="total_won"> 원 </span>
                                                            </p>

                                                        </div>
                                                     

                                                    </div>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <!-- 금액끝 -->
                            <div class="product_buy_buttons">
                                <a href="" class="product_buy_button_cart"> <span class="button_cart"> 장바구니 </span></a>
                                <a href="" class="product_buy_button_buy"><span class="button_buy"> 구매하기 </span></a>
                            </div>

                        </div>

                    </div>


                </div>

            </div>
            
            <div class="detail_tab">
                <div class="tab_wrap">
                    <ul>
                        <li class="tab_product_information"> 상품 정보 </li>
                        <li class="tab_product_review"> 상품 리뷰 </li>
                        <li class="tab_product_question"> 상품 문의 </li>
                    </ul>
                </div>
            </div>

            <div class="detail_container">

                <div id="tab_product_information"></div>
                <div id="tab_product_review"></div>
                <div id="tab_product_question"></div>

            </div>

        </div>
    
    
    
    	<%@ include file="../division/common/footer.jsp" %>
    </div>


    <script src="${path}/js/common/jquery-3.6.0.min.js"></script>
	<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.8.js"></script>

	<script type="text/javascript" src="${path}/js/common/nav.js"></script>
	
	<script type="text/javascript" src="${path}/js/product/option.js"></script>
	<script type="text/javascript" src="${path}/js/product/tab.js"></script>
	
	<script type="text/javascript" src="${path}/js/payment/iamport.js"></script>

    ${result}

</body>
</html>