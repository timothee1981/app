/* check username */
const username = document.getElementById("username");
username.addEventListener("input", function () {
    let usernameInput = username.value;
    let usernameCheck = window.location.pathname + `/u_check?username=${usernameInput}`;
    const re = /^[a-zA-Z0-9_-]{3,15}$/
    if(!re.test(usernameInput)) {
        console.log("komt ie hier dan");
        setUsernameNotAvailable("Between 3 and 15 numbers and letters");
        username.classList.add("isInvalid");
        username.classList.remove("isValid");
    } else {
        fetch(usernameCheck)
            .then((response) => {
                if (!response.ok) {
                    throw new Error("Response error");
                }
                return response.json();
            })
            .then((data) => {
                if (data) {
                    hideUsernameNotAvailable();
                    username.classList.add("isValid");
                    username.classList.remove("isInvalid");
                } else {
                    setUsernameNotAvailable("Choose another username");
                    username.classList.add("isInvalid");
                    username.classList.remove("isValid");
                }
            })
            .catch((error) => {
                console.log(error);
            })
    }
});

/* Check password */
const password = document.getElementById("password");
password.addEventListener('input', function () {
    let password = document.getElementById('password');
    let passwordInput = password.value;
    const re = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!"#$%&'()*+,\-./:;<=>?@^_`{|}~\[\]])[A-Za-z\d!"#$%&'()*+,\-./:;<=>?@^_`{|}~\[\]]{10,100}$/;

    if (re.test(passwordInput)) {
        password.classList.add("isValid");
        password.classList.remove("isInvalid")
    } else {
        password.classList.add("isInvalid");
        password.classList.remove("isValid")
    }
});


/* Check firstName */
const firstName = document.getElementById("firstName");
firstName.addEventListener('input', function () {
    let firstName = document.getElementById('firstName');
    let firstNameInput = firstName.value;
    const re = /^[^\s].*[a-zA-Z-'\s?][^.]{1,100}/;

    if (re.test(firstNameInput)) {
        firstName.classList.add("isValid");
        firstName.classList.remove("isInvalid");
    } else {
        firstName.classList.add("isInvalid");
        firstName.classList.remove("isValid");
    }
});


/* Check lastName */
const lastName = document.getElementById("lastName");
lastName.addEventListener('input', function () {
    let lastName = document.getElementById('lastName');
    let lastNameInput = lastName.value;
    const re = /^[^\s].*[a-zA-Z-'\s?][^.]{1,100}/;

    if (re.test(lastNameInput)) {
        lastName.classList.add("isValid");
        lastName.classList.remove("isInvalid");
    } else {
        lastName.classList.add("isInvalid");
        lastName.classList.remove("isValid");
    }
});


/* Check email */
const email = document.getElementById("email");
email.addEventListener('input', function () {
    let email = document.getElementById('email');
    let emailInput = email.value;
    const re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/

    if (re.test(emailInput)) {
        document.getElementById("InvalidEmail").style.display = "none";
        email.classList.add("isValid");
        email.classList.remove("isInvalid")
    } else {
        document.getElementById("InvalidEmail").style.display = "inline";
        email.classList.add("isInvalid");
        email.classList.remove("isValid")
    }
});


/* Check Phone Number */
const phoneNumber = document.getElementById("phoneNumber");
phoneNumber.addEventListener('input', function () {
    let phoneNumber = document.getElementById('phoneNumber');
    let phoneNumberInput = phoneNumber.value;
    console.log("input :" + phoneNumberInput);
    const re = /^(((0)[1-9]{2}[0-9][-]?[1-9][0-9]{5})|((\\+31|0|0031)[1-9][0-9][-]?[1-9][0-9]{6}))$|^(((\\+31|0|0031)6){1}[1-9]{1}[0-9]{7})$/;
    phoneNumberInput.replace(/ /g,'');
    if (re.test(phoneNumberInput)) {
        document.getElementById("InvalidPhoneNumber").style.display = "none";
        phoneNumber.classList.add("isValid");
        phoneNumber.classList.remove("isInvalid");
    } else {
        document.getElementById("InvalidPhoneNumber").style.display = "inline";
        phoneNumber.classList.add("isInvalid");
        phoneNumber.classList.remove("isValid");
    }
});


/* Check BSN */
const BSN = document.getElementById("BSN");
BSN.addEventListener("input", function () {
    let BSNInput = BSN.value;
    let BSNCheck = window.location.pathname + `/b_check?BSN=${BSNInput}`;
    const re = /^[0-9]{9}$/

    if(!re.test(BSNInput)){
        setUBSNNotAvailable("Must be 9 numbers")
        BSN.classList.add("isInvalid");
        BSN.classList.remove("isValid")

    } else {
        fetch(BSNCheck)
            .then((response) => {
                if (!response.ok) {
                    throw new Error("Response error");
                }
                return response.json();
            })
            .then((data) => {
                if (data === true) {
                    hideBSNNotAvailable();
                    BSN.classList.add("isValid");
                    BSN.classList.remove("isInvalid")
                } else {
                    setUBSNNotAvailable("Invalid BSN number");
                    BSN.classList.add("isInvalid");
                    BSN.classList.remove("isValid")
                }
            })
            .catch((error) => {
                console.log(error);
            })
    }
});


/* Check huisnummer */
const houseNumber = document.getElementById("houseNumber");
houseNumber.addEventListener('input', function () {
    let houseNumber = document.getElementById('houseNumber');
    let houseNumberInput = houseNumber.value;
    const re = /^[0-9]{1,6}([-]\d{1,5})?$/;

    if (re.test(houseNumberInput)) {
        houseNumber.classList.add("isValid");
        houseNumber.classList.remove("isInvalid");
    } else {
        houseNumber.classList.add("isInvalid");
        houseNumber.classList.remove("isValid");
    }
});

/* Check city */
const city = document.getElementById("city");
city.addEventListener('input', function () {
    let city = document.getElementById('city');
    let cityInput = city.value;
    const re = /^[a-zA-Z]+(?:[\s-][a-zA-Z]+)*$/;
    console.log(re.test(cityInput));

    if (re.test(cityInput)) {
        city.classList.add("isValid");
        city.classList.remove("isInvalid");
    } else {
        city.classList.add("isInvalid");
        city.classList.remove("isValid");
    }
});


/* Check postal code */
const postalCode = document.getElementById("postalCode");
postalCode.addEventListener('input', function () {
    let postalCode = document.getElementById('postalCode');
    let postalCodeInput = postalCode.value;
    const re = /^[1-9][0-9]{3} ?(?!sa|SA|Sa|sA|sd|SD|sD|Sd|ss|SS|sS|sS)[a-zA-Z]{2}$/;
    console.log(re.test(postalCodeInput));

    if (re.test(postalCodeInput)) {
        postalCode.classList.add("isValid");
        postalCode.classList.remove("isInvalid");
    } else {
        postalCode.classList.add("isInvalid");
        postalCode.classList.remove("isValid");
    }
});

/* enable/disable submit button */
const form = document.getElementById("form");
form.addEventListener('keyup', function () {
    if(password.classList.contains("isValid") &&
        email.classList.contains("isValid") &&
        firstName.classList.contains("isValid") &&
        lastName.classList.contains("isValid") &&
        email.classList.contains("isValid") &&
        phoneNumber.classList.contains("isValid") &&
        houseNumber.classList.contains("isValid") &&
        city.classList.contains("isValid") &&
        postalCode.classList.contains("isValid") &&
        username.classList.contains("isValid") &&
        BSN.classList.contains("isValid")
    ){
        document.getElementById("submitButton").disabled = false;
    } else {
        document.getElementById("submitButton").disabled = true;
    }
});