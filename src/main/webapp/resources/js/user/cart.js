'use strict';
(function (){
    //local storage 가 있으면 변수에 담기
    let cartItems = window.localStorage.getItem("cartItems");
    cartItems = JSON.parse(cartItems);

    if(cartItems !== null) {

    }

    const cartList = document.querySelector("#cart_list"); //카트 상품

    //post method
    ajax('cart', cartItems, cbkLoadCart);

    function cbkLoadCart(result){
        try{
            result = JSON.parse(result);
        }catch(e){
            //오류
            return;
        }

        for(let elem of result){

            let child = document.createElement("tr");
            child.id = "옵션번호";
            child.innerHTML =
                `
                <td><input type="checkbox" checked></td>
                <td>썸네일</td>
                <td>상품명<br>옵션</td>
                <td><input type="number" min="1"></td>
                <td>금액</td>
                <td>삭제</td>
            
                `;

            cartList.appendChild(child);

        }

    }





    //상품 생성

    //총 합계

    //내용 바꾸기

    //event listener 등록(수량 변경, 금액 변경, 삭제)





    //수량 변경

    //카트 빼기

})();