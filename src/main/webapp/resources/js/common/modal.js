'use strict';

/**
 * 모달창
 *
 * 사용할 html 페이지에 "modal_container" 아이디의 div를 생성
 * div 이후에 js import 하여 사용
 *
 *
 * @author taedi
 */

const modalContainer = document.querySelector("#modal_container"); //모달창이 들어갈 부모요소
const modalOverlay = `<div id="modal_overlay" style="background-color: rgba(0,0,0,0.6);width: 100%;height: 100%;position: absolute;"></div>`; //모달창 오버레이(검정배경)
let idNum = 1; //모달창 식별 번호
let zIndexStart = 100; //다른 요소보다 뒤에 표시되면 값을 상향 조정
let callbackObject = new Object(); //콜백 함수를 저장해둘 객체

//버튼 종류
const buttonAlert = `<button class="modal_button" type="button" onclick="closeModal(this)">예</button>`;
const buttonConfirm =
    `<button type="button" class="modal_button" onclick="yesFunc(this)">예</button>
<button type="button" class="modal_button" onclick="closeModal(this)">아니오</button> `;

//모달컨테이너 초기화
modalContainer.innerHTML = ``;


//모달창 닫기
function closeModal(elem) {

    const modal = elem.parentElement.parentElement;

    //드래그 이벤트 제거
    modal.removeEventListener('mousedown', (e) => startPointing(e, modal));
    modal.removeEventListener('touchstart', (e) => startPointing(e, modal), {passive: false});

    //모달창 요소 삭제
    modal.remove();

    //모달창이 하나도 없으면 오버레이 지우기
    const modals = modalContainer.querySelectorAll(".modal_window");
    if (modals.length <= 0) {
        modalContainer.querySelector("#modal_overlay").remove();
        modalContainer.style = ``;
    }

}


//모달창 생성하기
function newModal(html = "", kind = 1, callback = () => {
}) {

    const id = "modal" + idNum++;

    let buttonType;

    //버튼 종류 설정
    if (kind == 1) {
        buttonType = buttonAlert;
    } else if (kind == 2) {
        buttonType = buttonConfirm;
        callbackObject[id] = callback;
    } else {
        buttonType = ``;
    }

    const modalWindow =
        `<div id="${id}" class="modal_window" callback="${callback}"
            style="
            background-color: white;
            padding: 2.5rem 5rem;
            text-align: center;
            position: absolute;
            width: 20rem;
            border-radius: 0.1rem;
            box-shadow: 0 1rem 2rem rgba(0, 0, 0, 0.19), 0.6rem 0.6rem rgba(0, 0, 0, 0.23);
            font-size: var(--font-base);
            z-index: ${zIndexStart++}">
            <div class="modal_content">${html}</div>
            <div style="margin-top:1rem;">${buttonType}</div>
        </div>`;

    //오버레이 추가
    const overlay = modalContainer.querySelector("#modal_overlay");
    if (overlay === null) {
        modalContainer.style = `position: fixed;top: 0;left: 0;width: 100%;height: 100%;z-index:${zIndexStart};`;
        modalContainer.innerHTML += modalOverlay;
    }

    //모달창 표시하기
    modalContainer.innerHTML += modalWindow;

    const modal = modalContainer.querySelector("#" + id);

    //가운데정렬
    const left = (window.innerWidth - modal.getBoundingClientRect().width) / 2;
    const top = (window.innerHeight - modal.getBoundingClientRect().height) / 2;
    modal.style.top = `${top}px`;
    modal.style.left = `${left}px`;

    //드래그 이벤트 추가
    const modals = modalContainer.querySelectorAll(".modal_window");
    modals.forEach((modal) => {
        modal.addEventListener('mousedown', (e) => startPointing(e, modal));
        modal.addEventListener('touchstart', (e) => startPointing(e, modal), {passive: false});
    });

};

function yesFunc(elem) {

    const modal = elem.parentElement.parentElement;

    callbackObject[modal.id]();
    delete callbackObject[modal.id];

    closeModal(elem)
}

function test(){
    console.log("hi");
}

function getCoordinates(e) {

    let clientX = undefined;
    let clientY = undefined;

    //모바일 & 데스크탑 체크
    if (e.type == 'touchstart' || e.type == 'touchmove' || e.type == 'touchend' || e.type == 'touchcancel') {
        let evt = (typeof e.originalEvent === 'undefined') ? e : e.originalEvent;
        let touch = evt.touches[0] || evt.changedTouches[0];
        clientX = touch.pageX;
        clientY = touch.pageY;
    } else if (e.type == 'mousedown' || e.type == 'mouseup' || e.type == 'mousemove' || e.type == 'mouseover' || e.type == 'mouseout' || e.type == 'mouseenter' || e.type == 'mouseleave') {
        clientX = e.clientX;
        clientY = e.clientY;
    }

    return {clientX: clientX, clientY: clientY};
}


//포인팅 시작(touchstart, mousedown)
function startPointing(e, modal) {
    //e.preventDefault();
    e.stopPropagation();

    const coordiantes = getCoordinates(e);

    let clientY = coordiantes.clientY;
    let clientX = coordiantes.clientX;


    // 사이드바의 X좌표
    const modalPos = modal.getBoundingClientRect();
    const modalX = modalPos.x;
    const modalY = modalPos.y;

    // 사이드바 안에 있는 마우스 커서의 X좌표
    const gapX = clientX - modalX;
    const gapY = clientY - modalY;

    modal.setAttribute("gap-x", gapX);
    modal.setAttribute("gap-y", gapY);


    //
    modal.classList.add("hold");

    //클릭한 모달창을 최상단에 유지
    const modals = modalContainer.querySelectorAll(".modal_window");

    modals.forEach((modal) => {
        if (modal.classList.contains("hold")) {
            modal.style.zIndex = zIndexStart++;
        }
    });

}

//드래그
function movePointing(e) {
    e.preventDefault();
    e.stopPropagation();

    const coordinate = getCoordinates(e);
    let clientX = coordinate.clientX;
    let clientY = coordinate.clientY;


    const modals = modalContainer.querySelectorAll(".modal_window");

    modals.forEach((modal) => {
        if (modal.classList.contains("hold")) {

            //포인팅 위치와 오브젝트 사이의 갭
            const gapX = modal.getAttribute("gap-x");
            const gapY = modal.getAttribute("gap-y");

            //마우스 커서 위치에 따른 이동
            let modalX = clientX - gapX;
            let modalY = clientY - gapY;
            modal.setAttribute("modal-x", modalX);
            modal.setAttribute("modal-y", modalY);

            //이동 한계 처리 (최대치 이상은 움직이지 않도록)
            const maxLeft = window.innerWidth - modal.getBoundingClientRect().width;
            const maxHeight = window.innerHeight - modal.getBoundingClientRect().height;

            if (modalX <= 0) {
                modalX = 0;
            } else if (modalX > maxLeft) {
                modalX = maxLeft;
            }

            if (modalY <= 0) {
                modalY = 0;
            } else if (modalY > maxHeight) {
                modalY = maxHeight;
            }

            //오브젝트 이동
            modal.style.left = `${modalX}px`;
            modal.style.top = `${modalY}px`;

        }
    });
}

//포인팅 종료(touchend, mouseup)
function endPointing() {
    //e.preventDefault();

    const modals = modalContainer.querySelectorAll(".modal_window");


    modals.forEach((modal) => {
        if (modal.classList.contains("hold")) {

            // 움직임에 적용된 속성 및 class를 삭제
            modal.classList.remove("hold");
            modal.removeAttribute("gap-x");
            modal.removeAttribute("gap-y");
            modal.removeAttribute("modal-x");
            modal.removeAttribute("modal-y");

        }
    });

}


// 마우스가 범위를 벗어날 수 있기 때문에 move, up은 문서 전체에 할당
document.addEventListener('mouseup', endPointing);
document.addEventListener('touchend', endPointing, {passive: false});
document.addEventListener('mousemove', movePointing);
document.addEventListener('touchmove', movePointing, {passive: false});
