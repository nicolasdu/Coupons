	var xmlhttp;
	function init (){
		
		xmlhttp=new XMLHttpRequest ();
	}
function getDetails (){
		alert("get");
		var coupon=document.getElementById("couponId");
		var url = "http://localhost:8080/Coupons/rest/companyService/getcoupon?couponId="+coupon.value;
		xmlhttp.open('GET', url, true);
		xmlhttp.send(null);
		alert("sent");
		xmlhttp.onreadystatechange=function(){
			
			var start=document.getElementById("start"); 
			var end=document.getElementById("end"); 
			var type=document.getElementById("type"); 
			var message=document.getElementById("message"); 
			var couptitle=document.getElementById("couptitle"); 
			var amount=document.getElementById("amount"); 
			var image=document.getElementById("image"); 
			var price=document.getElementById("price"); 
			
			if(xmlhttp.readyState == 4){
				if(xmlhttp.status == 200){
						
					var det= eval("(" + xmlhttp.responseText+")");	
					if(coupon.value > 0){
			
						start.value=det.start;
						end.value=det.end;
						type.value=det.type;
						message.value=det.message;
						couptitle.value=det.couptitle;
						amount.value=det.amount;
						image.value=det.image;
						price.value=det.price;
						
					}
					else{
						
						start.value="";
						end.value="";
						type.value="";
						message.value="";
						couptitle.value="";
						amount.value="";
						image.value="";
						price.value="";
						aler(" Invalid Coupon Id ");
					}
				}
				else { 
					
					alert("Error -->" +xmlhttp.responseText);
				}
			}
			
		}
}