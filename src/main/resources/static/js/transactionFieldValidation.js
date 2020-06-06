const fromAccountFieldId = "fromAccountNumber";
const toAccountFieldId = "toAccountNumber";
const amountFieldId = "amount";
const descriptionFieldId = "description";
const toAccountErrorId = "toAccountError";
const amountErrorId = "amountError";


const fromAccountField = document.getElementById(fromAccountFieldId);
const toAccountField = document.getElementById(toAccountFieldId);
const amountField = document.getElementById(amountFieldId);


const errorAccountDoesntExist = "Enter an existing Royal Stacks account number <br>"
const errorSenderSameAsReceiver = "The receiving account can't be the same as the sending account"
const errorAmountTooHigh = "The amount can't be higher than the available balance"
const errorAmountFormatInvalid = "The amount should only consist of numbers, with a maximum of two decimals"


fromAccountField.addEventListener("click",function () {
    if(amountField.value.length > 0){
        if(isAmountValid()){
            addClassValidRemoveClassInvalid(amountFieldId);
        }
        else {
            addClassInvalidRemoveClassValid(amountFieldId);
        }
    }
});

toAccountField.addEventListener("input",function() {
     if (isToAccountValid()) {
           addClassValidRemoveClassInvalid(toAccountFieldId);
     }
     else {
            addClassInvalidRemoveClassValid(toAccountFieldId);
     }
} );

amountField.addEventListener("input", function () {
    if (isAmountValid()) {
        addClassValidRemoveClassInvalid(amountFieldId);
    }
    else{
        addClassInvalidRemoveClassValid(amountFieldId);
    }
})




function showError(errorMessage, elementId){
    if(document.getElementById(elementId).innerHTML.indexOf(errorMessage) === -1) {
        document.getElementById(elementId).innerHTML = errorMessage;
    }
}
function hideError(errorMessage, elementId){
    if(document.getElementById(elementId).innerHTML.indexOf(errorMessage) !== -1) {
    document.getElementById(elementId).innerHTML = "";
    }
}
function addClassValidRemoveClassInvalid(id) {
   document.getElementById(id).classList.add('isValid');
    document.getElementById(id).classList.remove('isInvalid');
}
function addClassInvalidRemoveClassValid(id) {
    document.getElementById(id).classList.add('isInvalid');
    document.getElementById(id).classList.remove('isValid');
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
    if (accountFormatRegex.test(toAccountField.value)){
        hideError(errorAccountDoesntExist, toAccountErrorId);
        return true;
    }
    else{
        showError(errorAccountDoesntExist, toAccountErrorId);
        return false;
    }
}
function isAccountDifferentThanFromAccount(){
    if (fromAccountField.value.toUpperCase() !== toAccountField.value.toUpperCase()){
        hideError(errorSenderSameAsReceiver, toAccountErrorId);
        return true;
    }
    else {
        showError(errorSenderSameAsReceiver, toAccountErrorId);
        return false;
    }
}
function doesAccountExistInDb(){
    return true;
}
function  isAmountFormatValid(){
    let amountFormatRegex = new RegExp(/^\d+([,.][\d]{1,2})?$/);
    if(amountFormatRegex.test(amountField.value)){
        hideError(errorAmountFormatInvalid, amountErrorId);
        return true;
    }
    else{
        showError(errorAmountFormatInvalid, amountErrorId);
        return false;
    }

}
function isAmountSmallerThanBalance(){
    if(parseInt(fromAccountField.options[fromAccountField.selectedIndex].text, 10) >= amountField.value){
        hideError(errorAmountTooHigh, amountErrorId);
        return true;
    }
    else{
        showError(errorAmountTooHigh, amountErrorId);
        return false;
    }
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

