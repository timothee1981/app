/**
 * Constanten & magic numbers
 */

// ElementIds
const IS_VALID_CLASS = "isValid";
const IS_INVALID_CLASS = "isInvalid";
const PASS_REQ_VALIDATED = "valid";
const PASS_REQ_INVALID = "invalid";
const USERNAME_ERROR_ID = "usernameNotAvailable";
const EMAIL_ERROR_ID = "InvalidEmail";
const PHONE_ERROR_ID = "InvalidPhoneNumber";
const BSN_ERROR_ID = "BSNNotAvailable";
const PASS_REQ_LEFT = "passRequirements1of2";
const PASS_REQ_RIGHT = "passRequirements2of2";

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



/**
 * Functies gerelateerd aan valid of invalid classes
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
    return document.getElementById(elementId).classList.contains(IS_VALID_CLASS)
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

function setFieldPink(id){
    document.getElementById(id).style.backgroundColor = '#ffdede';
}

function setFieldGrey(id){
    document.getElementById(id).style.backgroundColor = '#f1f1f1';
}

function setFieldGreyOrPink(id){
    !isInputValid(id) ? setFieldPink(id) : setFieldGrey(id);
}

function setPasswordFieldGreyOrPink(){
    !isInputValid("showPasswordButton") ? setFieldPink("password") : setFieldGrey("password");
}

function isPasswordValid(){
    return document.getElementById("letter").classList.contains(PASS_REQ_VALIDATED) &&
        document.getElementById("capital").classList.contains(PASS_REQ_VALIDATED) &&
        document.getElementById("number").classList.contains(PASS_REQ_VALIDATED) &&
        document.getElementById("special").classList.contains(PASS_REQ_VALIDATED) &&
        document.getElementById("length").classList.contains(PASS_REQ_VALIDATED);
}

function setPassReq(regex, id){
    let element = document.getElementById(id);
    document.getElementById("password").value.match(regex) ? setPassRequirementValid(element) : setPassRequirementInvalid(element);
}

function setPassLengthReq(){
    const PASSWORD_FIELD = document.getElementById("password");
    const LENGTH_REQ = document.getElementById("length");

    PASSWORD_FIELD.value.length >= MIN_PASS_LENGTH && PASSWORD_FIELD.value.length <= MAX_PASS_LENGTH
        ? setPassRequirementValid(LENGTH_REQ) : setPassRequirementInvalid(LENGTH_REQ)
}

/**
 * Global functions
 */
function checkNameField(id){
    let nameField = document.getElementById(id);
    let nameInput = nameField.value;

    if (regex.username.test(nameInput)) {
        setFieldValid(nameField);
    } else {
        setFieldInvalid(nameField);
    }
}

function showPassword(){
    const PASSWORD_FIELD = document.getElementById("password");
    const SHOW_PASSWORD_BUTTON =  document.getElementById("showPasswordButton");

    if (PASSWORD_FIELD.type === "password") {
        PASSWORD_FIELD.type = "text";
        SHOW_PASSWORD_BUTTON .value = "hide";
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

/**
 * Username veld
 */
document.getElementById("username").addEventListener("input", function () {
    const USERNAME_FIELD = document.getElementById("username");
    let usernameInput = USERNAME_FIELD.value;

    if(!regex.username.test(usernameInput) || usernameInput.length < MIN_USERNAME_LENGTH || usernameInput.length > MAX_USERNAME_LENGTH) {
        showElementAndSetText(USERNAME_ERROR_ID, USERNAME_IS_INVALID);
        setFieldInvalid(USERNAME_FIELD);
    } else {
        let url = `/api/username?username=${usernameInput}`;
        const api = new API(url);
        api.isUnique(url).then(r => {
            if (r) {
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

document.getElementById("showPasswordButton").addEventListener('click', showPassword);

document.getElementById("passwordElements").addEventListener('focusin', function() {
    showElement(PASS_REQ_LEFT);
    showElement(PASS_REQ_RIGHT);
});
document.getElementById("passwordElements").addEventListener('focusout', function() {
    hideElement(PASS_REQ_LEFT);
    hideElement(PASS_REQ_RIGHT);
});

document.getElementById("password").addEventListener('keyup', function () {
    setPassLengthReq();
    setPassReq(regex.lowerCase, "letter");
    setPassReq(regex.upperCase, "capital");
    setPassReq(regex.numbers, "number");
    setPassReq(regex.specials, "special");

    if (isPasswordValid()) {
        setFieldValid(document.getElementById("showPasswordButton"));
    } else {
        setFieldInvalid(document.getElementById("showPasswordButton"));
    }
});

/**
 * First en Last Name
 */
document.getElementById( "firstName").addEventListener('input', function () {
    checkNameField( "firstName");
});

document.getElementById("lastName").addEventListener('input', function() {
    checkNameField("lastName");
});

/**
 * Email veld
 */
document.getElementById("email").addEventListener('input', function () {
    const EMAIL_FIELD = document.getElementById("email");

    if (regex.email.test(EMAIL_FIELD.value)) {
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
document.getElementById("phoneNumber").addEventListener('input', function () {
    const PHONE_NUMBER_FIELD = document.getElementById("phoneNumber");

    if (regex.phoneNumber.test(PHONE_NUMBER_FIELD.value)) {
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
document.getElementById("BSN").addEventListener("input", function () {
    const BSN_FIELD = document.getElementById("BSN");

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
        const api = new API(url);
        api.isUnique(url).then(r => {
            if (r) {
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
document.getElementById("postalCode").addEventListener('input', function () {
    const POSTAL_CODE_FIELD = document.getElementById("postalCode");

    if (regex.postalCode.test(POSTAL_CODE_FIELD.value)) {
        setFieldValid(POSTAL_CODE_FIELD);
    } else {
        setFieldInvalid(POSTAL_CODE_FIELD);
    }
});


/**
 * houseNumber, city en street veld
 */
document.getElementById("addressFields").addEventListener('input', function(){
    const HOUSE_NUMBER_FIELD = document.getElementById("houseNumber");

    if(isInputValid("postalCode")){
        const api = new API();
        api.cityAddress().then(r => {
            if(r !== undefined){
                setValue("city", r.city);
                setValue("street", r.street);
                setFieldValid(HOUSE_NUMBER_FIELD);
            } else {
                emptyValue("city");
                emptyValue("street");
                setFieldInvalid(HOUSE_NUMBER_FIELD);
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
document.getElementById("signUpButton").addEventListener("mouseenter", function() {
    if(document.getElementById("submitButton").disabled === true){
        setPasswordFieldGreyOrPink();
        setFieldGreyOrPink("username");
        setFieldGreyOrPink("email");
        setFieldGreyOrPink("phoneNumber");
        setFieldGreyOrPink("firstName");
        setFieldGreyOrPink("lastName");
        setFieldGreyOrPink("houseNumber");
        setFieldGreyOrPink("city");
        setFieldGreyOrPink("street");
        setFieldGreyOrPink("postalCode");
        setFieldGreyOrPink("BSN");
    }
});

document.getElementById("signUpButton").addEventListener("mouseleave", function() {
    setFieldGrey("username");
    setFieldGrey("email");
    setFieldGrey("password");
    setFieldGrey("phoneNumber");
    setFieldGrey( "firstName");
    setFieldGrey("lastName");
    setFieldGrey("houseNumber");
    setFieldGrey("city");
    setFieldGrey("street");
    setFieldGrey("postalCode");
    setFieldGrey("BSN");
});

document.getElementById("form").addEventListener('keyup', function () {

    // als een veld niet valid is, disable knop
    document.getElementById("submitButton").disabled =
        !isInputValid("username") || !isInputValid("email") ||
        !isInputValid("showPasswordButton") || !isInputValid("phoneNumber") ||
        !isInputValid( "firstName") || !isInputValid("lastName") ||
        !isInputValid("houseNumber") || !isInputValid("city") ||
        !isInputValid("street") || !isInputValid("postalCode") ||
        !isInputValid("BSN");
});