var xmlhttp;
	function init (){
		
		xmlhttp=new XMLHttpRequest ();
	}
function getDetails (){
		var comp=document.getElementById("compId");
		var url = "http://localhost:8080/Coupons/rest/adminServices/get?compId="+comp.value;
		xmlhttp.open('GET', url, true);
		xmlhttp.send(null);
		xmlhttp.onreadystatechange=function(){
			
			var compname=document.getElementById("compname"); 
			var compemail=document.getElementById("compemail"); 

			if(xmlhttp.readyState == 4){
				if(xmlhttp.status == 200){
					
					var det= eval("(" + xmlhttp.responseText+")");	
					if(comp.value > 0){
			
						compname.value=det.name;
						compemail.value=det.compemail;
					}
					else{
						compname.value="";
						compemail.value="";
						aler(" Invalid Company Id ");
					}
				}
				else { 
					
					alert("Error -->" +xmlhttp.responseText);
				}
			}
			
			
		}
}