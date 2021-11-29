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
            const isSoldOut = Boolean(elem.option.stock <= 0);

            let child = document.createElement("tr");
            child.id = "item" + elem.option.optionNo;
            child.classList.add("item");
            child.innerHTML =
                `
                <td><input type="checkbox" class="check_input" ${(isSoldOut ? "disabled" : "checked")}></td>
                <td><img src="upload/${elem.option.item.image}/thumb_80.png" alt=""></td>
                <td class="item_title">${elem.option.item.title}<br>${elem.option.name} (${elem.option.optionPrice})</td>
                <td>
                    ${(isSoldOut ? "재고 부족" :
                    '<button type="button">➖</button>' +
                    '<input class="quantity_input" type="number" data-option-no="' + elem.option.optionNo 
                    + '" data-unit-price="' + unitPrice + '" min="1" value="' + elem.quantity + '">' +
                    '<button type="button">➕</button>')}
                </td>
                <td class="item_price">${price.toLocaleString('ko-KR') + "원"}</td>
                <td><img class="remove_button" data-option-no="${elem.option.optionNo}" src="img/common/x.svg" alt=""></td>
                `;

            cartList.appendChild(child);
            getTotalAmount();

        }

        //event listener 등록(수량 변경, 금액 변경, 삭제)
        const quantityInputs = document.querySelectorAll(".quantity_input"); //수량
        const removeButtons = document.querySelectorAll(".remove_button"); //삭제하기
        const checkInputs = document.querySelectorAll(".check_input"); //체크상자

        // 수량 변경
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


        //카트 빼기
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

        //상품 선택
        checkInputs.forEach(
            (checkInput) => {
                checkInput.addEventListener('change', ()=>{
                    getTotalAmount();
                });
            }
        )

        //주문하기
        const purchaseButton = document.querySelector("#purchase_button");
        purchaseButton.addEventListener('click', ()=>{

            let selectedItems = [];

            checkInputs.forEach( (checkInput) => {
                //선택 상품만 주문창으로 넘어가기
                if(checkInput.checked){
                    //옵션, 수량
                    const row = checkInput.parentNode.parentNode;
                    const quantityElem = row.querySelector(".quantity_input");
                    let order = new Object();

                    order.optionNo = quantityElem.dataset.optionNo;
                    order.quantity = quantityElem.value;
                    selectedItems.push(order);

                }
            });

            if(selectedItems.length > 0){
                toOrder(selectedItems,false);
            }

        });

    }

    //합계 및 배송비
    function getTotalAmount(){

        const checkInputs = document.querySelectorAll(".check_input"); //체크상자

        const itemPrice = document.querySelector("#item_price"); //상품 금액
        const deliveryFee = document.querySelector("#delivery_fee"); //배송비
        const totalPrice = document.querySelector("#total_price"); //합계


        let iPrice = 0;
        checkInputs.forEach( (checkInput) => {
                if(checkInput.checked){
                    const row = checkInput.parentNode.parentNode;
                    const quantityElem = row.querySelector(".quantity_input");
                    console.log(quantityElem);
                    if(quantityElem){
                        iPrice += quantityElem.dataset.unitPrice * quantityElem.value;
                    }
                }
            }

        )

        if(iPrice > 0){
            itemPrice.innerHTML = "상품 금액 " + iPrice.toLocaleString('ko-KR') + "원";
            const fee = (iPrice >= 50000 ? 0 : 2500);
            deliveryFee.innerHTML = "배송비 " +  fee.toLocaleString('ko-KR') + "원";
            totalPrice.innerHTML ="총합계 " +  (iPrice + fee).toLocaleString('ko-KR') + "원";
        }

    }

})();