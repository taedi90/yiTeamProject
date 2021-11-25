'use strict';

(function () {
    const slideList = document.querySelector('.slide_list');  // Slide parent dom
    const slideWrap = document.querySelector('.slide_wrap');  // Slide parent dom
    const slideContents = document.querySelectorAll('.slide_content');  // each slide dom
    const slideBtnNext = document.querySelector('.slide_btn_next'); // next button
    const slideBtnPrev = document.querySelector('.slide_btn_prev'); // prev button
    const pagination = document.querySelector('.slide_pagination');
    const slideLen = slideContents.length;  // slide length
    const slideSpeed = 300; // slide speed
    const startNum = 0; // initial slide index (0 ~ 4)
    const wrapRatio = 7/19; // 19:7
    const slideRatio = 1; // 3:4
    const minWrapHeight = 375;
    let wrapWidth = 0;
    let wrapHeight = 0;
    let slideWidth = 0;
    let slideHeight = 0;

    // Copy first and last slide
    let firstChild = slideList.firstElementChild;
    let lastChild = slideList.lastElementChild;
    let clonedFirst = firstChild.cloneNode(true);
    let clonedLast = lastChild.cloneNode(true);

    // Add copied Slides
    slideList.appendChild(clonedFirst);
    slideList.insertBefore(clonedLast, slideList.firstElementChild);


    window.addEventListener('resize', () => {
        resizeSlide();
        for (let i = 0; i < slideLen + 2; i++) {
            if(slideContents[i].classList.contains("slide_active")){
                init(i);
                break;
            }
        }
    });

    // Auto slide
    let interval = setInterval(nextItem, 5000);
    function nextItem() {
        slideBtnNext.click();
    }

    function resetInterval(){
        clearInterval(interval);
        interval = setInterval(nextItem, 5000);
    }


    function resizeSlide(){

        // let realWidth = document.body.clientWidth;
        let realWidth = slideWrap.parentNode.clientWidth;

        // resize button element
        if(realWidth < 1200){
            slideWrap.style.fontSize = ((1200 - (1200 - realWidth)/2) / 1200) * 10 + "px";
        }else {
            slideWrap.style.fontSize = "10px";
        }



        wrapWidth = realWidth;
        wrapHeight = wrapWidth * wrapRatio;
        if(wrapHeight < minWrapHeight) {
            wrapHeight = minWrapHeight;
        }


        slideHeight = wrapHeight;
        slideWidth = wrapHeight / slideRatio;

        const slideContentAll = document.querySelectorAll('.slide_content');  // each slide dom
        for (let i = 0; i < slideLen + 2; i++) {
            slideContentAll[i].style.width = slideWidth;
            slideContentAll[i].style.height = slideHeight;
        }
        slideWrap.style.width = wrapWidth;
        slideWrap.style.height = wrapHeight;
    }
    resizeSlide();

    function init(curIndex = startNum){
        slideList.style.width = (slideWidth + 10) * (slideLen + 2) + "px";

        // Add pagination dynamically
        let pageChild = '';
        for (var i = 0; i < slideLen; i++) {
            pageChild += '<li class="dot';
            pageChild += (i === curIndex) ? ' dot_active' : '';
            pageChild += '" data-index="' + i + '"><a href="#"></a></li>';
        }
        pagination.innerHTML = pageChild;
        const pageDots = document.querySelectorAll('.dot'); // each dot from pagination

        const adjustment = -((wrapWidth - slideWidth)/2);

        // slideList.style.transform = "translate3d(-" + (slideWidth * (curIndex + 1)) + "px, 0px, 0px)";
        slideList.style.transform = "translate3d(-" + (adjustment + ((slideWidth + 10) * (curIndex + 1)))  + "px, 0px, 0px)";

        //let curIndex = startNum; // current slide index (except copied slide)
        let curSlide = slideContents[curIndex]; // current slide dom
        curSlide.classList.add('slide_active');



        /** Next Button Event */
        slideBtnNext.addEventListener('click', function() {
            resetInterval();

            if (curIndex <= slideLen - 1) {
                slideList.style.transition = slideSpeed + "ms";
                slideList.style.transform = "translate3d(-" + (adjustment + ((slideWidth + 10)* (curIndex + 2))) + "px, 0px, 0px)";
            }
            if (curIndex === slideLen - 1) {
                setTimeout(function() {
                    slideList.style.transition = "0ms";
                    slideList.style.transform = "translate3d(-" + (adjustment + ((slideWidth + 10) * (curIndex + 1))) + "px, 0px, 0px)";
                }, slideSpeed);
                curIndex = -1;
            }
            curSlide.classList.remove('slide_active');
            pageDots[(curIndex === -1) ? slideLen - 1 : curIndex].classList.remove('dot_active');
            curSlide = slideContents[++curIndex];
            curSlide.classList.add('slide_active');
            pageDots[curIndex].classList.add('dot_active');
        });

        /** Prev Button Event */
        slideBtnPrev.addEventListener('click', function() {
            resetInterval();

            if (curIndex > 0) {
                slideList.style.transition = slideSpeed + "ms";
                slideList.style.transform = "translate3d(-" + (adjustment + (slideWidth + 10) * curIndex) + "px, 0px, 0px)";
            }
            if (curIndex === 0) {
                slideList.style.transition = slideSpeed + "ms";
                slideList.style.transform = "translate3d(" + (-adjustment) + "px, 0px, 0px)";
                setTimeout(function() {
                    slideList.style.transition = "0ms";
                    slideList.style.transform = "translate3d(-" + (adjustment + (slideWidth + 10) * slideLen) + "px, 0px, 0px)";
                }, slideSpeed);
                curIndex = slideLen;
            }
            curSlide.classList.remove('slide_active');
            pageDots[(curIndex === slideLen) ? 0 : curIndex].classList.remove('dot_active');
            curSlide = slideContents[--curIndex];
            curSlide.classList.add('slide_active');
            pageDots[curIndex].classList.add('dot_active');
        });

        /** Pagination Button Event */
        let curDot;
        Array.prototype.forEach.call(pageDots, function (dot, i) {

            dot.addEventListener('click', function (e) {
                e.preventDefault();
                resetInterval();
                curDot = document.querySelector('.dot_active');
                curDot.classList.remove('dot_active');

                curDot = this;
                this.classList.add('dot_active');

                curSlide.classList.remove('slide_active');
                curIndex = Number(this.getAttribute('data-index'));
                curSlide = slideContents[curIndex];
                curSlide.classList.add('slide_active');
                slideList.style.transition = slideSpeed + "ms";
                slideList.style.transform = "translate3d(-" + (adjustment+ (slideWidth + 10) * (curIndex + 1)) + "px, 0px, 0px)";
            });
        });
    }
    init();

})();