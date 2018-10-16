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
		getCheckList();//掉用函数
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
	console.log(123)
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
	console.log(12)
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
 * 在销售模块
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
 * 获取在销售列表
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
			console.log(456)
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
			for(var i = 0; i < guser.gusers.length; i++){
				guser.gusers[i].shelftime = that.format(guser.gusers[i].shelftime)
			console.log(789)
			}
		}
	});
}

/**
 * 公告管理模块
 */
var gnotice = new Vue({
	el:"#gnotice",
	data:{
		gnotices:[]
	},
	methods:{},
	created:function(){
		getgnoticeList();
	}
})

/**
 * 获取用户管理列表
 */
function getgnoticeList(){
	var that = this;
	$.ajax({
		type:"get",
		url:"../admin/get_total_userlist",
		async:true,
		success:function(res){
			gnotice.gnotices = res.data;
			for(var i = 0; i < gnotice.gnotices.length; i++){
				gnotice.gnotices[i].shelftime = that.format(gnotice.gnotices[i].shelftime)
			console.log(789)
			}
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