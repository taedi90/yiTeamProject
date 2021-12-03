function goPayment(result){
	var data = { orderNo : result };
		
	ajax("payment", data, suc, fail);
	
	function suc(res){
		res = JSON.parse(res);
		
		console.log(res);
		
		payment(res);
	}
	
	function fail(){
		alert("실패");
	}

}

