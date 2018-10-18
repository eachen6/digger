$(document).ready(function() {
		$("#logina").click(function(event) {
			var username = $("#username").val();
			var password = $("#password").val();
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