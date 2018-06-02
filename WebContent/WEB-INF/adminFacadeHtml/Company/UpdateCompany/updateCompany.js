var xmlhttp;
	function init() {
		
		xmlhttp=new XMLHttpRequest ();
		alert("xml");
	}
	
	function updateDetails(){
		alert("update");
		var compid=document.getElementById("compid");
		alert(compid);
		var compname=document.getElementById("compname");
		var compemail=document.getElementById("compemail");
		var comppass=document.getElementById("comppass");
		var url = "http://localhost:8080/Coupons/rest/adminServices/updatecompany?id="+compid.value+"&name="+compname.value+"&email="+compemail.value+"&pass="+comppass.value;
		alert(url);
		xmlhttp.open('PUT', url, true);
		xmlhttp.send(null);
	}