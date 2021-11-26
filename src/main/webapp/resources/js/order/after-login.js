'use strict';
(function (){
    // local storage 정보 읽기
    const selectedItems = JSON.parse(window.localStorage.getItem("selectedItems"));

    // local storage 정보 지우기
    window.localStorage.removeItem("selectedItems");

    //toOrder(selectedItems);

    // ajax 통신하기
    ajax('order', selectedItems, cbkSetOrder);

    // order 이동
    function cbkSetOrder(res) {
        const result = JSON.parse(res);

        if (parseInt(result) != null) {
            window.location.href = "order?orderNo=" + result;
            return;
        }

        // 에러 메세지
        window.location.href = "main";
    }

    // 10초 이상 진행이 되지 않을 경우 메인으로 이동
    setTimeout(()=>{
        window.location.href = "main";
    },10000);
})();