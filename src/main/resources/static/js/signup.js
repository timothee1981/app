/**
 * Constanten, regex & magic numbers
 */

// ElementIds
const CITY_ID = "city";
const STREET_ID = "street";
const POSTAL_CODE_ID = "postalCode";

const IS_VALID_CLASS = "isValid";
const IS_INVALID_CLASS = "isInvalid";
const PASS_REQ_VALIDATED = "valid";
const PASS_REQ_INVALID = "invalid";

const USERNAME_ERROR_ID = "usernameNotAvailable";
const EMAIL_ERROR_ID = "InvalidEmail";
const PHONE_ERROR_ID = "InvalidPhoneNumber";
const BSN_ERROR_ID = "BSNNotAvailable";

const POST_CODE_API_TOKEN = "ccf855f3-4bd0-4cd6-8f12-25c9e254efd2";

// Fields & buttons
const USERNAME_FIELD = document.getElementById("username");
const PASSWORD_FIELD = document.getElementById("password");
const SHOW_PASSWORD_BUTTON = document.getElementById("showPasswordButton");
const FIRST_NAME_FIELD = document.getElementById( "firstName");
const LAST_NAME_FIELD = document.getElementById("lastName");
const EMAIL_FIELD = document.getElementById("email");
const BSN_FIELD = document.getElementById("BSN");
const FORM = document.getElementById("form");
const HOUSE_NUMBER_FIELD = document.getElementById("houseNumber");
const PHONE_NUMBER_FIELD = document.getElementById("phoneNumber");
const CITY_FIELD = document.getElementById(CITY_ID );
const STREET_FIELD = document.getElementById(STREET_ID);
const POSTAL_CODE_FIELD = document.getElementById(POSTAL_CODE_ID);
const SUBMIT_BUTTON = document.getElementById("submitButton");
const ADDRESS_FIELDS = document.getElementById("addressFields");

// Magic numbers
const BSN_LENGTH = 9;
const MIN_PASS_LENGTH = 10;
const MAX_PASS_LENGTH = 100;
const MIN_USERNAME_LENGTH = 3;
const MAX_USERNAME_LENGTH = 20;

// Messages
const USERNAME_NOT_AVAILABLE = "Choose another username";
const USERNAME_IS_INVALID = "Between " + MIN_USERNAME_LENGTH + " and " + MAX_USERNAME_LENGTH + " letters and numbers";
const BSN_INCORRECT_LENGTH = "Enter " + BSN_LENGTH + " numbers";
const BSN_IS_INVALID = "Enter a valid BSN";

// Elementen voor password check
const LOWERCASE_REQ = document.getElementById("letter");
const UPPERCASE_REQ = document.getElementById("capital");
const NUMBER_REQ = document.getElementById("number");
const SPECIAL_REQ = document.getElementById("special");
const LENGTH_REQ = document.getElementById("length");

// Regular expressions
const USERNAME_REGEX = /^[a-zA-Z0-9_-]+$/;
const EMAIL_REGEX = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
const NAMES_REGEX = /^[^\s]*[a-zA-Z\s,.'\-][^\s]{1,100}$/;
const PHONE_NUMBER_REGEX = /^(((0)[1-9]{2}[0-9][-]?[1-9][0-9]{5})|((\\+31|0|0031)[1-9][0-9][-]?[1-9][0-9]{6}))$|^(((\\+31|0|0031)6)[1-9][0-9]{7})$/;
const POSTAL_CODE_REGEX = /^[1-9][0-9]{3} ?(?!sa|SA|Sa|sA|sd|SD|sD|Sd|ss|SS|sS)[a-zA-Z]{2}$/;
const LOWERCASE_REGEX = /[a-z]/g;
const UPPERCASE_REGEX = /[A-Z]/g;
const NUMBERS_REGEX = /[0-9]/g;
const SPECIALS_REGEX = /[!"#$%&'()*+,\-./:;<=>?@^_`{|}~\[\]]/g;

// Returns van APIs
let cityApi;
let streetApi;
let responseApi;

/**
 * Functies gereleteerd aan valid of invalid classes
 */

function setFieldValid(field){
    field.classList.remove(IS_INVALID_CLASS);
    field.classList.add(IS_VALID_CLASS);
}

function setFieldInvalid(field){
    field.classList.add(IS_INVALID_CLASS);
    field.classList.remove(IS_VALID_CLASS);
}

function removeValidation(field){
    if(field.classList.contains(IS_VALID_CLASS)){
        field.classList.remove(IS_VALID_CLASS);
    }

    if(field.classList.contains(IS_INVALID_CLASS)){
        field.classList.remove(IS_INVALID_CLASS);
    }
}

function isInputValid(elementId){
    return elementId.classList.contains(IS_VALID_CLASS)
}

function setPassRequirementValid(requirement){
    requirement.classList.remove(PASS_REQ_INVALID);
    requirement.classList.add(PASS_REQ_VALIDATED);
}

function setPassRequirementInvalid(requirement){
    requirement.classList.remove(PASS_REQ_VALIDATED);
    requirement.classList.add(PASS_REQ_INVALID);
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
    document.getElementById(elementId).classList.remove(IS_VALID_CLASS);
}

// worden gebruikt in HTML focusin/out
let showPasswordRequirements, hidePasswordRequirements;
showPasswordRequirements = () => {
    showElement("passRequirements1of2");
    showElement("passRequirements2of2");
};
hidePasswordRequirements = () => {
    hideElement("passRequirements1of2");
    hideElement("passRequirements2of2");
};

function isPasswordValid(){
    return LOWERCASE_REQ.classList.contains(PASS_REQ_VALIDATED) &&
        UPPERCASE_REQ.classList.contains(PASS_REQ_VALIDATED) &&
        NUMBER_REQ.classList.contains(PASS_REQ_VALIDATED) &&
        SPECIAL_REQ.classList.contains(PASS_REQ_VALIDATED) &&
        LENGTH_REQ.classList.contains(PASS_REQ_VALIDATED);
}

/**
 * Global functions
 */

// voor First en Last Name
function checkNameField(nameField){
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
            responseApi = data;
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
                cityApi = null;
                streetApi = null;
                throw new Error("Response error");
            }
            return response.json();
        })
        .then((data) => {
                cityApi = data.city;
                streetApi = data.street;
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
        showElementAndSetText(USERNAME_ERROR_ID, USERNAME_IS_INVALID);
        setFieldInvalid(USERNAME_FIELD);
    } else {
        let url = `/api/username?username=${usernameInput}`;

        fetchApiResponse(url).then(() => {
            if (responseApi) {
                setFieldValid(USERNAME_FIELD);
                hideElement(USERNAME_ERROR_ID);
            } else {
                setFieldInvalid(USERNAME_FIELD);
                showElementAndSetText(USERNAME_ERROR_ID, USERNAME_NOT_AVAILABLE);
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
    checkNameField(FIRST_NAME_FIELD);
});

LAST_NAME_FIELD.addEventListener('input', function() {
    checkNameField(LAST_NAME_FIELD);
});

/**
 * Email veld
 */
EMAIL_FIELD.addEventListener('input', function () {
    let emailInput = EMAIL_FIELD.value;

    if (EMAIL_REGEX.test(emailInput)) {
        hideElement(EMAIL_ERROR_ID);
        setFieldValid(EMAIL_FIELD);
    } else {
        showElement(EMAIL_ERROR_ID);
        setFieldInvalid(EMAIL_FIELD);
    }
});

/**
 * Phone Number veld
 */
PHONE_NUMBER_FIELD.addEventListener('input', function () {
    let phoneNumberInput = PHONE_NUMBER_FIELD.value;

    if (PHONE_NUMBER_REGEX.test(phoneNumberInput)) {
        hideElement(PHONE_ERROR_ID);
        setFieldValid(PHONE_NUMBER_FIELD);

    } else {
        showElement(PHONE_ERROR_ID);
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
        showElementAndSetText(BSN_ERROR_ID, BSN_INCORRECT_LENGTH);

        // 11-proef
    } else if (!passCheckDigit(BSNInput)) {
        setFieldInvalid(BSN_FIELD);
        showElementAndSetText(BSN_ERROR_ID, BSN_IS_INVALID);

    } else {
        let url = `/api/bsn?BSN=${BSNInput}`;
        fetchApiResponse(url).then(() => {
            if (responseApi) {
                setFieldValid(BSN_FIELD);
                hideElement(BSN_ERROR_ID);
            } else {
                setFieldInvalid(BSN_FIELD);
                showElementAndSetText(BSN_ERROR_ID, BSN_IS_INVALID);
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

        getCityAndStreet().then(() => {
            if (cityApi !== null) {
                setFieldValid(HOUSE_NUMBER_FIELD);
                setValue(CITY_ID, cityApi);
            } else {
                emptyValue(CITY_ID);
                setFieldInvalid(HOUSE_NUMBER_FIELD);
            }
            if (streetApi !== null) {
                setValue(STREET_ID, streetApi);
            } else {
                emptyValue(STREET_ID);
            }
        })

    } else {
        removeValidation(HOUSE_NUMBER_FIELD);
        emptyValue(STREET_ID);
        emptyValue(CITY_ID);
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