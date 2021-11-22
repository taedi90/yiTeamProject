    var price = parseInt(document.getElementById('price_span').innerHTML);
    var optionPrice = 
    
    //option금액 추가해야됨
    //가격부분 = price + option금액
    //버튼 클릭시 수량증감 + 가격변동

    $("#option").change(function(){
        const option_val = $("#option option:selected").val();
        const option_text = $("#option option:selected").text();     

        const newSelectedOption = 
            `<div class="new_selected_option">
                <div style="width:38rem; float: left;">
                    <span class="selected_option_name">11월 특가 롱원피스 ` + option_text + `</span>
                </div>
                <div>
                    <img src="img/icon_x.png" alt="x" class="delete_selected_option" onclick="$(this).parents('.new_selected_option').remove()">
                </div>
                <div style="text-align: right;">
                    <div class="quantity">
                        <img src="img/icon_minus.png" style="width:20px; height:20px; opacity:0.8" onclick="this.parentNode.querySelector('#product_quantity').stepDown()" class="quantity_down" alt="minus">
                        <input type="number" name="product_quantity" id="product_quantity" class="" value="1">
                        <img src="img/icon_plus.png" style="width:20px; height:20px; opacity:0.8" onclick="this.parentNode.querySelector('#product_quantity').stepUp()" class="quantity_up" alt="plus">
                    </div>
                    <strong class="selected_option_price">
                    <span> `+ price +` </span> 원 </strong>
                    </div>
                </div>`;
        
        if(option_val !== 'null'){
            $(".selected_option").append(newSelectedOption);
        }

        var quantity = parseInt(document.getElementById('product_quantity').value);        
        var one;
        var value = document.getElementById('product_quantity').value;
        one = 1;
         console.log(quantity);

        
        

        $(".quantity_down").click(function(){          
            quantity --;         
            value = quantity;
            console.log(quantity);
            console.log(value);
            
            
            if(quantity<1){
                alert("1개 이상 입력해주세요.");
                quantity ++;
                document.getElementById('product_quantity').value = one;
                console.log(quantity)
            }
            
        });

        $(".quantity_up").click(function(){
            quantity ++;
            value = quantity;
            console.log(quantity);
            console.log(value);
        });



        function realPrice() {
            return price * value;
        }

        console.log(quantity);
        console.log(value);
        console.log(price);
        console.log(realPrice);

    }); 