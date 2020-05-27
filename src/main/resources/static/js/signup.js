const disableButton = false; //change this value to false and the button will be clickable
const button = document.getElementById('submit');

if (disableButton) button.disabled = "disabled";git
// Show password button
function showPassword() {
    const x = document.getElementById("password");
    if (x.type === "password") {
        x.type = "text";
    } else {
        x.type = "password";
    }
}

// Show password requirements
function showPasswordRequirements() {
    const x = document.getElementById("passRequirements1of2");
    const y = document.getElementById("passRequirements2of2");
    x.style.display = "inline";
    y.style.display = "inline";
}

// Hide password requirements
function hidePasswordRequirements() {
    const x = document.getElementById("passRequirements1of2");
    const y = document.getElementById("passRequirements2of2");
    x.style.display = "none";
    y.style.display = "none";
}


// Show & hide UsernameNotAvailable
function showUsernameNotAvailable() {
    const x = document.getElementById("usernameNotAvailable");
    x.style.display = "inline";
}

function hideUsernameNotAvailable() {
    const x = document.getElementById("usernameNotAvailable");
    x.style.display = "none";
}

// Show & hide BSNNotAvailable
function showUBSNNotAvailable() {
    const x = document.getElementById("BSNNotAvailable");
    x.style.display = "inline";
}

function hideBSNNotAvailable() {
    const x = document.getElementById("BSNNotAvailable");
    x.style.display = "none";
}

// Password validation
const myInput = document.getElementById("password");
const letter = document.getElementById("letter");
const capital = document.getElementById("capital");
const number = document.getElementById("number");
const special = document.getElementById("special");
const length = document.getElementById("length");

// When the user starts to type something inside the password field
myInput.onkeyup = function() {
    // Validate lowercase letters
    let lowerCaseLetters = /[a-z]/g;
    if(myInput.value.match(lowerCaseLetters)) {
        letter.classList.remove("invalid");
        letter.classList.add("valid");

    } else {
        letter.classList.remove("valid");
        letter.classList.add("invalid");
    }

    // Validate capital letters
    let upperCaseLetters = /[A-Z]/g;
    if(myInput.value.match(upperCaseLetters)) {
        capital.classList.remove("invalid");
        capital.classList.add("valid");
    } else {
        capital.classList.remove("valid");
        capital.classList.add("invalid");
    }

    // Validate numbers
    let numbers = /[0-9]/g;
    if(myInput.value.match(numbers)) {
        number.classList.remove("invalid");
        number.classList.add("valid");
    } else {
        number.classList.remove("valid");
        number.classList.add("invalid");
    }

    // Validate special character
    let specials = /[@$!%*?&]/g;
    if(myInput.value.match(specials)) {
        special.classList.remove("invalid");
        special.classList.add("valid");
    } else {
        special.classList.remove("valid");
        special.classList.add("invalid");
    }

    // Validate length
    if(myInput.value.length >= 10) {
        length.classList.remove("invalid");
        length.classList.add("valid");
    } else {
        length.classList.remove("valid");
        length.classList.add("invalid");
    }
};

let correctUsername = false;
let correctBSN = false;

/* Real-time username check in database */
let userNameInput = document.getElementById("username");
userNameInput.addEventListener("input", function () {
    let userName = userNameInput.value;
    let usernameCheck = window.location.pathname + `/u_check?username=${userName}`;

    fetch(usernameCheck)
        .then((response) => {
            if (!response.ok) {
                throw new Error("Response error");
            }
            return response.json();
        })
        .then((data) => {
            console.log(data);
            if (userName.length > 1) {
                if (data === true) {
                    hideUsernameNotAvailable();
                    correctUsername = true;
                } else {
                    showUsernameNotAvailable();
                    correctUsername = false;
                }
            }
        })
        .catch((error) => {
            console.log(error);
        })
});


/* Real-time BSN check in database */
let BSNInput = document.getElementById("BSN");
BSNInput.addEventListener("input", function () {
    let BSN = BSNInput.value;
    let BSNCheck = window.location.pathname + `/b_check?BSN=${BSN}`;

    fetch(BSNCheck)
        .then((response) => {
            if (!response.ok) {
                throw new Error("Response error");
            }
            return response.json();
        })
        .then((data) => {
            console.log(data);
            if (data === false && BSN.length === 9) {
                showUBSNNotAvailable();
                correctBSN = false;
            } else if (data === true){
                hideBSNNotAvailable();
                correctBSN = true;
            } else {
                hideBSNNotAvailable();
               correctBSN = false;
            }
        })
        .catch((error) => {
            console.log(error);
        })
});

