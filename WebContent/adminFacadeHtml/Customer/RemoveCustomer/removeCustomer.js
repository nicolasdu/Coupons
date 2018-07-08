
	var xmlhttp;
	function init (){
		
		xmlhttp=new XMLHttpRequest ();
	}
	function removeDetails (){
		
		var custid=document.getElementById("custid");
		var url = "http://localhost:8080/Coupons/rest/adminServices/deletecustomer?custid="+custid.value;
		xmlhttp.open('DELETE', url, true);
		xmlhttp.send(null);	
		swal("Customer Has Been Removed !!");
}