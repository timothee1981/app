/* check username */
let username = document.getElementById("username");
username.addEventListener("input", function () {
    let usernameInput = username.value;
    let usernameCheck = window.location.pathname + `/u_check?username=${usernameInput}`;
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
                showUsernameNotAvailable();
                username.classList.add("isInvalid");
                username.classList.remove("isValid");
            }
        })
        .catch((error) => {
            console.log(error);
        })
});

/* Check password */
const password = document.getElementById("password");
password.addEventListener('input', function () {
    let password = document.getElementById('password');
    let passwordInput = password.value;
    const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{10,}$/;

    if (passwordRegex.test(passwordInput)) {
        password.classList.add("isValid");
        password.classList.remove("isInvalid")
    } else {
        password.classList.add("isInvalid");
        password.classList.remove("isValid")
    }
});


/* Check firstName */
let firstName = document.getElementById("firstName");
firstName.addEventListener('input', function () {
    let firstName = document.getElementById('firstName');
    let firstNameInput = firstName.value;
    const firstNameRegex = /^[^\s].*[a-zA-Z-'\s?]{1,100}/;

    if (firstNameRegex.test(firstNameInput)) {
        firstName.classList.add("isValid");
        firstName.classList.remove("isInvalid");
    } else {
        firstName.classList.add("isInvalid");
        firstName.classList.remove("isValid");
    }
});


/* Check lastName */
let lastName = document.getElementById("lastName");
lastName.addEventListener('input', function () {
    let lastName = document.getElementById('lastName');
    let lastNameInput = lastName.value;
    const lastNameRegex = /^[^\s].*[a-zA-Z-'\s?]{1,100}/;

    if (lastNameRegex.test(lastNameInput)) {
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
    const emailRegex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/

    if (emailRegex.test(emailInput)) {
        email.classList.add("isValid");
        email.classList.remove("isInvalid")
        console.log("email valid: " + emailIsValid)
    } else {
        email.classList.add("isInvalid");
        email.classList.remove("isValid")
        console.log("email valid: " + emailIsValid)
    }
});


/* Check Phone Number */
let phoneNumber = document.getElementById("phoneNumber");
phoneNumber.addEventListener('input', function () {
    let phoneNumber = document.getElementById('phoneNumber');
    let phoneNumberInput = phoneNumber.value;
    const phoneNumberRegex = /^(((0)[1-9]{2}[0-9][-]?[1-9][0-9]{5})|((\\+31|0|0031)[1-9][0-9][-]?[1-9][0-9]{6}))$|^(((\\+31|0|0031)6){1}[1-9]{1}[0-9]{7})$/;
    console.log(phoneNumberRegex.test(phoneNumberInput));

    if (phoneNumberRegex.test(phoneNumberInput)) {
        phoneNumber.classList.add("isValid");
        phoneNumber.classList.remove("isInvalid");
    } else {
        phoneNumber.classList.add("isInvalid");
        phoneNumber.classList.remove("isValid");
    }
});


/* Check BSN */
let BSN = document.getElementById("BSN");
BSN.addEventListener("input", function () {
    let BSNInput = BSN.value;
    let BSNCheck = window.location.pathname + `/b_check?BSN=${BSNInput}`;

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
            } else if (BSNInput.length === 9){
                showUBSNNotAvailable();
                BSN.classList.add("isInvalid");
                BSN.classList.remove("isValid")
            } else {
                hideBSNNotAvailable();
                BSN.classList.add("isInvalid");
                BSN.classList.remove("isValid")
            }
        })
        .catch((error) => {
            console.log(error);
        })
});


/* Check Address */
let address = document.getElementById("address");
address.addEventListener('input', function () {
    let address = document.getElementById('address');
    let addressInput = address.value;
    const addressRegex = /^([1-9][e][\s])*([a-zA-Z]+(([\.][\s])|([\s]))?)+[\s][1-9][0-9]*(([-][1-9][0-9]*)|([\s]?[a-zA-Z]+))?$/;

    if (addressRegex.test(addressInput)) {
        address.classList.add("isValid");
        address.classList.remove("isInvalid");
    } else {
        address.classList.add("isInvalid");
        address.classList.remove("isValid");
    }
});

/* Check city */
let city = document.getElementById("city");
city.addEventListener('input', function () {
    let city = document.getElementById('city');
    let cityInput = city.value;
    const cityRegex = /[A-Z']?[a-zA-Z _']+/;
    console.log(cityRegex.test(cityInput));

    if (cityRegex.test(cityInput)) {
        city.classList.add("isValid");
        city.classList.remove("isInvalid");
    } else {
        address.classList.add("isInvalid");
        city.classList.remove("isValid");
    }
});


/* Check postal code */
let postalCode = document.getElementById("postalCode");
postalCode.addEventListener('input', function () {
    let postalCode = document.getElementById('postalCode');
    let postalCodeInput = postalCode.value;
    const postalCodeRegex = /^[1-9][0-9]{3} ?(?!sa|SA|Sa|sA|sd|SD|sD|Sd|ss|SS|sS|sS)[a-zA-Z]{2}$/;
    console.log(postalCodeRegex.test(postalCodeInput));

    if (postalCodeRegex.test(postalCodeInput)) {
        postalCode.classList.add("isValid");
        postalCode.classList.remove("isInvalid");
    } else {
        address.classList.add("isInvalid");
        postalCode.classList.remove("isValid");
    }
});

/* enable/disable submit button */
const form = document.getElementById("form");
form.addEventListener('input', function () {
    if(password.classList.contains("isValid") &&
        email.classList.contains("isValid") &&
        firstName.classList.contains("isValid") &&
        lastName.classList.contains("isValid") &&
        email.classList.contains("isValid") &&
        phoneNumber.classList.contains("isValid") &&
        address.classList.contains("isValid") &&
        city.classList.contains("isValid") &&
        postalCode.classList.contains("isValid")
    ){
        document.getElementById("submitButton").disabled = false;
    } else {
        document.getElementById("submitButton").disabled = true;
    }
});





























// Show password button
function showPassword() {
    const x = document.getElementById("password");
    if (x.type === "password") {
        x.type = "text";
    } else {
        x.type = "password";
    }
}

// Show password requirements
function showPasswordRequirements() {
    const x = document.getElementById("passRequirements1of2");
    const y = document.getElementById("passRequirements2of2");
    x.style.display = "inline";
    y.style.display = "inline";
}

// Hide password requirements
function hidePasswordRequirements() {
    const x = document.getElementById("passRequirements1of2");
    const y = document.getElementById("passRequirements2of2");
    x.style.display = "none";
    y.style.display = "none";
}

// Show & hide UsernameNotAvailable
function showUsernameNotAvailable() {
    const x = document.getElementById("usernameNotAvailable");
    x.style.display = "inline";
}

function hideUsernameNotAvailable() {
    const x = document.getElementById("usernameNotAvailable");
    x.style.display = "none";
}

// Show & hide BSNNotAvailable
function showUBSNNotAvailable() {
    const x = document.getElementById("BSNNotAvailable");
    x.style.display = "inline";
}

function hideBSNNotAvailable() {
    const x = document.getElementById("BSNNotAvailable");
    x.style.display = "none";
}

// Password validation
const myInput = document.getElementById("password");
const letter = document.getElementById("letter");
const capital = document.getElementById("capital");
const number = document.getElementById("number");
const special = document.getElementById("special");
const length = document.getElementById("length");

// When the user starts to type something inside the password field
myInput.onkeyup = function() {
    // Validate lowercase letters
    let lowerCaseLetters = /[a-z]/g;
    if(myInput.value.match(lowerCaseLetters)) {
        letter.classList.remove("invalid");
        letter.classList.add("valid");

    } else {
        letter.classList.remove("valid");
        letter.classList.add("invalid");
    }

    // Validate capital letters
    let upperCaseLetters = /[A-Z]/g;
    if(myInput.value.match(upperCaseLetters)) {
        capital.classList.remove("invalid");
        capital.classList.add("valid");
    } else {
        capital.classList.remove("valid");
        capital.classList.add("invalid");
    }

    // Validate numbers
    let numbers = /[0-9]/g;
    if(myInput.value.match(numbers)) {
        number.classList.remove("invalid");
        number.classList.add("valid");
    } else {
        number.classList.remove("valid");
        number.classList.add("invalid");
    }

    // Validate special character
    let specials = /[@$!%*?&]/g;
    if(myInput.value.match(specials)) {
        special.classList.remove("invalid");
        special.classList.add("valid");
    } else {
        special.classList.remove("valid");
        special.classList.add("invalid");
    }

    // Validate length
    if(myInput.value.length >= 10) {
        length.classList.remove("invalid");
        length.classList.add("valid");
    } else {
        length.classList.remove("valid");
        length.classList.add("invalid");
    }
};