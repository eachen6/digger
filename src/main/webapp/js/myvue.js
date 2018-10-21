/**
 * 待审核模块
 */
var check = new Vue({
	el:"#sour",
	data:{
		checks:[],
		upcheck:[],
		pageNum: "",
		total: "",
		pages: "",
	    prePage:"",
		nextPage:"",
		isFirstPage:"",
		isLastPage:"",
		hasPreviousPage:"",
		hasNextPage:"",
		navigatePages:"",
		navigatepageNums:[]
	},
	methods:{
		getcheckId:function(index){
			check.upcheck = check.checks[index];
		},
		pass:function(id){
			gamepass(id);
		},
		change:function(pn){
			getCheckList(pn);
		}
	},
	created:function(){
		getCheckList(1);//调用函数
	}
})

/**
 * 获取待审核列表
 */
function getCheckList(pn){
	console.log("测试审核列表")
	var that = this;
	$.ajax({
		type:"get",
		url:"../gameaudit/unaudited_gamelist/"+pn,
		async:true,
		success:function(res){
			//console.log(res)//通过打印返回内容确定数据格式，根据数据调整当前页面内容
			if(res.status==0)
			{
				check.pageNum = res.data.pageNum;
				check.total = res.data.total;
				check.pages = res.data.pages;
				check.prePage = res.data.prePage;
				check.nextPage = res.data.nextPage;
				check.isFirstPage = res.data.isFirstPage;
				check.isLastPage = res.data.isLastPage;
				check.hasPreviousPage = res.data.hasPreviousPage;
				check.hasNextPage = res.data.hasNextPage;
				check.navigatePages = res.data.navigatePages;
				check.navigatepageNums = res.data.navigatepageNums;
				check.checks = res.data.list;//把值赋给VUE的变量
				for(var i = 0; i < check.checks.length; i++){
					check.checks[i].createtime = that.format(check.checks[i].createtime)
				}
			}
		}
	});
}
/*
 * 游戏审核通过
 */
function gamepass(id){
	console.log(id)
	$.ajax({
		type:"post",
		data:{"id":id},
		url:"../gameaudit/onthesshelf_game",
		async:true,
		success:function(res){
			//console.log(res)
		}
	});
}

/**
 * 未上架模块
 */
var notup = new Vue({
	el:"#regu",
	data:{
		notups:[],
		mo_noticeId:"",
		pageNum: "",
		total: "",
		pages: "",
	    prePage:"",
		nextPage:"",
		isFirstPage:"",
		isLastPage:"",
		hasPreviousPage:"",
		hasNextPage:"",
		navigatePages:"",
		navigatepageNums:[]
	},
	methods:{
		upstock:function(id){
			uptheStock(id);
		},
		pass:function(id){
			notup.mo_noticeId=id;
			var info ="公告内容";
            $('#one').summernote('code', info)
		},
		mo_upstock:function(){
			passtheNotic(notup.mo_noticeId);
		},
		change:function(pn){
			getnotupList(pn);
		}
	},
	created:function(){
		getnotupList(1);
	}
})

/**
 * 获取未上架列表
 */
function getnotupList(pn){
	var that = this;
	$.ajax({
		type:"get",
		url:"../gameaudit/notonthesshelf_gamelist/"+pn,
		async:true,
		success:function(res){
			if(res.status==0)
			{
				notup.pageNum = res.data.pageNum;
				notup.total = res.data.total;
				notup.pages = res.data.pages;
				notup.prePage = res.data.prePage;
				notup.nextPage = res.data.nextPage;
				notup.isFirstPage = res.data.isFirstPage;
				notup.isLastPage = res.data.isLastPage;
				notup.hasPreviousPage = res.data.hasPreviousPage;
				notup.hasNextPage = res.data.hasNextPage;
				notup.navigatePages = res.data.navigatePages;
				notup.navigatepageNums = res.data.navigatepageNums;
				notup.notups = res.data.list;
				for(var i = 0; i < notup.notups.length; i++){
					notup.notups[i].updatetime = that.format(notup.notups[i].updatetime)
				}
			}
		}
	});
}
/**
 * 上架游戏
 */
function uptheStock(id){
	$.ajax({
		type:"post",
		data:{"id":id},
		url:"../gameaudit/onthesshelf_game",
		async:true,
		success:function(res){
			//console.log(res)
			window.location.reload();
		}
	});
}
/**
 * 上传公告图片并回调
 */
function sendFile1(files, editor, $editable) {
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
			$('#one').summernote('insertImage', res.data.richimgurl);
		},
		error: function() {
			alert("上传失败");
		}
	});
}

/**
 * 添加公告发布
 */
function passtheNotic(id){
	var code = $('#one').summernote('code');
		var form = new FormData(document.getElementById("addnotice"));
		form.append("content", code);
		form.append("gameid", id);
		console.log(form)
		$.ajax({
			url: "../announcement/add_announcement",
			type: "POST",
			data: form,
			processData: false,
			contentType: false,
			success: function(res) {
				//window.clearInterval(timer);
				console.log(res);
				if(res.status == 0){
					alert(res.msg)
					
				}
			}
		})
}

/**
 * 在销售模块
 */
var sale = new Vue({
	el:"#stud",
	data:{
		pageNum: "",
		total: "",
		pages: "",
	    prePage:"",
		nextPage:"",
		isFirstPage:"",
		isLastPage:"",
		hasPreviousPage:"",
		hasNextPage:"",
		navigatePages:"",
		navigatepageNums:[],
		sales:[]
	},
	methods:{
		downgame:function(index){
			var id = sale.sales[index].id;
			//console.log(id)
			$.ajax({
				type:"post",
				url:"../gameaudit/pulloffshelves_game",
				data:{"id":id},
				async:true,
				success:function(res){
					//console.log(res)
					window.location.reload();
				}
			});
		},
		change:function(pn){
			getsaleList(pn);
		}
	},
	created:function(){
		getsaleList(1);
	}
})

/**
 * 获取在销售列表
 */
function getsaleList(pn){
	var that = this;
	$.ajax({
		type:"get",
		url:"../gameaudit/onthesshelf_gamelist/"+pn,
		async:true,
		success:function(res){
			if(res.status==0)
			{
				sale.pageNum = res.data.pageNum;
				sale.total = res.data.total;
				sale.pages = res.data.pages;
				sale.prePage = res.data.prePage;
				sale.nextPage = res.data.nextPage;
				sale.isFirstPage = res.data.isFirstPage;
				sale.isLastPage = res.data.isLastPage;
				sale.hasPreviousPage = res.data.hasPreviousPage;
				sale.hasNextPage = res.data.hasNextPage;
				sale.navigatePages = res.data.navigatePages;
				sale.navigatepageNums = res.data.navigatepageNums;
				sale.sales = res.data.list;
				for(var i = 0; i < sale.sales.length; i++){
					sale.sales[i].shelftime = that.format(sale.sales[i].shelftime)
				}
			}
		}
	});
}

/**
 * 下架模块
 */
var isdown = new Vue({
	el:"#sitt",
	data:{
		pageNum: "",
		total: "",
		pages: "",
	    prePage:"",
		nextPage:"",
		isFirstPage:"",
		isLastPage:"",
		hasPreviousPage:"",
		hasNextPage:"",
		navigatePages:"",
		navigatepageNums:[],
		isdowns:[]
	},
	methods:{
		upgame:function(index){
			var id = isdown.isdowns[index].id;
			$.ajax({
				type:"post",
				url:"../gameaudit/onthesshelf_game",
				data:{"id":id},
				async:true,
				success:function(res){
					window.location.reload();
				}
			});
		},
		change:function(pn){
			getisdownList(pn);
		}
	},
	created:function(){
		getisdownList(1);
	}
})

/**
 * 获取下架列表
 */
function getisdownList(pn){
	var that = this;
	$.ajax({
		type:"get",
		url:"../gameaudit/pulloffshelves_gamelist/"+pn,
		async:true,
		success:function(res){
			console.log("下架测试")
			console.log(res)
			if(res.status==0)
			{
				isdown.pageNum = res.data.pageNum;
				isdown.total = res.data.total;
				isdown.pages = res.data.pages;
				isdown.prePage = res.data.prePage;
				isdown.nextPage = res.data.nextPage;
				isdown.isFirstPage = res.data.isFirstPage;
				isdown.isLastPage = res.data.isLastPage;
				isdown.hasPreviousPage = res.data.hasPreviousPage;
				isdown.hasNextPage = res.data.hasNextPage;
				isdown.navigatePages = res.data.navigatePages;
				isdown.navigatepageNums = res.data.navigatepageNums;
				isdown.isdowns = res.data.list;
				for(var i = 0; i < isdown.isdowns.length; i++){
					isdown.isdowns[i].shelftime = that.format(isdown.isdowns[i].shelftime)
				}
			}
		}
	});
}

/**
 * 用户管理模块
 */
var guser = new Vue({
	el:"#user",
	data:{
		pageNum: "",
		total: "",
		pages: "",
	    prePage:"",
		nextPage:"",
		isFirstPage:"",
		isLastPage:"",
		hasPreviousPage:"",
		hasNextPage:"",
		navigatePages:"",
		navigatepageNums:[],
		gusers:[]
	},
	methods:{
		updatestate:function(index){
			var id = guser.gusers[index].id;
			var state = guser.gusers[index].state;
			if(state>0){state=0;}
			else{state=1;}
			$.ajax({
				type:"post",
				url:"../admin/updatestatebyid",
				data:{
					"id":id,
					"state":state
				},
				async:true,
				success:function(res){
					window.location.reload();
				}
			});
		},
		deletestate:function(index){
			guser.id=guser.gusers[index].id;
		},
		deluser:function(id){
			$.ajax({
				type:"post",
				url:"../admin/deleteuserbyid",
				data:{"id":id},
				async:true,
				success:function(res){
					window.location.reload();
				}
			});
		},
		change:function(pn){
			getguserList(pn);
		}
	},
	created:function(){
		getguserList(1);
	}
})

/**
 * 获取用户管理列表
 */
function getguserList(pn){
	var that = this;
	$.ajax({
		type:"get",
		url:"../admin/get_total_userlist/"+pn,
		success:function(res){
			console.log(res);
			if(res.status==0)
			{
				guser.pageNum = res.data.pageNum;
				guser.total = res.data.total;
				guser.pages = res.data.pages;
				guser.prePage = res.data.prePage;
				guser.nextPage = res.data.nextPage;
				guser.isFirstPage = res.data.isFirstPage;
				guser.isLastPage = res.data.isLastPage;
				guser.hasPreviousPage = res.data.hasPreviousPage;
				guser.hasNextPage = res.data.hasNextPage;
				guser.navigatePages = res.data.navigatePages;
				guser.navigatepageNums = res.data.navigatepageNums;
				guser.gusers = res.data.list;
			}
		}
	});
}

/**
 * 查询用户
 */
$(document).ready(function(){
	$("#selectbyname").click(function(event){
		var name = $("#select").val();
		$.ajax({
			type:"get",
			url:"../admin/selectuserbyusername",
			data:{"username":name},
			async:true,
			success:function(res){
				
			}
		});
	})
})

/**
 * 公告管理模块
 */
var gnotice = new Vue({
	el:"#gonggao",
	data:{
		pageNum: "",
		total: "",
		pages: "",
	    prePage:"",
		nextPage:"",
		isFirstPage:"",
		isLastPage:"",
		hasPreviousPage:"",
		hasNextPage:"",
		navigatePages:"",
		navigatepageNums:[],
		list:[],
		oldtitle:"",
		oldcontent:"",
		syid:""
	},
	methods:{
		mo_updategg:function(index){
			gnotice.syid=gnotice.list[index].id;
			gnotice.oldtitle=gnotice.list[index].title;
			var info =gnotice.list[index].content;
	        $('#two').summernote('code', info)
		},
		savenew:function(){
			asavenew(gnotice.syid);
		},
		change:function(pn){
			getgnoticeList(pn);
		}
	},
	created:function(){
		getgnoticeList(1);
	}
})

/**
 * 获取公告管理列表
 */
function getgnoticeList(pn){
	console.log("测试公告")
	var that = this;
	$.ajax({
		type:"get",
		url:"../announcement/get_announcement/"+pn,
		success:function(res){
			console.log(res)
			if(res.status==0)
			{
				gnotice.pageNum = res.data.pageNum;
				gnotice.total = res.data.total;
				gnotice.pages = res.data.pages;
				gnotice.prePage = res.data.prePage;
				gnotice.nextPage = res.data.nextPage;
				gnotice.isFirstPage = res.data.isFirstPage;
				gnotice.isLastPage = res.data.isLastPage;
				gnotice.hasPreviousPage = res.data.hasPreviousPage;
				gnotice.hasNextPage = res.data.hasNextPage;
				gnotice.navigatePages = res.data.navigatePages;
				gnotice.navigatepageNums = res.data.navigatepageNums;
				gnotice.list = res.data.list;
				for(var i = 0; i < gnotice.list.length; i++){
					gnotice.list[i].updatetime = that.format(gnotice.list[i].updatetime)
				}
			}
		}
	});
}
/**
 * 上传公告图片并回调
 */
function sendFile2(files, editor, $editable) {
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
			$('#tow').summernote('insertImage', res.data.richimgurl);
		},
		error: function() {
			alert("上传失败");
		}
	});
}
/**
 * 保存修改公告
 */
function asavenew(id){
	console.log(id)
	var code = $('#two').summernote('code');
	var form = new FormData(document.getElementById("updatenotice"));
	form.append("content", code);
	form.append("id", id);
	console.log("保存修改")
	console.log(form)
	$.ajax({
		type:"POST",
		data:form,
		url:"../announcement/update_announcement",
		async:true,
		processData: false,
		contentType: false,
		success:function(res){
			console.log(res)
			window.location.reload();
		}
	});
}
/**
 * 个人信息模块
 */
var gr = new Vue({
	el:"#home",
	data:{
		usern : "",
		grs : []
	},
	created:function(){
		this.usern = getUrlParam("username");
		getgrList(this.usern);
	}
})

/**
 * 获取个人信息
 */
function getgrList(usern){
	var that = this;
	var username = usern;
	console.log(username)
	$.ajax({
		type:"get",
		url:"../admin/selectuserbyusername",
		data:{"username":username},
		async:true,
		success:function(res){
			console.log(res)
			gr.grs = res.data;
			gr.grs.createtime = that.format(gr.grs.createtime)
		}
	});
}


/**
 * 修改个人信息
 */
function updateMessage(){
	$.ajax({
		type:"get",
		url:"../admin/selectuserbyusername",
		async:true,
		success:function(res){
		}
	});
}

/**
 * 时间戳装换格式函数
 * @param {Object} m
 */
function add0(m){return m<10?'0'+m:m }
function format(shijianchuo)
{
	//shijianchuo是整数，否则要parseInt转换
	var time = new Date(shijianchuo);
	var y = time.getFullYear();
	var m = time.getMonth()+1;
	var d = time.getDate();
	var h = time.getHours();
	var mm = time.getMinutes();
	var s = time.getSeconds();
	return y+'-'+add0(m)+'-'+add0(d)+' '+add0(h)+':'+add0(mm)+':'+add0(s);
}
/*
 * 公告富文本添加
 */
function sendtheFile(files, editor, $editable) {
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

	$("#adddeclared").click(function() {
		var code = $('.summernote').summernote('code');
		var form = new FormData(document.getElementById("declared"));
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
				console.log(res)
				if(res.status == 0){
					alert(res.msg)
					window.location.href="upload.html"
				}
			}
		})
	})

/*
 * 商品打折
*/
	var gamediscount = new Vue({
	el:"#gamediscount",
	data:{
		pageNum: "",
		total: "",
		pages: "",
	    prePage:"",
		nextPage:"",
		isFirstPage:"",
		isLastPage:"",
		hasPreviousPage:"",
		hasNextPage:"",
		navigatePages:"",
		navigatepageNums:[],
		goods:[]
		
	},
	methods:{
		change:function(pn){
			getgoodsList(pn);
		}
	},
	created:function(){
		getgoodsList(1);
	}
})

/**
 * 获取商品打折详情
 */
function getgoodsList(pn){
	var that = this;
	console.log(pn)
	$.ajax({
		type:"post",
		url:"../game/get_discount_gamelist/"+pn,
		async:true,
		success:function(res){
			console.log(res.data)
			gamediscount.goods = res.data.list;
		    gamediscount.pageNum = res.data.pageNum;
			gamediscount.total = res.data.total;
			gamediscount.pages = res.data.pages;
			gamediscount.prePage = res.data.prePage;
			gamediscount.nextPage = res.data.nextPage;
			gamediscount.isFirstPage = res.data.isFirstPage;
			gamediscount.isLastPage = res.data.isLastPage;
			gamediscount.hasPreviousPage = res.data.hasPreviousPage;
			gamediscount.hasNextPage = res.data.hasNextPage;
			gamediscount.navigatePages = res.data.navigatePages;
			gamediscount.navigatepageNums = res.data.navigatepageNums;
			for(var i = 0; i < gamediscount.goods.length; i++){
				gamediscount.goods[i].starttime = that.format(gamediscount.goods[i].starttime)
				
			}
		}
	});
}


/**
 * 所有无打折销售游戏
 */
var nonediscount = new Vue({
	el:"#nonediscount",
	data:{
		nonediscount:[]
	},
	methods:{},
	created:function(){
		getnonediscountLit();
	}
})

function getnonediscountLit(){
	var that = this;
	$.ajax({
		type:"get",
		url:"../gameaudit/onthesshelf_gamelist",
		async:true,
		success:function(res){
			nonediscount.nonediscount = res.data;
			for(var i = 0; i < nonediscount.nonediscount.length; i++){
				nonediscount.nonediscount[i].shelftime = that.format(nonediscount.nonediscount[i].shelftime)
			}
		}
	});
}
/*
 * 获取路径参数
 */
function getUrlParam(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if(r != null)
		return decodeURI(r[2]);
	return null;
}


/**
 * 公告发布
 */
$(function() {
	$("#up").click(function() {
		
	})

	$("#et").click(function() {
		$('.summernote').summernote('reset');
	})
})
//判断老密码
/*function validatePwd() {
	var pwd1 = document.getElementById("password").value;
	//var testPwd = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,10}$/;
	if (pwd1.trim().length == 0 || pwd1 == "") {
		document.getElementById("tishiPwd1").innerHTML = "<font color='red'>密码不能为空</font>";
		document.getElementById("finish-button").disabled = true;
	}else {
		document.getElementById("tishiPwd").innerHTML = "<font color='white'></font>";
		document.getElementById("save_button").disabled = false;
	}
}*/
//校验第一次输入的密码
/*function validatePwd1() {
	var pwd1 = document.getElementById("password").value;
	//var testPwd = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,10}$/;
	if (pwd1.trim().length == 0 || pwd1 == "") {
		document.getElementById("tishiPwd1").innerHTML = "<font color='red'>密码不能为空</font>";
		document.getElementById("finish-button").disabled = true;
	}else {
		document.getElementById("tishiPwd1").innerHTML = "<font color='white'></font>";
		document.getElementById("finish-button").disabled = false;
	}
}*/

	//		 对比两次输入的密码 
/*function validatePwd2() {
	var pwd1 = document.getElementById("password").value;
	var pwd2 = document.getElementById("repassword").value;
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
}*/

/**
 * 密码修改模块
 */
var updatapw = new Vue({
	el:"#updatapw",
	data:{
		username:"",
		oldpassword:"",
		newpassword:""
	},
	methods:{
		updatapassword:function(){
			var that = this;
			var oldpassword = $("#oldpassword").val();
			var newpassword = $("#newpassword").val();
			$.ajax({
				type:"post",
				url:"../user/updatePassword",
				data:{
					"passwordOld":oldpassword,
					"passwordNew":newpassword
				},
				async:true,
				success:function(res){
					console.log(res)
				}
			});
		}
	},
	created:function(){
		this.username = getUrlParam("username");
	}
})

