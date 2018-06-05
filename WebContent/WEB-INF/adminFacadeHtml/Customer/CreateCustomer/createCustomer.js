var xmlhttp;
	function init() {
		
		xmlhttp=new XMLHttpRequest ();
	}
	
	function setDetails() {
		var cusid=document.getElementById("cusid");
		var cusname=document.getElementById("cusname"); 
		var cuspass=document.getElementById("cuspass");
		var url = "http://localhost:8080/Coupons/rest/adminServices/createcustomer?id="+cusid.value+"&name="+cusname.value+"&pass="+cuspass.value;
		xmlhttp.open('POST',url, true);
		xmlhttp.send(null);
		alert("SENT");
		swal("Customer has been Added !!");
	}