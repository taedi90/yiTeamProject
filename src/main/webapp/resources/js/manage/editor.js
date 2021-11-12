'use strict';

let data ={
    name: document.getElementById(""),

}

function getData(){
    let product = new Object(); //상품 정보를 담을 객체
    let category = new Object(); //카테고리 정보를 담을 객체
    let options = []; //옵션 정보를 담을 객체

    //상품 정보 받아오기
    let productInputs = document.getElementsByClassName("item_input");
    for (let i of productInputs) {
        product[i.name] = i.value;
    }

    //카테고리 추가하기
    category.categoryNo = document.getElementById("category_no").value;
    product.category = category;

    //옵션 추가하기
    let optionInput1 = document.querySelectorAll('.option_input > [name="name"]');
    let optionInput2 = document.querySelectorAll('.option_input > [name="optionPrice"]');
    let optionInput3 = document.querySelectorAll('.option_input > [name="stock"]');
    for (let i = 0; i < optionInput1.length; i++) {
        options.push({
            name: optionInput1[i].value,
            optionPrice:optionInput2[i].value,
            stock:optionInput3[i].value
        });
    }
    product.options = options;


    ajax('manage/product/put', product);

    console.log(JSON.stringify(product));

}


//상품상세 페이지 없으면 자동으로 숨김처리
//옵션은 1개 이상 있어야함
//할인 시작일 < 종료일 - js로 날짜 수정해주기