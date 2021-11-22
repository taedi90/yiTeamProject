function payment() {

        var IMP = window.IMP; // 생략가능
        IMP.init('imp58325007');
        // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
        // i'mport 관리자 페이지 -> 내정보 -> 가맹점식별코드

        IMP.request_pay({
        pg: 'inicis',
        pay_method: 'card',
        name: '주문명:결제테스트',  //결제창에서 보여질 이름
        amount: 1000,   //가격
        buyer_email: 'iamport@siot.do',
        buyer_name: '구매자이름',
        buyer_tel: '010-1234-5678',
        buyer_addr: '서울특별시 강남구 삼성동',
        buyer_postcode: '123-456',
        //     m_redirect_url: 'https://www.yourdomain.com/payments/complete'
        // /*
        // 모바일 결제시,
        // 결제가 끝나고 랜딩되는 URL을 지정
        // (카카오페이, 페이코, 다날의 경우는 필요없음. PC와 마찬가지로 callback함수로 결과가 떨어짐)
        // */

        }, function (rsp) {
            console.log(rsp);


            	if (rsp.success) {
                	var msg = '결제가 완료되었습니다.';
                	msg += '고유ID : ' + rsp.imp_uid;
    	            msg += '결제 금액 : ' + rsp.paid_amount;

        	        var data = { 
									uid: rsp.imp_uid,
									amount: rsp.paid_amount,

								}


        	        ajax('payment/success', data);

        	        //callback 서버로보낼거 주문번호, 결제번호, 금액
	            } else {
    	            var msg = '결제에 실패하였습니다.';
        	        msg += '에러내용 : ' + rsp.error_msg;

            }

        alert(msg);
        });

    } 