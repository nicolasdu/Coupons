var xmlhttp;
	function init() {
		
		xmlhttp=new XMLHttpRequest ();
	}
	
	function updateDetails(){
		alert("update");
		var custid=document.getElementById("custid");
		var custname=document.getElementById("custname");
		var custpass=document.getElementById("custpass");
		var url = "http://localhost:8080/Coupons/rest/adminServices/updatecustomer?id="+custid.value+"&name="+custname.value+"&pass="+custpass.value;
		alert(url);
		xmlhttp.open('PUT', url, true);
		xmlhttp.send(null);
}	