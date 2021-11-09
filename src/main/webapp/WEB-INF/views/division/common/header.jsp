<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/common/common.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/common/header.css">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- header -->
    <div class="header">


        <div class="user">
            로그인 | 회원가입 | 마이페이지 | 장바구니
        </div>

        <!-- 모바일 메뉴 -->
        <div class="mobile_menu">

            <img class="mobile_button" src="${path}/img/common/icon_menu.png" alt="목록">

            <nav class="mobile_nav">
                <ul>
                    <li  class="main_menu">
                        <a href="#">메뉴1</a>
                    </li>

                    <li  class="main_menu">
                        <a href="#">메뉴2</a>
                    </li>

                    <li  class="main_menu">
                        <a href="#">메뉴3</a>
                    </li>

                    <li  class="main_menu">
                        <a href="#">메뉴4</a>
                    </li >

                    <li  class="main_menu">
                        <a href="#">메뉴5</a>
                    </li>

                    <li  class="main_menu">
                        <a href="#">메뉴6</a>
                    </li>

                    <li  class="main_menu">
                        <a href="#">메뉴7</a>
                    </li>

                </ul>

            </nav>
        </div>


        <!-- logo 데스크탑, 모바일 공통사용 -->
        <div class="logo">

            <img src="${path}/img/common/logo.png" class="logo_img" alt="logo">
            <img src="${path}/img/common/mobile_logo.png" class="moblie_logo_img" alt="logo">

        </div>

        <!-- 모바일 상단바 -->
        <div class="mobile_user">
            <img src="${path}/img/common/icon_search.png" alt="검색">
            <img src="${path}/img/common/icon_shopping_bag.png" alt="장바구니">
        </div>

    </div>
 