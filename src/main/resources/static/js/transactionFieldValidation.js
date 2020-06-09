const fromAccountField = document.getElementById("fromAccountNumber");
const toAccountField = document.getElementById("toAccountNumber");
const amountField = document.getElementById("amount");
const errorAccountDoesntExist1 = "while typing: Enter an existing Royal Stacks iban account number "
const errorAccountDoesntExist2 = "Account format error: Enter an existing Royal Stacks "
const errorAccountDoesntExist3 = "not in DB : Enter an existing Royal Stacks iban account number "
const errorSenderSameAsReceiver = "The receiving account can't be the same as the sending account"
const errorAmountTooHigh = "The amount can't be higher than the available balance"
const errorAmountFormatInvalid = "The amount should only consist of numbers, with a maximum of two decimals"
const errorNoAmount = "Enter the amount to be transfered"
const ibanLength = 18;
let apiResponse;

//Input validation checks

function isAccountFormatValid(){
    let accountFormatRegex = new RegExp(/^nl[\d]{2}roya[\d]{10}$/i);
    return (accountFormatRegex.test(toAccountField.value));
}
function isAccountFormatValidWhileTyping(){
    let accountFormatRegex = new RegExp(/^(?:n|$)(?:l|$)(?:\d|$)(?:\d|$)(?:r|$)(?:o|$)(?:y|$)(?:a|$)[\d]*$/i);
    return (accountFormatRegex.test(toAccountField.value));
}
function isAccountDifferentThanFromAccount(){
    return(fromAccountField.value.toUpperCase() !== toAccountField.value.toUpperCase());
}
function doesAccountExistInDb(){

    const options = {
        method: 'POST',
        body: toAccountField.value,
        headers: {
            'Content-Type': 'text/plain'
        }

    }
    var exists;
   return  fetch("/api/iban/",options)
        .then(response => {
            return response.text();
        })
        .then(response => {
            console.log(response);
        exists =  (response === "true") ;
        return (response === "true");
       ;
    });

}
function isAmountFormatValid(){
    let amountFormatRegex = new RegExp(/^\d{0,11}([,.][\d]{1,2})?$/);
    return (amountFormatRegex.test(amountField.value));
}

function isAmountSmallerThanBalance(){
    return (parseInt(fromAccountField.options[fromAccountField.selectedIndex].text, 10) >= amountField.value);
}
function isAmountFilledIn(){
    return (amountField.value.length !== 0);
}
//Combined validation checks on singel input fields
function isToAccountValid(){
    return(isAccountFormatValid() && isAccountDifferentThanFromAccount() && doesAccountExistInDb());
}
function isAmountValid(){
    return(isAmountFormatValid() && isAmountSmallerThanBalance() && isAmountFilledIn());
}
//Combined check on ALL input fields
function isAllInputValid(){
    return (isToAccountValid() && isAmountValid());
}
//Combined check and error handling for toAccount inputField
function toAccountFieldErrorHandler(){
    if(toAccountField.value.length < ibanLength) {
        if (!isAccountFormatValidWhileTyping()) {
            showError(errorAccountDoesntExist1, "toAccountError");
            setClassInvalid("toAccountNumber");
        }
    }
    else if (!isAccountFormatValid()){
        showError(errorAccountDoesntExist2, "toAccountError");
        setClassInvalid("toAccountNumber")
    }
    else if(!isAccountDifferentThanFromAccount()){
        showError(errorSenderSameAsReceiver, "toAccountError");
        setClassInvalid("toAccountNumber");
    }
    else {
        doesAccountExistInDb().then(r => {
            console.log(apiResponse);
            if (!apiResponse) {
                showError(errorAccountDoesntExist3, "toAccountError");
                setClassInvalid("toAccountNumber");
            } else {
                setClassValid("toAccountNumber");
            }
        })
    }
};
//Combined check and error handling for amount inputField
function amountFieldErrorHandler(){
    if(!isAmountFormatValid()){
        showError(errorAmountFormatInvalid, "amountError");
        setClassInvalid("amount");
    }
    else if(!isAmountSmallerThanBalance()){
        showError(errorAmountTooHigh, "amountError");
        setClassInvalid("amount");
    }
    else{
        setClassValid("amount");
    }

};
// Error Handlers
function errorHandlerWhileTyping(){
    if (!isAccountFormatValidWhileTyping()) {
        showError(errorAccountDoesntExist, "toAccountError");
        setClassInvalid("toAccountNumber");
    }
}
function errorHandlerNonValidAccount(){
    if(!isAccountFormatValid()){
        showError(errorAccountDoesntExist, "toAccountError");
        setClassInvalid("toAccountNumber");
    }
}
function errorHandlerSameAsSender() {
    if (!isAccountDifferentThanFromAccount()) {
        showError(errorSenderSameAsReceiver, "toAccountError");
        setClassInvalid("toAccountNumber");
    }
}function errorHandlerNonExistingAccount() {
        if (!doesAccountExistInDb()) {
            showError(errorAccountDoesntExist, "toAccountError");
            setClassInvalid("toAccountNumber");
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


//Event Listeners
toAccountField.addEventListener("input", function() {
    hideError(errorAccountDoesntExist1, "toAccountError");
    hideError(errorAccountDoesntExist2, "toAccountError");
    hideError(errorAccountDoesntExist3, "toAccountError");
    hideError(errorSenderSameAsReceiver, "toAccountError");
    removeValidInValidClasses("toAccountNumber");
    toAccountFieldErrorHandler();
    console.log(doesAccountExistInDb())
});

amountField.addEventListener("input", function(){
    hideError(errorAmountFormatInvalid, "amountError");
    hideError(errorAmountTooHigh, "amountError");
    removeValidInValidClasses("amount");
    amountFieldErrorHandler();

});
fromAccountField.addEventListener("click", function(){
    hideError(errorAccountDoesntExist1, "toAccountError");
    hideError(errorAccountDoesntExist2, "toAccountError");
    hideError(errorAccountDoesntExist3, "toAccountError");
    hideError(errorSenderSameAsReceiver, "toAccountError");
    hideError(errorAmountFormatInvalid, "amountError");
    hideError(errorAmountTooHigh, "amountError");
    removeValidInValidClasses("amount");
    removeValidInValidClasses("toAccountNumber");
    toAccountFieldErrorHandler();
    amountFieldErrorHandler();

});




/*fromAccountField.addEventListener("click",fromAccountValidationAction);
toAccountField.addEventListener("input", function(){
    if(!isAccountFormatValidWhileTyping()){
        setClassInvalid("toAccountNumber");}
    else{
        setClassValid("toAccountNumber")

    }
/!*    if(toAccountField.value.length > 17) {
        toAccountValidationAction();
    }
    else{
        removeValidClass(toAccountFieldId);
    }*!/
});
toAccountField.addEventListener("blur", function(){
    toAccountValidationAction();
    checkAndEnableButton();
});
toAccountField.addEventListener("mouseout", function(){
    checkAndEnableButton();
});
amountField.addEventListener("input", function(){
    checkAndEnableButton();
    amountValidationAction();
});
amountField.addEventListener("blur", function pleaseEnterAmount(){
    if(!isAmountFilledIn() ){
        showError(errorNoAmount, "amountError");
        setClassInvalid("amount");
    }
    checkAndEnableButton();
});
amountField.addEventListener("mouseout", function pleaseEnterAmount(){
   checkAndEnableButton();
});*/

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
    /*

    function fromAccountValidationAction() {
        if(toAccountField.value.length > 0 ) {
            showHideToAccountValidMark();
            showHideToAccountErrors();
        }
        if(isAmountFilledIn()) {
            showHideAmountValidMark();
            showHideAmountErrors();
        }
        else{
            removeValidClass("amount");
            disableButton()
        }
    }
    function toAccountValidationAction(){
        showHideToAccountErrors();
        showHideToAccountValidMark();
    }
    function amountValidationAction() {
        showHideAmountErrors();
        showHideAmountValidMark();
    }


    function showHideToAccountErrors(){
        if(isAccountFormatValid()) {
            hideError(errorAccountDoesntExist, "toAccountError");
         }
        else{
            showError(errorAccountDoesntExist, "toAccountError");
            disableButton()
        }
        if(isAccountDifferentThanFromAccount()){
            hideError(errorSenderSameAsReceiver, "toAccountError");
        }
        else{
            showError(errorSenderSameAsReceiver, "toAccountError");
            disableButton()
        }

    }
    function showHideAmountErrors(){
        hideError(errorNoAmount, "amountError");
        if(isAmountFormatValid()){
            hideError(errorAmountFormatInvalid, "amountError");
        }
        else{
            showError(errorAmountFormatInvalid, "amountError");
            disableButton()
        }
        if(isAmountSmallerThanBalance()){
            hideError(errorAmountTooHigh, "amountError");
        }
        else {
            showError(errorAmountTooHigh, "amountError");
            disableButton()
        }
    }
    function showHideAmountValidMark(){
        if(isAmountValid()){
            setClassValid("amount");}
        else{
            setClassInvalid("amount");
        }
    }
    function showHideToAccountValidMark(){
        if(isToAccountValid()){
            setClassValid("toAccountNumber");}
        else{
            setClassInvalid("toAccountNumber");
        }
    }*/




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








