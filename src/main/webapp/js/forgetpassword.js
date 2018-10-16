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

$(document).ready(function() {

	var question="";
		$("#fpassword-button").click(function(event) {
			var username = $("#username").val();
			function getType() {
				var username = document.getElementById("username").value;
				var nameTest = /^[a-zA-Z0-9_]{2,15}$/;
				var emailTest = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
				if(emailTest.test(username))
					return "email";
				else
					return "username";
			}
			var typevalue = getType();
			//console.log(typevalue)
	
			$.ajax({
				url: "../user/question",
				data: {
					"username": username,
					"type": typevalue
				},
				type: 'POST',
				success: function(res) {
					console.log(res)
					if(res.status == 0) {
						question = res.data
					}
					window.location.href= "/digger/views/testanswer.html?question="+question+'&username='+username;
				}
			})
		})

})