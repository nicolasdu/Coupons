var xmlhttp;
	function init (){
		
		xmlhttp=new XMLHttpRequest ();
	}
	function removeDetails (){
	
		var coupon=document.getElementById("couponid");
		var url = "http://localhost:8080/Coupons/rest/companyService/deletecoupon?couponid="+coupon.value;
		xmlhttp.open('DELETE', url, true);
		xmlhttp.send(null);	
	}