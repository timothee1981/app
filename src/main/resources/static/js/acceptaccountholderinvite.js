const accountNumberField = document.getElementById("accountNumber");
const verificationCodeField = document.getElementById("verificationCode");
const messageVerificationCodeInvalid = "Please enter a code consisting of five numbers."
const messageAccountNumberInvalid = "Please enter a valid account number."

function isVerificationCodeFormatValid(){
    let verificationCodeFormatRegex = new RegExp(/^[/\d]{5}?$/);
    return (verificationCodeFormatRegex.test(verificationCodeField.value));
}

function isVerificationCodeProvided() {
    return (verificationCodeField.value.length !== 0);
}

function isAccountNumberProvided() {
    return (accountNumberField.value.length !== 0);
}


function verificationCodeFieldMessageHandler() {
    if ((!isVerificationCodeFormatValid()) || (!isVerificationCodeProvided())) {
        showMessage(messageVerificationCodeInvalid, "verificationCodeMessage");
        //setClassInvalid("verificationCode");
    }
}

function accountNumberFieldMessageHandler() {
    if (!isAccountNumberFormatValid() || (!isAccountNumberProvided())) {
        showMessage(messageAccountNumberInvalid, "accountNumberMessage");
        //setClassInvalid("accountNumber");
    }
}

function isAccountNumberFormatValid(){
    let accountFormatRegex = new RegExp(/^nl[\d]{2}roya[\d]{10}$/i);
    return (accountFormatRegex.test(toAccountField.value));
}

function isAllInputValid(){
    return (isVerificationCodeFormatValid() && isAccountNumberFormatValid() && isVerificationCodeProvided() && isAccountNumberProvided());
}


function buttonHandler(){
    if(isAllInputValid()){
        enableButton();
    }
    else{
        disableButton()
    }
}



//Event Listeners
accountNumberField.addEventListener("input", function() {
    hideMessage(messageAccountNumberInvalid, "accountNumberMessage");
    //removeValidInValidClasses("accountNumber");
    accountNumberFieldMessageHandler();
    buttonHandler();
})

verificationCodeField.addEventListener("input", function() {
    hideMessage(messageVerificationCodeInvalid, "verificationCodeMessage");
    //removeValidInValidClasses("verificationCode");
    verificationCodeFieldMessageHandler();
    buttonHandler();
})


function enableButton(){
    if(document.getElementById("submitButton").disabled === true){
        document.getElementById("submitButton").disabled = false;
    }
}
function disableButton(){
    if(document.getElementById("submitButton").disabled === false) {
        document.getElementById("submitButton").disabled = true;
    }
}

function showMessage(message, elementId){
    let currentText = document.getElementById(elementId).innerHTML;
    if(!currentText.includes(message)) {
        let newString = currentText.concat(message);
        document.getElementById(elementId).innerHTML = newString;
    }
}
function hideMessage(message, elementId){
    let currentText = document.getElementById(elementId).innerHTML;
    if(currentText.includes(message)) {
        let newString = currentText.replace(message, "");
        document.getElementById(elementId).innerHTML = newString;
    }
}

/*
function setClassValid(id) {
    if(document.getElementById(id).classList.contains('isInvalid')){
        document.getElementById(id).classList.remove('isInvalid');
    }
    if(!document.getElementById(id).classList.contains('isValid')) {
        document.getElementById(id).classList.add('isValid');
    }
}
function setClassInvalid(id) {
    if(!document.getElementById(id).classList.contains('isInvalid')) {
        document.getElementById(id).classList.add('isInvalid');
    }
    if(document.getElementById(id).classList.contains('isValid')){
        document.getElementById(id).classList.remove('isValid');
    }
}
function removeValidInValidClasses(id){
    if(document.getElementById(id).classList.contains('isValid')){
        document.getElementById(id).classList.remove('isValid');
    }
    if(document.getElementById(id).classList.contains('isInvalid')){
        document.getElementById(id).classList.remove('isInvalid');
    }
}*/
