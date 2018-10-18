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
			console.log(3)
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
			console.log(4)
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
			console.log(5)
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