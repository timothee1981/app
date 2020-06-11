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
        //setClassInvalid("verificationCode");
    }
}

function usernameMessageHandler() {
    if (!isUsernameProvided()){
        showMessage(messageUsernameEmpty, "usernameMessage");
        //setClassInvalid("inviteeUsername");
    }
}

function isAllInputValid(){
    return (isVerificationCodeFormatValid() && isUsernameProvided() && isVerificationCodeProvided());
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
inviteeUsernameField.addEventListener("input", function() {
    hideMessage(messageUsernameEmpty, "usernameMessage");
    //removeValidInValidClasses("inviteeUsername");
    usernameMessageHandler();
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
