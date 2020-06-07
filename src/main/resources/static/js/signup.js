/**
 * Constanten, regex & magic numbers
 */

// Alle velden en knoppen
const USERNAME_FIELD = document.getElementById("username");
const PASSWORD_FIELD = document.getElementById("password");
const SHOW_PASSWORD_BUTTON = document.getElementById("showPasswordButton");
const FIRST_NAME_FIELD = document.getElementById("firstName");
const LAST_NAME_FIELD = document.getElementById("lastName");
const EMAIL_FIELD = document.getElementById("email");
const BSN_FIELD = document.getElementById("BSN");
const FORM = document.getElementById("form");
const HOUSE_NUMBER_FIELD = document.getElementById("houseNumber");
const PHONE_NUMBER_FIELD = document.getElementById("phoneNumber");
const CITY_FIELD = document.getElementById("city");
const STREET_FIELD = document.getElementById("street");
const POSTAL_CODE_FIELD = document.getElementById("postalCode");
const SUBMIT_BUTTON = document.getElementById("SUBMIT_BUTTON");
const ADDRESS_FIELDS = document.getElementById("addressFields");

const POST_CODE_API_TOKEN = "ccf855f3-4bd0-4cd6-8f12-25c9e254efd2";

// magic numbers
const BSN_LENGTH = 9;
const MIN_PASS_LENGTH = 10;
const MAX_PASS_LENGTH = 100;
const MIN_USERNAME_LENGTH = 3;
const MAX_USERNAME_LENGTH = 20;

// messages
const USERNAME_NOT_AVAILABLE = "Choose another username";
const USERNAME_IS_INVALID = "Between " + MIN_USERNAME_LENGTH + " and " + MAX_USERNAME_LENGTH + " letters and numbers";
const BSN_INCORRECT_LENGTH = "Enter " + BSN_LENGTH + " numbers";
const BSN_IS_INVALID = "Enter a valid BSN";

// elementen voor password check
const LOWERCASE_REQ = document.getElementById("letter");
const UPPERCASE_REQ = document.getElementById("capital");
const NUMBER_REQ = document.getElementById("number");
const SPECIAL_REQ = document.getElementById("special");
const LENGTH_REQ = document.getElementById("length");

// regular expressions
const USERNAME_REGEX = /^[a-zA-Z0-9_-]+$/;
const EMAIL_REGEX = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
const NAMES_REGEX = /^[^\s]*[a-zA-Z\s,.'\-][^\s]{1,100}$/;
const PHONE_NUMBER_REGEX = /^(((0)[1-9]{2}[0-9][-]?[1-9][0-9]{5})|((\\+31|0|0031)[1-9][0-9][-]?[1-9][0-9]{6}))$|^(((\\+31|0|0031)6){1}[1-9]{1}[0-9]{7})$/;
const POSTAL_CODE_REGEX = /^[1-9][0-9]{3} ?(?!sa|SA|Sa|sA|sd|SD|sD|Sd|ss|SS|sS)[a-zA-Z]{2}$/;
const LOWERCASE_REGEX = /[a-z]/g;
const UPPERCASE_REGEX = /[A-Z]/g;
const NUMBERS_REGEX = /[0-9]/g;
const SPECIALS_REGEX = /[!"#$%&'()*+,\-./:;<=>?@^_`{|}~\[\]]/g;

// returns van APIs
let city;
let street;
let apiResponse;

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

function removeValidation(field){
    if(field.classList.contains("isValid")){
        field.classList.remove("isValid");
    }

    if(field.classList.contains("isInvalid")){
        field.classList.remove("isInvalid");
    }
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

// worden gebruikt in HTML focusin/out
function showPasswordRequirements() {
    showElement("passRequirements1of2");
    showElement("passRequirements2of2");
}

function hidePasswordRequirements() {
    hideElement("passRequirements1of2");
    hideElement("passRequirements2of2");
}

function isPasswordValid(){
    return LOWERCASE_REQ.classList.contains("valid") &&
        UPPERCASE_REQ.classList.contains("valid") &&
        NUMBER_REQ.classList.contains("valid") &&
        SPECIAL_REQ.classList.contains("valid") &&
        LENGTH_REQ.classList.contains("valid");
}

/**
 * Global functions
 */

// voor First en Last Name
function checkNameField(elementId){
    const nameField = document.getElementById(elementId);
    let nameInput = nameField.value;

    if (NAMES_REGEX.test(nameInput)) {
        setFieldValid(nameField);
    } else {
        setFieldInvalid(nameField);
    }
}

// Show PASSWORD_FIELD knop op scherm
function showPassword() {
    if (PASSWORD_FIELD.type === "password") {
        PASSWORD_FIELD.type = "text";
        SHOW_PASSWORD_BUTTON.value = "hide";
    } else {
        PASSWORD_FIELD.type = "password";
        SHOW_PASSWORD_BUTTON.value = "show"
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
function fetchApiResponse(url){
    return fetch(url, {
        method: 'GET',
    })
        .then((response) => {
            if (!response.ok) {
                throw new Error("Response error");
            }
            return response.json();
        })
        .then((data) => {
            apiResponse = data;
        })
        .catch((error) => {
            console.log(error);
        });
}


function getCityAndStreet() {
    const header = new Headers();
    let url = `https://postcode.tech/api/v1/postcode?postcode=${POSTAL_CODE_FIELD.value}&number=${HOUSE_NUMBER_FIELD.value}`;
    header.append('Authorization', 'Bearer ' + POST_CODE_API_TOKEN);

    return fetch(url, {
        method: 'GET',
        headers: header,
    })
        .then((response) => {
            if (!response.ok) {
                city = null;
                street = null;
                throw new Error("Response error");
            }
            return response.json();
        })
        .then((data) => {
                city = data.city;
                street = data.street;
        })
        .catch((error) => {
            console.log(error);
        });
}

/**
 * Username veld
 */
USERNAME_FIELD.addEventListener("input", function () {
    let usernameInput = USERNAME_FIELD.value;

    if(!USERNAME_REGEX.test(usernameInput) || usernameInput.length < MIN_USERNAME_LENGTH || usernameInput.length > MAX_USERNAME_LENGTH) {
        showElementAndSetText("usernameNotAvailable", USERNAME_IS_INVALID);
        setFieldInvalid(USERNAME_FIELD);
    } else {
        let url = `/api/username?username=${usernameInput}`;

        fetchApiResponse(url).then(r => {
            if (apiResponse) {
                setFieldValid(USERNAME_FIELD);
                hideElement("usernameNotAvailable");
            } else {
                setFieldInvalid(USERNAME_FIELD);
                showElementAndSetText("usernameNotAvailable", USERNAME_NOT_AVAILABLE);
            }
        })
    }
});


/**
 * Password veld: Gebruiker ziet real-time aan welke eisen de ingevoerde password voldoet
 */
PASSWORD_FIELD.onkeyup = function () {
    PASSWORD_FIELD.value.match(LOWERCASE_REGEX) ? setPassRequirementValid(LOWERCASE_REQ) : setPassRequirementInvalid(LOWERCASE_REQ);
    PASSWORD_FIELD.value.match(UPPERCASE_REGEX) ? setPassRequirementValid(UPPERCASE_REQ) : setPassRequirementInvalid(UPPERCASE_REQ);
    PASSWORD_FIELD.value.match(NUMBERS_REGEX) ? setPassRequirementValid(NUMBER_REQ) : setPassRequirementInvalid(NUMBER_REQ);
    PASSWORD_FIELD.value.match(SPECIALS_REGEX) ? setPassRequirementValid(SPECIAL_REQ) : setPassRequirementInvalid(SPECIAL_REQ);
    PASSWORD_FIELD.value.length >= MIN_PASS_LENGTH && PASSWORD_FIELD.value.length <= MAX_PASS_LENGTH ? setPassRequirementValid(LENGTH_REQ) : setPassRequirementInvalid(LENGTH_REQ)
};

// Wanneer alle checks zijn voldaan, zet veld op valid
PASSWORD_FIELD.addEventListener('keyup', function () {

    if (isPasswordValid()) {
        setFieldValid(SHOW_PASSWORD_BUTTON);
    } else {
        setFieldInvalid(SHOW_PASSWORD_BUTTON);
    }
});

/**
 * First en Last Name
 */
FIRST_NAME_FIELD.addEventListener('input', function () {
    checkNameField("firstName");
});

LAST_NAME_FIELD.addEventListener('input', function() {
    checkNameField("lastName");
});

/**
 * Email veld
 */
EMAIL_FIELD.addEventListener('input', function () {
    let emailInput = EMAIL_FIELD.value;

    if (EMAIL_REGEX.test(emailInput)) {
        hideElement("InvalidEmail");
        setFieldValid(EMAIL_FIELD);
    } else {
        showElement("InvalidEmail");
        setFieldInvalid(EMAIL_FIELD);
    }
});

/**
 * Phone Number veld
 */
PHONE_NUMBER_FIELD.addEventListener('input', function () {
    let phoneNumberInput = PHONE_NUMBER_FIELD.value;

    if (PHONE_NUMBER_REGEX.test(phoneNumberInput)) {
        hideElement("InvalidPhoneNumber");
        setFieldValid(PHONE_NUMBER_FIELD);

    } else {
        showElement("InvalidPhoneNumber");
        setFieldInvalid(PHONE_NUMBER_FIELD)
    }
});

/**
 * BSN veld
 */
BSN_FIELD.addEventListener("input", function () {
    let BSNInput = BSN_FIELD.value;

    if (BSNInput.length !== BSN_LENGTH) {
        setFieldInvalid(BSN_FIELD);
        showElementAndSetText("BSNNotAvailable", BSN_INCORRECT_LENGTH);

        // 11-proef
    } else if (!passCheckDigit(BSNInput)) {
        setFieldInvalid(BSN_FIELD);
        showElementAndSetText("BSNNotAvailable", BSN_IS_INVALID);

    } else {
        let url = `/api/bsn?BSN=${BSNInput}`;
        fetchApiResponse(url).then(r => {
            if (apiResponse) {
                setFieldValid(BSN_FIELD);
                hideElement("BSNNotAvailable");
            } else {
                setFieldInvalid(BSN_FIELD);
                showElementAndSetText("BSNNotAvailable", BSN_IS_INVALID);
            }
        });
    }
});

/**
 * Postal Code veld
 */
POSTAL_CODE_FIELD.addEventListener('input', function () {
    let postalCodeInput = POSTAL_CODE_FIELD.value;

    if (POSTAL_CODE_REGEX.test(postalCodeInput)) {
        setFieldValid(POSTAL_CODE_FIELD);
    } else {
        setFieldInvalid(POSTAL_CODE_FIELD);
    }
});


/**
 * houseNumber, city en street veld
 */
ADDRESS_FIELDS.addEventListener('input', function(){

    if(isInputValid(POSTAL_CODE_FIELD)){

        getCityAndStreet().then(r => {
            if (city !== null) {
                setFieldValid(HOUSE_NUMBER_FIELD);
                setValue("city", city);
            } else {
                emptyValue("city");
                setFieldInvalid(HOUSE_NUMBER_FIELD);
            }
            if (street !== null) {
                setValue("street", street);
            } else {
                emptyValue("street");
            }
        })

    } else {
        removeValidation(HOUSE_NUMBER_FIELD);
        emptyValue("street");
        emptyValue("city");
    }
});


/**
 * Sign Up knop
 */
FORM.addEventListener('keyup', function () {

    // als een veld niet valid is, disable knop
    SUBMIT_BUTTON.disabled =
        !isInputValid(USERNAME_FIELD) || !isInputValid(EMAIL_FIELD) ||
        !isInputValid(SHOW_PASSWORD_BUTTON) || !isInputValid(PHONE_NUMBER_FIELD) ||
        !isInputValid(FIRST_NAME_FIELD) || !isInputValid(LAST_NAME_FIELD) ||
        !isInputValid(HOUSE_NUMBER_FIELD) || !isInputValid(CITY_FIELD) ||
        !isInputValid(STREET_FIELD) || !isInputValid(POSTAL_CODE_FIELD) ||
        !isInputValid(BSN_FIELD)
});