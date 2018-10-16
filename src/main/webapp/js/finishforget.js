//校验第一次输入的密码
		function validatePwd1() {
			var pwd1 = document.getElementById("password").value;
			//var testPwd = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,10}$/;
			if (pwd1.trim().length == 0 || pwd1 == "") {
				document.getElementById("tishiPwd1").innerHTML = "<font color='red'>密码不能为空</font>";
				document.getElementById("finish-button").disabled = true;
			} /* else if (!testPwd.test(pwd1)) {
				document.getElementById("tishiPwd1").innerHTML = "<font color='red'>必须包含大小写字母和数字的组合，不能使用特殊字符，长度在8-10之间</font>";
				document.getElementById("finish-button").disabled = true;
			}  */else {
				document.getElementById("tishiPwd1").innerHTML = "<font color='white'></font>";
				document.getElementById("finish-button").disabled = false;
			}

		}

		function validatePwd2() {
			var pwd1 = document.getElementById("password").value;
			var pwd2 = document.getElementById("repassword").value;
			//		 对比两次输入的密码 
			if (pwd2.trim().length == 0) {
				document.getElementById("tishiPwd2").innerHTML = "<font color='red'>密码不能为空</font>";
				document.getElementById("finish-button").disabled = true;
			} else if (pwd1 == pwd2) {
				document.getElementById("tishiPwd2").innerHTML = "<font color='white'>两次密码相同</font>";
				document.getElementById("finish-button").disabled = false;
			} else {
				document.getElementById("tishiPwd2").innerHTML = "<font color='red'>两次密码不相同</font>";
				document.getElementById("finish-button").disabled = true;
			}
		}

		var uname = new Vue({
			el : "#upassword",
			data : {
				usern : "",
				token : ""
			},
			created : function() {
				this.usern = getUrlParam("username");
				this.token = getUrlParam("forgetToken");
				console.log(this.token)
			}
		})

		/*
		 * 获取路径参数
		 */
		function getUrlParam(name) {
			var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
			var r = window.location.search.substr(1).match(reg);
			if (r != null)
				return decodeURI(r[2]);
			return null;
		}
		$("#finish-button").click(function(event) {
			var username = uname.usern;
			var password = $("#password").val();
			var forgetToken = uname.token;
			$.ajax({
				url : "../user/password",
				data : {
					"username" : username,
					"passwordNew" : password,
					"forgetToken" : forgetToken
				},
				type : 'POST',
				success : function(res) {
					console.log(res)
					console.log(username)
					console.log(password)
					console.log(forgetToken)
					setTimeout(function() {
						window.location.href = 'main.html';
					}, 1500); 
				}
			})

		});