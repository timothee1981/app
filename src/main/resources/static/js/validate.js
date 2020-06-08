class Validate{
    constructor(field) {
        this.field = field
    }
    field;

    BSN_LENGTH = 9;
    MIN_PASS_LENGTH = 10;
    MAX_PASS_LENGTH = 100;
    MIN_USERNAME_LENGTH = 3;
    MAX_USERNAME_LENGTH = 20;

    USERNAME_NOT_AVAILABLE = "Choose another username";
    USERNAME_IS_INVALID = "Between " + this.MIN_USERNAME_LENGTH + " and " + this.MAX_USERNAME_LENGTH + " letters and numbers";
    BSN_INCORRECT_LENGTH = "Enter " + this.BSN_LENGTH + " numbers";
    BSN_IS_INVALID = "Enter a valid BSN";

    IS_VALID_CLASS = "isValid";
    IS_INVALID_CLASS = "isInvalid";
    PASS_REQ_VALIDATED = "valid";
    PASS_REQ_INVALID = "invalid";
    USERNAME_ERROR_ID = "usernameNotAvailable";
    EMAIL_ERROR_ID = "InvalidEmail";
    PHONE_ERROR_ID = "InvalidPhoneNumber";
    BSN_ERROR_ID = "BSNNotAvailable";


    /**
     * FUNCTIONS
     */
    username() {
        const USERNAME_FIELD = document.getElementById("username");
        let username = USERNAME_FIELD.value;

        if (!regex.username.test(username) || username.length < this.MIN_USERNAME_LENGTH || username.length > this.MAX_USERNAME_LENGTH) {
            this.revealElementAndSetText(this.USERNAME_ERROR_ID, this.USERNAME_IS_INVALID);
            this.setElementInvalid("username");
        } else {
            let url = `/api/username?username=${username}`;
            const api = new API(url);
            api.isUnique(url).then(r => {
                if (r) {
                    this.setElementValid("username");
                    this.hideElement(this.USERNAME_ERROR_ID);
                } else {
                    this.setElementInvalid("username");
                    this.revealElementAndSetText(this.USERNAME_ERROR_ID, this.USERNAME_NOT_AVAILABLE);
                }
            })
        }
    }


    password(){
        this.checkAllRequirements();
        if (this.isPasswordValid()) {
            this.setElementValid("showPasswordButton");
        } else {
            this.setElementInvalid("showPasswordButton");
        }
    }

    checkAllRequirements(){
        this.checkPassLength();
        this.checkRequirement(regex.lowerCase, "letter");
        this.checkRequirement(regex.lowerCase, "letter");
        this.checkRequirement(regex.upperCase, "capital") ;
        this.checkRequirement(regex.numbers, "number");
        this.checkRequirement(regex.specials, "special");
    }

    isPasswordValid(){
        return document.getElementById("letter").classList.contains(this.PASS_REQ_VALIDATED) &&
            document.getElementById("capital").classList.contains(this.PASS_REQ_VALIDATED) &&
            document.getElementById("number").classList.contains(this.PASS_REQ_VALIDATED) &&
            document.getElementById("special").classList.contains(this.PASS_REQ_VALIDATED) &&
            document.getElementById("length").classList.contains(this.PASS_REQ_VALIDATED);
    }


    names(id){
        let nameInput = document.getElementById(id).value;

        if (regex.username.test(nameInput)) {
            this.setElementValid(id);
        } else {
            this.setElementInvalid(id);
        }
    }


    email(){
        const EMAIL_FIELD = document.getElementById("email");

        if (regex.email.test(EMAIL_FIELD.value)) {
            this.hideElement(this.EMAIL_ERROR_ID);
            this.setElementValid("email");
        } else {
            this.revealElement(this.EMAIL_ERROR_ID);
            this.setElementInvalid("email");
        }
    }


    phoneNumber(){
        const PHONE_NUMBER_FIELD = document.getElementById("phoneNumber");

        if (regex.phoneNumber.test(PHONE_NUMBER_FIELD.value)) {
            this.hideElement(this.PHONE_ERROR_ID);
            this.setElementValid("phoneNumber");

        } else {
            this.revealElement(this.PHONE_ERROR_ID);
            this.setElementInvalid("phoneNumber")
        }
    }


    bsn(){
        const BSN_FIELD = document.getElementById("BSN");

        let BSNInput = BSN_FIELD.value;

        if (BSNInput.length !== this.BSN_LENGTH) {
            this.setElementInvalid("BSN");
            this.revealElementAndSetText(this.BSN_ERROR_ID, this.BSN_INCORRECT_LENGTH);

        } else if (!this.passCheckDigit(BSNInput)) {
            this.setElementInvalid("BSN");
            this.revealElementAndSetText(this.BSN_ERROR_ID, this.BSN_IS_INVALID);

        } else {
            let url = `/api/bsn?bsn=${BSNInput}`;
            const api = new API(url);
            api.isUnique(url).then(r => {
                if (r) {
                    this.setElementValid("BSN");
                    this.hideElement(this.BSN_ERROR_ID);
                } else {
                    this.setElementInvalid("BSN");
                    this.revealElementAndSetText(this.BSN_ERROR_ID, this.BSN_IS_INVALID);
                }
            });
        }
    }


    // 11-proef
    passCheckDigit(BSN) {
        if(BSN.length !== this.BSN_LENGTH){
            return false;
        }

        const firstNumbers = BSN.substring(0, this.BSN_LENGTH -1 );
        const lastNumber = BSN.charAt(this.BSN_LENGTH - 1);
        let sum = 0;
        let i;
        for (i = 0; i < firstNumbers.length; i++) {
            sum += firstNumbers.charAt(i) * (this.BSN_LENGTH - i);
        }
        sum += lastNumber;

        return sum % 11 === 0;
    }


    postalCode(){
        const POSTAL_CODE_FIELD = document.getElementById("postalCode");

        if (regex.postalCode.test(POSTAL_CODE_FIELD.value)) {
            this.setElementValid("postalCode");
        } else {
            this.setElementInvalid("postalCode");
        }
    }


    addressFields(){

        if(this.isInputValid("postalCode")){
            const api = new API();
            api.cityAddress().then(r => {
                if(r !== undefined){
                    this.setValue("city", r.city);
                    this.setValue("street", r.street);
                    this.setElementValid("houseNumber");
                } else {
                    this.emptyValue("city");
                    this.emptyValue("street");
                    this.setElementInvalid("houseNumber");
                }
            })

        } else {
            this.removeValidation("houseNumber");
            this.emptyValue("street");
            this.emptyValue("city");
        }
    }


    /**
     * SETTERS
     */
    checkRequirement(regex, id) {
        let element = document.getElementById(id);
        document.getElementById("password").value.match(regex) ? this.setPassRequirementValid(element) : this.setPassRequirementInvalid(element);
        return element.classList.contains(this.PASS_REQ_VALIDATED)
    }

    setPassRequirementValid(requirement) {
        requirement.classList.remove(this.PASS_REQ_INVALID);
        requirement.classList.add(this.PASS_REQ_VALIDATED);
    }

    setPassRequirementInvalid(requirement){
        requirement.classList.remove(this.PASS_REQ_VALIDATED);
        requirement.classList.add(this.PASS_REQ_INVALID);
    }

    checkPassLength() {
        const PASSWORD_FIELD = document.getElementById("password");
        const LENGTH_REQ = document.getElementById("length");

        PASSWORD_FIELD.value.length >= this.MIN_PASS_LENGTH && PASSWORD_FIELD.value.length <= this.MAX_PASS_LENGTH
            ? this.setPassRequirementValid(LENGTH_REQ) : this.setPassRequirementInvalid(LENGTH_REQ)

    }

    setValue(id, value){
        document.getElementById(id).value = value;
        this.setElementValid(id);
    }

    emptyValue(id){
        document.getElementById(id).value = "";
        document.getElementById(id).classList.remove(this.IS_VALID_CLASS);
    }

     setElementValid(id){
        document.getElementById(id).classList.remove(this.IS_INVALID_CLASS);
        document.getElementById(id).classList.add(this.IS_VALID_CLASS);
    }

     setElementInvalid(id){
        document.getElementById(id).classList.add(this.IS_INVALID_CLASS);
        document.getElementById(id).classList.remove(this.IS_VALID_CLASS);
    }

    removeValidation(id){
        if(document.getElementById(id).classList.contains(this.IS_VALID_CLASS)){
            document.getElementById(id).classList.remove(this.IS_VALID_CLASS);
        }

        if(document.getElementById(id).classList.contains(this.IS_INVALID_CLASS)){
            document.getElementById(id).classList.remove(this.IS_INVALID_CLASS);
        }
    }

     revealElementAndSetText(id, String){
        document.getElementById(id).style.display = 'inline';
        document.getElementById(id).innerHTML = String;
    }

    isInputValid(id){
        return document.getElementById(id).classList.contains(this.IS_VALID_CLASS)
    }

    revealElement(id){
        document.getElementById(id).style.display = "inline";
    }

    hideElement(id){
        document.getElementById(id).style.display = "none";
    }
}