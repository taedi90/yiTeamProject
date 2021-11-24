const mobile_button = document.querySelector('.mobile_button');
const mobile_nav = document.querySelector('.nav .menu');

mobile_button.addEventListener('click',() => {
    console.log("clicked");
    mobile_nav.classList.toggle('active');
})