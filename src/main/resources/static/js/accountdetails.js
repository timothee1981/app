const accountNumber = document.getElementById("accountNumber");

updateClock();
loadingTable();


//FUNCTION THAT UPDATE CLOCK

function updateClock() {
    let now = new Date(), // current date
        months = ['January', 'February', 'March','April','Mei','June','July','Augustus','September','October','November','December'];

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



// Creating a new XMLHttpRequest object that loads Table transaction when window is loading

function loadingTable() {

    const request = new XMLHttpRequest();
//Initialize request
    let accountNumbervalue = accountNumber.innerText;
    request.open("GET", `/accountdetails/transactions?accountNumber=${accountNumbervalue}`, true);

//Send HTTP request & handle server output
    request.onreadystatechange = function () {
        if (request.readyState === 4) {
            if (request.status === 200) {
                createTable(JSON.parse(request.responseText));
            } else {
                alert('Something is wrong!');
            }
        }
    };
    request.send(null);
}

/*CREATING TABLE TRANSACTION*/

function createTable(transactions) {
    const body = document.body,
        tbl = document.createElement('div');
    tbl.setAttribute("class", "table");
    tbl.style.width = '50%';
    createTableHeaderRow(tbl);
    transactions.forEach(element => {
        createRow(element, tbl);
    });
    body.appendChild(tbl);
}

/*CREATING TABLE ROW HEADER*/

function createTableHeaderRow(tbl){
    const headerRow = document.createElement('div');
    headerRow.setAttribute("class","row header");
    tbl.appendChild(headerRow);
    createTableHeader("Date and Time", headerRow);
    createTableHeader("IBAN", headerRow);
    createTableHeader("Description", headerRow);
    createTableHeader("Amount", headerRow);

}

//ADDING CELL TO HEADER

function createTableHeader(title, row){
    const cell = document.createElement('div');
    cell.setAttribute("class","cell");
    cell.appendChild(document.createTextNode(title));
    row.appendChild(cell);
}


//CREATING TABLE ROW
function createRow(element, tbl){
    const row = document.createElement('div');

    row.setAttribute("class","row");
    tbl.appendChild(row);
    createTableCell(element.dateTime, row);
    createTableCell(element.bankAccountNumber, row)
    createTableCell(element.description, row);

    if(element.credit === null) {
        setAmountDebit(element.debit,row);
    }
    else {
        setAmountCredit(element.credit,row);
    }

}

//ADDING CELL TO ROW

function createTableCell(content, row){
    const cell = document.createElement('div');
    cell.setAttribute("class","cell");
    cell.appendChild(document.createTextNode(content));
    row.appendChild(cell);
}

//SET CELL AMOUNT TO DEBIT
function setAmountDebit(amount,row) {
    amount = numberWithCommas(amount.toFixed(2));
    createTableCell("- €" + amount,row);
 //   cell.setAttribute("id","cellred");
}

//SET CELL AMOUNT TO CREDIT
function setAmountCredit(amount,row){
    amount = numberWithCommas(amount.toFixed(2));
    const cell = document.createElement('div');
    cell.setAttribute("class","cell");
    cell.appendChild(document.createTextNode("+ €" + amount));
    row.appendChild(cell);
    cell.setAttribute("id","cellgreen");

}


//PUTTING AMOUNT TO RIGHT FORMAT
function numberWithCommas(x) {
    var parts = x.toString().split(".");
    parts[0]=parts[0].replace(/\B(?=(\d{3})+(?!\d))/g,".");
    return parts.join(",");
}






