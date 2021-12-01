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

            let child = document.createElement("div");
            child.id = "item" + elem.option.optionNo;
            child.classList.add("item");
            child.innerHTML =
                `
                <div class="check_wrap"><input type="checkbox" class="check_input" ${(isSoldOut ? "disabled" : "checked")}></div>
                <div class="info_wrap">
                    <div class="item_info">
                        <div class="item_image"><img src="upload/${elem.option.item.image}/thumb_80.png" alt=""></div>
                        <div class="item_text">
                            <div>
                                ${elem.option.item.title}
                            </div>
                            <div>
                                ${elem.option.name} (${elem.option.optionPrice})
                            </div>
                        </div>
                    </div>
                    <div class="order_info">
                        ${(isSoldOut ? "재고 부족" :
                    '<div>' +
                '<button class="quantity_down" type="button" onclick="quantityDown(this)">➖</button>' +
                '<input class="quantity_input" type="number" data-option-no="' + elem.option.optionNo
                + '" data-unit-price="' + unitPrice + '" min="1" value="' + elem.quantity + '">' +
                '<button class="quantity_up" type="button" onclick="quantityUp(this)">➕</button>' +
                    '</div>')}
                        <div class="item_price">${price.toLocaleString('ko-KR') + "원"}</div>
                    </div>

                </div>
                <div class="remove_wrap">
                    <img class="remove_button" data-option-no="${elem.option.optionNo}" src="img/common/x.svg" style="width: 20px;height: 20px" alt="">
                </div>
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
                const parent = quantityInput.parentNode; //부모요소
                const quantityDown = parent.querySelector(".quantity_down"); //수량 감소
                const quantityUp = parent.querySelector(".quantity_up"); //수량 감소

                function changePrice(){
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
                };

                quantityUp.addEventListener('click',() => {
                    quantityInput.stepUp();
                    changePrice();
                });

                quantityDown.addEventListener('click', () => {
                    if(quantityInput.value <= 1){
                        alert("1개 이상 입력해주세요.");
                        return;
                    }

                    quantityInput.stepDown();
                    changePrice();
                });
            }


        );

        toggleSummaryBox();

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

                    toggleSummaryBox();
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

        let fee = 0;
        if(iPrice > 0){
            fee = (iPrice >= 50000 ? 0 : 2500);
        }

        itemPrice.innerHTML = iPrice.toLocaleString('ko-KR') + "원";
        deliveryFee.innerHTML = fee.toLocaleString('ko-KR') + "원";
        totalPrice.innerHTML = (iPrice + fee).toLocaleString('ko-KR') + "원";

    }

    function toggleSummaryBox() {
        const items = document.querySelectorAll(".item");
        if(items.length > 0){
            document.querySelector("#summary").style.display = "flex";
        }else{
            document.querySelector("#summary").style.display = "none";
        }
    }

})();