var xmlhttp;
		
			function init() {
				xmlhttp = new XMLHttpRequest();
			}
		
			function LoginCall() {
				
				var username = document.getElementById("username").value;
				var password = document.getElementById("password").value;
				var loginType = document.getElementById("loginType").value;
				var url = "http://localhost:8080/Coupons/rest/loginservice/login?username="+username+"&password="+password+"&clientType="+loginType;
				xmlhttp.open("GET", url, true);
				xmlhttp.send(null);
							
							var det=xmlhttp.responseText;
							alert(det);
							if(det==''){
									alert("Incorrect Username Or Password");	
								}
							else{
							if(loginType=='adminLogin')
								window.location.href = "http://localhost:8080/Coupons/IFrame/admin.html";
								
							else if(loginType=='companyLogin')
								window.location.href = "http://localhost:8080/Coupons/IFrame/Company.html";
							
							else if(loginType=='customerLogin')
								window.location.href = "http://localhost:8080/Coupons/IFrame/Customer.html";							
						}		
			}