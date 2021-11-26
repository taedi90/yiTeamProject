(function () {

    let selectedItems = []; //선택 상품

    const productButtonBuy = document.querySelector(".product_button_buy"); // 구매 버튼
    const productButtonCart = document.querySelector(".product_button_cart"); // 카트 버튼

    productButtonBuy.addEventListener('click',() => {

        // 선택 상품 확인
        const result = getSelectedItems();
        if (result === false) {
            return;
        }

        // 주문 요청
        toOrder(selectedItems);

    });

    productButtonCart.addEventListener('click',() => {

        // 선택 상품 확인
        const result = getSelectedItems();
        if (result === false) {
            return;
        }

        // 장바구니 담기
        toOrder(selectedItems,true);

    });

    // 선택 상품 가져오기
    function getSelectedItems(){

        selectedItems = [];

        let selectedOptions = document.querySelectorAll(".product_quantity");

        if (selectedOptions.length <= 0) {
            alert("옵션을 선택해주세요.");
            return false;
        }

        for (let i = 0; i < selectedOptions.length; i++) {
            let order = new Object();

            order.optionNo = selectedOptions[i].dataset.no;
            order.quantity = selectedOptions[i].value;
            selectedItems.push(order);
        }

        return true;
    }
})();