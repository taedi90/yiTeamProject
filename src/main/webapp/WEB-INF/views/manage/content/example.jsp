<%--
  Created by IntelliJ IDEA.
  User: taedi
  Date: 2021/11/09
  Time: 2:32 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="${path}/css/manage/member.css">

<div id="table_wrap">
    <div id="title">
        회원 관리
    </div>
    <div>
        <input type="radio" name="type">
        아이디
        <input type="radio" name="type">
        이름
        <input type="radio" name="type">
        이메일
        <input type="text">
        <button>검색</button>
    </div>
    <div id="actions">
        <button>인증 메일 재발송</button>
        <button>탈퇴 처리</button>
        <button>엑셀 내려받기</button>
        <button>쿠폰 발행</button>
    </div>
    <div id="content">
        <table>
            <tr>
                <th>선택</th>
                <th>No</th>
                <th>아이디</th>
                <th>이름</th>
                <th>이메일</th>
                <th>연락처</th>
                <th>소셜 아이디</th>
                <th>가입일</th>
                <th>탈퇴일</th>
            </tr>
            <tr>
                <td><input type="checkbox"></td>
                <td>1</td>
                <td>test123</td>
                <td>김복자</td>
                <td>test@email.com</td>
                <td>010-1234-5678</td>
                <td>N</td>
                <td>2021년 11월 11일</td>
                <td>-</td>
            </tr>
            <tr>
                <td><input type="checkbox"></td>
                <td>2</td>
                <td>google_test@google.com</td>
                <td>김장남</td>
                <td>test@email.com</td>
                <td>010-1234-5678</td>
                <td>Y</td>
                <td>2021년 11월 11일</td>
                <td>2021년 11월 11일</td>
            </tr>
        </table>
    </div>
</div>
