'use strict';

// 회원 탈퇴
function deactivateMember() {
    newModal("탈퇴하면 복구할 수 없습니다. 계속하시겠습니까?", 2, cbkDeactivateMember);
}

function cbkDeactivateMember() {
    const inner =
        `
        <div id="modal_confirm">
            <p>확인을 위하여 패스워드를 입력해주세요.</p>
            <p><input id="modal_password_confirm" type="password" placeholder="패스워드"></p>
            <br>
            <p><button type="button" onclick="applyDeactivation(this)">확인</button></p>
        </div>
        `;
    newModal(inner, 3);
}

function applyDeactivation(elem) {
    const data = document.querySelector('#modal_password_confirm').value;
    const modalWindow = elem.closest('#modal_confirm');
    if (modalWindow) {
        closeModal(modalWindow);
    }
    console.log(modalWindow);
    // ajax('deactivate-member', data, cbkDeactivationResult);
}

