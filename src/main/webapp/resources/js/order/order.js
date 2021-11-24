'use strict';

function getOrderData(){
	/*order*/
	let order = new Object(); // 주문정보를 담을 객체	
	let member =  new Object(); // 회원정보를 담는 객체
	let nonMember =  new Object(); // 비회원정보를 담는 객체
	
	/*address*/
	let address = new Object(); // 주소정보를 담을 객체
		
	/*orderItem*/	
    let orderItems = [];// 주문상품 정보를 담는 객체    
    let category = new Object(); //카테고리 정보를 담을 객체
    let items = new Object(); //상품 정보를 담을 객체
    let options = new Object(); //옵션 정보를 담을 객체
    let coupons = new Object(); //쿠폰정보를 담을 객체
    let carrier = new Object();//택배사 정보를 담을객체
    let returnOpotion = new Object();// 반품사유를 담을객체
    
    /*payment*/
    let paymentList = new Object();// 결제환불 정보를 담는 객체  	
    


/* Order Insert */ //포인트사용액, 주문 상태?
   
   //주문 정보 받아오기 (주문번호, 받는분, 휴대폰, 생성일자)
    let orderInputs = document.getElementsByClassName("order_input");
    for (let i of orderInputs) {
        order[i.name] = i.value;
    }

	// 회원아이디 추가하기 
	member.username =  document.getElementsByClassName("username");
	order.member = member;

	// 비회원아이디 추가하기 
	nonMember.nonUsername =  document.getElementsByClassName("non_username")
	order.nonMember = nonMember;

	// 주소 추가하기 
	let addressZip = document.getElementsByClassName("address_input");
	for(let i of addressZip){
		address[i.name] = i.value;
	}
	order.address = addressZip;
	
	

    console.log(JSON.stringify(order));


/*	let memberInput1 = document.querySelectorAll('.member_input > [name="username"]');*/


/* Order Update */
// 주소, 받는분, 휴대폰 





/* Order Select */




















}
    
    
	
	
	
	


	
	
	
	
	
	
	
	
	

