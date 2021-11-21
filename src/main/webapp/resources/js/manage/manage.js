'use strict';

const descriptionHolders = document.getElementsByClassName("description_holder");
const iconHolders = document.getElementsByClassName("icon_holder");
const hideImage = document.getElementById("hide_image");

let token = 1;

// 숨기기 & 보이기 버튼 클릭
function toggleHide(){

    if (token === 1) {
        hideSidebarAnimation();
        token = 0;
    } else {
        showSidebarAnimation();
        token = 1;
    }

}

// 모바일이거나 스크린 사이즈가 768px 보다 작으면 사이드바 숨기기
if( /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent) ||
    window.innerWidth <= 1500) {
    hideSidebar();
}

// 사이드바 보여주기
function showSidebar(){
    for(let i of descriptionHolders) {
        i.classList.remove("hidden");
    }
    for(let i of iconHolders) {
        i.classList.remove("tooltip");
    }
    hideImage.classList.remove("revert");
    token = 1;
}

// 사이드바 숨기기
function hideSidebar(){
    for(let i of descriptionHolders) {
        i.classList.add("hidden");
    }
    for(let i of iconHolders) {
        i.classList.add("tooltip");
    }
    hideImage.classList.add("revert");
    token = 0;
}

// 사이드바 숨기기 애니메이션
function hideSidebarAnimation(){
    for(let i of descriptionHolders) {
        i.classList.add("disappear");
    }
    setTimeout(()=> {
        for(let i of descriptionHolders) {
            i.classList.remove("disappear");
            i.classList.add("hidden");
        }
    }, 300);
    setTimeout(()=> {
        for(let i of iconHolders) {
            i.classList.add("tooltip");
        }
    }, 500);

    hideImage.classList.add("revert");
}

// 사이드바 보여주기 애니메이션
function showSidebarAnimation(){
    for(let i of descriptionHolders) {
        i.classList.remove("hidden");
        i.classList.add("appear");
    }
    for(let i of iconHolders) {
        i.classList.remove("tooltip");
    }
    setTimeout(()=> {
        for(let i of descriptionHolders) {
            i.classList.remove("appear");

        }
    }, 300)
    hideImage.classList.remove("revert");
}