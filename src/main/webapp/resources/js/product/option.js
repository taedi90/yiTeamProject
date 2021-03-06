const title = document.querySelector('.product_title');
let priceElem = document.getElementById('price_span');
const price = parseInt(priceElem.innerText.replace(/,/g, ''));
  
$("#option").change(function(){
	var optionBox = $("#option option:selected");
    // 옵션 번호
    var optionNo = optionBox.data("optionNo");
    // 옵션명
    var optionName = optionBox.data("name");     
    // 옵션 금액
    var optionPrice = optionBox.data("optionPrice");
    
    // 옵션 콤보박스 초기화
     $("#option").val("");     
         
    //인풋상자배열저장
    var inputs = document.querySelectorAll(".product_quantity");
	
	if(inputs.length >0){
		for(let i = 0; i < inputs.length; i++){
			if(inputs[i].dataset.no==optionNo){
				quantityUp(inputs[i]);
				return;
			}
		}
	}
    
    
        
        
        
    var newSelectedOption = 
        
        `<div class="new_selected_option">
            <div style="width:calc(100% - 2.5rem); float: left;">
                <span class="selected_option_name"> ${title.innerText} ${optionName} </span>
            </div>
            <div>
                <img src="img/common/icon_x.png" alt="x" class="delete_selected_option" onclick="removeOption(this)">
            </div>
            <div style="text-align: right;">
                <div class="quantity">
                    <img src="img/common/icon_minus.png" style="width:20px; height:20px; opacity:0.8" onclick="quantityDown(this)" alt="minus">
                    <input type="number" class="product_quantity" value="1" data-no="${optionNo}" data-option-price="${optionPrice}">
                    <img src="img/common/icon_plus.png" style="width:20px; height:20px; opacity:0.8" onclick="quantityUp(this)" alt="plus">
                </div>
                <strong class="selected_option_price">
                <span class="option_price"> ${parseInt(price+optionPrice).toLocaleString('ko-KR')}</span> 원 </strong>
            </div>
        </div>`;
        
        
       
    if(optionNo == null){
		return;
	}
	
    
    if(optionNo !== null){
        $(".selected_option").append(newSelectedOption);
    }


    

}); 



    
function quantityUp(elem){

    let parent = elem.parentNode;
    let inputBox = parent.querySelector('.product_quantity');


    inputBox.stepUp();
    calPrice(parent);
    getTotalPrice();
}


function quantityDown(elem){
    let parent = elem.parentNode;
    let inputBox = parent.querySelector('.product_quantity');

    if(inputBox.value <= 1){
        alert("1개 이상 입력해주세요.");
        return;
    } 

    inputBox.stepDown();
    calPrice(parent);
    getTotalPrice();
}


function removeOption(elem){
	
	let parent = elem.closest('.new_selected_option');
	parent.remove();
		
    getTotalPrice();
    
    
}


function calPrice(elem){
    let inputBox = elem.querySelector('.product_quantity');
    let priceBox = elem.parentNode.querySelector('.option_price');

    const optionPrice = parseInt(inputBox.dataset.optionPrice);
    const quantity = parseInt(inputBox.value);

    priceBox.innerHTML = ((price + optionPrice) * quantity).toLocaleString('ko-KR');
}


	window.onchange = function(){
		getTotalPrice();
	}
	
	
	function getTotalPrice(){
		var optionPrice = document.querySelectorAll('.option_price');
		var totalPrice = 0;
		let opt = [];
		for(var i = 0; i < optionPrice.length; i++){
		
			opt.push(parseInt(optionPrice[i].innerText.replace(/,/g, '')));
			
	
		}
		var ss = opt.reduce((initialValue, currentValue) => {
	    			return initialValue + currentValue;}, totalPrice);
	    			
	    totalPrice += ss;
	    totalPrice = totalPrice.toLocaleString('ko-KR');
	    
	    document.querySelector("#total_price").innerText = totalPrice;
	    
		
	}