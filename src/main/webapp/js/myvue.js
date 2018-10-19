/**
 * 待审核模块
 */
var check = new Vue({
	el:"#check",
	data:{
		checks:[]
	},
	methods:{
		shenhe:function(a){
			console.log(a)
		}
	},
	created:function(){
		getCheckList();//调用函数
	}
})

/**
 * 获取待审核列表
 */
function getCheckList(){
	var that = this;
	$.ajax({
		type:"get",
		url:"../gameaudit/unaudited_gamelist",
		async:true,
		success:function(res){
			console.log(res)//通过打印返回内容确定数据格式，根据数据调整当前页面内容
			check.checks = res.data;//把值赋给VUE的变量
			for(var i = 0; i < check.checks.length; i++){
				check.checks[i].createtime = that.format(check.checks[i].createtime)
			}
		}
	});
}



/**
 * 获取游戏id查找审核游戏的信息
 */
$(function(){
$("#getid").click(function(event){
	console.log(1)
    var id= document.getElementById("getid");
    console.log(id)
});
})


/**
 * 未上架模块
 */
var notup = new Vue({
	el:"#notup",
	data:{
		notups:[]
	},
	methods:{
		upgame:function(index){
			var id = notup.notups[index].id;
			//console.log(id)
			$.ajax({
				type:"post",
				url:"../gameaudit/onthesshelf_game",
				data:{"id":id},
				async:true,
				success:function(res){
					//console.log(res)
					window.location.reload();
				}
			});
		}
	},
	created:function(){
		getnotupList();
	}
})

/**
 * 获取未上架列表
 */
function getnotupList(){
	var that = this;
	$.ajax({
		type:"get",
		url:"../gameaudit/notonthesshelf_gamelist",
		async:true,
		success:function(res){
			notup.notups = res.data;
			for(var i = 0; i < notup.notups.length; i++){
				notup.notups[i].updatetime = that.format(notup.notups[i].updatetime)
			}
		}
	});
}

/**
 * 在销售模块
 */
var sale = new Vue({
	el:"#sale",
	data:{
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
		}
	},
	created:function(){
		getsaleList();
	}
})

/**
 * 获取在销售列表
 */
function getsaleList(){
	var that = this;
	console.log(2)
	$.ajax({
		type:"get",
		url:"../gameaudit/onthesshelf_gamelist",
		async:true,
		success:function(res){
			sale.sales = res.data;
			for(var i = 0; i < sale.sales.length; i++){
				sale.sales[i].shelftime = that.format(sale.sales[i].shelftime)
			}
		}
	});
}

/**
 * 下架模块
 */
var isdown = new Vue({
	el:"#isdown",
	data:{
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
		}
	},
	created:function(){
		getisdownList();
	}
})

/**
 * 获取下架列表
 */
function getisdownList(){
	var that = this;
	$.ajax({
		type:"get",
		url:"../gameaudit/pulloffshelves_gamelist",
		async:true,
		success:function(res){
			isdown.isdowns = res.data;
			for(var i = 0; i < isdown.isdowns.length; i++){
				isdown.isdowns[i].shelftime = that.format(isdown.isdowns[i].shelftime)

			}
		}
	});
}

/**
 * 用户管理模块
 */
var guser = new Vue({
	el:"#guser",
	data:{
		gusers:[]
	},
	methods:{},
	created:function(){
		getguserList();
	}
})

/**
 * 获取用户管理列表
 */
function getguserList(){
	var that = this;
	$.ajax({
		type:"get",
		url:"../admin/get_total_userlist",
		async:true,
		success:function(res){
			guser.gusers = res.data;
			console.log(2)

			for(var i = 0; i < guser.gusers.length; i++){
				guser.gusers[i].shelftime = that.format(guser.gusers[i].shelftime)
			console.log(4)
			}

		}
	});
}

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
		list:[]
	},
	methods:{
		change:function(pn){
			getgnoticeList(pn);
		}
	},
	created:function(){
		getgnoticeList(1);

	}
})

	
/**
   * 公告修改管理模块
   */
var note = new Vue({
	el:"#upgg",
	data:{
		id:'',
		stste:''
	},
	methods:{ 
		upd:function(a){
		console.log(a)
	  note.id=a;
	  note.stste=b;
	}
	}
	
})

/**
 * 获取公告管理列表
 */
function getgnoticeList(pn){
	var that = this;
	$.ajax({
		type:"get",
		url:"../announcement/get_announcement/"+pn,
		/*data:{"pn":pn},*/
		success:function(res){

			gnotice.gnotices = res.data;
			for(var i = 0; i < gnotice.gnotices.length; i++){
				gnotice.gnotices[i].shelftime = that.format(gnotice.gnotices[i].shelftime)
			console.log(5)

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
					gnotice.list[i].createtime = that.format(gnotice.list[i].createtime)
				}
				//alert(page.total);

			}
			
		}
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
			console.log(5)
		}
	});
}

/**
 * 修改用户封禁状态
 */
function updatestate(){
	$.ajax({
		type:"get",
		url:"../admin/updatestatebyid",
		async:true,
		success:function(res){
			console.log(6)
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
<<<<<<< HEAD
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
		goods:[]
	},
	methods:{
		/*change:function(){
			
		}*/
	},
	created:function(){
		console.log(6)
		getgoodsList(1);
	}
})

/**
 * 获取商品打折详情
 */
function getgoodsList(pn){
	var that = this;
	$.ajax({
		type:"post",
		url:"../game/get_discount_gamelist"+pn,
		async:true,
		success:function(res){
			console.log(res.data)
			gamediscount.goods = res.data;
			for(var i = 0; i < gamediscount.goods.length; i++){
				gamediscount.goods[i].starttime = that.format(gamediscount.goods[i].starttime)
				console.log(7)
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
			console.log(8)
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

