<%--
  Created by IntelliJ IDEA.
  User: taedi
  Date: 2021/11/09
  Time: 2:32 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" href="${path}/css/manage/product/product-list.css">
<link rel="stylesheet" href="${path}/css/manage/manage.css">

<%--${requestScope.result}--%>
<%-- ${orderDetail}  --%>


<div id="table_wrap">
    <div id="title">
        상품 관리
    </div>
    <div id="actions">
        <input type="text" name="keyword" value="${requestScope.result.keyword}">
        <button onclick="search()">검색</button>
        <button onclick="deleteItem()">선택 삭제</button>
    </div>
    <div id="content">
        <table>
            <tr>
                <th onclick="selectAll()">선택</th>
                <th>orderNo</th>
                <th>상품수량</th>
                <th>결제금액</th>
                <th>주문일시</th>
                <th>주문상태</th>
                <th>주문자</th>
            </tr>

             <c:forEach var="orderDetail" items="${requestScope.result.records}">
                <tr>               	
               		<td><input type="checkbox" class="checkbox"></td>
					<td  onclick="window.location.href = 'manage?section=order&func=detail&orderNo=${orderDetail.orderNo}'">${orderDetail.orderNo}</td>
					<td>	
						<c:if test="${orderDetail.cnt % 2 == 0}">
						<fmt:formatNumber type="number"  pattern="#,###" value="${orderDetail.cnt/2} " />					
						</c:if>
						<c:if test="${orderDetail.cnt % 2 != 0}">
							${orderDetail.cnt}
						</c:if>
					</td> 
					
					<c:set var="amount" value="0" scope="page"></c:set>
					<c:forEach var="payment" items="${orderDetail.paymentList}">				
						<c:set var="amount" value="${amount+ payment.amount}" scope="page"></c:set>
					</c:forEach> 				
					<td>${amount}원</td> 
					
					<td><fmt:formatDate value="${orderDetail.regDate}" pattern="yyyy-MM-dd"/></td> 
					<td>${orderDetail.status}</td> 
					<td onclick="window.location.href = 'manage?section=member&func=detail&username=${orderDetail.member.username}'">${orderDetail.member.name}</td>
                
            </c:forEach> 

        </table>
    </div>
    <div id="link">

        <%-- 기본 링크 설정 --%>
        <c:set var="link"
               value="manage?section=order&func=list&amount=${requestScope.result.amount}&order=${requestScope.result.order}"/>
        <c:if test="${requestScope.result.category ne null}">
            <c:set var="link" value="${link}&category=${requestScope.result.category}"/>
        </c:if>
        <c:if test="${requestScope.result.keyword ne null}">
            <c:set var="link" value="${link}&keyword=${requestScope.result.keyword}"/>
        </c:if>


        <%-- 이전 --%>
        <c:if test="${requestScope.result.prev > 0}">
            <a href="${link}&pageNo=${requestScope.result.prev}">이전</a>&nbsp;
        </c:if>

        <%-- 페이지번호 --%>
        <c:forEach begin="${requestScope.result.startPage}" end="${requestScope.result.endPage}" step="1" varStatus="i">
            <c:set var="pgNo" value="${requestScope.result.startPage + i.count - 1}"/>
            <c:choose>
                <c:when test="${pgNo ne requestScope.result.pageNo}">
                    &nbsp;<a href="${link}&pageNo=${pgNo}">${pgNo}</a>&nbsp;
                </c:when>
                <c:when test="${pgNo eq requestScope.result.pageNo}">&nbsp;<span id="now_page">${pgNo}</span>&nbsp;</c:when>
            </c:choose>
        </c:forEach>

        <%-- 다음 --%>
        <c:if test="${requestScope.result.next > 0}">
            &nbsp;<a href="${link}&pageNo=${requestScope.result.next}">다음</a>
        </c:if>
    </div>
</div>

<script src="${path}/js/manage/product/product-list.js"></script>