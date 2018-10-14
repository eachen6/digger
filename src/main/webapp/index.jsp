<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript " src="js/jquery-1.9.1.js"></script>
<title>Insert title here</title>
</head>
<body>
		hello digger!
		<a href="views/main.html">首页</a>
		<form action="" method="post">
			账号：<input id="username" name="username"  type="text"/>
			密码：<input id="password" name="password" type="password" />
			<input id="submit" type="button" value="提交" />
		</form>
		
		<input id="delete" type="button" value="删除" />
		<input id="update" type="button" value="更新" />
		
		
		<form action="game/add" method="post" enctype="multipart/form-data">
			视频上传：<input id="video" name="files"  type="file"/>
			图片上传：<input id="img" name="files"  type="file"/>
			文字：<input id="name" name="name"  type="text"/>
			分类：<input id="category" name="category"  type="text"/>
			<input id="submit" type="submit" value="提交文件" />
		</form>
		
		
</body>
	<script>
		$(document).ready(function(){
			$("#submit").click(function(e){
				var username = $("#username").val();
				var password = $("#password").val();
				console.log(username,password);
				$.ajax({
					url: "user/login",
					data:{
						username:username,
						password:password
					},
					method:'POST',
					success:function(res){
						console.log(res)
					}
				})
			})
			
			$("#delete").click(function(e){

				$.ajax({
					url: "login/1",
					method:'DELETE',
					success:function(res){
						console.log(res)
					}
				})
			})
			
			$("#update").click(function(e){

				$.ajax({
					url: "login/1",

					method:'PUT',
					success:function(res){
						console.log(res)
					}
				})
			})
			
		})
	</script>
</html>