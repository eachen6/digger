/**
 * 待审核模块
 */
var check = new Vue({
	el:"#check",
	data:{
		checks:[]
	},
	methods:{},
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
 * 未上架模块
 */
var notup = new Vue({
	el:"#notup",
	data:{
		notups:[]
	},
	methods:{},
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
	methods:{},
	created:function(){
		getsaleList();
	}
})

/**
 * 获取在销售列表
 */
function getsaleList(){
	var that = this;
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
	methods:{},
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
			console.log(1)
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
			console.log(11)
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
			alert(pn);
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
	var that = this;
	$.ajax({
		type:"get",
		url:"../announcement/get_announcement/"+pn,
		/*data:{"pn":pn},*/
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
					gnotice.list[i].createtime = that.format(gnotice.list[i].createtime)
				}
				//alert(page.total);
			}
			
		}
	});
}

/**
 * 分页模块
 */
/*$(document).ready(function(){
	  mypage(1);
});

var page = new Vue({
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
	methods: {
		change:function(pn){
			alert(pn);
			mypage(pn);
		}
	},
	created: function(){
		
	}
})

function mypage(pn){
	$.ajax({
		url: "../user/pagetest",
		data:{"pn":pn},
		type:'GET',
		contentType:"application/json; charset=utf-8",
		success:function(res){
			console.log(res);
			if(res.status==0)
			{
				page.pageNum = res.data.pageNum;
				page.total = res.data.total;
				page.pages = res.data.pages;
				page.prePage = res.data.prePage;
				page.nextPage = res.data.nextPage;
				page.isFirstPage = res.data.isFirstPage;
				page.isLastPage = res.data.isLastPage;
				page.hasPreviousPage = res.data.hasPreviousPage;
				page.hasNextPage = res.data.hasNextPage;
				page.navigatePages = res.data.navigatePages;
				page.navigatepageNums = res.data.navigatepageNums;
				page.list = res.data.list;
				//alert(page.total);
			}
		}
	}) 
}
*/
/**
 * 个人信息模块
 */
var gr = new Vue({
	el:"#home",
	data:{
		grs:[]
	},
	methods:{},
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
		url:"../admin/selectuserbyusername",
		async:true,
		success:function(res){
			gr.grs = res.data;
			for(var i = 0; i < gr.grs.length; i++){
				gr.grs[i].createtime = that.format(gr.grs[i].createtime)
			console.log(4)
			}
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
