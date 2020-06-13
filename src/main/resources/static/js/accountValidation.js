
const vatnumber = document.getElementById("vatNumber");
const kvknumber = document.getElementById("kvkNumber");
const companyname = document.getElementById("companyName");



/* Regex */
const companynameregex = /^[\w@ ]*[^\W_ ][\w- @ & +]*$/;

/* check companyname */
kvknumber.addEventListener('input', function () {
    validateKvkNumber();

});

vatnumber.addEventListener('input', function () {
    validateVatNumber();
});

companyname.addEventListener("input", function () {
    validateCompanyName();
});

/*check id Company name valid*/

function validateCompanyName() {
        let companyname = document.getElementById("companyName");
        let companynameInput = companyname.value;
        if (companynameregex.test(companynameInput)) {
           setElementIsValid("InvalidCompanyName");
        } else {
            setElementIsInvalid("InvalidCompanyName")
        }

}

function setElementIsValid(id){
    document.getElementById(id).style.display = "none";
    companyname.classList.add("isValid");
    companyname.classList.remove("isInvalid");

}

function setElementIsInvalid(id){
    document.getElementById(id).style.display = "inline";
    companyname.classList.add("isInvalid");
    companyname.classList.remove("isValid");

}

/*check if kvk valid*/


function validateKvkNumber() {
        let kvknumber = document.getElementById("kvkNumber");
        let kvknumberInput = kvknumber.value;
        const re = /^[0-9]{8}$/;
        if (re.test(kvknumberInput)) {
            document.getElementById("InvalidKvkNumber").style.display = "none";
            kvknumber.classList.add("isValid");
            kvknumber.classList.remove("isInvalid");
        } else {
            document.getElementById("InvalidKvkNumber").style.display = "inline";
            kvknumber.classList.add("isInvalid");
            kvknumber.classList.remove("isValid");
        }

}


/*set vat class to valid/invalid*/

function setVatClassInValid() {
    hideVatNumberNotCorrect();
    vatnumber.classList.add("isInvalid");
    vatnumber.classList.remove("isValid");
}

function setVatClassValid() {
    hideVatNumberNotCorrect();
    vatnumber.classList.add("isValid");
    vatnumber.classList.remove("isInvalid")
}

/* check if vat valid*/

function validateVatNumber() {
        const vatnumberInput = vatnumber.value;
        const VATchek = window.location.pathname + `/v_check?vatnumber=${vatnumber.value}`;

        const re = /^[nN][lL][0-9]{9}[bB][0-9]{2}$/;
        console.log(VATchek);
        if (!re.test(vatnumberInput)) {
            setVatClassInValid();

        } else {
            getUrlResponseVat(VATchek);
        }

}

function getUrlResponseVat(VATchek) {
    fetch(VATchek)
        .then((response) => {
            if (!response) {
                throw new Error("Response Error")

            }
            return response.json();
        })
        .then((data) => {
            if (data === true) {
                setVatClassValid();
            } else {
                setVatClassInValid();
            }
        })
        .catch((error) => {
            console.log(error);
        })
}


/* enable/disable submit button met click nb: kan waarschijnlijk in een methode maar weet niet hoe */


document.getElementById("businessFields").style.display = "none";
const sector = document.getElementById("sector");
const form = document.getElementById("form");
form.addEventListener('click', function () {
    if(document.getElementById("business").checked) {
        if (companyname.classList.contains("isValid") &&
            kvknumber.classList.contains("isValid") &&
            vatnumber.classList.contains("isValid") &&
            sector.value !== "") {
            document.getElementById("submitButton").disabled = false;
        } else {
            document.getElementById("submitButton").disabled = true;
        }
    }else{
        hideBusinessFields();
    }
});

/* enable/disable submit button met keyup*/

form.addEventListener('keyup', function () {
    if(companyname.classList.contains("isValid") &&
        kvknumber.classList.contains("isValid") &&
        vatnumber.classList.contains("isValid")&&
        sector.value !== "")
    {
        document.getElementById("submitButton").disabled = false;
    } else {
        document.getElementById("submitButton").disabled = true;
    }
});



/* SHOW BUSINESS FIELD METHOD */

function showBusinessFields() {

    document.getElementById("businessFields").style.display = "inline";
    document.getElementById("submitButton").disabled = true;
    document.getElementById("companyName").required = true;
    document.getElementById("kvkNumber").required = true;
    document.getElementById("vatNumber").required = true;
    document.getElementById("sector").required = true;



}


/*HIDE BUSINESS FIELD METHOD*/

function hideBusinessFields() {
    document.getElementById("submitButton").disabled = false;
    document.getElementById("businessFields").style.display = "none";
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







