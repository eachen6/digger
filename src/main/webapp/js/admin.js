//校验用户名
function validateUserN() {
	var username = document.getElementById("username").value;
	var nameTest =/^[a-zA-Z0-9_]{2,15}$/;  //^[a-zA-Z][a-zA-Z0-9_]{4,15}$/;
	var emailTest = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
	if(username.trim().length == 0 || username == "") {
		document.getElementById("tishiUserName").innerHTML = "<font color='red'>用户名不能为空</font>";
		document.getElementById("fpassword-button").disabled = true;
	}else if(nameTest.test(username)) {
		document.getElementById("tishiUserName").innerHTML = "<font color='white'></font>"
		document.getElementById("fpassword-button").disabled = false;
	}else if(emailTest.test(username)) {
		document.getElementById("tishiUserName").innerHTML = "<font color='white'></font>"
		document.getElementById("fpassword-button").disabled = false;
	} //else if(){
	//Todo 查重
	//}
	else {
		document.getElementById("tishiUserName").innerHTML = "<font color='red'>必须为用户名或者邮箱</font>";
		document.getElementById("fpassword-button").disabled = true;
	}
}

//校验输入的密码
		function validatePwd() {
			var pwd1 = document.getElementById("password").value;
			//var testPwd = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,10}$/;
			if (pwd1.trim().length == 0 || pwd1 == "") {
				document.getElementById("tishiPwd").innerHTML = "<font color='red'>密码不能为空</font>";
				document.getElementById("finish-button").disabled = true;
			} else {
				document.getElementById("tishiPwd").innerHTML = "<font color='white'></font>";
				document.getElementById("finish-button").disabled = false;
			}

		}
$(document).ready(function() {
		$("#logina").click(function(event) {
			var username = $("#username").val();
			var password = $("#password").val();
			console.log(username)
			console.log(password)
			$.ajax({
				url: "../user/login",
				data: {
					"username": username,
					"password": password
				},
				type: 'POST',
				success: function(res) {
					console.log(res)
					if(res.status == 0) {
						window.location.href= "/digger/views/index.html?username="+username+'&password='+password;
					}
				}
			})
		})

})
