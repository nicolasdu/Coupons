var xmlhttp;
	function init() {
		
		xmlhttp=new XMLHttpRequest ();
	}
	
	function setDetails() {
		alert("Hello");
		var compid=document.getElementById("compid");
		var compname=document.getElementById("compname"); 
		var compemail=document.getElementById("compemail");
		var comppass=document.getElementById("comppass");
		var url = "http://localhost:8080/Coupons/rest/adminServices/createcompany?id="+compid.value+"&name="+compname.value+"&email="+compemail.value+"&pass="+comppass.value;
		xmlhttp.open('POST',url, true);
		xmlhttp.send(null);
		alert("SENT");
		swal("Company Has Been Created");
	}