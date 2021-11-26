'use strict';
function toOrder(selectedItems, isCart = false){

    checkUser();

    // 회원 권한 확인
    function checkUser() {

        ajax('check-user', {targetUrl: "/after-login"}, cbkCheckUser);

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
            window.localStorage.setItem("selectedItems", JSON.stringify(selectedItems));

            // 로그인 페이지로 리다이렉트
            window.location.href = "login";
        }
    }

    // 주문 콜백
    function cbkSetOrder(res) {
        const result = JSON.parse(res);
        if (parseInt(result) != null) {
            // order?orderNo=123242434343243 주소로 이동! (get) => String(view)
            window.location.href = "order?orderNo=" + result;
        }

        // 에러 메세지
    }

    // 카트 콜백
    function cbkSetCart(res){
        window.location.href = "cart";
    }
}