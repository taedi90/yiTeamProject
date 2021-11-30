'use strict';

/**
 * 모달창 생성 스크립트
 *
 * @param {string} html - (optional) 모달창 내용
 * @param {int} kind - (optional) 버튼 종류 (1: 확인, 2: 예&아니오)
 * @param {function} confirmCallback - (optional) 확인, 예 클릭 후 실행 함수
 * @param {function} cancelCallback -  (optional) 아니오 클릭 후 실행 함수
 *
 * @requires <div id="modal_container"></div>
 *
 * @author taedi <taedi90@gmail.com>
 */

let modalContainer = document.querySelector("#modal_container"); //모달창이 들어갈 부모요소
const modalOverlay = `<div id="modal_overlay" style="background-color: rgba(0,0,0,0.6);width: 100%;height: 100%;position: absolute;"></div>`; //모달창 오버레이(검정배경)
let idNum = 1; //모달창 식별 번호
let zIndexStart = 100; //다른 요소보다 뒤에 표시되면 값을 상향 조정
let callbackObject = new Object(); //콜백 함수를 저장해둘 객체

//버튼 종류
const buttonAlert = `<button class="modal_button" type="button" onclick="confirmFunc(this)">확인</button>`;
const buttonConfirm =
    `<button type="button" class="modal_button" onclick="confirmFunc(this)">예</button>
<button type="button" class="modal_button" onclick="cancelFunc(this)">아니오</button>`;

//없을 경우 생성하기
if(modalContainer == null){
    modalContainer = document.createElement("div");
    modalContainer.id = "modal_container";
    document.body.appendChild(modalContainer);
}

//모달컨테이너 초기화
modalContainer.innerHTML = ``;

//모달창 생성하기
function newModal(html = "", kind = 1, confirmCallback = () => {}, cancelCallback = () => {}) {

    const id = "modal" + idNum++;

    let buttonType;

    //버튼 종류 설정(1 : 확인, 2 : 예 & 아니오)
    if (kind == 1) {
        buttonType = buttonAlert;
        callbackObject[id] = {confirmCallback:confirmCallback};
    } else if (kind == 2) {
        buttonType = buttonConfirm;
        callbackObject[id] = {confirmCallback:confirmCallback,cancelCallback:cancelCallback};
    } else {
        buttonType = ``;
    }

    const modalWindow =
        `<div id="${id}" class="modal_window"
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

}

//모달창 닫기
function closeModal(elem) {

    // const modal = elem.parentElement.parentElement;
    const modal = elem.closest('.modal_window');

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


function confirmFunc(elem) {

    const modalId = elem.closest('.modal_window').id;

    closeModal(elem);

    callbackObject[modalId].confirmCallback();
    delete callbackObject[modalId];

}

function cancelFunc(elem) {

    const modalId = elem.closest('.modal_window').id;

    closeModal(elem);

    callbackObject[modalId].cancelCallback();
    delete callbackObject[modalId];

}


//사용자가 가르키는 좌표를 받아오는 함수
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
function startPointing(e, elem) {
    //e.preventDefault();
    e.stopPropagation();

    //사용자 포인팅 지점 가져오기
    const coordiantes = getCoordinates(e);
    const clientX = coordiantes.clientX;
    const clientY = coordiantes.clientY;

    //선택한 요소의 좌표 가져오기
    const elemPos = elem.getBoundingClientRect();
    const elemX = elemPos.x;
    const elemY = elemPos.y;

    //사용자 좌표와 요소 간의 갭 구하기
    const gapX = clientX - elemX;
    const gapY = clientY - elemY;

    elem.setAttribute("gap-x", gapX);
    elem.setAttribute("gap-y", gapY);

    //선택한 요소에 'hold'클래스 부여
    elem.classList.add("hold");

    //클릭한 요소를 최상단에 유지
    const elems = modalContainer.querySelectorAll(".modal_window");

    elems.forEach((elem) => {
        if (elem.classList.contains("hold")) {
            elem.style.zIndex = zIndexStart++;
        }
    });

}

//드래그
function movePointing(e) {
    e.preventDefault();
    e.stopPropagation();

    //사용자 좌표 가져오기
    const coordinate = getCoordinates(e);
    let clientX = coordinate.clientX;
    let clientY = coordinate.clientY;

    //현재 포커싱 된 요소 찾기
    const elems = modalContainer.querySelectorAll(".modal_window");

    elems.forEach((elem) => {
        if (elem.classList.contains("hold")) {

            //포인팅 위치와 요소 사이의 갭
            const gapX = elem.getAttribute("gap-x");
            const gapY = elem.getAttribute("gap-y");

            //최초에 지정했던 gap과  현재 포인팅 위치를 기준으로 요소가 이동해야할 좌표 값 구하기
            let elemX = clientX - gapX;
            let elemY = clientY - gapY;
            elem.setAttribute("elem-x", elemX);
            elem.setAttribute("elem-y", elemY);

            //이동 한계 처리 (최대치 이상은 움직이지 않도록)
            const maxLeft = window.innerWidth - elem.getBoundingClientRect().width;
            const maxHeight = window.innerHeight - elem.getBoundingClientRect().height;

            if (elemX <= 0) {
                elemX = 0;
            } else if (elemX > maxLeft) {
                elemX = maxLeft;
            }

            if (elemY <= 0) {
                elemY = 0;
            } else if (elemY > maxHeight) {
                elemY = maxHeight;
            }

            //요소 이동
            elem.style.left = `${elemX}px`;
            elem.style.top = `${elemY}px`;

        }
    });
}

//포인팅 종료(touchend, mouseup)
function endPointing() {
    //e.preventDefault();

    const elems = modalContainer.querySelectorAll(".modal_window");


    elems.forEach((elem) => {
        if (elem.classList.contains("hold")) {

            // 움직임에 적용된 속성 및 class를 삭제
            elem.classList.remove("hold");
            elem.removeAttribute("gap-x");
            elem.removeAttribute("gap-y");
            elem.removeAttribute("elem-x");
            elem.removeAttribute("elem-y");

        }
    });

}


// 마우스가 범위를 벗어날 수 있기 때문에 move, up은 문서 전체에 할당
document.addEventListener('mouseup', endPointing);
document.addEventListener('touchend', endPointing, {passive: false});
document.addEventListener('mousemove', movePointing);
document.addEventListener('touchmove', movePointing, {passive: false});
