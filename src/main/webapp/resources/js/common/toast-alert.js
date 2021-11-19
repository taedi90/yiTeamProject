'use strict';

const toastStyle = `
        position: fixed;
        bottom: 30px;
        left: 50%;
        padding: 15px 20px;
        transform: translate(-50%, 10px);
        border-radius: 30px;
        overflow: hidden;
        font-size: .8rem;
        opacity: 0;
        visibility: hidden;
        transition: opacity .5s, visibility .5s, transform .5s;
        background: rgba(0, 0, 0, .35);
        color: #fff;
        z-index: 10000;`;

const toastElem = document.createElement("div");
toastElem.id = "toast";
toastElem.style = toastStyle;
document.querySelector("body").appendChild(toastElem);

let toastTimeout;

function newToast(string) {

    clearTimeout(toastTimeout);

    toastElem.innerText = string;

    toastElem.style.opacity = 1;
    toastElem.style.visibility = "visible";
    toastElem.style.transform = "translate(-50%, 0)";

    toastTimeout = window.setTimeout(() => {
        toastElem.style.opacity = 0;
        toastElem.style.visibility = "hidden";
        toastElem.style.transform = "translate(-50%, 10px)";
    }, 2000);
}