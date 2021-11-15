//내용 가져오기
function editorGetContent() {
    oEditors.getById["smartEditor"].exec("UPDATE_CONTENTS_FIELD", []); //textarea의 id를 적어줍니다.
    let content = document.getElementById("smartEditor").value;

    return content;
}


let oEditors = [];

function editorInit(){
    oEditors = [];
    nhn.husky.EZCreator.createInIFrame({
        oAppRef: oEditors,
        elPlaceHolder: "smartEditor", //textarea의 id와 동일하게 작성

        sSkinURI : "se2/SmartEditor2Skin.html", //skin 경로 지정
        fCreator : "createSEditor2", htParams : {
            bUseToolbar : true, // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
            elAppContainer : false
            //bUseVerticalResizer : true, // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
            //bUseModeChanger : true // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
        }
    });
    // if (document.postTemp){
    //     oEditors.getById["smartEditor"].exec("PASTE_HTML", document.postTemp.value);
    //     document.postTemp.removeChild();
    //     //oEditors.getById["smartEditor"].exec("PASTE_HTML", document.getElementById("postTemp").innerText);
    // }

}
editorInit();