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

//On click event on suspend user button
var suspendButtons = document.getElementsByClassName("btn-suspend");
for (var i = 0; i < suspendButtons.length; i++) {
    suspendButtons[i].addEventListener("click", function (ev) {
        var $btn = $(this);
        var rowId = $btn.attr('data-id');
        console.log('suspend', rowId);
        document.getElementById("suspend-form").setAttribute('action', '/user/suspend/' + rowId)
    })
}

//On click event on reinstate user button
var reinstateButtons = document.getElementsByClassName("btn-reinstate");
for (var i = 0; i < reinstateButtons.length; i++) {
    reinstateButtons[i].addEventListener("click", function (ev) {
        var $btn = $(this);
        var rowId = $btn.attr('data-id');
        console.log('reinstate',rowId);
        document.getElementById("reinstate-btn").setAttribute('href', '/user/reinstate/' + rowId)
    })
}

//On click event on approver user button
var acceptButtons = document.getElementsByClassName("btn-accept");
for (var i = 0; i < acceptButtons.length; i++) {
    acceptButtons[i].addEventListener("click", function (ev) {
        var $btn = $(this);
        var rowId = $btn.attr('data-id');
        console.log('accept', rowId);
        document.getElementById("accept-btn").setAttribute('action', '/user/approve/' + rowId)
    })
}

//On click event on reject user button
var rejectButtons = document.getElementsByClassName("btn-reject");
for (var i = 0; i < rejectButtons.length; i++) {
    rejectButtons[i].addEventListener("click", function (ev) {
        var $btn = $(this);
        var rowId = $btn.attr('data-id');
        console.log('reject',rowId);
        document.getElementById("reject-btn").setAttribute('href', '/user/reject/' + rowId)
    })
}

//On click event on delete post button
var deleteButtons = document.getElementsByClassName("btn-delete");
for (var i = 0; i < deleteButtons.length; i++) {
    deleteButtons[i].addEventListener("click", function (ev) {
        var $btn = $(this);
        var rowId = $btn.attr('data-id');
        console.log('delete',rowId);
        document.getElementById("delete-form").setAttribute('action', '/delete-post');
        document.getElementById("id-container").setAttribute('value', rowId)
    })
}

// Flutterwave Payment (Monthly Dues)
const API_publicKey = "FLWPUBK_TEST-a55cebaec65ef9f1cc6d77db8ff35c22-X";
const MONTHLY_DUES = 2000;

/*<![CDATA[*/
    // const user = {"email": [[${email}]], "phoneNumber": [[${phoneNumber}]], "amount": [[${monthlyDueAmount}]]};




// function payWithRave(customerDetails) {
//     console.log(customerDetails)
//     // console.log(user)
//
//     const {email, phoneNumber, amount} = JSON.parse(customerDetails);
//     console.log(email, phoneNumber, amount)
//     let payment = getpaidSetup({
//         PBFPubKey: API_publicKey,
//         customer_email: email,
//         amount: amount,
//         customer_phone: phoneNumber,
//         currency: "NGN",
//         txref: "rave-nba-dues-"+Date.now().valueOf()+"",
//         meta: [{
//             metaname: "flightID",
//             metavalue: "AP1234"
//         }],
//         redirect_url: "/make-payment?amount="+amount+"&email="+email,
//         onclose: function () {
//         },
//         callback: function (response) {
//             const txref = response.data.txRef; // collect txRef returned and pass to a                    server page to complete status check.
//             console.log("This is the response returned after a charge", response);
//             if (
//                 response.data.chargeResponseCode == "00" ||
//                 response.data.chargeResponseCode == "0"
//             ) {
//                 // redirect to a success page
//                 // fetch("/make-payment",{
//                 //     method: "POST",
//                 //     body: {"paymentRef": txref, "userEmail": email, "amount": amount, "paymentStatus": "COMPLETED"}
//                 // })
//                 //     .then(resp => console.log(resp))
//                 //     .catch(err => console.log(err))
//             } else {
//                 // redirect to a failure page.
//             }
//             payment.close(); // use this to close the modal immediately after payment.
//         }
//     });
// }

/*]]>*/