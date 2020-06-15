const api = new API();
document.getElementById('businessAccountIban').addEventListener('input', function () {
    let accountNumber = document.getElementById('businessAccountIban').value;
    api.isBusinessAccount(`/api/isBusinessAccount?accountNumber=${accountNumber}`).then(r => {
        console.log(r);
        if (r) {
            document.getElementById('submit').disabled = false;
        } else {
            document.getElementById('submit').disabled = true;
        }
    })
});