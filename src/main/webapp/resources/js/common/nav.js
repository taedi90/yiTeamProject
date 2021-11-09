const mobile_button = document.querySelector('.mobile_button');
const mobile_nav = document.querySelector('.mobile_nav');

mobile_button.addEventListener('click',() => {
    mobile_nav.classList.toggle('active');
})