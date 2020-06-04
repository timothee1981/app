// alle velden
const usernameField = document.getElementById("username");
const passwordField = document.getElementById("password");
const showPasswordButton = document.getElementById("showPasswordButton");
const nameFields = document.getElementById("names");
const firstNameField = document.getElementById("firstName");
const lastNameField = document.getElementById("lastName");
const emailField = document.getElementById("email");
const BSNField = document.getElementById("BSN");
const formButton = document.getElementById("form");
const houseNumberField = document.getElementById("houseNumber");
const phoneNumberField = document.getElementById("phoneNumber");
const cityField = document.getElementById("city");
const postalCodeField = document.getElementById("postalCode");
const submitButton = document.getElementById("submitButton");

// elementen voor password check
const passwordInput = document.getElementById("password");
const letter = document.getElementById("letter");
const capital = document.getElementById("capital");
const number = document.getElementById("number");
const special = document.getElementById("special");
const length = document.getElementById("length");

// global functies
function setFieldValid(field){
    field.classList.add("isValid");
    field.classList.remove("isInvalid")
}

function setFieldInvalid(field){
    field.classList.add("isInvalid");
    field.classList.remove("isValid")
}

function setPassRequirementValid(requirement){
    requirement.classList.remove("invalid");
    requirement.classList.add("valid");
}

function setPassRequirementInvalid(requirement){
    requirement.classList.remove("valid");
    requirement.classList.add("invalid");
}

function hideElement(elementId){
    document.getElementById(elementId).style.display = 'none';
}

function showElement(elementId){
    document.getElementById(elementId).style.display = "inline";
}

function showElementAndSetText(elementId, String){
    document.getElementById(elementId).style.display = 'inline';
    document.getElementById(elementId).innerHTML = String;
}

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

// voor focus in/out in html
function showPasswordRequirements() {
    showElement("passRequirements1of2");
    showElement("passRequirements2of2");
}

function hidePasswordRequirements() {
    hideElement("passRequirements1of2");
    hideElement("passRequirements2of2");
}

// wordt gebruikt voor first en last name
function checkNameField(elementId){
    const nameField = document.getElementById(elementId);
    let nameInput = nameField.value;

    const re = /^[^\s].*[a-zA-Z-'\s?][^.]{1,100}/;

    if (re.test(nameInput)) {
        setFieldValid(nameField);
    } else {
        setFieldInvalid(nameField);
    }

}

// elfproef, wordt gebruikt door BSN check
function isValidBSN(BSN) {
    if(BSN.length !== 9){
        return false;
    }

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

/**
 * Username veld
 *
 * Eerst wordt gekeken of het in de juist format is, daarna wordt bekene of deze al bestaat in de database
 */
usernameField.addEventListener("input", function () {
    let usernameInput = usernameField.value;
    let usernameCheck = `/api/username?username=${usernameInput}`;

    const re = /^[a-zA-Z0-9_-]{3,15}$/;

    // Maak koppeling met db voor check of username correct en uniek is
    function checkDatabase() {
        fetch(usernameCheck)
            .then((response) => {
                if (!response.ok) {
                    throw new Error("Response error");
                }
                return response.json();
            })
            .then((data) => {
                console.log("data : " + data);
                if (data) {
                    // als hij niet in de database voorkomt
                    hideElement("usernameNotAvailable");
                    setFieldValid(usernameField);
                } else {
                    // als hij wel in de database voorkomt
                    showElementAndSetText("usernameNotAvailable", "Choose another username");
                    setFieldInvalid(usernameField);
                }
            })
            .catch((error) => {
                console.log(error);
            })
    }

    if(!re.test(usernameInput)) {
        showElementAndSetText("usernameNotAvailable", "Between 3 and 15 numbers and letters");
        setFieldInvalid(usernameField);
    } else {
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
passwordInput.onkeyup = function () {

    let lowerCaseLetters = /[a-z]/g;
    if (passwordInput.value.match(lowerCaseLetters)) {
        setPassRequirementValid(letter);
    } else {
        setPassRequirementInvalid(letter)
    }

    let upperCaseLetters = /[A-Z]/g;
    if (passwordInput.value.match(upperCaseLetters)) {
        setPassRequirementValid(capital);
    } else {
        setPassRequirementInvalid(capital);
    }

    let numbers = /[0-9]/g;
    if (passwordInput.value.match(numbers)) {
        setPassRequirementValid(number);
    } else {
        setPassRequirementInvalid(number);
    }

    let specials = /[!"#$%&'()*+,\-./:;<=>?@^_`{|}~\[\]]/g;
    if (passwordInput.value.match(specials)) {
        setPassRequirementValid(special);
    } else {
        setPassRequirementInvalid(special);
    }

    if (passwordInput.value.length >= 10 && passwordInput.value.length <= 100) {
        setPassRequirementValid(length);
    } else {
        setPassRequirementInvalid(length);
    }
};

// Wanneer alle checks zijn voldaan, zet veld op valid
passwordField.addEventListener('keyup', function () {
    console.log("letter classlist: " + letter.classList.contains("valid"));
    if (letter.classList.contains("valid") &&
        capital.classList.contains("valid") &&
        number.classList.contains("valid") &&
        special.classList.contains("valid") &&
        length.classList.contains("valid"))
    {
        setFieldValid(showPasswordButton);

    } else {
        setFieldInvalid(showPasswordButton);
    }
});

/**
 * First en Last Name
 */
nameFields.addEventListener('input', function () {
    checkNameField("firstName");
    checkNameField("lastName");
});

/**
 * Email veld
 */
emailField.addEventListener('input', function () {
    let emailInput = emailField.value;
    const re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

    if (re.test(emailInput)) {
        hideElement("InvalidEmail");
        setFieldValid(emailField);
    } else {
        showElement("InvalidEmail");
        setFieldInvalid(emailField);
    }
});


/**
 * Phone Number veld
 */
phoneNumberField.addEventListener('input', function () {
    let phoneNumberInput = phoneNumberField.value;
    console.log("input :" + phoneNumberInput);
    const re = /^(((0)[1-9]{2}[0-9][-]?[1-9][0-9]{5})|((\\+31|0|0031)[1-9][0-9][-]?[1-9][0-9]{6}))$|^(((\\+31|0|0031)6){1}[1-9]{1}[0-9]{7})$/;
    phoneNumberInput.replace(/ /g, '');
    if (re.test(phoneNumberInput)) {
        hideElement("InvalidPhoneNumber");
        setFieldValid(phoneNumberField);

    } else {
        showElement("InvalidPhoneNumber");
        setFieldInvalid(phoneNumberField)
    }
});
/**
 * BSN veld
 *
 * Eerst wordt er gecontrolleerd of de input 9 nummers bevat.
 * Als dat zo is wordt gekeken of deze voldoet aan de elfproef.
 * Wanneer dit zo is wordt in de database gekeken of deze BSN al gebruikt wordt.
 */
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
                    hideElement("BSNNotAvailable");
                    setFieldValid(BSNField);
                } else {
                    // als hij wel voorkomt (false)
                    showElementAndSetText("BSNNotAvailable", "Enter a valid BSN");
                    setFieldInvalid(BSNField);
                }
            })
            .catch((error) => {
                console.log(error);
            })
    }


    if (BSNInput.length !== 9) {
        showElementAndSetText("BSNNotAvailable", "Must be 9 numbers");
        setFieldInvalid(BSNField);


    } else if (!isValidBSN(BSNInput)) {
        showElementAndSetText("BSNNotAvailable", "Enter a valid BSN");
        setFieldInvalid(BSNField);
    }

    else {
        checkDatabase();
    }
});

/**
 * House Number veld
 */
houseNumberField.addEventListener('input', function () {
    let houseNumberInput = houseNumberField.value;
    const re = /^[0-9]{1,6}([-]\d{1,5})?$/;

    if (re.test(houseNumberInput)) {
        setFieldValid(houseNumberField);
    } else {
        setFieldInvalid(houseNumberField);
    }
});

/**
 * City veld
 */
cityField.addEventListener('input', function () {
    let cityInput = cityField.value;
    const re = /^[a-zA-Z]+(?:[\s-][a-zA-Z]+)*$/;

    if (re.test(cityInput)) {
        setFieldValid(cityField);
    } else {
        setFieldInvalid(cityField);
    }
});


/**
 * Postal Code veld
 */
postalCodeField.addEventListener('input', function () {
    let postalCodeInput = postalCodeField.value;
    const re = /^[1-9][0-9]{3} ?(?!sa|SA|Sa|sA|sd|SD|sD|Sd|ss|SS|sS)[a-zA-Z]{2}$/;
    console.log(re.test(postalCodeInput));

    if (re.test(postalCodeInput)) {
        setFieldValid(postalCodeField);
    } else {
        setFieldInvalid(postalCodeField);
    }
});

/**
 * De submit button wordt op disable gezet wanneer nog niet alle klasses goed ingevuld zijn
 */
formButton.addEventListener('keyup', function () {

    function isAllInputValid() {
        return showPasswordButton.classList.contains("isValid") &&
            emailField.classList.contains("isValid") &&
            firstNameField.classList.contains("isValid") &&
            lastNameField.classList.contains("isValid") &&
            emailField.classList.contains("isValid") &&
            phoneNumberField.classList.contains("isValid") &&
            houseNumberField.classList.contains("isValid") &&
            cityField.classList.contains("isValid") &&
            postalCodeField.classList.contains("isValid") &&
            usernameField.classList.contains("isValid") &&
            BSNField.classList.contains("isValid");
    }

    submitButton.disabled = !isAllInputValid()
    ;});