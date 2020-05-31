/* check which button is checked */






/* hide or show fields according to which button is checked */

function hideBusinessFields() {
    document.getElementById("submitButton").disabled = false;
    document.getElementById("businessFields").style.visibility = "hidden";
    document.getElementById("companyName").removeAttribute("required");
    document.getElementById("kvkNumber").removeAttribute("required");
    document.getElementById("vatNumber").removeAttribute("required");
    document.getElementById("sector").removeAttribute("required");


}

function showBusinessFields() {

    document.getElementById("submitButton").disabled = true;
    if((companyname.classList.contains("isValid")&&
        kvknumber.classList.contains("isValid"))
    ){
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

/* check companyname */

const companyname = document.getElementById("companyName");
companyname.addEventListener("input",function () {
    let companyname = document.getElementById("companyName");
    let companynameInput = companyname.value;
    const re = /^[\w@ ]*[^\W_ ][\w- \@\ &\ \+]*$/;
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


/*check if sector has value*/



/* enable/disable submit button */

const form = document.getElementById("form");
form.addEventListener('keyup', function () {

    //CHECK WHICH BUTTON IS CHECKED

    if (document.getElementById("business").checked){
        showBusinessFields();
    } else {
        hideBusinessFields();

    }





});




