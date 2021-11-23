<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/common/nav.css">

	<!-- 웹 네비바 -->
    <nav class="nav">

        <div class="blank" >

        </div>


        <div class="menu">

            <ul>
                <li  class="main_menu">
                    <a href="#">About</a>
                </li>

                <li  class="main_menu">
                    <a href="#">Product</a>

                    <ul class="sub_menu">
                        <li><a href="#">아우터</a></li>
                        <li><a href="#">상의</a></li>
                        <li><a href="#">하의</a></li>
                        <li><a href="#">원피스</a></li>
                    </ul>
                </li>

                <li  class="main_menu">
                    <a href="#">메뉴3</a>
                </li>

                <li  class="main_menu">
                    <a href="#">메뉴4</a>

                    <ul class="sub_menu">
                        <li><a href="#">메뉴4-1</a></li>
                        <li><a href="#">메뉴4-2</a></li>
                        <li><a href="#">메뉴4-3</a></li>
                        <li><a href="#">메뉴4-4</a></li>
                        <li><a href="#">메뉴4-5</a></li>
                    </ul>
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

        </div>

        <div class="icon">

            <img src="${path}/img/common/icon_search.png" alt="검색">
            <img src="${path}/img/common/icon_shopping_bag.png" alt="장바구니">
            <img src="${path}/img/common/icon_menu.png" alt="목록">

        </div>


    </nav>

       
       
	<script src="${pageContext.request.contextPath}/js/common/nav.js" type="text/javascript"></script>