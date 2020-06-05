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

// magic numbers
const BSN_LENGTH = 9;
const MIN_PASSWORD_LENGTH = 10;
const MAX_PASSWORD_LENGTH = 100;
const MIN_USERNAME_LENGTH = 3;
const MAX_USERNAME_LENGTH = 15;

// globale functies
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

// Show passwordField knop op scherm
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

/**
 * Username veld
 *
 * Eerst wordt gekeken of het in de juist format is, daarna wordt bekene of deze al bestaat in de database
 */
usernameField.addEventListener("input", function () {
    let usernameInput = usernameField.value;

    const re = /^[a-zA-Z0-9_-]$/;

    console.log(isUsernameUnique());
    if(!re.test(usernameInput) && usernameInput.length < MIN_USERNAME_LENGTH && usernameInput.length > MAX_USERNAME_LENGTH) {
        showElementAndSetText("usernameNotAvailable", "Between " + MIN_USERNAME_LENGTH + " and " + MAX_USERNAME_LENGTH + " numbers and letters");
        setFieldInvalid(usernameField);
    } else {
        isUsernameUnique();
    }

    // check met API of gebruikersnaam uniek is
    function isUsernameUnique() {
        let usernameCheck = `/api/username?username=${usernameInput}`;
        fetch(usernameCheck)
            .then((response) => {
                if (!response.ok) {
                    throw new Error("Response error");
                }
               // return response.json();
            })
            .then((data) => {
                if (data){
                    setFieldValid(usernameField);
                    hideElement("usernameNotAvailable");
                } else{
                    setFieldInvalid(usernameField)
                    showElementAndSetText("usernameNotAvailable", "Choose another username");
                }
            })
            .catch((error) => {
                console.log(error);
            });
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

    const lowerCaseLetters = /[a-z]/g;
    const upperCaseLetters = /[A-Z]/g;
    const numbers = /[0-9]/g;
    const specials = /[!"#$%&'()*+,\-./:;<=>?@^_`{|}~\[\]]/g;

    passwordInput.value.match(lowerCaseLetters) ? setPassRequirementValid(letter) : setPassRequirementInvalid(letter);
    passwordInput.value.match(upperCaseLetters) ? setPassRequirementValid(capital) : setPassRequirementInvalid(capital);
    passwordInput.value.match(numbers) ? setPassRequirementValid(number) : setPassRequirementInvalid(number);
    passwordInput.value.match(specials) ? setPassRequirementValid(special) : setPassRequirementInvalid(special);
    passwordInput.value.length >= MIN_PASSWORD_LENGTH && passwordInput.value.length <= MAX_PASSWORD_LENGTH ? setPassRequirementValid(length) : setPassRequirementInvalid(length)
};

// Wanneer alle checks zijn voldaan, zet veld op valid
passwordField.addEventListener('keyup', function () {

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

    if (BSNInput.length !== BSN_LENGTH) {
        setFieldInvalid(BSNField);
        showElementAndSetText("BSNNotAvailable", "Must be 9 numbers");

    } else if (!isValidBSN(BSNInput)) {
        setFieldInvalid(BSNField);
        showElementAndSetText("BSNNotAvailable", "Enter a valid BSN");

    } else {
        isBSNUnique();
    }



    // NESTED FUNCTIONS
    function isBSNUnique() {

        let BSNCheck = `/api/bsn?BSN=${BSNInput}`;
        fetch(BSNCheck)
            .then((response) => {
                if (!response.ok) {
                    throw new Error("Response error");
                }
                return response.json();
            })

            .then((data) => {
            if (data){
                setFieldValid(BSNField);
                hideElement("BSNNotAvailable");
            } else {
                setFieldInvalid(BSNField);
                showElementAndSetText("BSNNotAvailable", "Enter a valid BSN");
            }
                return data
            })

            .catch((error) => {
                console.log(error);
            })
    }

    // Elfproef
    function isValidBSN(BSN) {
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