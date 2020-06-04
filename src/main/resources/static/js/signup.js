/**
 * Username veld
 *
 * Eerst wordt gekeken of het in de juist format is, daarna wordt bekene of deze al bestaat in de database
 */

const usernameField = document.getElementById("username");
usernameField.addEventListener("input", function () {
    let usernameInput = usernameField.value;
    let usernameCheck = `/api/username?username=${usernameInput}`;

    const re = /^[a-zA-Z0-9_-]{3,15}$/;

    function checkDatabase() {
        fetch(usernameCheck)
            .then((response) => {
                if (!response.ok) {
                    throw new Error("Response error");
                }
                return response.json();
            })
            .then((data) => {
                console.log("data : " + data)
                if (data) {
                    // als hij niet in de database voorkomt
                    document.getElementById("usernameNotAvailable").style.display = "none";
                    usernameField.classList.add("isValid");
                    usernameField.classList.remove("isInvalid");
                } else {
                    // als hij wel in de database voorkomt
                    setUsernameNotAvailable("Choose another username");
                    usernameField.classList.add("isInvalid");
                    usernameField.classList.remove("isValid");
                }
            })
            .catch((error) => {
                console.log(error);
            })
    }


    function setUsernameNotAvailable(String) {
        const x = document.getElementById("usernameNotAvailable");
        x.style.display = "inline";
        x.innerHTML = String;
    }


    // check usernameField input met regex zodat de server niet onnodig opgeroepen wordt
    if(!re.test(usernameInput)) {
        setUsernameNotAvailable("Between 3 and 15 numbers and letters");
        usernameField.classList.add("isInvalid");
        usernameField.classList.remove("isValid");
    } else {
        // Als hij regex doorstaat wordt via /u_check gecheckt of deze voorkomt in de database
        checkDatabase();
    }
});

/**
 * Password veld
 *
 * De passwordField heeft een show password knop zodat de gebruiker kan zien wat hij getypt heeft.
 * Zodra het veld geselecteerd wordt krijgt de gebruiker real-time te zien of de input aan alle eisen voldoet.
 * Wanneer alle requirements zijn voldaan wordt het veld "valid" gemaakt
 */

const passwordField = document.getElementById("password");
const showPasswordButton = document.getElementById("showPasswordButton");

// Show passwordField knop
function showPassword() {
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
passwordField.addEventListener('keyup', function () {
    console.log("letter classlist: " + letter.classList.contains("valid"));
    if (letter.classList.contains("valid") &&
        capital.classList.contains("valid") &&
        number.classList.contains("valid") &&
        special.classList.contains("valid") &&
        length.classList.contains("valid")
    ) {
        showPasswordButton.classList.add("isValid");
        showPasswordButton.classList.remove("isInvalid")
    } else {
        showPasswordButton.classList.add("isInvalid");
        showPasswordButton.classList.remove("isValid")
    }
});

/**
 * First en Last Name velden
 */

/* Check firstName */
const firstNameField = document.getElementById("firstName");
firstNameField.addEventListener('input', function () {
    let firstNameInput = firstNameField.value;
    const re = /^[^\s].*[a-zA-Z-'\s?][^.]{1,100}/;

    if (re.test(firstNameInput)) {
        firstNameField.classList.add("isValid");
        firstNameField.classList.remove("isInvalid");
    } else {
        firstNameField.classList.add("isInvalid");
        firstNameField.classList.remove("isValid");
    }
});

/* Check lastName */
const lastNameField = document.getElementById("lastName");
lastNameField.addEventListener('input', function () {
    let lastNameInput = lastNameField.value;
    const re = /^[^\s].*[a-zA-Z-'\s?][^.]{1,100}/;

    if (re.test(lastNameInput)) {
        lastNameField.classList.add("isValid");
        lastNameField.classList.remove("isInvalid");
    } else {
        lastNameField.classList.add("isInvalid");
        lastNameField.classList.remove("isValid");
    }
});

/**
 * Email veld
 */
/* Check email */
const emailField = document.getElementById("email");
emailField.addEventListener('input', function () {
    let emailInput = emailField.value;
    const re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/

    if (re.test(emailInput)) {
        document.getElementById("InvalidEmail").style.display = "none";
        emailField.classList.add("isValid");
        emailField.classList.remove("isInvalid")
    } else {
        document.getElementById("InvalidEmail").style.display = "inline";
        emailField.classList.add("isInvalid");
        emailField.classList.remove("isValid")
    }
});


/**
 * Phone Number veld
 */
const phoneNumberField = document.getElementById("phoneNumber");
phoneNumberField.addEventListener('input', function () {
    let phoneNumberInput = phoneNumberField.value;
    console.log("input :" + phoneNumberInput);
    const re = /^(((0)[1-9]{2}[0-9][-]?[1-9][0-9]{5})|((\\+31|0|0031)[1-9][0-9][-]?[1-9][0-9]{6}))$|^(((\\+31|0|0031)6){1}[1-9]{1}[0-9]{7})$/;
    phoneNumberInput.replace(/ /g, '');
    if (re.test(phoneNumberInput)) {
        document.getElementById("InvalidPhoneNumber").style.display = "none";
        phoneNumberField.classList.add("isValid");
        phoneNumberField.classList.remove("isInvalid");
    } else {
        document.getElementById("InvalidPhoneNumber").style.display = "inline";
        phoneNumberField.classList.add("isInvalid");
        phoneNumberField.classList.remove("isValid");
    }
});


/**
 * BSNN veld
 *
 * Eerst wordt er gecontrolleerd of de input 9 nummers bevat.
 * Als dat zo is wordt gekeken of deze voldoet aan de elfproef.
 * Wanneer dit zo is wordt in de database gekeken of deze BSN al gebruikt wordt.
 */

// checkt de input van BSN veld
const BSNField = document.getElementById("BSN");
BSNField.addEventListener("input", function () {
    let BSNInput = BSNField.value;
    let BSNCheck = `/api/bsn?BSN=${BSNInput}`;

    function checkDatabase() {
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

                    BSNField.classList.add("isValid");
                    BSNField.classList.remove("isInvalid")
                } else {
                    // als hij wel voorkomt (false)
                    setBSNNotAvailable("Enter a valid BSN");

                    BSNField.classList.add("isInvalid");
                    BSNField.classList.remove("isValid")
                }
            })
            .catch((error) => {
                console.log(error);
            })
    }


    if (BSNInput.length !== 9) {
        setBSNNotAvailable("Must be 9 numbers");

        BSNField.classList.add("isInvalid");
        BSNField.classList.remove("isValid")


    } else if (!isValidBSN(BSNInput)) {
        setBSNNotAvailable("Enter a valid BSN");
        BSNField.classList.add("isInvalid");
        BSNField.classList.remove("isValid");
    }


    else {
        checkDatabase();
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
const houseNumberField = document.getElementById("houseNumber");
houseNumberField.addEventListener('input', function () {
    let houseNumberInput = houseNumberField.value;
    const re = /^[0-9]{1,6}([-]\d{1,5})?$/;

    if (re.test(houseNumberInput)) {
        houseNumberField.classList.add("isValid");
        houseNumberField.classList.remove("isInvalid");
    } else {
        houseNumberField.classList.add("isInvalid");
        houseNumberField.classList.remove("isValid");
    }
});

/**
 * City veld
 */
const cityField = document.getElementById("city");
cityField.addEventListener('input', function () {
    let cityInput = cityField.value;
    const re = /^[a-zA-Z]+(?:[\s-][a-zA-Z]+)*$/;
    console.log(re.test(cityInput));

    if (re.test(cityInput)) {
        cityField.classList.add("isValid");
        cityField.classList.remove("isInvalid");
    } else {
        cityField.classList.add("isInvalid");
        cityField.classList.remove("isValid");
    }
});


/**
 * Postal Code veld
 */
const postalCodeField = document.getElementById("postalCode");
postalCodeField.addEventListener('input', function () {
    let postalCodeInput = postalCode.value;
    const re = /^[1-9][0-9]{3} ?(?!sa|SA|Sa|sA|sd|SD|sD|Sd|ss|SS|sS|sS)[a-zA-Z]{2}$/;
    console.log(re.test(postalCodeInput));

    if (re.test(postalCodeInput)) {
        postalCodeField.classList.add("isValid");
        postalCodeField.classList.remove("isInvalid");
    } else {
        postalCodeField.classList.add("isInvalid");
        postalCodeField.classList.remove("isValid");
    }
});

/**
 * De submit button wordt pas activeerd als alle velden juist gevuld zijn
 */
const formButton = document.getElementById("form");
formButton.addEventListener('keyup', function () {

    document.getElementById("submitButton").disabled = !(
        showPasswordButton.classList.contains("isValid") &&
        emailField.classList.contains("isValid") &&
        firstNameField.classList.contains("isValid") &&
        lastNameField.classList.contains("isValid") &&
        emailField.classList.contains("isValid") &&
        phoneNumberField.classList.contains("isValid") &&
        houseNumberField.classList.contains("isValid") &&
        cityField.classList.contains("isValid") &&
        postalCodeField.classList.contains("isValid") &&
        usernameField.classList.contains("isValid") &&
        BSNField.classList.contains("isValid"))
    ;});