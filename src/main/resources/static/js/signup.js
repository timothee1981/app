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


    // hide & show password
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
    // disable sumbit tot alle velden valid zijn
    document.getElementById("form").addEventListener('keyup', function () {
        document.getElementById("submitButton").disabled =
            !isInputValid("username") || !isInputValid("email") ||
            !isInputValid("showPasswordButton") || !isInputValid("phoneNumber") ||
            !isInputValid("firstName") || !isInputValid("lastName") ||
            !isInputValid("houseNumber") || !isInputValid("city") ||
            !isInputValid("street") || !isInputValid("postalCode") ||
            !isInputValid("BSN");
    });

    function isInputValid(elementId){
        return document.getElementById(elementId).classList.contains("isValid")
    }

    // bij mouseenter zet invalid velden op pink
    document.getElementById("signUpButton").addEventListener("mouseenter", function () {
        if (document.getElementById("submitButton").disabled === true) {
            setPasswordFieldGreyOrPink();
            setFieldGreyOrPink("username"); setFieldGreyOrPink("email");
            setFieldGreyOrPink("phoneNumber");setFieldGreyOrPink("firstName");
            setFieldGreyOrPink("lastName"); setFieldGreyOrPink("houseNumber");
            setFieldGreyOrPink("city"); setFieldGreyOrPink("street");
            setFieldGreyOrPink("postalCode"); setFieldGreyOrPink("BSN");
        }
    });

    function setPasswordFieldGreyOrPink(){
        !isInputValid("showPasswordButton") ? setFieldPink("password") : setFieldGrey("password");
    }

    function setFieldGreyOrPink(id){
        !isInputValid(id) ? setFieldPink(id) : setFieldGrey(id);
    }

   // bij mouseleave zet alles op grijs
    document.getElementById("signUpButton").addEventListener("mouseleave", function () {
        setFieldGrey("username"); setFieldGrey("email"); setFieldGrey("password");
        setFieldGrey("phoneNumber"); setFieldGrey("firstName"); setFieldGrey("lastName");
        setFieldGrey("houseNumber"); setFieldGrey("city"); setFieldGrey("street");
        setFieldGrey("postalCode"); setFieldGrey("BSN");
    });

    function setFieldPink(id){
        document.getElementById(id).style.backgroundColor = '#ffdede';
    }

    function setFieldGrey(id){
        document.getElementById(id).style.backgroundColor = '#f1f1f1';
    }
}();






