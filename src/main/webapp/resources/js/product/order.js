$(".product_button_buy").click(function() {       	

        var selectedOptions = document.querySelectorAll(".product_quantity");

        if(selectedOptions.length <= 0){
            alert("옵션을 선택해주세요.");
            return;
        }

        let orders = [];
        
        for(var i = 0; i < selectedOptions.length; i++){
            let order = new Object();

            order.optionNo = selectedOptions[i].dataset.no;
            order.quantity = selectedOptions[i].value;
            orders.push(order);
        }

        console.log(orders);

        ajax('order', orders, cbkAction); //post => 결과 responsebody
        

    });

    function cbkAction(){
    	//if 주문번호가 있다?
    			// order?orderNo=123242434343243 주소로 이동! (get) => String(view)
    	//else if 없다?
    			// 에러 메세지
    }