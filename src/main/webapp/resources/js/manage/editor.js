'use strict';

// let data ={
//     name: document.getElementById(""),
//
// }

function getData(){
    let product = new Object(); //상품 정보를 담을 객체
    let category = new Object(); //카테고리 정보를 담을 객체
    let options = []; //옵션 정보를 담을 객체

    //상품 정보 받아오기
    let productInputs = document.getElementsByClassName("item_input");
    for (let i of productInputs) {
        if(i.value != "") {
            product[i.name] = i.value;
        }
    }

    //카테고리 추가하기
    category.categoryNo = document.getElementById("category_no").value;
    product.category = category;

    //옵션 추가하기
    const optionInputs = document.querySelectorAll(".option_input");

    for (let optionInput of optionInputs) {

        let option = new Object();

        const childInputs = optionInput.querySelectorAll("input");

        for (let childInput of childInputs) {
            if(childInput.value != ""){
                option[childInput.name] = childInput.value;
            }
        }
        options.push(option);
    }

    product.options = options;


    return product;

}

function setData(){
    document.querySelectorAll('[name=""]')
}

//상품상세 페이지 없으면 자동으로 숨김처리
//옵션은 1개 이상 있어야함
//할인 시작일 < 종료일 - js로 날짜 수정해주기


/**
 * 옵션 추가하기
 */
function addOption(){
    //ajax 통신으로 option 번호 받아오기
        //성공시 요소 추가
    let option = new Object();
    let item = new Object();

    item.itemNo = document.querySelector('[name="itemNo"]').value;

    option.item = item;

    ajax('item/option', option, cbkAddOption,(res) => console.error(res),'post');


}

function cbkAddOption(result){
    console.log(result);
    const optionNo = JSON.parse(result).object.optionNo;

    const optionContent = `
            <input type="hidden" name="optionNo" value="${optionNo}">
            <input type="text" name="name" placeholder="옵션명">
            <input type="number" name="optionPrice" placeholder="추가금액">
            <input type="number" name="stock" placeholder="재고수량">
            <button onclick="addOption()">+</button>
            <button onclick="removeOption(this.parentNode)">-</button>`;

    let optionElem = document.createElement("div");
    optionElem.classList.add("option_input");
    optionElem.innerHTML = optionContent;

    document.querySelector("#option").appendChild(optionElem);
}

let removeElem = undefined;


/**
 * 옵션 삭제하기
 */
function removeOption(elem){

    removeElem = elem;

    let option = new Object();
    option.optionNo = elem.querySelector('[name="optionNo"]').value;
    console.log(option);

    //ajax 통신으로 option 삭제
    ajax('item/option',option,cbkRemoveOption,(res) => console.error(res),'delete');
}

function cbkRemoveOption(result) {
    removeElem.remove();
}


/**
 * 썸네일 업로드
 */
let formData = undefined;

const thumbInput = document.querySelector('[name="uploadThumb"]');
thumbInput.addEventListener("change", () =>
{
    console.log(thumbInput.files[0]);
    formData = new FormData();
    uploadFileChk(thumbInput.files[0]);
});

// 파일 확인
function uploadFileChk(data){

    // 확장자 확인
    if (data.type != 'image/jpeg' && data.type != 'image/gif' && data.type != 'image/png'){
        newModal("지정 된 확장자(.jpg, .png, .gif)만 업로드 가능합니다.");
        thumbInput.value = null;
        return;
    }

    // 사이즈 확인
    if (data.size > 20 * 1024 * 1024){
        newModal("10mb 이하의 파일을 선택해주세요!");
        thumbInput.value = null;
        return;
    }

    formData.append("thumb", data);
    formData.append("item", new Blob([JSON.stringify(getData())], {type: "application/json; charset=utf-8"}));

    uploadBtnEvent();
}

function uploadBtnEvent(){

    let xhr = new XMLHttpRequest();
    xhr.open('post', 'item/thumb', true);
    //xhr.setRequestHeader('Content-Type', contentType); // request type 설정
    xhr.send(formData);

    xhr.onload = function () {
        if (xhr.status === 200 || xhr.status === 201) { // 통신 성공 시
            document.querySelector("[name='image']").value = JSON.parse(xhr.response).object;
            document.querySelector("#detail").style.background = "";
            document.querySelector("#detail").style.background = "white url('upload/" + JSON.parse(xhr.response).object  + "/thumb_130.png') no-repeat right top/18rem";
        } else { // 통신 실패 시
            //
        }
    }

}

/**
 * 에디터 사진 업로드
 */

const seUpload = document.querySelector("#seUpload"); //스마트 에디터 이미지 업로드 요소

seUpload.addEventListener("change",() => {
    formData = new FormData();

    for(let file of seUpload.files) {
        formData.append("images", file);
    }

    formData.append("item", new Blob([JSON.stringify(getData())], {type: "application/json; charset=utf-8"}));
    ajax('item/image',formData, cbkImageUpload, (res) => console.log(res), 'post', 'multipart');
});

function cbkImageUpload(results){

    //스마트 에디터 본문 가져오기
    const seRootIframe = document.querySelector("iframe");
    const seChildIframe = seRootIframe.contentWindow.document.querySelector("#se2_iframe");
    const seBody = seChildIframe.contentWindow.document.body;
    console.log(seBody.innerHTML);

    for (let result of JSON.parse(results)) {

        if (result.success == true) {
            seBody.innerHTML += '<p><img src="/shop/'  + result.path + '" /></p>';
        }

    }

}

//에디터 사진 버튼 클릭
function addImage(){



    document.querySelector("#seUpload").click();



}



/**
 * 5분마다 임시 저장
 */
let autoSave = setInterval(tempSave,5 * 60 * 1000);

//임시 저장
function tempSave() {

    //작성한 내용 불러오기
    let data = getData();

    //발행여부 false
    data.publish = false;

    //업데이트 요청
    ajax('item', getData(), cbkTempSave, (e) => console.log(e), 'put');

}

function cbkTempSave(res) {
    const result = JSON.parse(res);
    if(result.success == true) {
        toastAlert("임시 저장 완료!");
    } else {
        toastAlert("임시 저장 실패!");
    }
}


