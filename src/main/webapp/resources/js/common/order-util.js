/**
 * 장바구니 담기 & 주문 요청 스크립트
 *
 * @requires modal.js
 * @requires ajax.js
 *
 * @author taedi <taedi90@gmail.com>
 */

'use strict';
function toOrder(selectedItems, isCart = false){

    checkUser();

    // 회원 권한 확인
    function checkUser() {

        if (isCart == true){
            ajax('check-user', {}, cbkCheckUser);
        } else {
            ajax('check-user', {targetUrl: "/after-login"}, cbkCheckUser);
        }

    }

    // 권한 확인 콜백
    function cbkCheckUser(res) {
        const result = JSON.parse(res);
        if (result.success == true) {// 이미 로그인이 되어있을 경우
            if (isCart == true) {
                ajax('cart', selectedItems, cbkSetCart);
            } else {
                ajax('order', selectedItems, cbkSetOrder);
            }
        } else {
            if (isCart == true) {
                let cartItems = window.localStorage.getItem("cartItems");
                cartItems = JSON.parse(cartItems);

                if(cartItems !== null) {
                    // 배열 합치기
                    cartItems = cartItems.concat(selectedItems);

                    // 중복 처리(합치기)
                    for(let i = 0; i < cartItems.length; i++) {
                        for(let j = i + 1; j < cartItems.length; j++) {
                            if(cartItems[i].optionNo === cartItems[j].optionNo) {
                                cartItems[i].quantity = parseInt(cartItems[i].quantity) + parseInt(cartItems[j].quantity);
                                cartItems.splice(j--, 1);
                            }
                        }
                    }
                } else {
                    cartItems = selectedItems;
                }
                window.localStorage.setItem("cartItems", JSON.stringify(cartItems));
                cbkSetCart();

            } else {
                window.localStorage.setItem("selectedItems", JSON.stringify(selectedItems));
                // 로그인 페이지로 리다이렉트
                window.location.href = "login";
            }

        }
    }

    // 주문 콜백
    function cbkSetOrder(result) {
        if (parseInt(result) != null) {
            // order?orderNo=123242434343243 주소로 이동! (get) => String(view)
            window.location.href = "order?orderNo=" + result;
        }

        // 에러 메세지
    }

    // 카트 콜백
    function cbkSetCart(res){
        newModal(`장바구니 담기 완료!<br>이동하시겠습니까?`, 2, goCart);
    }

    function goCart(){
        window.location.href = "cart";
    }
}