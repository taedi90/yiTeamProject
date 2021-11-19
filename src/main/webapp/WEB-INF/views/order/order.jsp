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
    <title>주문 페이지</title>
      <link rel="stylesheet" href="${path}/css/order/order.css">
</head>
<body>
	
	<div id="wrap">
	<%@ include file="../division/common/header.jsp" %>
	<%@ include file="../division/common/nav.jsp" %>


       <!-- content -->
    <div class="content">
        <div class="content_inner">
		${orderItemList}
   
            <h1>order</h1>
            <!-- 웹 주문할 상품 테이블  -->   
            <div>       
                <form action="#">
                    <p class="table_info">주문할 상품</p>
                        <table class="order_table order_product">
            
                            <tr>
                                <td class="order_product_name">상품명</td>
                                <td class="order_product_cnt">수량</td>
                                <td class="order_product_coupon">쿠폰</td>
                                <td class="order_product_price">결제가격</td>
                            </tr>

                            <tr>
                                <td>
                	                <div class="order_items" >
                	                    <div class="Thumbnail_130" ><img alt="" src="https://www.sappun.co.kr/shopimages/sappun/0080010004663.jpg" width="130px"></div>
                	                    <div class="order_item_info">
                                    		<p class="order_item_name">상품명</p>
                                    		<p class="order_item_option">옵션명</p>
                    		                <p class="order_item_price">정상가격</p>
                	                    </div>
                	                </div>
                                </td>
                                <td>${orderItemList}수량</td>
                                <td> 
                                    <select name="" id="">
                                    <option value="naver.com">쿠폰</option>
                                    <option value="naver.com">naver.com</option>
                                    <option value=""></option>
                                    </select>
                                </td>
                                <td>결제가격</td>
                            </tr>
							
                        </table>
                </form>
            </div>
    
 
            <!-- 주문자 정보 테이블  -->
            <div>
                <form action="#">
                    <p>주문자 정보</p>
                    <table class="order_table ordering_info">
                        <!-- 주문자 이름  -->
                        <tr>           
                            <td class="order_col_name">이름</td>
                            <td><input type="text" name="name" class="order_input"></td>
                        </tr>

			            <!-- 이메일 -->
                        <tr>
                            <td class="order_col_name">E-mail</td>
                            <td>
                                <input type="text"> @ <input type="text"> 
                                <select name="email" id="email">
                                <option value="naver.com">직접입력하기</option>
                                <option value="naver.com">naver.com</option>
                                <option value=""></option>
                                </select>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>

            
            
            <!-- 배송지 정보 테이블  -->
            <div>
                <form action="#">
                    <p>배송지 정보 </p>
                    <table class="order_table delivery_info">
		                <!-- 주소 -->
                        <tr>
                            <td class="order_col_name" >주소</td>
                            <td>
                                <input type="text" value="기본 주소">
                                <input type="button" value="주소추가하기" class="order_input select_address"  onclick="location.href='addr.html'">
                            </td>
                        </tr>
		                <!-- Order name -->
                        <tr>
                            <td class="order_col_name">이름</td>
                            <td><input type="text" class="order_input"></td>
                        </tr>
		                <!-- Order phone -->
                        <tr>
                        <td class="order_col_name">휴대폰</td>
                        <td><input type="tel"  class="order_input"></td>
                        </tr>
                    </table>
               </form>
            </div>


            <!-- 모바일 총결제 금액 -->
            <div class="mobile_order_div">

               <div>
                    <form action="#">
                        <p>총 결제 금액</p>
                        <table class="mobile_payment_info" >
                            <tr>
                                <td class="payment_info_name">상품합계</td>
                                <td class="payment_info_product_sum"><input type="text">원</td>
                            </tr>
    
                            <tr>
                                <td class="payment_info_name">포인트 사용</td>
                                <td class="payment_info_point">
                                    <input type="text"> 점 <br>
                                    <span style="font-size: var(--font-xs);">총 사용가능 적립금 :0000 포인트</span>
                                </td>
                            </tr>
    
                            <tr>
                                <td class="payment_info_name">배송비</td>
                                <td class="payment_info_delivery_charge">
                                    <input type="text">원   <br>
                                    <span style="font-size: var(--font-xs);">5만원이상 구매시 무료배송(이외 2500원)</span>
                                </td>
                            </tr>
       
                            <tr>
                                <td class="payment_info_name payment_info_payment_amount">결제금액</td>
                                <td class="payment_info_payment_amount"><input type="text">원</td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
        


   
            <!-- 모바일 주문할 상품 테이블  -->    
            <div>  
       
                <form action="#">
                    <p class="mobile_table_info">주문할 상품</p>
                    <table class="mobile_order_table mobile_order_product">
            
                        <tr>
                            <td class="mobile_order_product_name">상품명</td>
                        </tr>

                        <tr>
                            <td>
                            	<div class="mobile_order_items" >
                                	<div class="Thumbnail_80" ><img alt="" src="https://www.sappun.co.kr/shopimages/sappun/0080010004663.jpg" width="80px"></div>
                                	<div class="mobile_order_item_info">
                                		<p class="mobile_order_item_name" >상품명</p>
                                		<p class="mobile_order_item_option">옵션명</p>
                    		            <p class="mobile_order_item_price">정상가격</p>
                                		<p class="mobile_order_product_cnt">수량</p>
                	                </div>
                	            </div>
                            </td>
                        </tr>
            
                        <tr>
                            <td class="mobile_order_product_coupon">쿠폰</td>
                            <td class="mobile_order_product_price">결제가격</td>
                        </tr>
            
                        <tr>
                            <td> 
                                <select name="" id="" class="mobile_coupon">
                                    <option value="naver.com">쿠폰</option>
                                    <option value="naver.com">naver.com</option>
                                    <option value=""></option>
                                </select>
                            </td>
                            <td>결제가격</td>
                        </tr>

                    </table>
                </form>
    
            </div>
        </div>
    







        <!-- 웹 결제금액 -->
        <div class="order_div">
            <div>
                <form action="#">
                <p>총 결제 금액</p>
                    <table class="payment_info" >
                        <tr>
                            <td class="payment_info_name">상품합계</td>
                            <td class="payment_info_product_sum"><input type="text">원</td>
                        </tr>

                        <tr>
                            <td class="payment_info_name">포인트 사용</td>
                            <td class="payment_info_point">
                                <input type="text">점   <br>
                                <span style="font-size: var(--font-xs);">총 사용가능 적립금 :0000 포인트</span>
                            </td>
                        </tr>

                        <tr>
                            <td class="payment_info_name">배송비</td>
                            <td class="payment_info_delivery_charge">
                                <input type="text">원   <br>
                                <span style="font-size: var(--font-xs);">5만원이상 구매시 무료배송(이외 2500원)</span>
                            </td>
                        </tr>
   
                        <tr>
                            <td class="payment_info_name payment_info_payment_amount">결제금액</td>
                            <td class="payment_info_payment_amount"><input type="text">원</td>
                        </tr>
                    </table>
                </form>
            </div>
            
            <div class="payment_info_div">
                <div> 
                    <input type="checkbox" value="ok" class="payment_info_agree"> 구매진행에 동의합니다. 
                </div>
                
                <div>
                    <input type="button" value="구매하기" class="payment_info_order">
                </div>
          

            </div>
        </div> 

    </div>

    <!-- 모바일 결제버튼 -->
    <div class="mobile_payment_info_div">
        <div> 
            <input type="checkbox" value="ok" class="payment_info_agree"> 구매진행에 동의합니다. 
        </div>
        <div style="border: 1px solid red;">
            <input type="button" value="구매하기" class="payment_info_order">
        </div>
    </div>
    
    
    	<%@ include file="../division/common/footer.jsp" %>
    </div>

	<script src="${path}/js/order/order.js"></script>
    <script src="${path}/js/common/jquery-3.6.0.min.js"></script>
</body>
</html>
