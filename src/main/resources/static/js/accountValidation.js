



if (document.getElementById("business").checked){
    showBusinessFields();
} else{
    hideBusinessFields();
}



function hideBusinessFields() {
    document.getElementById("businessFields").style.visibility = "hidden";
    document.getElementById("companyName").removeAttribute("required");
    document.getElementById("kvkNumber").removeAttribute("required");
    document.getElementById("vatNumber").removeAttribute("required");
    document.getElementById("sector").removeAttribute("required");

}
function showBusinessFields() {
    document.getElementById("businessFields").style.visibility = "visible";
    document.getElementById("companyName").required = true;
    document.getElementById("kvkNumber").required = true;
    document.getElementById("vatNumber").required = true;
    document.getElementById("sector").required = true;



}