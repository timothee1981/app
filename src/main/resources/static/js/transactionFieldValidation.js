const toAccountField = document.getElementById("toAccountNumber");


toAccountField.addEventListener("input", function validateToAccountFormat()
    {
        if (toAccountField.value.length > 7) {
        toAccountField.classList.add('isValid')
            toAccountField.classList.remove('isInvalid')
        } else {
        toAccountField.classList.add('isInvalid');
        toAccountField.classList.remove('isValid')
        }
    }
);

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

