/**
 * Username veld
 *
 * Eerst wordt gekeken of het in de juist format is, daarna wordt bekene of deze al bestaat in de database
 */

const username = document.getElementById("username");
username.addEventListener("input", function () {
    let usernameInput = username.value;
    let usernameCheck = window.location.hostname + `/api/username?username=${usernameInput}`;
    console.log(usernameCheck);
    const re = /^[a-zA-Z0-9_-]{3,15}$/;

    // check username input met regex zodat de server niet onnodig opgeroepen wordt
    if(!re.test(usernameInput)) {
        setUsernameNotAvailable("Between 3 and 15 numbers and letters");
        username.classList.add("isInvalid");
        username.classList.remove("isValid");
    } else {
        // Als hij regex doorstaat wordt via /u_check gecheckt of deze voorkomt in de database
        fetch(usernameCheck)
            .then((response) => {
                if (!response.ok) {
                    throw new Error("Response error");
                }
                return response.json();
            })
            .then((data) => {
                if (data) {
                    // als hij niet in de database voorkomt
                    document.getElementById("usernameNotAvailable").style.display = "none";
                    username.classList.add("isValid");
                    username.classList.remove("isInvalid");
                } else {
                    // als hij wel in de database voorkomt
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

function setUsernameNotAvailable(String) {
    const x = document.getElementById("usernameNotAvailable");
    x.style.display = "inline";
    x.innerHTML = String;
}

/**
 * Password veld
 *
 * De password veld heeft een show password knop zodat de gebruiker kan zien wat hij getypt heeft.
 * Zodra het veld geselecteerd wordt krijgt de gebruiker real-time te zien of de input aan alle eisen voldoet.
 * Wanneer alle requirements zijn voldaan wordt het veld "valid" gemaakt
 */

// Show password knop
function showPassword() {
    const passwordField = document.getElementById("password");
    const button = document.getElementById("showPasswordButton");
    if (passwordField.type === "password") {
        passwordField.type = "text";
        button.value = "hide";
    } else {
        passwordField.type = "password";
        button.value = "show"
    }
}

// Wanneer veld geselecteerd is, laat requirements zien
function showPasswordRequirements() {
    const left = document.getElementById("passRequirements1of2");
    const right = document.getElementById("passRequirements2of2");
    left.style.display = "inline";
    right.style.display = "inline";
}

// Bij focus out, hide veld
function hidePasswordRequirements() {
    const x = document.getElementById("passRequirements1of2");
    const y = document.getElementById("passRequirements2of2");
    x.style.display = "none";
    y.style.display = "none";
}

/*
Validatie check van Password
*/

// Declaratie alle requirements
const myInput = document.getElementById("password");
const letter = document.getElementById("letter");
const capital = document.getElementById("capital");
const number = document.getElementById("number");
const special = document.getElementById("special");
const length = document.getElementById("length");

// Wanneer de gebruiker typt, kijk of een requirement wordt voldaan
myInput.onkeyup = function () {
    // Validate lowercase letters
    let lowerCaseLetters = /[a-z]/g;
    if (myInput.value.match(lowerCaseLetters)) {
        letter.classList.remove("invalid");
        letter.classList.add("valid");

    } else {
        letter.classList.remove("valid");
        letter.classList.add("invalid");
    }

    // Validate capital letters
    let upperCaseLetters = /[A-Z]/g;
    if (myInput.value.match(upperCaseLetters)) {
        capital.classList.remove("invalid");
        capital.classList.add("valid");
    } else {
        capital.classList.remove("valid");
        capital.classList.add("invalid");
    }

    // Validate numbers
    let numbers = /[0-9]/g;
    if (myInput.value.match(numbers)) {
        number.classList.remove("invalid");
        number.classList.add("valid");
    } else {
        number.classList.remove("valid");
        number.classList.add("invalid");
    }

    // Validate special character
    let specials = /[!"#$%&'()*+,\-./:;<=>?@^_`{|}~\[\]]/g;
    if (myInput.value.match(specials)) {
        special.classList.remove("invalid");
        special.classList.add("valid");
    } else {
        special.classList.remove("valid");
        special.classList.add("invalid");
    }

        // Validate length
    if (myInput.value.length >= 10 && myInput.value.length <= 100) {
        length.classList.remove("invalid");
        length.classList.add("valid");
    } else {
        length.classList.remove("valid");
        length.classList.add("invalid");
    }
};

// Wanneer alle checks zijn voldaan, zet veld op valid
const password = document.getElementById("password");
password.addEventListener('keyup', function () {
    if (letter.classList.contains("valid") &&
        capital.classList.contains("valid") &&
        number.classList.contains("valid") &&
        special.classList.contains("valid") &&
        length.classList.contains("valid")
    ) {
        password.classList.add("isValid");
        password.classList.remove("isInvalid")
    } else {
        password.classList.add("isInvalid");
        password.classList.remove("isValid")
    }
});

/**
 * First en Last Name velden
 */

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

/**
 * Email veld
 */
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


/**
 * Phone Number veld
 */
const phoneNumber = document.getElementById("phoneNumber");
phoneNumber.addEventListener('input', function () {
    let phoneNumber = document.getElementById('phoneNumber');
    let phoneNumberInput = phoneNumber.value;
    console.log("input :" + phoneNumberInput);
    const re = /^(((0)[1-9]{2}[0-9][-]?[1-9][0-9]{5})|((\\+31|0|0031)[1-9][0-9][-]?[1-9][0-9]{6}))$|^(((\\+31|0|0031)6){1}[1-9]{1}[0-9]{7})$/;
    phoneNumberInput.replace(/ /g, '');
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


/**
 * BSN veld
 *
 * Eerst wordt er gecontrolleerd of de input 9 nummers bevat.
 * Als dat zo is wordt gekeken of deze voldoet aan de elfproef.
 * Wanneer dit zo is wordt in de database gekeken of deze BSN al gebruikt wordt.
 */

// checkt de input van BSN veld
const BSN = document.getElementById("BSN");
BSN.addEventListener("input", function () {
    let BSNInput = BSN.value;
    let BSNCheck = window.location.hostname + `/api/bsn?BSN=${BSNInput}`;

    // check of input 9 getallen bevat
    if (BSNInput.length !== 9) {
        setBSNNotAvailable("Must be 9 numbers");

        BSN.classList.add("isInvalid");
        BSN.classList.remove("isValid")

    // Bevat 9 letters, check nu of het de 11-proef doorstaat, zo niet:
    } else if (!isValidBSN(BSNInput)) {
        setBSNNotAvailable("Enter a valid BSN");
        BSN.classList.add("isInvalid");
        BSN.classList.remove("isValid");
    }

    // Wanneer het de 11-proef doorstaat, check of het voorkomt in de database
    else {
        fetch(BSNCheck)
            .then((response) => {
                if (!response.ok) {
                    throw new Error("Response error");
                }
                return response.json();
            })

            // data komt terug van de fetch
            .then((data) => {
                if (data === true) {
                    // als hij niet voorkomt (true)
                    hideBSNNotAvailable();

                    BSN.classList.add("isValid");
                    BSN.classList.remove("isInvalid")
                } else {
                    // als hij wel voorkomt (false)
                    setBSNNotAvailable("Enter a valid BSN");

                    BSN.classList.add("isInvalid");
                    BSN.classList.remove("isValid")
                }
            })
            .catch((error) => {
                console.log(error);
            })
    }
});

/* elfproef, wordt gebruikt door BSN check*/
function isValidBSN(BSN) {
    const firstNumbers = BSN.substring(0, 8);
    const lastNumber = BSN.charAt(8);
    let sum = 0;
    let i;
    for (i = 0; i < firstNumbers.length; i++) {
        sum += firstNumbers.charAt(i) * (BSN.length - i);
    }
    sum += lastNumber;

    return sum % 11 === 0;
}

// Geef error dat BSN niet gebruikbaar is
function setBSNNotAvailable(String) {
    const x = document.getElementById("BSNNotAvailable");
    x.style.display = "inline";
    x.innerHTML = String;
}

// Verwijder error notificatie
function hideBSNNotAvailable() {
    const x = document.getElementById("BSNNotAvailable");
    x.style.display = "none";
}

/**
 * House Number veld
 */
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

/**
 * City veld
 */
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


/**
 * Postal Code veld
 */
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

/**
 * De submit button wordt pas activeerd als alle velden juist gevuld zijn
 */
const form = document.getElementById("form");
form.addEventListener('keyup', function () {
    document.getElementById("submitButton").disabled = !(password.classList.contains("isValid") &&
        email.classList.contains("isValid") &&
        firstName.classList.contains("isValid") &&
        lastName.classList.contains("isValid") &&
        email.classList.contains("isValid") &&
        phoneNumber.classList.contains("isValid") &&
        houseNumber.classList.contains("isValid") &&
        city.classList.contains("isValid") &&
        postalCode.classList.contains("isValid") &&
        username.classList.contains("isValid") &&
        BSN.classList.contains("isValid"));});