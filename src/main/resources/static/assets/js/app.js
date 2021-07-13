const burger = document.querySelector(".burger");
const nav = document.querySelector(".mobile-nav");
const navLink = document.querySelectorAll(".mobile-nav li");
const mobile = document.querySelectorAll(".m-link");
// fuction to toggle nav
let state = 0;
const openDrop = () => {
    nav.style.display = "flex";
}
const closeDrop = () => {
    nav.style.display = "none";
    state = 0;
}

function toggleNav(n) {
    state += n;
    if (state == 1) {
        openDrop();
    } else {
        closeDrop();
    }

    // to animate nav
    navLink.forEach((link, index) => {
        if (link.style.animation) { // without the if statement the list fade in will only implent on refresh of the page
            link.style.animation = "";
        } else {
            link.style.animation = `navLinkFade 0.5s ease forwards ${index/7 + 0.5}s`;
        }
    });
    // to animate the burger icon
    burger.classList.toggle("toggle");
}
// to close mobile nav bar on click
const closeDropdownMenuSelectingItem = (() => mobile.forEach((item) => item.addEventListener("click", () => {
    closeDrop();
    toggleNav();
})))()

//On click event on suspend button
var suspendButtons = document.getElementsByClassName("btn-suspend");
for (var i = 0; i < suspendButtons.length; i++) {
    suspendButtons[i].addEventListener("click", function (ev) {
        var $btn = $(this);
        var rowId = $btn.attr('data-id');
        console.log('suspend', rowId);
        document.getElementById("suspend-form").setAttribute('action', '/user/suspend/' + rowId)
    })
}

//On click event on reinstate button
var reinstateButtons = document.getElementsByClassName("btn-reinstate");
for (var i = 0; i < reinstateButtons.length; i++) {
    reinstateButtons[i].addEventListener("click", function (ev) {
        var $btn = $(this);
        var rowId = $btn.attr('data-id');
        console.log('reinstate',rowId);
        document.getElementById("reinstate-btn").setAttribute('href', '/user/reinstate/' + rowId)
    })
}

//On click event on accept button
var acceptButtons = document.getElementsByClassName("btn-accept");
for (var i = 0; i < acceptButtons.length; i++) {
    acceptButtons[i].addEventListener("click", function (ev) {
        var $btn = $(this);
        var rowId = $btn.attr('data-id');
        console.log('accept', rowId);
        document.getElementById("accept-btn").setAttribute('action', '/user/approve/' + rowId)
    })
}

//On click event on reject button
var rejectButtons = document.getElementsByClassName("btn-reject");
for (var i = 0; i < rejectButtons.length; i++) {
    rejectButtons[i].addEventListener("click", function (ev) {
        var $btn = $(this);
        var rowId = $btn.attr('data-id');
        console.log('reject',rowId);
        document.getElementById("reject-btn").setAttribute('href', '/user/reject/' + rowId)
    })
}