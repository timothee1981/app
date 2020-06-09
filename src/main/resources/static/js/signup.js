validation = function() {
    const validate = new Validate();
    document.getElementById("username").addEventListener("input", function () {
        validate.username(); });
    document.getElementById("password").addEventListener('input', function () {
        validate.password(); });
    document.getElementById("firstName").addEventListener('input', function () {
        validate.names("firstName") });
    document.getElementById("lastName").addEventListener('input', function () {
        validate.names("lastName") });
    document.getElementById("email").addEventListener('input', function () {
        validate.email() });
    document.getElementById("phoneNumber").addEventListener('input', function () {
        validate.phoneNumber() });
    document.getElementById("BSN").addEventListener("input", function () {
        validate.bsn() });
    document.getElementById("postalCode").addEventListener('input', function () {
        validate.postalCode() });
    document.getElementById("addressFields").addEventListener('input', function () {
        validate.addressFields() });
}();

passwordFeatures = function(){
    // reveal requirements
    document.getElementById("passwordElements").addEventListener('focusin', function() {
        document.getElementById("passRequirements1of2").style.display = 'inline';
        document.getElementById("passRequirements2of2").style.display = 'inline';
    });

    // hide requirements
    document.getElementById("passwordElements").addEventListener('focusout', function() {
        document.getElementById("passRequirements1of2").style.display = 'none';
        document.getElementById("passRequirements2of2").style.display = 'none';
    });


    // hide & show button
    document.getElementById("showPasswordButton").addEventListener('click', function(){
        const passwordField = document.getElementById("password");
        const showPasswordButton = document.getElementById("showPasswordButton");
        if (passwordField.type === "password") {
            passwordField.type = "text";
            showPasswordButton.value = "hide";
        } else {
            passwordField.type = "password";
            showPasswordButton.value = "show"
        }
    });
}();

submitButton = function() {
    const validate = new Validate();
    const allFields = ["username", "email", "password", "phoneNumber", "firstName",
        "lastName", "city", "street", "postalCode", "houseNumber", "BSN"];

    // zet button disabled uit als alle velden valid zijn
    document.getElementById("form").addEventListener('keyup', function () {
        document.getElementById("submitButton").disabled = false;
        for (let i = 0; i < allFields.length ; i++) {
            if(validate.isInputValid(allFields[i]) === false){
                document.getElementById("submitButton").disabled = true;
                break;
            }
        }
    });

    // bij mouseenter zet invalid velden op pink
    document.getElementById("signUpButton").addEventListener("mouseenter", function () {
        if (document.getElementById("submitButton").disabled === true) {
            for (let i = 0; i < allFields.length ; i++) {
                setFieldGreyOrPink(allFields[i]);
            }
        }
    });

   // bij mouseleave zet alles op grijs
    document.getElementById("signUpButton").addEventListener("mouseleave", function () {
        for (let i = 0; i < allFields.length ; i++) {
            setFieldGrey(allFields[i]);
        }
    });

    function setFieldGreyOrPink(id){
        if(id === "password"){
            validate.isInputValid("showPasswordButton") ? setFieldGrey(id) : setFieldPink(id) ;
        }
        validate.isInputValid(id) ? setFieldGrey(id) : setFieldPink(id);
    }

    function setFieldPink(id){
        document.getElementById(id).style.backgroundColor = '#ffdede';
    }

    function setFieldGrey(id){
        document.getElementById(id).style.backgroundColor = '#f1f1f1';
    }
}();






