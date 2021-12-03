<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<%--  ${result}  --%>
<link rel="stylesheet" href="${path}/css/user/mypage/order.css">

<div class="mypage_order_list">
    <h1> 최근 주문 내역 </h1>
  	<c:forEach var="result" items="${result}">
    <div class="order_list">
      <c:if test="${result == null}">
      	주문 내역이 없습니다.
      </c:if>
      <c:if test="${result != null}">
      
      <!-- 기본내용 -->
			<div id="post${result.orderNo}" class="postCard" >
			<!-- 주문일자, 주문상태 -->
				<div class="postDesc" data-no="${i.getNo()}" onclick="postClick(this.dataset.no)">
					<div><fmt:formatDate value="${result.regDate}" pattern="yyyy-MM-dd"/></div>
					<div id="status">주문상태 : ${result.status}
					</div>                 
                </div>
            <!-- 이름, 수량, 금액 , 주문하기버튼 -->
              <div class="order_show">
              	<div>
              		<div class="product_info">

              		<span class="product_title">
	              		<c:forEach var="orderItemList" items="${result.itemList}" end="0">
	              		${orderItemList.option.item.name}
              	        </c:forEach> 	
              		</span>

              		<span class="number_of_prizes"></span></div>
              		<c:set var="amount" value="0" scope="page"></c:set>
					<c:forEach var="payment" items="${result.paymentList}">				
					<c:set var="amount" value="${amount+ payment.amount}" scope="page"></c:set>
					</c:forEach> 								
            		<div>${amount}원</div>
              	</div>
              	<div class="go_order"><button class="go_order_btn"  onclick="window.location.href = 'orderupdate?orderNo=${result.orderNo}'">결제하기</button></div>
              </div>
            </div>
  


			<%--상세내용 누르면 보임--%>   
            <div id="post${result.orderNo}_con" class="postBody hidden">
            <c:forEach var="orderItemList" items="${result.itemList}">
                <div class="postContainer">
                	<div class="order_items">
                        <div class="Thumbnail"><img alt="" src="upload/${orderItemList.option.item.image}/thumb_80.png" width="80px"></div>
                        <div class="order_items">
                        	<div class="order_item_info">
                            	<div class="order_item_title" ><span class="order_item_name">${orderItemList.option.item.name}</span></div>
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
                     <div class="after_payment_btn">
                        <button class="purchase_confirme_btn" onclick="#">구매확정</button>
                        <button class="refund_btn"  onclick="window.location.href = 'payment/refund?orderNo=${result.orderNo}&optionNo=${orderItemList.option.optionNo}'" style="margin-left: 1rem">환불</button>
                     </div>
                </div>
                </c:forEach> 
            </div>

      </c:if>
    
    
    </div>  
    
    
    	
  	</c:forEach>

</div>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
    <script src="${path}/js/user/order.js" defer></script>


