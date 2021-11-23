'use strict';

let page = 2;
const btnMore = document.querySelector("div#more");

function viewMore() {

    let pager = new Object();

    pager.pageNo = page++;

    if(typeof category !== 'undefined'){
        pager.category = category;
    }

    btnMore.style.display = "none";

    ajax('main', pager, cbkViewMore);


}

function cbkViewMore(res) {

    let result;

    try {
        result = JSON.parse(res);
        console.log(result);
    } catch (e) {
        console.log(e);
        return;
    }

    if(result.finalPageNo >= page){
        btnMore.style.display = "block";
    }

    const parent = document.querySelector(".product_container");

    for (let item of result.records) {

        let child = document.createElement("a");
        child.setAttribute("href", "detail?itemNo=" + item.itemNo);

        let options = '';
        for(let option of item.options){
            options += `${option.name}&nbsp;`;
        }

        child.innerHTML =
            `
                                <div class="product">
                        <div class="image_holder">
                            <img src="upload/${item.image}/thumb_350.png" onerror="this.src='/shop/img/common/lazy.svg'" alt="">
                        </div>
                        <div class="desc">
                            <p class="product_category">${item.category.title}</p>
                            <p class="product_title">${item.title}</p>

                            <p class="product_price">${item.price.toLocaleString('ko-KR')}원</p>
                            <p class="product_option">
                                ${options}
                            </p>

                        </div>
                    </div>
            `;
        parent.appendChild(child);
    }

}