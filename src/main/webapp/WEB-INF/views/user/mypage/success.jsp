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
    <link rel="stylesheet" href="${path}/css/common/main.css">
    <link rel="stylesheet" href="${path}/css/user/mypage.css">
    <link rel="stylesheet" href="${path}/css/user/mypage/success.css">
    <title>결제완료</title>
</head>
<body>

<div id="wrap">
    <%@ include file="../../division/common/header.jsp" %>
    <%@ include file="../../division/common/nav.jsp" %>

    <!-- content -->
    <div class="content">
        <div class="content_wrap">
            <div class="side_wrap">
                <%@ include file="sidebar.jsp" %>
            </div>

            <div class="mypage_content_wrap">
            	<div class="payment_succes">
                	<div style="margin-bottom: 2.5rem;"> 
                		결제완료 되었습니다.
                	</div>
                	<div>
                		<a href='main'> 메인으로 가기 </a>
                		<a href='mypage?section=order&func=detail'> 주문 내역 보기 </a>
                	</div>
                </div>
            </div>
			
        </div>
	
		
	
    </div>


    <%@ include file="../../division/common/footer.jsp" %>
</div>


</body>
</html>