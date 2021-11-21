'use strict';

const searchInput = document.querySelector("#search_input");
const autocomplete = document.querySelector("#autocomplete");
const searchList = document.querySelector("#search_list");


document.addEventListener("click", ()=> {
    searchList.innerHTML = '';
})
document.addEventListener("touchstart", ()=> {
    searchList.innerHTML = '';
})

searchInput.addEventListener("input", keywordSearch);

function keywordSearch() {
    const keyword = searchInput.value.trim();
    if (keyword.length > 0) {
        ajax('search-non-admin', {username:keyword}, cbkKeywordSearch);
    } else {
        //자동완성 창 닫기
        searchList.innerHTML = '';
    }
}

function cbkKeywordSearch(res) {
    let result = JSON.parse(res);

    searchList.innerHTML = '';

    if(result.length <= 0) {
        //없음
        searchList.innerHTML = `<li><div>결과가 없습니다.</div></li>`;
        return;
    }


    for(let i of result) {
        let elem = document.createElement("li");
        elem.id = i.username;
        elem.addEventListener("click", () => {
            searchInput.value = elem.id;
            searchList.innerHTML = '';
        });

        const keyword = searchInput.value.trim();

        const username = i.username.replace(keyword, '<span>' + keyword + '</span>');

        elem.innerHTML =
            `<div>${username}</div>`;

        searchList.appendChild(elem);
    }
}

function addManager(){

    //빈값 확인
    const keyword = searchInput.value.trim();

    if(keyword.length > 0) {
        ajax('admin/auth', {username: keyword}, cbkAddManager);
    }

}

function cbkAddManager(res){
    const result = JSON.parse(res);

    if(result.success == 1) {
        location.reload();
    } else {
        newModal(result.comment);
    }

}

function deleteManager(username){

    ajax('admin/auth', {username: username}, cbkDeleteManager, (e) => console.log(e), 'delete');

}

function cbkDeleteManager(res){
    const result = JSON.parse(res);

    if(result.success == 1) {
        location.reload();
    } else {
        newModal(result.comment);
    }

}


