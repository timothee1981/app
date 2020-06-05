/**
 * Constanten, regex & magic numbers
 */

// Alle velden en knoppen
const usernameField = document.getElementById("username");
const passwordField = document.getElementById("password");
const showPasswordButton = document.getElementById("showPasswordButton");
const firstNameField = document.getElementById("firstName");
const lastNameField = document.getElementById("lastName");
const emailField = document.getElementById("email");
const BSNField = document.getElementById("BSN");
const form = document.getElementById("form");
const houseNumberField = document.getElementById("houseNumber");
const phoneNumberField = document.getElementById("phoneNumber");
const cityField = document.getElementById("city");
const streetField = document.getElementById("street");
const postalCodeField = document.getElementById("postalCode");
const submitButton = document.getElementById("submitButton");
const NAWFields = document.getElementById("NAW");

// elementen voor password check
const passwordInput = document.getElementById("password");
const lowercase = document.getElementById("letter");
const uppercase = document.getElementById("capital");
const number = document.getElementById("number");
const special = document.getElementById("special");
const length = document.getElementById("length");

// magic numbers
const BSN_LENGTH = 9;
const MIN_PASS_LENGTH = 10;
const MAX_PASS_LENGTH = 100;
const MIN_USERNAME_LENGTH = 3;
const MAX_USERNAME_LENGTH = 15;

// regular expressions
const usernameRegex = /^[a-zA-Z0-9_-]+$/;
const emailRegex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
const namesRegex = /^[^\s].*[a-zA-Z-'\s?][^.]{1,100}/;
const phoneNumberRegex = /^(((0)[1-9]{2}[0-9][-]?[1-9][0-9]{5})|((\\+31|0|0031)[1-9][0-9][-]?[1-9][0-9]{6}))$|^(((\\+31|0|0031)6){1}[1-9]{1}[0-9]{7})$/;
const postalCodeRegex = /^[1-9][0-9]{3} ?(?!sa|SA|Sa|sA|sd|SD|sD|Sd|ss|SS|sS)[a-zA-Z]{2}$/;
const houseNumberRegex = /^[0-9]{1,6}([-]\d{1,5})?$/;
const lowerCaseLetters = /[a-z]/g;
const upperCaseLetters = /[A-Z]/g;
const numbers = /[0-9]/g;
const specials = /[!"#$%&'()*+,\-./:;<=>?@^_`{|}~\[\]]/g;

let city;
let street;

/**
 * Functies gereleteerd aan valid of invalid classes
 */

function setFieldValid(field){
    field.classList.remove("isInvalid");
    field.classList.add("isValid");
}

function setFieldInvalid(field){
    field.classList.add("isInvalid");
    field.classList.remove("isValid");
}

function isInputValid(elementId){
    return elementId.classList.contains("isValid")
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

function setValue(elementId, value){
    document.getElementById(elementId).value = value;
    setFieldValid(document.getElementById(elementId));
}

function emptyValue(elementId){
    document.getElementById(elementId).value = "";
    document.getElementById(elementId).classList.remove("isValid");
}

function showPasswordRequirements() {
    showElement("passRequirements1of2");
    showElement("passRequirements2of2");
}

function hidePasswordRequirements() {
    hideElement("passRequirements1of2");
    hideElement("passRequirements2of2");
}

function isPasswordValid(){
    return lowercase.classList.contains("valid") &&
        uppercase.classList.contains("valid") &&
        number.classList.contains("valid") &&
        special.classList.contains("valid") &&
        length.classList.contains("valid");
}

/**
 * Global functions
 */

// voor First en Last Name
function checkNameField(elementId){
    const nameField = document.getElementById(elementId);
    let nameInput = nameField.value;

    if (namesRegex.test(nameInput)) {
        setFieldValid(nameField);
    } else {
        setFieldInvalid(nameField);
    }
}

// Show passwordField knop op scherm
function showPassword() {
    if (passwordField.type === "password") {
        passwordField.type = "text";
        showPasswordButton.value = "hide";
    } else {
        passwordField.type = "password";
        showPasswordButton.value = "show"
    }
}

// 11-proef
function passCheckDigit(BSN) {
    if(BSN.length !== BSN_LENGTH){
        return false;
    }

    const firstNumbers = BSN.substring(0, BSN_LENGTH -1 );
    const lastNumber = BSN.charAt(BSN_LENGTH - 1);
    let sum = 0;
    let i;
    for (i = 0; i < firstNumbers.length; i++) {
        sum += firstNumbers.charAt(i) * (BSN.length - i);
    }
    sum += lastNumber;

    return sum % 11 === 0;
}

// haalt data uit database op via de API. Wordt gebruikt om Username en BSN te checken
function fetchApiAndRespond(url, field, elementId, message){
    fetch(url)
        .then((response) => {
            if (!response.ok) {
                throw new Error("Response error");
            }
            return response.json();
        })
        .then((data) => {
            if (data){
                setFieldValid(field);
                hideElement(elementId);
            } else{
                setFieldInvalid(field);
                showElementAndSetText(elementId, message);
            }
        })
        .catch((error) => {
            console.log(error);
        });
}


function getCityAndStreet() {
    const header = new Headers();
    let url = `https://postcode.tech/api/v1/postcode?postcode=${postalCodeField.value}&number=${houseNumberField.value}`;
    header.append('Authorization', 'Bearer ccf855f3-4bd0-4cd6-8f12-25c9e254efd2');

    return fetch(url, {
        method: 'GET',
        headers: header,
    })
        .then((response) => {
            if (!response.ok) {
                throw new Error("Response error");
            }
            return response.json();
        })
        .then((data) => {
            city = data.city;
            street = data.street;
        })
        .catch((error) => {
            console.error(error);
        });
}

/**
 * Username veld
 */
usernameField.addEventListener("input", function () {
    let usernameInput = usernameField.value;

    if(!usernameRegex.test(usernameInput) || usernameInput.length < MIN_USERNAME_LENGTH || usernameInput.length > MAX_USERNAME_LENGTH) {
        showElementAndSetText("usernameNotAvailable", "Between " + MIN_USERNAME_LENGTH + " and " + MAX_USERNAME_LENGTH + " numbers and letters");
        setFieldInvalid(usernameField);
    } else {
        let url = `/api/username?username=${usernameInput}`;
        fetchApiAndRespond(url, usernameField, "usernameNotAvailable","Choose another username" )
    }
});


/**
 * Password veld: Gebruiker ziet real-time aan welke eisen de ingevoerde password voldoet
 */
passwordInput.onkeyup = function () {
    passwordInput.value.match(lowerCaseLetters) ? setPassRequirementValid(lowercase) : setPassRequirementInvalid(lowercase);
    passwordInput.value.match(upperCaseLetters) ? setPassRequirementValid(uppercase) : setPassRequirementInvalid(uppercase);
    passwordInput.value.match(numbers) ? setPassRequirementValid(number) : setPassRequirementInvalid(number);
    passwordInput.value.match(specials) ? setPassRequirementValid(special) : setPassRequirementInvalid(special);
    passwordInput.value.length >= MIN_PASS_LENGTH && passwordInput.value.length <= MAX_PASS_LENGTH ? setPassRequirementValid(length) : setPassRequirementInvalid(length)
};

// Wanneer alle checks zijn voldaan, zet veld op valid
passwordField.addEventListener('keyup', function () {

    if (isPasswordValid()) {
        setFieldValid(showPasswordButton);
    } else {
        setFieldInvalid(showPasswordButton);
    }
});

/**
 * First en Last Name
 */
firstNameField.addEventListener('input', function () {
    checkNameField("firstName");
});

lastNameField.addEventListener('input', function() {
    checkNameField("lastName");
});

/**
 * Email veld
 */
emailField.addEventListener('input', function () {
    let emailInput = emailField.value;

    if (emailRegex.test(emailInput)) {
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

    if (phoneNumberRegex.test(phoneNumberInput)) {
        hideElement("InvalidPhoneNumber");
        setFieldValid(phoneNumberField);

    } else {
        showElement("InvalidPhoneNumber");
        setFieldInvalid(phoneNumberField)
    }
});

/**
 * BSN veld
 */
BSNField.addEventListener("input", function () {
    let BSNInput = BSNField.value;

    if (BSNInput.length !== BSN_LENGTH) {
        setFieldInvalid(BSNField);
        showElementAndSetText("BSNNotAvailable", "Must be 9 numbers");

        // 11-proef
    } else if (!passCheckDigit(BSNInput)) {
        setFieldInvalid(BSNField);
        showElementAndSetText("BSNNotAvailable", "Enter a valid BSN");

    } else {
        let url = `/api/bsn?BSN=${BSNInput}`;
        fetchApiAndRespond(url, BSNField, "BSNNotAvailable", "Enter a valid BSN" );
    }
});

/**
 * Postal Code veld
 */
postalCodeField.addEventListener('input', function () {
    let postalCodeInput = postalCodeField.value;

    if (postalCodeRegex.test(postalCodeInput)) {
        setFieldValid(postalCodeField);
    } else {
        setFieldInvalid(postalCodeField);
    }
});

/**
 * House Number veld
 */
houseNumberField.addEventListener('input', function () {
    let houseNumberInput = houseNumberField.value;

    if (houseNumberRegex.test(houseNumberInput)) {
        setFieldValid(houseNumberField);
    } else {
        setFieldInvalid(houseNumberField);
    }
});

/**
 * City en Street veld
 */
NAWFields.addEventListener('input', function(){
    if(isInputValid(postalCodeField) && isInputValid(houseNumberField)){
        getCityAndStreet().then(r => {
            if (city !== undefined) {
                setValue("city", city);
            } else {
                emptyValue("city");
            }
            if (street !== undefined) {
                setValue("street", street);
            } else {
                emptyValue("street");
            }
        })
    } else {
        emptyValue("street");
        emptyValue("city");
    }
});


/**
 * Sign Up knop
 */
form.addEventListener('keyup', function () {

    // als een veld niet valid is, disable knop
    submitButton.disabled =
        !isInputValid(usernameField) || !isInputValid(emailField) ||
        !isInputValid(showPasswordButton) || !isInputValid(phoneNumberField) ||
        !isInputValid(firstNameField) || !isInputValid(lastNameField) ||
        !isInputValid(houseNumberField) || !isInputValid(cityField) ||
        !isInputValid(streetField) || !isInputValid(postalCodeField) ||
        !isInputValid(BSNField)
});