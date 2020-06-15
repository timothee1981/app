const accountNumber = document.getElementById("accountNumber");
function updateClock() {
    let now = new Date(), // current date
        months = ['January', 'February', 'March','April','Mei','June','July','Augustus','September','October','November','December']; // you get the idea

    let time = now.getHours() + ':' + ((now.getMinutes() < 10)?"0":"") + now.getMinutes();

    // a cleaner way than string concatenation
    date = [now.getDate(),
        months[now.getMonth()],
        now.getFullYear()].join(' ');

    // set the content of the element with the ID time to the formatted string
    document.getElementById('dateTime').innerHTML = [date, time].join(' / ');

    // call this function again in 1000ms
    setTimeout(updateClock, 1000);
}
updateClock(); // initial call
<!--TABLE HOUDT INFORMATIE OVER TRANSACTIE REKENING HOUDER -->
// Creating a new XMLHttpRequest object
const request = new XMLHttpRequest();
//Initialize request
let accountNumbervalue = accountNumber.innerText;
request.open("GET",`/accountdetails/transactions?accountNumber=${accountNumbervalue}`, true);

//Send HTTP request & handle server output
request.onreadystatechange = function() {
    if (request.readyState === 4) {
        if (request.status === 200) {
            createTable(JSON.parse(request.responseText));
        }
        else {
            alert('Something is wrong!');
        }
    }
};
request.send(null);

function createTable(transactions) {
    const body = document.body,
        tbl = document.createElement('div');
    tbl.setAttribute("class", "table");
    tbl.style.width = '50%';
    const headerRow = document.createElement('div');
    headerRow.setAttribute("class","row header");
    tbl.appendChild(headerRow);
    createTableHeader("Date and Time", headerRow);
    createTableCell("IBAN", headerRow);
    createTableHeader("Description", headerRow);
    createTableHeader("Amount", headerRow);

    transactions.forEach(element => {
        console.log(element.credit);
        console.log(element.debit);
        let amount;
        if(element.credit === null) {
            amount = element.debit;
        }
        else{
            amount = element.credit;
        }
        console.log(amount);
        const row = document.createElement('div');
        row.setAttribute("class","row");

        tbl.appendChild(row);

        createTableCell(element.dateTime, row);
        createTableCell(element.bankAccountNumber, row)
        createTableCell(element.description, row);

        if(element.credit === null) {
            amount = element.debit;
            amount = numberWithCommas(amount.toFixed(2));
            createTableCell("- €" + amount, row);
            row.setAttribute("id","rowred");
        }
        else {
            amount = element.credit;
            amount = numberWithCommas(amount.toFixed(2));
            createTableCell("+ €" + amount, row);
            row.setAttribute("id","rowgreen");

        }
    });
    body.appendChild(tbl);
}

function numberWithCommas(x) {
    var parts = x.toString().split(".");
    parts[0]=parts[0].replace(/\B(?=(\d{3})+(?!\d))/g,".");
    return parts.join(",");
}

function createTableHeader(title, row){
    const cell = document.createElement('div');
    cell.setAttribute("class","cell");
    cell.appendChild(document.createTextNode(title));
    row.appendChild(cell);
}

function createTableCell(content, row){
    const cell = document.createElement('div');
    cell.setAttribute("class","cell");
    cell.appendChild(document.createTextNode(content));
    row.appendChild(cell);
}


