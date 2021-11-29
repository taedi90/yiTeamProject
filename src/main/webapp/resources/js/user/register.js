'use strict';
const errorHolder = document.getElementById("error_holder"); // 오류 메세지를 출력할 요소


// 버튼 눌렀을 때 액션
function doRegister(){
    const className = "register_input"; // 전송할 내용이 담긴 input
    const inputs = document.getElementsByClassName(className);
    let member = {}; // ajax 통신에 사용할 데이터

    // 값 가져오기
    for(let i of inputs){
        member[i.name] = i.value;
    }

    // 비밀번호 확인 체크
    if (member.password !== member.passwordConfirm) {
        errorHolder.innerHTML = "비밀번호 확인문자가 일치하지 않습니다.";
        return;
    }

    // 개인정보처리방침 번호
    const policy = document.querySelector(".policy_input").value;

    let formData = new FormData();
    formData.append("member", new Blob([JSON.stringify(member)], {type: "application/json; charset=utf-8"}));
    formData.append("policyNo", new Blob([policy], {type: "application/x-www-form-urlencoded"}));

    // ajax 전송
    ajax('register', formData, afterRegister, (res) => console.log(res), 'post', 'multipart');

    // 로딩창 띄우기

}

// ajax 통신 성공했을 때 액션
function afterRegister(result){
    result = JSON.parse(result);

    if(result.success === true){
        alert(result.comment);
        window.location.href = "login";
    } else {
        errorHolder.innerHTML = result.comment;
    }

}