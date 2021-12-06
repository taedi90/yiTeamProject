<%--
  Created by IntelliJ IDEA.
  User: taedi
  Date: 2021/11/19
  Time: 2:26 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" href="${path}/css/manage/order/order-detail.css">


<div id="table_wrap">
    <div id="product">
        <div class="row">
            <h2>주문 상세</h2>
        </div>

        <div class="row">
            <h3>기본정보</h3>
        </div>

        <div class="row">
            <div class="key_col">주문 번호</div>
            <div class="value_col">${result.orderNo}</div>
        </div>
        <div class="row">
            <div class="key_col">주문 일자</div>
            <div class="value_col"><fmt:formatDate value="${result.regDate}" pattern="yyyy-MM-dd"/></div>
            
        </div>
        <div class="row">
            <div class="key_col">주문 상태</div>
            <div class="value_col">${result.status}</div>
        </div>
        <div class="row">
            <div class="key_col">결제 금액</div>           
           		<c:set var="amount" value="0" scope="page"></c:set>
				<c:forEach var="payment" items="${result.paymentList}">				
					<c:set var="amount" value="${payment.amount}" scope="page"></c:set>
				</c:forEach> 								
            <div class="value_col">${amount}원</div>
        </div>
        <br>
 
        <div class="row">
            <h3>주문 상품</h3>
        </div>
        
        <c:forEach var="orderItemList" items="${result.itemList}">
        <div class="row" >
            <div class="key_col"> <div class="Thumbnail"><img alt="" src="upload/${orderItemList.option.item.image}/thumb_130.png" width="130px"></div></div>
            <div class="value_col order_item_info">
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
        </c:forEach> 
 </div>      


    <button onclick="history.go(-1)">뒤로가기</button>

</div>



