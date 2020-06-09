//Creating a new XMLHttpRequest object
var request;
if (window.XMLHttpRequest) {
    request = new XMLHttpRequest(); //for IE7+, Firefox, Chrome, Opera, Safari
}
else {
    request = new ActiveXObject("Microsoft.XMLHTTP"); //for IE6, IE5
}

//Initialize request
request.open("GET","/api/top10transactions", true);

//Send HTTP request & handle server output
request.onreadystatechange = function() {
    if (request.readyState === 4) {
        if (request.status === 200) {
            tableCreate(JSON.parse(request.responseText));
        }
        else {
            alert('Something is wrong !!');
        }
    }
};
request.send(null);


function tableCreate(top10transactions) {
    var body = document.body,
        tbl = document.createElement('table');
    tbl.style.width = '65%';
    var row = tbl.insertRow();
    var cell = document.createElement('th');
    cell.appendChild(document.createTextNode("First name"));
    row.appendChild(cell);

    var cell = document.createElement('th');
    cell.appendChild(document.createTextNode("Last name"));
    row.appendChild(cell);

    var cell = document.createElement('th');
    cell.appendChild(document.createTextNode("Number of transactions"));
    row.appendChild(cell);

    var cell = document.createElement('th');
    cell.appendChild(document.createTextNode("Balance"));
    row.appendChild(cell);


    for (var i = 0; i < top10transactions.length; i++) {
        var row = tbl.insertRow();

        var cell = row.insertCell();
        cell.appendChild(document.createTextNode('Cell'));
        cell.innerHTML = top10transactions[i].firstName;

        var cell = row.insertCell();
        cell.appendChild(document.createTextNode('Cell'));
        cell.innerHTML = top10transactions[i].lastName;

        var cell = row.insertCell();
        cell.appendChild(document.createTextNode('Cell'));
        cell.innerHTML = top10transactions[i].numberOfTransactions;

        var cell = row.insertCell();
        cell.appendChild(document.createTextNode('Cell'));
        cell.innerHTML = top10transactions[i].balance.toFixed(2);
    }
    body.appendChild(tbl);
}
