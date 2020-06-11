
SignUp = function() {
    const allFields = ["username", "email", "password", "phoneNumber", "firstName",
        "lastName", "city", "street", "postalCode", "houseNumber", "BSN"];
    const validate = new Validate();
    const submitButton = new SubmitButton;

    document.getElementById("username").addEventListener("input", function () {
        validate.username()});
    document.getElementById("password").addEventListener('input', function () {
        validate.password()});
    document.getElementById("firstName").addEventListener('input', function () {
        validate.names("firstName")});
    document.getElementById("lastName").addEventListener('input', function () {
        validate.names("lastName")});
    document.getElementById("email").addEventListener('input', function () {
        validate.email()});
    document.getElementById("phoneNumber").addEventListener('input', function () {
        validate.phoneNumber()});
    document.getElementById("BSN").addEventListener("input", function () {
        validate.bsn()});
    document.getElementById("postalCode").addEventListener('input', function () {
        validate.postalCode()});
    document.getElementById("addressFields").addEventListener('input', function () {
        validate.addressFields()});

    document.getElementById("signUpButton").addEventListener("mouseenter", function() {
        submitButton.mouseEnter(allFields)});
    document.getElementById("signUpButton").addEventListener("mouseleave", function() {
        submitButton.mouseLeave(allFields)});
    document.getElementById("form").addEventListener("keyup", function(){
        submitButton.checkFields(allFields)});
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