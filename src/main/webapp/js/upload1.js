/*
 * 取路径参数
 */
function getUrlParam(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if(r != null) return decodeURI(r[2]);
	return null;
}

//$(document).ready(function(){
//	$(function(){
//		var id=getUrlParam('id');
//		if(id!=null){
//			tf.gameid = id
//		}
//		else 
//			history.go(-1);
//	});
//})

var tf = new Vue({
	el: "#tf",
	data: {
		gameid: "00",
		category:""
	},
	methods: {},
	created: function() {
		var id = getUrlParam('id');
		this.gameid = id;
	}
})

function sendFile(files, editor, $editable) {
	var filename = String(files.name).replace(/\.[^.\/]+$/, "");
	console.log(filename)

	var data = new FormData();
	data.append("files", files);
	$.ajax({
		data: data,
		type: "POST",
		url: "../game/add_rich_img", //图片上传出来的url，返回的是图片上传后的路径，http格式
		cache: false,
		contentType: false,
		processData: false,
		dataType: "json",
		success: function(res) { //data是返回的hash,key之类的值，key是定义的文件名
			console.log(res)
			$('.summernote').summernote('insertImage', res.data.richimgurl);
		},
		error: function() {
			alert("上传失败");
		}
	});
}

$(function() {
	$("#up").click(function() {
		var code = $('.summernote').summernote('code');
		var form = new FormData(document.getElementById("tf"));
		form.append("detailtxt", code);
		console.log(form)
		$.ajax({
			url: "../game/add1",
			type: "POST",
			data: form,
			processData: false,
			contentType: false,
			success: function(res) {
				//window.clearInterval(timer);
				console.log(res);
				if(res.status == 0){
					alert(res.msg)
					window.location.href="index.html"
				}
			}
		})
	})

	$("#et").click(function() {
		$('.summernote').summernote('reset');
	})
})

$(document).ready(function() {
	checkboxOnClick();
	$("reback").click(function(){
		$("input[name='genres']:checked").attr('disabled', false);
	})
})

function  checkboxOnClick()  {          
	var  a = new Array();
	var temp = 0;              
	$("input[type=checkbox]").click(function()  {                      
		var  checkbox_value  =  $(this).attr('value');
		for(var i = 0; i < a.length; i++) {
			if(a[i]==checkbox_value){
				temp = i;
				break;
			}
		}       
		if(temp == 0){
			a.push(checkbox_value);
		}else{
			a.splice(temp,1)
			temp = 0
		} 
		console.log(a,temp)
		if(a.length==4){
//			alert("标签不能超过四个")
//			a.splice(4,1)
			console.log(a)
			$("input[name='genres']").attr('disabled', true);
		}
		tf.category = a.toString(",");    
	});          
} 

