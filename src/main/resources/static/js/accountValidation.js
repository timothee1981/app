




/* check companyname */
const sector = document.getElementById("sector");
const companyname = document.getElementById("companyName");
companyname.addEventListener("input",function () {
    let companyname = document.getElementById("companyName");
    let companynameInput = companyname.value;
    const re = /^[\w@ ]*[^\W_ ][\w- @ & +]*$/;
    if(re.test(companynameInput)) {
        companyname.classList.add("isValid");
        companyname.classList.remove("isInvalid");
    }else{
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
        kvknumber.classList.add("isValid");
        kvknumber.classList.remove("isInvalid");
    }else{
        kvknumber.classList.add("isInvalid");
        kvknumber.classList.remove("isValid");
    }
});


/*check if VAT valid*/
const vatnumber = document.getElementById("vatNumber");
vatnumber.addEventListener('input', function () {
    let vatnumberInput = vatnumber.value;
    const re = /^[nN][lL][0-9]{9}[bB][0-9]{2}$/;

    if(re.test(vatnumberInput)) {
        vatnumber.classList.add("isValid");
        vatnumber.classList.remove("isInvalid")

    } else {
        vatnumber.classList.add("isInvalid");
        vatnumber.classList.remove("isValid");
    }
});




/* check which button is checked */
const form = document.getElementById("form");
form.addEventListener('click', function () {

    //CHECK WHICH BUTTON IS CHECKED

    if (document.getElementById("business").checked){
        showBusinessFields();
    } else {
        hideBusinessFields();

    }

});

/* hide or show fields according to which button is checked */



function showBusinessFields() {


    document.getElementById("submitButton").disabled = true;
    if (companyname.classList.contains("isValid") &&
        kvknumber.classList.contains("isValid")&&
        vatnumber.classList.contains("isValid")&&
        sector.value !== "" ) {
        document.getElementById("submitButton").disabled = false;
        } else {
        document.getElementById("submitButton").disabled = true;
        }



    document.getElementById("businessFields").style.visibility = "visible";
    document.getElementById("companyName").required = true;
    document.getElementById("kvkNumber").required = true;
    document.getElementById("vatNumber").required = true;
    document.getElementById("sector").required = true;

}

function hideBusinessFields() {
    document.getElementById("submitButton").disabled = false;
    document.getElementById("businessFields").style.visibility = "hidden";
    document.getElementById("companyName").removeAttribute("required");
    document.getElementById("kvkNumber").removeAttribute("required");
    document.getElementById("vatNumber").removeAttribute("required");
    document.getElementById("sector").removeAttribute("required");


}


function setVatNumberNotCorrect(String) {
    const x = document.getElementById("vatNumberNotAvailable");
    x.style.display = "inline";
    x.innerHTML = String;
}

function hideVatNumberNotCorrect() {
    const x = document.getElementById("vatNumberNotAvailable");
    x.style.display = "none";
}







