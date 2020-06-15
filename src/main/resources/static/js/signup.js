
SignUp = function() {
    const fieldsToValidate = ["username", "email", "password", "phoneNumber", "firstName",
        "lastName", "city", "street", "postalCode", "houseNumber", "BSN"];

    const passRequirements = { lowercase: regex.lowerCase, uppercase: regex.upperCase,
        number: regex.numbers, special: regex.specials, length: regex.pass_length };

    const validate = new Validate();
    const submitButton = new SubmitButton;

    document.getElementById("username").addEventListener("input", () => {
        validate.username()});
    document.getElementById("password").addEventListener('input', () => {
        validate.password(passRequirements)});
    document.getElementById("firstName").addEventListener('input', () => {
        validate.names("firstName")});
    document.getElementById("lastName").addEventListener('input', () => {
        validate.names("lastName")});
    document.getElementById("email").addEventListener('input', () => {
        validate.email()});
    document.getElementById("phoneNumber").addEventListener('input', () => {
        validate.phoneNumber()});
    document.getElementById("BSN").addEventListener("input", () => {
        validate.bsn()});
    document.getElementById("postalCode").addEventListener('input', () => {
        validate.postalCode()});
    document.getElementById("addressFields").addEventListener('input', () => {
        validate.addressFields()});

    document.getElementById("signUpButton").addEventListener("mouseenter", () => {
        submitButton.mouseEnter(fieldsToValidate)});
    document.getElementById("signUpButton").addEventListener("mouseleave", () => {
        submitButton.mouseLeave(fieldsToValidate);
    });

    // Duct-tape oplossing voor tegen auto-fill: timer ipv EventListener
    window.setInterval( () => {
        submitButton.checkFields(fieldsToValidate);
    }, 50)
}();



passwordFeatures = function(){
    // reveal requirements
    document.getElementById("passwordElements").addEventListener('focusin', () => {
        document.getElementById("passRequirements1of2").style.display = 'inline';
        document.getElementById("passRequirements2of2").style.display = 'inline';
    });

    // hide requirements
    document.getElementById("passwordElements").addEventListener('focusout', () => {
        document.getElementById("passRequirements1of2").style.display = 'none';
        document.getElementById("passRequirements2of2").style.display = 'none';
    });


    // hide & show button
    document.getElementById("showPasswordButton").addEventListener('click', () => {
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