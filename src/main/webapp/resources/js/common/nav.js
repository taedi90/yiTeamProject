'use strict';
(function (){
    //모바일 메뉴 아이콘
    const mobile_button = document.querySelector('.mobile_button');
    const mobile_nav = document.querySelector('.nav .menu');
    const rightIcon = document.querySelector('.nav .right_icon');

    mobile_button.addEventListener('click',() => {
        mobile_nav.classList.toggle('active');
        rightIcon.classList.toggle('active');
    })

    //검색 아이콘
    const navSearchIcon = document.querySelector("#nav_search_icon");
    const navSearchbar = document.querySelector("#nav_searchbar");

    navSearchIcon.addEventListener('click', () => {
        navSearchbar.classList.toggle('hidden');
    });

    //검색
    navSearchbar.addEventListener('keypress', (e) =>{
        if(e.key === 'Enter') {
            window.location.href = 'main?keyword=' + navSearchbar.value;
        }
    });
})();

