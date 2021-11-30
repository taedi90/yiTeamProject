'use strict';

window.addEventListener('load', getTotalPrice());

// 총 결제 금액
function getTotalPrice(){	
// todo order_item_price를 쿠폰 적용한 값으로 바꾸기
	
	var orderItems = document.querySelectorAll(".order_items");
	
	var totalPrice = 0; // 함계
	var paymentAmount = 0; //결제금액
	
    for (let orderItem of orderItems) {
		var itemPrice = orderItem.querySelector(".order_product_price").innerText;
		itemPrice = parseInt(itemPrice.replace(/[^0-9\.]/g,""));
    	totalPrice +=  itemPrice;    	  
	}

	var fee = (totalPrice >= 50000 ? 0 : 2500);	
	
	paymentAmount = (totalPrice + fee).toLocaleString('ko-KR') + "원";
	totalPrice = (totalPrice).toLocaleString('ko-KR') + "원";
	fee = (fee).toLocaleString('ko-KR') + "원";
	
	document.querySelector(".payment_info_product_sum").innerText = totalPrice;	//상품 합계
	document.querySelector(".payment_info_delivery_charge").innerText = fee;	//배송비
	document.querySelector(".payment_info_payment_amount").innerText = paymentAmount;//결제금액	

}


// 구매하기 버튼
const purchaseButton = document.querySelector("#purchase_button");
purchaseButton.addEventListener('click', ()=>{ getOrderData()});


// 넘길 정보 확인
function ckInput(){
	if($(".postal_code").val() == "" ||
		$(".road_name_address").val() == "" ||
		$(".detailed_address").val() == "" ||
		$("input[name='name']").val() == "" ||
		$("input[name='phone']").val() == "" ){
			
			alert("정보를 모두 입력해주세요");
			return 0; 						
		}		
		return 1 ;
}


// 데이터를 json방식으로 바꾼뒤 ajax통신으로 update하기
function getOrderData(){
	
	var res = ckInput();
	
	if(res != 1){
		return;
	}
	
// 주문정보를 담을 객체		
	let order = new Object();

// 주문정보 객체에 넣기
    let orderInputs = document.getElementsByClassName("order_input");
    for (let i of orderInputs) {
        order[i.name] = i.value;
    }

    console.log(JSON.stringify(order));


//ajax 전송
   ajax('orderUpdate', order, afterUpdate, error);
}
    
    
// ajax 통신 성공 후 결제    
function afterUpdate(result){
	goPayment(result);
}

function error(result){
	alert("실패" + result);
}
    
	
	
	
	


	
	
	
	
	
	
	
	
	

