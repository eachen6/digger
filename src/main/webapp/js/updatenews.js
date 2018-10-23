/**
 * 修改个人信息
 */
var updatenew = new Vue({
	el:"#upda",
	data:{
		news : []
	},
	methods:{
		updatenews:function(){
			var user = {
				username:updatenew.news.username,
				email:updatenew.news.email,
				question:updatenew.news.question,
				answer:updatenew.news.answer
			}
			$.ajax({
				type:"post",
				url:"../user/update",
				data:JSON.stringify(user),
				dataType:"json",
        		contentType:"application/json",
				async:true,
				success:function(res){
					console.log("修改个人信息成功")
					alert(res.msg)
				}
			});
		}
	},
	created:function(){
		getgrList();
	}
})

/**
 * 获取个人信息
 */
function getgrList(){
	var that = this;
	$.ajax({
		type:"get",
		url:"../user/get_userinfo",
		async:true,
		success:function(res){
			console.log(res)
			updatenew.news = res.data;
		}
	});
}

//校验邮箱
function validateEmail(){
				var email = document.getElementById("email").value;
				var tEmail = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/ ;
				//var right = email.match(tEmail);
				if(email.value==""||email.length==0){
					document.getElementById("tishiEmail").innerHTML = "<font color='red'>请输入邮箱</font>";
					document.getElementById("registed-button").disabled=true;
				}else if(!tEmail.test(email)){
					document.getElementById("tishiEmail").innerHTML = "<font color='red'>邮箱格式错误</font>";
					document.getElementById("registed-button").disabled = true;
				}else{
					document.getElementById("tishiEmail").innerHTML = "<font> </font>"
					document.getElementById("registed-button").disabled = false;
				}
			}