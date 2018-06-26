var xmlhttp;
	function init() {
		
		xmlhttp=new XMLHttpRequest ();
	}
	
	function setDetails() {
		var input;
		var coupid=document.getElementById("coupid");
		var couptitle=document.getElementById("couptitle"); 
		var start=document.getElementById("start");
		var end=document.getElementById("end");
		var type=document.getElementById("type");
		var message=document.getElementById("message"); 
		var amount=document.getElementById("amount");
		var price=document.getElementById("price");
		var image=document.getElementById("image").value;
		var url ="http://localhost:8080/Coupons/rest/companyService/createcoupon?id="+coupid.value+"&title="+couptitle.value+"&start="+start.value+"&end="+end.value+"&type="+type.value+"&message="+message.value+"&amount="+amount.value+"&price="+price.value+"&image="+image;
		xmlhttp.open('POST',url, true);
		xmlhttp.send(null);
		alert(url);
		swal("Coupon Has Been Added !!");
		
	}
	

