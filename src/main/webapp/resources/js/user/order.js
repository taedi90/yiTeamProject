$(function(){

'use strict';

window.addEventListener('load', getView());



function getView(){

	
	var order_list = document.querySelectorAll(".order_list");

	console.log(order_list);
	
	
	for(var i = 0 ; i <order_list.length ; i++){
		
		var product = order_list[i].querySelector(".postBody");
		
		var product_cnt = product.querySelectorAll(".postContainer");
		product_cnt = product_cnt.length;
		
		if(product_cnt>1){			
			order_list[i].querySelector(".number_of_prizes").innerText = "외" + (product_cnt -1)+"개";
		}
		
		
		order_status();	
		
	}		
	
};


// 주문상태 확인
function order_status(){
	
		
	$(".go_order").hide();
	$(".after_payment_btn").hide();
	
	var status = "";	
	var order_list = document.querySelectorAll(".order_list");
	
	    for (let orderItem of order_list) {
		var status = orderItem.querySelector("#status").innerText;
		status = status.substr(7,10);
		console.log(status);
 	  
 	  
	  	if(status == "주문대기" || status == "결제대기"){
			$(".go_order").show();
			$(".after_payment_btn").hide();
		}else if(status == "결제완료" ){
			$(".after_payment_btn").show();	
			$(".go_order").hide();
	
		}
	}

	

	
	
	
	
};


// 창열기
function openDetail(){

	
};











})