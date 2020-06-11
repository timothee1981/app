const inviteeUsernameField = document.getElementById("inviteeUsername");
const verificationCodeField = document.getElementById("verificationCode");
const messageVerificationCodeInvalid = "Please enter a code consisting of five numbers."
const messageUsernameEmpty = "Please enter the new account holder's username."

function isVerificationCodeFormatValid(){
    let verificationCodeFormatRegex = new RegExp(/^[/\d]{5}?$/);
    return (verificationCodeFormatRegex.test(verificationCodeField.value));
}

function isVerificationCodeProvided() {
    return (verificationCodeField.value.length !== 0);
}

function isUsernameProvided() {
    return (inviteeUsernameField.value.length !== 0);
}


function verificationCodeFieldMessageHandler() {
    if ((!isVerificationCodeFormatValid()) || (!isVerificationCodeProvided())) {
        showMessage(messageVerificationCodeInvalid, "verificationCodeMessage");
        setClassInvalid("verificationCode");
    }
}

function usernameMessageHandler() {
    if (!isUsernameProvided()){
        showMessage(messageUsernameEmpty, "usernameMessage");
        setClassInvalid("inviteeUsername");
    }
}



//Button enable/disable handler
function buttonHandler(){
    if(isAllInputValid()){
        enableButton();
    }
    else{
        disableButton()
    }
}
// remove errors and valid/invalid classen
function resetErrorsAndClasses(){

}


//Event Listeners
toAccountField.addEventListener("input", function() {
    hideError(errorAccountDoesntExist1, "toAccountError");
    hideError(errorAccountDoesntExist2, "toAccountError");
    hideError(errorSenderSameAsReceiver, "toAccountError");
    removeValidInValidClasses("toAccountNumber");
    toAccountFieldErrorHandler();
    buttonHandler();

});

amountField.addEventListener("input", function(){
    hideError(errorAmountFormatInvalid, "amountError");
    hideError(errorAmountTooHigh, "amountError");
    removeValidInValidClasses("amount");
    amountFieldErrorHandler();
    buttonHandler();

});
fromAccountField.addEventListener("click", function(){
    hideError(errorAccountDoesntExist1, "toAccountError");
    hideError(errorAccountDoesntExist2, "toAccountError");
    hideError(errorSenderSameAsReceiver, "toAccountError");
    hideError(errorAmountFormatInvalid, "amountError");
    hideError(errorAmountTooHigh, "amountError");
    removeValidInValidClasses("amount");
    removeValidInValidClasses("toAccountNumber");
    toAccountFieldErrorHandler();
    amountFieldErrorHandler();


});

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

function showError(errorMessage, elementId){
    let currentText = document.getElementById(elementId).innerHTML;
    if(!currentText.includes(errorMessage)) {
        let newString = currentText.concat(errorMessage);
        document.getElementById(elementId).innerHTML = newString;
    }
}
function hideError(errorMessage, elementId){
    let currentText = document.getElementById(elementId).innerHTML;
    if(currentText.includes(errorMessage)) {
        let newString = currentText.replace(errorMessage, "");
        document.getElementById(elementId).innerHTML = newString;
    }
}

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
}