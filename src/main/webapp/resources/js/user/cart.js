'use strict';
(function (){
    /**
     * 페이지가 로딩될 때 장바구니 아이템 조회하기
     */

    // 로그인 확인
    ajax('check-user', {targetUrl: "/"}, cbkCheckUser);

    //local storage 가 있으면 변수에 담기
    let cartItems = window.localStorage.getItem("cartItems");
    cartItems = JSON.parse(cartItems);

    // 권한 확인 콜백
    function cbkCheckUser(result) {

        result = JSON.parse(result);
        if (result.success == true) {// 이미 로그인이 되어있을 경우 웹 카트 지우기
            window.localStorage.removeItem("cartItems");
        }
    }

    //카트 로드하기
    ajax('cart', cartItems, cbkLoadCart);

    function cbkLoadCart(result){
        const cartList = document.querySelector("#cart_list"); //카트 상품

        console.log(result);

        try{
            result = JSON.parse(result);
        }catch(e){
            //오류
            return;
        }

        cartList.innerHTML = ``;

        for(let elem of result){

            const unitPrice = elem.option.item.price + elem.option.optionPrice;
            const price = unitPrice * elem.quantity;

            let child = document.createElement("tr");
            child.id = "item" + elem.option.optionNo;
            child.classList.add("item")
            child.innerHTML =
                `
                <td><input type="checkbox" checked></td>
                <td><img src="upload/${elem.option.item.image}/thumb_80.png" alt=""></td>
                <td>${elem.option.item.title}<br>${elem.option.name} (${elem.option.optionPrice})</td>
                <td><input class="quantity_input" type="number" data-option-no="${elem.option.optionNo}" data-unit-price="${unitPrice}" min="1" value="${elem.quantity}"></td>
                <td class="item_price">${price.toLocaleString('ko-KR') + "원"}</td>
                <td><button class="remove_button" data-option-no="${elem.option.optionNo}">삭제하기</button></td>
                `;

            cartList.appendChild(child);
            getTotalAmount();

        }

        const quantityInputs = document.querySelectorAll(".quantity_input");
        const removeButtons = document.querySelectorAll(".remove_button");

        //event listener 등록(수량 변경, 금액 변경, 삭제)
        quantityInputs.forEach(
            (quantityInput) => {
                quantityInput.addEventListener('change', () =>{

                    const optionNo = quantityInput.dataset.optionNo;
                    const quantity = quantityInput.value;
                    const unitPrice = quantityInput.dataset.unitPrice;
                    const price = unitPrice * quantity;

                    //cart put
                    const data = {
                        optionNo:optionNo,
                        quantity:quantity
                    };

                    ajax('cart',data,()=>{},()=>{},'put');


                    quantityInput.parentNode.parentNode.querySelector(".item_price").innerHTML = price.toLocaleString('ko-KR') + "원";
                    getTotalAmount();
                });
            }
        )



        removeButtons.forEach(
            (removeButton) => {
                removeButton.addEventListener('click',() =>{
                    const optionNo = removeButton.dataset.optionNo;

                    //cart put
                    const data = {
                        optionNo:optionNo
                    };
                    //cart delete
                    ajax('cart',data,()=>{},()=>{},'delete');

                    document.querySelector("#item" + optionNo).remove();

                    getTotalAmount();
                });
            }
        )



    }

    //합계 및 배송비
    function getTotalAmount(){
        const totalAmount = document.querySelector("#total_amount"); //합계 요소
        const items = document.querySelectorAll(".quantity_input"); //합계 요소
        let total = 0;
        items.forEach((item) =>{
           total += item.dataset.unitPrice * item.value;
        });

        totalAmount.innerHTML = total.toLocaleString('ko-KR') + "원";
    }



    //수량 변경

    //카트 빼기

})();