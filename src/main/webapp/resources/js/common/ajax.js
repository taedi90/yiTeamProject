'use strict';

// 예시
// ajax('board/insert')
// ajax('board/insert',{title: '제목', context: '내용'}, funcAfterInsert)
// ajax('board/insert',{title: '제목', context: '내용'}, funcAfterInsert, funcAjaxFail, 'put', 'urlencoded')

function ajax(url = '', // url(필수) : 호출 할 url
              data = {}, // data(default null) : 전송 할 데이터
              callback = (res) => console.log(res), // callback(default consolelog) : ajax 통신 완료 후 실행 할 함수
              errorCallback = (res) => console.error(res), // callback(default consolelog) : ajax 통신 실패 시 실행 할 함수
              method = 'post', // method(default post) : post / get / put ...
              type = 'json' // type(default json) : json / multipart / urlencoded ...
) {

    let contentType = undefined;
    if(type == "json"){
        contentType = "application/json; charset=utf-8";
        data = JSON.stringify(data);
    }else if(type == "multipart"){
        //data = data;
    } else{
        contentType = "application/x-www-form-urlencoded; charset=utf-8;";
        url = url + "?" + new URLSearchParams(Object.entries(data)).toString();
        data = null;
    }


    let xhr = new XMLHttpRequest();
    xhr.open(method, url, true);
    if(type != "multipart"){
        xhr.setRequestHeader('Content-Type', contentType); // request type 설정
    }
    xhr.send(data);

    xhr.onload = function () {
        if (xhr.status === 200 || xhr.status === 201) { // 통신 성공 시
            callback(xhr.response);
        } else { // 통신 실패 시
            errorCallback(xhr.status);
        }
    }

}

// 동기 방식, jQuery 필요
function syncAjax(url = '', // url(필수) : 호출 할 url
                  data = {}, // data(default null) : 전송 할 데이터
                  callback = (res) => console.log(res), // callback(default consolelog) : ajax 통신 완료 후 실행 할 함수
                  errorCallback = (res) => console.error(res), // callback(default consolelog) : ajax 통신 실패 시 실행 할 함수
                  method = 'post', // method(default post) : post / get / put ...
                  type = 'json' // type(default json) : json / urlencoded
) {

    let contentType = undefined;
    if(type == "json"){
        contentType = "application/json; charset=utf-8";
        data = JSON.stringify(data);
    } else if(type == "multipart"){
        contentType = false;
    } else{
        contentType = "application/x-www-form-urlencoded";
    }

    console.log(data);

    $.ajax({
        url : url,
        method : method,
        async: false,
        data : data,
        contentType: contentType,
        success : function(result){
            callback(result);
        },
        error: function(e) {
            errorCallback(e);
        }
    });

}