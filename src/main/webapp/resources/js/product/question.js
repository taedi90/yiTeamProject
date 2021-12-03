'use strict';

const urlParameter = window.location.search;
const urlParams = new URLSearchParams(urlParameter);
const itemNo = urlParams.get("itemNo"); //현재 페이지의 아이템 번호

const questionTable = document.querySelector("#question_table"); //질문 테이블

questionTable.createTHead().insertRow(0).innerHTML =
    `
    <tr>
        <th>질문번호</th>
        <th>제목</th>
        <th>작성자</th>
        <th>작성일</th>
    </tr>
    `; //테이블 헤더

const tableBody = questionTable.createTBody(); //테이블 헤더

//질문 가져오기
function getQuestion(){

    ajax('item/question', {param: itemNo}, cbkGetQuestion, (e) => {console.log(e);}, 'get', 'urlEncoded');
}

function cbkGetQuestion(result) {

    tableBody.innerHTML = '';

    result = JSON.parse(result);
    console.log(result);

    const questions = result.records;

    for (let i = 0; i < questions.length; ++i){
        tableBody.insertRow(i*2);
        tableBody.rows[i*2].innerHTML =
            `
                <td>${questions[i].questionNo}</td>
                <td>${questions[i].title}</td>
                <td>${questions[i].member.name}</td>
                <td>${new Date(questions[i].regDate).toLocaleDateString()}</td>
            `;
        tableBody.insertRow(i*2 + 1);
        tableBody.rows[i*2 + 1].innerHTML =
            `
                <td colspan="4">
                    <div>${questions[i].text}</div>
                    <div>${(questions[i].answerTitle ? "답변제목 : " + questions[i].answerTitle:"")}</div>
                    <div>${(questions[i].answerRegDate ? "답변일자 : " + new Date(questions[i].answerRegDate).toLocaleDateString():"")}</div>
                    <div>${(questions[i].answerText ? "답변내용 : " + questions[i].answerText:"")}</div>
                </td>
            `;
    }
}

//게시글 등록
function postQuestion(){
    const title = document.question_form.title.value;
    const text = document.question_form.text.value;

    if(title != "" && text != "") {
        ajax('item/question', {title:title, text:text, item:{itemNo:itemNo}}, cbkPostQuestion);
    }
}

function cbkPostQuestion(result){
    result = JSON.parse(result);

    if(result.success == true) {
        const title = document.question_form.title;
        const text = document.question_form.text;

        title.value = null;
        text.value = null;

        getQuestion();
    }

}

getQuestion();