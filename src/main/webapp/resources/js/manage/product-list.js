'use strict';

const checkboxs = document.querySelectorAll(".checkbox");

function deleteItem(){

    newModal('정말 삭제하시히겠습니까?', 2, deleteItemConfirm);

}

function deleteItemConfirm(){
    //체크된 것 찾기


    let list = [];

    for (let checkbox of checkboxs){
        if (checkbox.checked){
            list.push({itemNo: checkbox.name});
        }
    }

    console.log(list);

    //아이템 객체에 담기
    //ajax 보내기

    ajax('item',list,cbkDeleteItem,(res) => console.error(res),'delete');
}


function cbkDeleteItem(result) {
    window.location.reload();
}

let token = 1;
function selectAll() {
    for (let checkbox of checkboxs){
        if(token == 1){
            checkbox.checked = true;
        } else {
            checkbox.checked = false;
        }

    }
    token = token * -1;
}