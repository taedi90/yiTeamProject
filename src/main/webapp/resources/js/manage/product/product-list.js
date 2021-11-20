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

let selectToken = 1;
function selectAll() {
    for (let checkbox of checkboxs){
        if(selectToken == 1){
            checkbox.checked = true;
        } else {
            checkbox.checked = false;
        }

    }
    selectToken = selectToken * -1;
}

//등록 여부
function publishOption(elem){

    const nowUrl = window.location.href;
    const relUrl = nowUrl.replace(/^http.*\//ig, "");
    let delParam = relUrl.replace(/&publish=[^&]*/ig, "");
    delParam = delParam.replace(/&pageNo=[^&]*/ig, "");
    const href = delParam + "&publish=" + elem.value;

    window.location.href = href;

    // console.log(currentHref);
    // console.log(relativeHref);
    // console.log(deleteParam);
    // console.log(href);

}

//공개 여부
function hideOption(elem){

    const nowUrl = window.location.href;
    const relUrl = nowUrl.replace(/^http.*\//ig, "");
    let delParam = relUrl.replace(/&hide=[^&]*/ig, "");
    delParam = delParam.replace(/&pageNo=[^&]*/ig, "");
    const href = delParam + "&hide=" + elem.value;

    window.location.href = href;

    // console.log(currentHref);
    // console.log(relativeHref);
    // console.log(deleteParam);
    // console.log(href);

}

//검색어
function search(){
    const keyword = document.querySelector("[name='keyword']").value;

    window.location.href = "manage?section=product&func=list&keyword=" + keyword;
}