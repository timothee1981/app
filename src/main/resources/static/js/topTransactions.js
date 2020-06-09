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
            document.getElementById("message").innerHTML = request.responseText;
        }
        else {
            alert('Something is wrong !!');
        }
    }
};
request.send(null);

//add something that fires the request (pageload?)



//vanuit Javascript aanmaak van een lege tabel
function tableCreate(){
        var body = document.body,
            tbl  = document.createElement('table');
        tbl.style.width  = '100px';
        tbl.style.border = '1px solid black';

        for(var i = 0; i < 10; i++){
            var tr = tbl.insertRow();
            for(var j = 0; j < 4; j++){
                if(i === 10 && j === 4){
                    break;
                } else {
                    var td = tr.insertCell();
                    td.appendChild(document.createTextNode('Cell'));
                    td.style.border = '1px solid black';
                    if(i === 1 && j === 1){
                        td.setAttribute('rowSpan', '10');
                    }
                }
            }
        }
        body.appendChild(tbl);
    }
    tableCreate();

//JSON opvangen die gestuurd(?) wordt en met die gegevens de tabel vullen??? callback function?





















// <p id="/api/top10transactions"></p>
//
// <script>
//     document.getElementById("").innerHTML =
//     obj.CustomerAndTransactions[1].firstName + " " +
//     obj.CustomerAndTransactions[2].lastName + " " +
//     obj.CustomerAndTransactions[3].numberOfTransactions + " " +
//     obj.CustomerAndTransactions[4].balance;
//     </script>