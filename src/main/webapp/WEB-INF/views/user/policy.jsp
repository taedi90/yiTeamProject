<%--
  Created by IntelliJ IDEA.
  User: taedi
  Date: 2021/11/29
  Time: 9:11 오전
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, maximum-scale=1">
    <title>Policy</title>
    <link rel="stylesheet" href="${path}/css/user/policy.css">
</head>
<body>
<div id="wrap">
    <div id="policy_title">
        Policy
    </div>

    <div id="policy_form">

        <form action="register" method="get">

            <input type="hidden" name="policyNo" value="${policy.policyNo}">

            <div id="policy_text">
                ${policy.text}
            </div>

            <div id="button_holder">
                <button id="agree" type="submit">동의</button>
                <button id="cancel" type="button" onclick="window.history.go(-1)">취소</button>
            </div>

        </form>

    <div><a href="main" style="color: lightgray">메인으로</a></div>
</div>

</body>
</html>
