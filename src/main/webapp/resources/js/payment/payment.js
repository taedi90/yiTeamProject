function payment(request) {
		
        var IMP = window.IMP; // 생략가능
        IMP.init('imp58325007');
        // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
        // i'mport 관리자 페이지 -> 내정보 -> 가맹점식별코드

        IMP.request_pay({
        pg: 'inicis',
        pay_method: 'card',
        merchant_uid: request.merchant_uid,
        name: request.name,  //결제창에서 보여질 이름
        amount: request.amount,   //가격
        buyer_email: request.buyer_email,
        buyer_name: request.buyer_name,
        buyer_tel: request.buyer_tel,
        buyer_addr: request.buyer_addr,
        buyer_postcode: request.buyer_postcode,
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
        	    var data = { 
								uid: rsp.imp_uid,
								orderNo: rsp.merchant_uid,
								amount: rsp.paid_amount,
								method: rsp.pay_method

							}

        	    ajax('payment_success', data, paymentSuccess, paymentfail);
        	    
        	    function paymentSuccess(){
					console.log("성공");
					window.location.href = 'payment_success';
					
					
				}

				function paymentfail(){
					var msg = '결제에 실패하였습니다.';
        	        msg += '에러내용 : ' + rsp.error_msg;
				}  
        	    
        	    
        	        
	            } else {
    	            var msg = '결제에 실패하였습니다.';
        	        msg += '에러내용 : ' + rsp.error_msg;

            }

        });

    }
    
   