
//校验旧密码
function validatePwd() {
	var pwd1 = document.getElementById("oldpassword").value;
	var testPwd = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,10}$/;
	if(pwd1.trim().length == 0 || pwd1 == "") {
		document.getElementById("tishiPwd").innerHTML = "<font color='red'>密码不能为空</font>";
		document.getElementById("uppw").disabled = true;
	}else if(!testPwd.test(pwd1)){
		document.getElementById("tishiPwd1").innerHTML = "<font color='red'>必须包含大小写字母和数字的组合，不能使用特殊字符，长度在8-10之间</font>";
		document.getElementById("registed-button").disabled = true;
	}
	else {
		document.getElementById("tishiPwd").innerHTML = "<font color='white'></font>";
		document.getElementById("uppw").disabled = false;
	}
}
//校验第一次输入的密码
function validatePwd1() {
	var pwd1 = document.getElementById("newpassword").value;
	var testPwd = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,10}$/;
	if(pwd1.trim().length == 0 || pwd1 == "") {
		document.getElementById("tishiPwd1").innerHTML = "<font color='red'>密码不能为空</font>";
		document.getElementById("uppw").disabled = true;
	}else if(!testPwd.test(pwd1)){
		document.getElementById("tishiPwd1").innerHTML = "<font color='red'>必须包含大小写字母和数字的组合，不能使用特殊字符，长度在8-10之间</font>";
		document.getElementById("registed-button").disabled = true;
	}
	else {
		document.getElementById("tishiPwd1").innerHTML = "<font color='white'></font>";
		document.getElementById("uppw").disabled = false;
	}
}

function validatePwd2() {
	var pwd1 = document.getElementById("newpassword").value;
	var pwd2 = document.getElementById("repassword").value;
	//		 对比两次输入的密码 
	if(pwd2.trim().length == 0) {
		document.getElementById("tishiPwd2").innerHTML = "<font color='red'>密码不能为空</font>";
		document.getElementById("uppw").disabled = true;
	} else if(pwd1 == pwd2) {
		document.getElementById("tishiPwd2").innerHTML = "<font color='white'>两次密码相同</font>";
		document.getElementById("uppw").disabled = false;
	} else {
		document.getElementById("tishiPwd2").innerHTML = "<font color='red'>两次密码不相同</font>";
		document.getElementById("uppw").disabled = true;
	}
}


function dologout(){
	$.ajax({
		type:"POST",
		url:"../user/logout",
		async:true,
		success: function(res){
			location.reload()
		}
	});
}


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
			updatepw.username = res.data.username;
		}
	});
}

/**
 * 密码修改模块
 */
var updatepw = new Vue({
	el:"#updatepw",
	data:{
		username:"",
		oldpassword:"",
		newpassword:""
	},
	methods:{
		updatepassword:function(){
			var that = this;
			var oldpassword = updatepw.oldpassword;
			var newpassword = updatepw.newpassword;
			$.ajax({
				type:"post",
				url:"../user/updatePassword",
				data:{
					"passwordOld":oldpassword,
					"passwordNew":newpassword
				},
				async:true,
				success:function(res){
						
						if(res.status == 0){
							alert("请重新登录")
							dologout();
						}else if(res.status == 1){
							alert(res.msg)
						}
				}
			});
		}
	},
	created:function(){
		getgrList();
	}
})