




/* check companyname */
const sector = document.getElementById("sector");
const companyname = document.getElementById("companyName");
companyname.addEventListener("input",function () {
    let companyname = document.getElementById("companyName");
    let companynameInput = companyname.value;
    const re = /^[\w@ ]*[^\W_ ][\w- @ & +]*$/;
    if(re.test(companynameInput)) {
        document.getElementById("InvalidCompanyName").style.display = "none";
        companyname.classList.add("isValid");
        companyname.classList.remove("isInvalid");
    }else{
        document.getElementById("InvalidCompanyName").style.display = "inline";
        companyname.classList.add("isInvalid");
        companyname.classList.remove("isValid");
    }

});


/*check if kvk valid*/

const kvknumber = document.getElementById("Kvk Number");
kvknumber.addEventListener('input',function(){
    let kvknumber = document.getElementById("Kvk Number");
    let kvknumberInput = kvknumber.value;
    const re = /^[0-9]{8}$/;
    if(re.test(kvknumberInput)){
        document.getElementById("InvalidKvkNumber").style.display = "none";
        kvknumber.classList.add("isValid");
        kvknumber.classList.remove("isInvalid");
    }else{
        document.getElementById("InvalidKvkNumber").style.display = "inline";
        kvknumber.classList.add("isInvalid");
        kvknumber.classList.remove("isValid");
    }
});


/*check if VAT valid*/
const vatnumber = document.getElementById("vatNumber");
vatnumber.addEventListener('input', function () {
    let vatnumberInput = vatnumber.value;
    let VATchek = window.location.pathname + `/v_check?VAT=${vatnumberInput}`;
    const re = /^[nN][lL][0-9]{9}[bB][0-9]{2}$/;

    if(re.test(vatnumberInput)) {
        hideVatNumberNotCorrect();
        vatnumber.classList.add("isValid");
        vatnumber.classList.remove("isInvalid");

    }else  {
        setVatNumberNotCorrect("Vat number not valid");
        vatnumber.classList.add("isInvalid");
        vatnumber.classList.remove("isValid")

    }

    //STILL TO BE DONE SO THAT IT CAN BE CHECKED ON THE 11 PROOF!!!!!!!!!!!!!!!!!!!!!!!!!
   /*fetch(VATchek)
       .then((response) => {
           if(!response){
               throw new Error("Response Error")

           }
           return   response.json();
       })
       .then((boolean) => {
           if (boolean === true) {
               hideVatNumberNotCorrect();
               vatnumber.classList.add("isValid");
               vatnumber.classList.remove("isInvalid")
           } else {
               setVatNumberNotCorrect("Vat number not valid");
               vatnumber.classList.add("isInvalid");
               vatnumber.classList.remove("isValid")
           }
       })
       .catch((error) => {
           console.log(error);
       })*/


});


/* check which button is checked */
const form = document.getElementById("form");

form.addEventListener('mousemove',function () {
    if (document.getElementById("business").checked) {
        showBusinessFields();
    }else {
        hideBusinessFields();
    }
});





/* SHOW BUSINESS FIELD METHOD */

function showBusinessFields(){


    document.getElementById("businessFields").style.visibility = "visible";
    document.getElementById("submitButton").disabled = true;


        // CHECK FIELD ARE VALID

    if (companyname.classList.contains("isValid") &&
        kvknumber.classList.contains("isValid") &&
        vatnumber.classList.contains("isValid") &&
        sector.value !== "") {
        document.getElementById("submitButton").disabled = false;
    } else {
        document.getElementById("submitButton").disabled = true;
    }



    document.getElementById("companyName").required = true;
    document.getElementById("kvkNumber").required = true;
    document.getElementById("vatNumber").required = true;
    document.getElementById("sector").required = true;


}


/*HIDE BUSINESS FIELD METHOD*/

function hideBusinessFields() {
    document.getElementById("submitButton").disabled = false;
    document.getElementById("businessFields").style.visibility = "hidden";
    document.getElementById("companyName").removeAttribute("required");
    document.getElementById("kvkNumber").removeAttribute("required");
    document.getElementById("vatNumber").removeAttribute("required");
    document.getElementById("sector").removeAttribute("required");


}




function setVatNumberNotCorrect(String) {
    const x = document.getElementById("invalidVatNumber");
    x.style.display = "inline";
    x.innerHTML = String;
}

function hideVatNumberNotCorrect() {
    const x = document.getElementById("invalidVatNumber");
    x.style.display = "none";
}







