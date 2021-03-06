/*
 * 取路径参数
 */
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURI(r[2]); return null; 
}

$(document).ready(function(){
	/*
	 * 跳转
	 */
	$(function(){
		var name=getUrlParam('location');
		var id=getUrlParam('id');
		if(name==null){
			$("#frame").load("firstindex.html");
		}
		else if(name=="home"){
			$("#frame").load("home.html");
		}
		else if(name=="details"){
			$("#frame").load("details.html");
		}
		else if(name=="etc"){
			$("#frame").load("etc.html");
		}
		else if(name=="order"){
			$("#frame").load("order.html");
		}
		else if(name=="webchat"){
			$("#frame").load("../socket/socketpage.html");
		}
		else if(name=="wish"){
			$("#frame").load("wish.html");
		}
		else if(name=="friends_wish"){
			$("#frame").load("friends_wish.html");
		}
		else if(name=="paysuccess"){
			$("#frame").load("paysuccess.html")
		}
		else if(name=="anno"){
			$("#frame").load("anno.html")
		}
		else if(name=="toupload"){
			$("#frame").load("upload.html")
		}
		else if(name=="mygame"){
			$("#frame").load("mygame.html")
		}
		else if(name=="update_mine"){
			$("#frame").load("updatenews.html")
		}
		else if(name=="update_pwd"){
			$("#frame").load("setNewPwd.html")
		}
		
		$(".loading").fadeOut(500);
	})
	
//	$("#Tologin").click(function(e){
//					var username = $("#account").val();
//					var password = $("#password").val();
//					$.ajax({
//						url: "../user/login",
//						data:{
//							"username": username,
//							"password": password
//						},
//						method:"POST",
//						success: function(res){
//							if(res.status == 0){
//							console.log(res.data)
//							main.user = res.data
//							location.reload();
//							}
//							else if(res.status == 1){
//								alert(res.msg)
//							}
//						}
//					})
//				})
	
})


var main = new Vue({
	el:"#main",
	data:{
		"user": []
	},
	methods: {
		login: function(){
			dologin();
		},
		logout: function(){
			dologout();
		}
	},
	created: function(){
		var that = this;
		$.ajax({
			type:"get",
			url:"../user/get_userinfo",
			async:true,
			success: function(res){
				console.log(res)
				if(res.status == 0)
				that.user = res.data;
			}
		});
	}
})

function dologin(){
	var username = $("#account").val();
	var password = $("#password").val();
	$.ajax({
		url: "../user/login",
		data:{
			"username": username,
			"password": password
		},
		method:"POST",
		success: function(res){
			console.log(res)
			if(res.status == 0){
			console.log(res.data)
			main.user = res.data
			location.reload();
			}
			else if(res.status == 1){
				alert(res.msg)
			}
		}
	})																
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
