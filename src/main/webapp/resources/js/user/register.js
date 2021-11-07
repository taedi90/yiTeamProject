'use strict';

// 버튼 눌렀을 때 액션
function doRegister(){
    let data = {
        username: document.form.username.value,
        name: document.form.name.value,
        password: document.form.password.value,
        email: document.form.email.value,
        phone: document.form.phone.value
    }

    ajax('register', data, endRegister);
    // 로딩창 뜨게

}

// ajax 통신 성공했을 때 액션
function endRegister(result){
    result = JSON.parse(result);
    alert(result.comment);

    if(result.success === true){
        window.location.href = "login";
    }

}