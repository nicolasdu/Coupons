var xmlhttp;
	function init (){
		
		xmlhttp=new XMLHttpRequest ();
	}
	function removeDetails (){
		
		var comp=document.getElementById("compid");
		var url = "http://localhost:8080/Coupons/rest/adminServices/"+comp.value;
		xmlhttp.open('DELETE', url, true);
		xmlhttp.send(null);	
}