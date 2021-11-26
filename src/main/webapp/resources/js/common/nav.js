'use strict';
(function (){
    const mobile_button = document.querySelector('.mobile_button');
    const mobile_nav = document.querySelector('.nav .menu');
    const rightIcon = document.querySelector('.nav .right_icon');

    mobile_button.addEventListener('click',() => {
        console.log("clicked");
        mobile_nav.classList.toggle('active');
        rightIcon.classList.toggle('active');
    })
})();

