'use strict';
// 버튼 눌렀을 때 액션
function sendMail(){
    let data = {
        username: document.form.username.value,
        email: document.form.email.value
    }

    ajax('email-confirm', data, afterSendMail);
    // 로딩창 뜨게

}

// ajax 통신 성공했을 때 액션
function afterSendMail(result){
    result = JSON.parse(result);
    alert(result.comment);

    if(result.success === true){
        window.location.href = "login";
    }

}