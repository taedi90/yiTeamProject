'use strict';

const className = "register_input"; // 전송할 내용이 담긴 input
const inputs = document.getElementsByClassName(className);
let data = {} // ajax 통신에 사용할 데이터
const errorHolder = document.getElementById("error_holder"); // 오류 메세지를 출력할 요소


// 버튼 눌렀을 때 액션
function doRegister(){

    // 값 가져오기
    for(let i of inputs){
        data[i.name] = i.value;
    }

    // 비밀번호 확인 체크
    if (data.password !== data.passwordConfirm) {
        errorHolder.innerHTML = "비밀번호 확인문자가 일치하지 않습니다.";
        return;
    }

    // ajax 전송
    ajax('register', data, afterRegister);

    // 로딩창 띄우기

}

// ajax 통신 성공했을 때 액션
function afterRegister(result){
    result = JSON.parse(result);

    if(result.success === true){
        alert(result.comment);
        window.location.href = "login";
    } else {
        document.getElementById("error_holder").innerHTML = result.comment;
    }



}