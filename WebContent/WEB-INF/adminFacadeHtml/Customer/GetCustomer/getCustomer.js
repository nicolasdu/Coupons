var xmlhttp;
	function init (){
		
		xmlhttp=new XMLHttpRequest ();
	}
function getDetails (){
		var custId=document.getElementById("custId");
		var url = "http://localhost:8080/Coupons/rest/adminServices/getcustomer?custId="+custId.value;
		xmlhttp.open('GET', url, true);
		xmlhttp.send(null);
		xmlhttp.onreadystatechange=function(){
			
			var custname=document.getElementById("custname"); 
			var custpass=document.getElementById("custpass"); 

			if(xmlhttp.readyState == 4){
				if(xmlhttp.status == 200){
					
					var det= eval("(" + xmlhttp.responseText+")");	
					if(custId.value > 0){
			
						custname.value=det.name;
						custpass.value=det.custpass;
					}
					else{
						custname.value="";
						custpass.value="";
						aler(" Invalid Company Id ");
					}
				}
				else { 
					
					alert("Error -->" +xmlhttp.responseText);
				}
			}
			
			
		}
}