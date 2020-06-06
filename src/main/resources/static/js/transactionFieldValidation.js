const fromAccountFieldId = "fromAccountNumber";
const toAccountFieldId = "toAccountNumber";
const amountFieldId = "amount";
const descriptionFieldId = "description";
const toAccountErrorId = "toAccountError";
const amountErrorId = "amountError";
const fromAccountField = document.getElementById(fromAccountFieldId);
const toAccountField = document.getElementById(toAccountFieldId);
const amountField = document.getElementById(amountFieldId);
const errorAccountDoesntExist = "Enter an existing Royal Stacks account number"
const errorSenderSameAsReceiver = "The receiving account can't be the same as the sending account"
const errorAmountTooHigh = "The amount can't be higher than the available balance"
const errorAmountFormatInvalid = "The amount should only consist of numbers, with a maximum of two decimals"
const errorNoAmount = "Enter the amount to be transfered"


fromAccountField.addEventListener("click",fromAccountValidationAction);
toAccountField.addEventListener("input", function(){
    if(toAccountField.value.length > 17) {
        toAccountValidationAction();
    }
    else{
        removeValidClass(toAccountFieldId)
    }
});
toAccountField.addEventListener("blur", toAccountValidationAction);
amountField.addEventListener("input", amountValidationAction);
amountField.addEventListener("blur", function pleaseEnterAmount(){
    if(amountField.value.length == 0 ){
        showError(errorNoAmount, amountErrorId);
        setClassInvalid(amountFieldId);
    }
});



function fromAccountValidationAction() {
    if(amountField.value.length > 0) {
        showHideAmountValidMark();
        showHideAmountErrors();
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
        hideError(errorAccountDoesntExist, toAccountErrorId);
     }
    else{
        showError(errorAccountDoesntExist, toAccountErrorId);
    }
    if(isAccountDifferentThanFromAccount()){
        hideError(errorSenderSameAsReceiver, toAccountErrorId);
    }
    else{
        showError(errorSenderSameAsReceiver, toAccountErrorId);
    }
}
function showHideAmountErrors(){
    hideError(errorNoAmount, amountErrorId);
    if(isAmountFormatValid()){
        hideError(errorAmountFormatInvalid, amountErrorId);
    }
    else{
        showError(errorAmountFormatInvalid, amountErrorId);
    }
    if(isAmountSmallerThanBalance()){
        hideError(errorAmountTooHigh, amountErrorId);
    }
    else {
        showError(errorAmountTooHigh, amountErrorId);
    }
}
function showHideAmountValidMark(){
    if(isAmountValid()){
        setClassValid(amountFieldId);}
    else{
        setClassInvalid(amountFieldId);
    }
}

function showHideToAccountValidMark(){
    if(isToAccountValid()){
        setClassValid(toAccountFieldId);}
    else{
        setClassInvalid(toAccountFieldId);
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
function removeValidClass(id){
    if(document.getElementById(id).classList.contains('isValid')){
        document.getElementById(id).classList.remove('isValid');
    }
}



/**
 * checks if ToAccount Input Field is Valid on multiple checks
 */
function isToAccountValid(){
    return(isAccountFormatValid() && isAccountDifferentThanFromAccount() && doesAccountExistInDb())
}
/**
 * checks if amount Input Field is Valid on multiple checks
 */
function isAmountValid(){
    return(isAmountFormatValid() && isAmountSmallerThanBalance())
}
/**
 * checks if description Input Field is Valid on Format
 */
function isDescriptionValid(){
    return
}


function isAccountFormatValid(){
    let accountFormatRegex = new RegExp(/^nl[\d]{2}roya[\d]{10}$/i);
    return (accountFormatRegex.test(toAccountField.value));
}
function isAccountDifferentThanFromAccount(){
    return(fromAccountField.value.toUpperCase() !== toAccountField.value.toUpperCase());
}
function doesAccountExistInDb(){
    return true;
}
function isAmountFormatValid(){
    let amountFormatRegex = new RegExp(/^\d{0,11}([,.][\d]{1,2})?$/);
    return (amountFormatRegex.test(amountField.value));
}
function isAmountSmallerThanBalance(){
    return (parseInt(fromAccountField.options[fromAccountField.selectedIndex].text, 10) >= amountField.value);
}


// error message: enter correct Iban
//toAccountField.addEventListener("change", validateToAccountDiffersFromSender()); // error message: receiving iban can't be same as senders iban
//toAccountField.addEventListener("change", checkIfAccountInDb()); // // error message: enter an existing RoyalStacks account number



//function validateToAccountFormat() {
    //let accountFormatRegex = new RegExp(/^nl[0-9]{2}roya[0-9]{10}$/i);
   // if (accountFormatRegex.test(toAccountField.value)) {
      //  toAccountField.classList.add('isValid')
      //  return true;



   // }
//}

