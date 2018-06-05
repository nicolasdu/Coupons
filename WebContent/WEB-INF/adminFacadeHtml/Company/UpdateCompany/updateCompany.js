var xmlhttp;
	function init() {
		
		xmlhttp=new XMLHttpRequest ();
	}
	
	function updateDetails(){
		var compid=document.getElementById("compid");
		var compname=document.getElementById("compname");
		var compemail=document.getElementById("compemail");
		var comppass=document.getElementById("comppass");
		var url = "http://localhost:8080/Coupons/rest/adminServices/updatecompany?id="+compid.value+"&name="+compname.value+"&email="+compemail.value+"&pass="+comppass.value;
		xmlhttp.open('PUT', url, true);
		xmlhttp.send(null);
		swal("Company ID = "+compid.value+" Has Been Updated !!");

	}