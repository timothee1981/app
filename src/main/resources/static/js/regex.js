const regex = (function () {

    const USERNAME = /^[a-zA-Z0-9_-]+$/;
    const EMAIL = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    const NAMES = /^[^\s]*[a-zA-Z\s,.'\-][^\s]{1,100}$/;
    const PHONE_NUMBER = /^(((0)[1-9]{2}[0-9][-]?[1-9][0-9]{5})|((\\+31|0|0031)[1-9][0-9][-]?[1-9][0-9]{6}))$|^(((\\+31|0|0031)6)[1-9][0-9]{7})$/;
    const POSTAL_CODE = /^[1-9][0-9]{3} ?(?!sa|SA|Sa|sA|sd|SD|sD|Sd|ss|SS|sS)[a-zA-Z]{2}$/;
    const LOWERCASE = /[a-z]/g;
    const UPPERCASE = /[A-Z]/g;
    const NUMBERS = /[0-9]/g;
    const SPECIALS = /[!"#$%&'()*+,\-./:;<=>?@^_`{|}~\[\]]/g;

    return {
        username : USERNAME,
        email : EMAIL,
        names : NAMES,
        phoneNumber : PHONE_NUMBER,
        postalCode : POSTAL_CODE,
        lowerCase : LOWERCASE,
        upperCase : UPPERCASE,
        numbers : NUMBERS,
        specials : SPECIALS,
    }
})();