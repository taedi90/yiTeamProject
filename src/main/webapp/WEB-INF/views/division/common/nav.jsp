<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/common/nav.css">

	<!-- 웹 네비바 -->
    <nav class="nav">

        <div class="left_icon" >
            <div class="mobile_menu">
                <img class="mobile_button" src="${path}/img/common/menu2.svg" alt="목록">
            </div>
        </div>


        <div class="menu">

            <ul>
                <li  class="main_menu">
                    <a href="https://github.com/taedi90/yiTeamProject">About</a>
                </li>

                <li  class="main_menu">
                    <a href="main">ALL</a>
                </li>

                <li  class="main_menu">
                    <a href="main?category=1">OUTER</a>
                </li>

                <li  class="main_menu">
                    <a href="main?category=2">DRESS</a>
                </li>

                <li  class="main_menu">
                    <a href="main?category=3">TOP</a>
                </li >

                <li  class="main_menu">
                    <a href="main?category=4">BOTTOM</a>
                </li>
            </ul>

        </div>

        <div class="mobile_logo">
            <a href="main"><img src="${path}/img/common/logo.svg" class="" alt="logo"></a>
        </div>

        <div class="right_icon">

            <div id="nav_search">
                <img id="nav_search_icon" src="${path}/img/common/search.svg" alt="검색">
                <input id="nav_searchbar" class="hidden" type="text">
            </div>
			<a href="cart"><img src="${path}/img/common/cart.svg" alt="장바구니"></a>
            <a href="mypage"><img src="${path}/img/common/mypage.svg" alt="목록"></a>

        </div>


    </nav>

       
       
	<script src="${pageContext.request.contextPath}/js/common/nav.js" type="text/javascript"></script>