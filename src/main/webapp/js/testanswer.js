var que = new Vue({
			el : "#question",
			data : {
				ques : "",
				usern : ""
			},
			created : function() {
				this.ques = getUrlParam("question");
				this.usern = getUrlParam("username");
				console.log(this.usern)
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

		$(document).ready(function() {
			var forgetToken = "";
			$("#btnSure").click(function(event) {
				var username = que.usern;
				var question = que.ques;
				var answer = $("#answer").val();
				console.log(question)
				$.ajax({
					url : "../user/answer",
					data : {
						"username" : username,
						"question" : question,
						"answer" : answer
					},
					type : 'POST',
					success : function(res) {
						console.log(res)
						if(res.status == 0) {
							forgetToken = res.data
						}
						setTimeout(function() {
							window.location.href = 'finishforget.html?username='+username+'&forgetToken='+forgetToken;
						}, 1500);
					},
					error : function() {
						alert("网络连接错误")
					}
				})
	
			})
		})