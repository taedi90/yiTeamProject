<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- jstl 태그 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 스프링 시큐리티 태그 --%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>


<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, maximum-scale=1">
    <title>주문 페이지</title>
      <link rel="stylesheet" href="${path}/css/common/main.css">
      <link rel="stylesheet" href="${path}/css/order/order.css">
</head>
<body>
 
	<input type="hidden" value="${orderDetail.orderNo}" class="order_input" name="orderNo" id="order_no">

	<div id="wrap">
	<%@ include file="../division/common/header.jsp" %>
	<%@ include file="../division/common/nav.jsp" %>


       <!-- content -->
  <div class="content">
        <div class="content_inner_order">
            <div class="row">
                <h1>order</h1>
            </div>
<!-- 주문할 상품 테이블 -->
            <div class="row table_info spacing_out">
                <p>주문할 상품</p>
                <span> 주문금액 50,000원 이상 무료배송입니다.</span>
             </div>

<!-- 주문상품 -->             
            <div>
                <div class="order_product">
                    <div class="order_product_name border_row">상품명</div>
                    <div class="order_product_cnt border_row">수량</div>
                    <div class="order_product_coupon border_row">쿠폰</div>
                    <div class="order_product_price border_row">결제가격</div>
                </div>

				<c:forEach var="orderItemList" items="${orderDetail.itemList}">
                <div class="order_items">
                    <div class="order_product_name">                 
                        <div class="Thumbnail"><img alt="" src="upload/${orderItemList.option.item.image}/thumb_130.png" width="130px"></div>
                        <div>
                        	<div class="order_item_info">
                            	<div><span class="order_item_name ">${orderItemList.option.item.name}</span></div>
                            	<div>
                            		<span class="order_item_option">[옵션]${orderItemList.option.name}</span>
                            		<span class="order_item_option_price">(+${orderItemList.option.optionPrice})</span>
                            	</div>                            
                            	<div>
                            		<span class="order_item_price">                     		
                                		<fmt:formatNumber value="${orderItemList.option.item.price}" pattern="#,###원"/>
                            		</span>
                            	</div>
                        	</div>
                        </div>
                    </div>
                    
                   	<div class="order_product_cnt">수량</div>
                   	<div class="order_product_coupon">쿠폰</div>
                   	<div class="order_product_price">
                        <fmt:formatNumber value="${orderItemList.option.item.price + orderItemList.option.optionPrice}" pattern="#,###원"/>
                   	</div>               
                </div>
 				</c:forEach>  
            </div>

           
<!-- 주문자 정보 테이블 -->

            <div class="row spacing_out" >
                <p>주문자 정보</p>
                
            </div>

            <hr>

            <div class="row">
                <div class="key_col"><label for="">이름</label></div>
                <div class="value_col"><sec:authentication property="principal.member.name"/> 님</div>
            </div>

            <div class="row email_row">
                <div class="key_col email"><label for="">이메일</label></div>
                
                <div class="value_col">
                
	                <div>
	                  <input type="email" class="register_input" name="email" placeholder="이메일"  value='<sec:authentication property="principal.member.email"/>' >
	                </div>

                                  
                </div>
            </div>

<!-- 배송지 정보 -->
<div>
            <div class="row spacing_out">
                <p>배송지 정보</p>
            </div>

            <hr>

            <div class="row">
                <div class="key_col"><label for="">주소</label></div>
                <div class="value_col add_adress">
				    <input type="text" id="sample4_postcode" placeholder="우편번호" class= "postal_code add_adress_input order_input" name="zipcode" required="required">
				    <input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br>
				    <input type="text" id="sample4_roadAddress" placeholder="도로명주소" class="road_name_address add_adress_input order_input" name="address1" required="required"><br>
				    <input type="hidden" id="sample4_jibunAddress" placeholder="지번주소">
				    <span id="guide" style="color:#999;display:none"></span>
				    <input type="text" id="sample4_detailAddress" placeholder="상세주소" name="address2" class="detailed_address add_adress_input order_input" required="required">
				    <input type="hidden" id="sample4_extraAddress" placeholder="참고항목">
                </div>
            </div>

            <div class="row ">
                <div class="key_col"><label for="">이름</label></div>
                <div class="value_col">
                    <input type="text" class="order_input" name="name" id="">
                </div>
            </div>

            <div class="row">
                <div class="key_col"><label for="">휴대폰</label></div>
                <div class="value_col">
                    <input type="tel"  class="order_input" name="phone">
                </div>
            </div>
</div>


        </div>

<!-- 결제 금액 확인 -->
        <div class="content_inner_payment">
            <div class="row total_title">
                <p>총 결제 금액</p>
            </div>

			<hr>
			    
            <div class="row">
                <div class="key_col payment_info_name"><label for="">상품합계</label></div>
                <div class="value_col payment_info_product_sum">원</div>
            </div>

            
            <div class="row">
                <div class="key_col payment_info_name"><label for="">포인트 </label></div>
                <div class="value_col payment_info_point">
                    <input type="text" class="use_point">점
                </div>
            </div>
          
            <div class="row">
                <div class="key_col payment_info_name"><label for="">배송비</label></div>
                <div class="value_col payment_info_delivery_charge">
                    원 
                </div>
            </div>

            
            <div class="row">
                <div class="key_col payment_info_name "><label for="">결제금액</label></div>
                <div class="value_col payment_info_payment_amount">원</div>
            </div>

 			<div class="mobile_sticky">
            <div class="row payment_info_agree"> 
                <input type="checkbox" value="ok" class="payment_info_agree"> 
                <span>구매진행에 동의합니다.</span>
            </div>
            
            <div class="row payment_btn">
                <input type="button" value="구매하기" id="purchase_button" class="payment_info_order">
            </div>
			</div>

        </div>
    </div>
    
    
    	<%@ include file="../division/common/footer.jsp" %>
	</div>



    <script src="${path}/js/common/jquery-3.6.0.min.js"></script>
    <script src="${path}/js/common/ajax.js"></script>
    <script src="${path}/js/order/order.js"></script>
    <script src="${path}/js/common/modal.js" defer></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script src="${path}/js/order/add-address.js" defer></script>
    <script src="${path}/js/order/payment.js" defer></script>
	<script src="${path}/js/payment/payment.js" defer></script>
	
	<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.8.js"></script>
	<script src="${path}/js/payment/iamport.js" defer></script>




</body>
</html>
