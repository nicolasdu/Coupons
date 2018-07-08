var xmlhttp;
	function init() {
		
		xmlhttp=new XMLHttpRequest ();
	}
	
	function setDetails() {
		alert("set details 2");
		var coupon=document.getElementById("coupid").value;
		var end=document.getElementById("end").value;
		var price=document.getElementById("price").value;
		
		var url ="http://localhost:8080/Coupons/rest/companyService/updatecoupon?id="+coupon+"&end="+end+"&price="+price;
		xmlhttp.open('PUT',url, true);
		xmlhttp.send(null);
		alert(url);
	}