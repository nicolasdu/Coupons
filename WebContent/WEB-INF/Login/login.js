var xmlhttp;
		
			function init() {
				xmlhttp = new XMLHttpRequest();
			}
		
			function LoginCall() {
				alert("function");
				var username = document.getElementById("username").value;
				var password = document.getElementById("password").value;
				var loginType = document.getElementById("loginType").value;
				alert(loginType);
				var url = "http://localhost:8080/Coupons/rest/adminServices/login?username="+username+"&password="+password+"&clientType="+loginType;
				xmlhttp.open("GET", url, true);
				xmlhttp.send(null);
				alert("sent");
			}
			
$(document).ready(function () {
	$('#logo').addClass('animated fadeInDown');
	$("input:text:visible:first").focus();
});
$('#username').focus(function() {
	$('label[for="username"]').addClass('selected');
});
$('#username').blur(function() {
	$('label[for="username"]').removeClass('selected');
});
$('#password').focus(function() {
	$('label[for="password"]').addClass('selected');
});
$('#password').blur(function() {
	$('label[for="password"]').removeClass('selected');
});