/**
 * 退出登录模块
 */
new Vue({
	el:"#personInfor",
	methods:{
		logout:function(){
			$.ajax({
				type:"post",
				url:"../user/logout",
				async:true,
				success:function(res){
					window.location.href = "admin.html";
				}
			});
		}
	}
})

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
		pass:function(id,pageNum){
			gamepass(id,pageNum);
		},
		change:function(pn){
			getCheckList(pn);
		},
		delcheck:function(id,pageNum){
			deletegame(id,pageNum);
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
function gamepass(id,pageNum){
	console.log(id)
	$.ajax({
		type:"post",
		data:{"id":id},
		url:"../gameaudit/audit_game",
		async:true,
		success:function(res){
			//console.log(res)
			console.log("审核成功")
			getCheckList(pageNum);

		}
	});
}
function deletegame(id,pageNum){
	$.ajax({
		type:"post",
		url:"../gameaudit/delete_game",
		data:{"id":id},
		async:true,
		success:function(res){
			getCheckList(pageNum);
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
		upstock:function(id,pageNum){
			uptheStock(id,pageNum);
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
function uptheStock(id,pageNum){
	$.ajax({
		type:"post",
		data:{"id":id},
		url:"../gameaudit/onthesshelf_game",
		async:true,
		success:function(res){
			getnotupList(pageNum);
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
	console.log(code)
		var form = new FormData(document.getElementById("addnotice"));
		form.append("content", code);
		form.append("gameid", id);
		//console.log(form)
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
		navigatepageNums:[],
		sales:[]
	},
	methods:{
		downgame:function(index,pageNum){
			var id = sale.sales[index].id;
			//console.log(id)
			$.ajax({
				type:"post",
				url:"../gameaudit/pulloffshelves_game",
				data:{"id":id},
				async:true,
				success:function(res){
					//console.log(res)
					getsaleList(pageNum);
				}
			});
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
	console.log(code)
	var form = new FormData(document.getElementById("addnotice"));
	form.append("content", code);
	form.append("gameid", id);
	//console.log(form)
	$.ajax({
		url: "../announcement/add_announcement",
		type: "POST",
		data: form,
		processData: false,
		contentType: false,
		success: function(res) {
			//window.clearInterval(timer);
			console.log(res);
			if(res.status == 0) {
				alert(res.msg)
			}
		}
	})
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
		upgame:function(index,pageNum){
			var id = isdown.isdowns[index].id;
			$.ajax({
				type:"post",
				url:"../gameaudit/onthesshelf_game",
				data:{"id":id},
				async:true,
				success:function(res){
					getisdownList(pageNum);
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
		gusers:[],
		username:""
	},
	methods:{
		updatestate:function(index,pageNum){
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
					getguserList(pageNum);

				}
			});
		},
/*		deletestate:function(index){
			guser.id=guser.gusers[index].id;
		},
		deluser:function(id){
			$.ajax({
				type:"post",
				url:"../admin/deleteuserbyid",
				data:{"id":id},
				async:true,
				success:function(res){
					getguserList(1);
				}
			});
		},*/
		change:function(pn){
				inputselect(pn);
		},
		//查询用户
		selectbyinput:function(){
			inputselect(1);
			getguserList(pn);
		},
		//查询用户
		selectbyname:function(){
			var username = guser.username;
			console.log(username);
			var pn = 1;
			var that = this;
			$.ajax({
				type:"get",
				url:"../admin/selectuserlikeusername/"+pn,
				data:{"username":username},
				async:true,
				success:function(res){
					console.log(res);
					if(res.status==0)
					{
						that.pageNum = res.data.pageNum;
						that.total = res.data.total;
						that.pages = res.data.pages;
						that.prePage = res.data.prePage;
						that.nextPage = res.data.nextPage;
						that.isFirstPage = res.data.isFirstPage;
						that.isLastPage = res.data.isLastPage;
						that.hasPreviousPage = res.data.hasPreviousPage;
						that.hasNextPage = res.data.hasNextPage;
						that.navigatePages = res.data.navigatePages;
						that.navigatepageNums = res.data.navigatepageNums;
						that.gusers = res.data.list;
					}
					alert(that.navigatepageNums);
				}
			});
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

function inputselect(pn){
	var username = guser.username;
	console.log(username);
	var that = this;
	$.ajax({
		type: "get",
		url: "../admin/selectuserlikeusername/" + pn,
		data: {
			"username": username
		},
		async: true,
		success: function(res) {
			console.log(res);
			if(res.status == 0) {
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
		syid:"",
		del:[]
	},
	methods:{
		getnoticeId:function(index){
			gnotice.del = gnotice.list[index];
		},
		mo_updategg:function(index){
			gnotice.syid=gnotice.list[index].id;
			gnotice.oldtitle=gnotice.list[index].title;
			var info =gnotice.list[index].content;
	        $('#two').summernote('code', info)
		},
		savenew:function(pageNum){
			asavenew(gnotice.syid,pageNum);
		},
		delnotice:function(id,pageNum){
			deletenotice(id,pageNum);
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
			$('#two').summernote('insertImage', res.data.richimgurl);
		},
		error: function() {
			alert("上传失败");
		}
	});
}
/**
 * 保存修改公告
 */
function asavenew(id,pageNum){
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
			getgnoticeList(pageNum);
		}
	});
}
//删除公告
function deletenotice(id,pageNum){
	$.ajax({
		type:"post",
		url:"../announcement/delete_announcement",
		data:{"id":id},
		async:true,
		success:function(res){
			getgnoticeList(pageNum);
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
	methods:{
		updateMessage:function(){
			var user = {
				username:gr.grs.username,
				email:gr.grs.email,
				question:gr.grs.question,
				answer:gr.grs.answer
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
					//window.location.reload();
				}
			});
		}
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
	el:"#discounts",
	data:{
		
		goods:[],
		nonediscounts:[],
		newid:"",
		olddiscountid:"",
		olddiscount:"",
		oldstarttime:"",
		olddeadline:"",
		deleteid:""
	},
	methods:{
		cut_price:function(id){ 
		gamediscount.newid = id ;
		},
		savefirstdiscount:function(){
			save_firstdiscount();
			
		},
		getdiscount:function(index){
		gamediscount.olddiscountid = gamediscount.goods[index].discountid ;	
		console.log(gamediscount.olddiscountid)
		gamediscount.olddiscount=gamediscount.goods[index].discount;
		gamediscount.oldstarttime=gamediscount.goods[index].starttime;
		gamediscount.olddeadline=gamediscount.goods[index].olddeadline;
		},
		saveupdateprice:function(){
			save_updateprice();
			
		},
		deletediscount:function(index){
			gamediscount.deleteid=gamediscount.goods[index].discountid
			delete_discounts();
			
		}
	},
	created:function(){
		getgoodsList();
		getnonediscountLit();
	}
})

/**
 * 获取商品打折详情
 */
function getgoodsList(){
	var that = this;
	$.ajax({
		type:"post",
		url:"../game/get_discount",
		async:true,
		success:function(res){
			console.log("测试已打折")
			gamediscount.goods = res.data;
			console.log(gamediscount.goods)
		}
	});
	
}
/**
 * 获取未打折商品详情
 */
function getnonediscountLit(){
	var that = this;
	$.ajax({
		type:"post",
		url:"../game/get_notdiscount",
		async:true,
		success:function(res){
			console.log("测试未打折")
			gamediscount.nonediscounts = res.data;
		}
	});
	
}

function save_firstdiscount(){
	var abc = new FormData(document.getElementById("firstdiscount"));
	var id = gamediscount.newid;
	abc.append("gameid",id);
	$.ajax({
		type:"POST",
		url:"../game/add_discount",
		data:abc,
		async:true,
		processData: false,
		contentType:false,
		success:function(res){
			console.log(res)
			getgoodsList();
		    getnonediscountLit();
		}
	});
	
}

function save_updateprice(){
	var form =new FormData();
	form.append("id",gamediscount.olddiscountid);
	form.append("discount",gamediscount.olddiscount);
	form.append("starttime",gamediscount.oldstarttime);
	form.append("deadline",gamediscount.olddeadline);
	
	$.ajax({
		type:"POST",
		url:"../game/update_discount",
		data:form,
		async:true,
		processData: false,
		contentType:false,
		success:function(res){
			console.log(res)
			getgoodsList();
		    getnonediscountLit();
		}
	});
	
}

function delete_discounts(){
	console.log("删除打折")
	var id = gamediscount.deleteid ;
	$.ajax({
		type:"POST",
		url:"../game/delete_discount",
		data:{"id":id},
		async:true,
		success:function(res){
			console.log(res)
			getgoodsList();
		    getnonediscountLit();
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
			var oldpassword = updatapw.oldpassword;
			var newpassword = updatapw.newpassword;
			$.ajax({
				type:"post",
				url:"../user/updatePassword",
				data:{
					"passwordOld":oldpassword,
					"passwordNew":newpassword
				},
				async:true,
				success:function(res){
					if(res.status==0){
						window.location.href= "/digger/views/index.html?username="+updatapw.username+'&password='+newpassword;						
					}else if(res.status==1){
						alert(res.msg)
					}
				}
			});
		}
	},
	created:function(){
		this.username = getUrlParam("username");
	}
})


//校验旧密码
function validatePwd() {
	var pwd1 = document.getElementById("oldpassword").value;
	//var testPwd = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,10}$/;
	if(pwd1.trim().length == 0 || pwd1 == "") {
		document.getElementById("tishiPwd").innerHTML = "<font color='red'>密码不能为空</font>";
		document.getElementById("uppw").disabled = true;
	}
	else {
		document.getElementById("tishiPwd").innerHTML = "<font color='white'></font>";
		document.getElementById("uppw").disabled = false;
	}
}
//校验第一次输入的密码
function validatePwd1() {
	var pwd1 = document.getElementById("newpassword").value;
	//var testPwd = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,10}$/;
	if(pwd1.trim().length == 0 || pwd1 == "") {
		document.getElementById("tishiPwd1").innerHTML = "<font color='red'>密码不能为空</font>";
		document.getElementById("uppw").disabled = true;
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


//退款审核
var refund = new Vue({
	el:"#refund",
	data:{
		order: [],
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
	methods: {
		agreerefund:function(ordernum,price){
			agreerefund(ordernum,price);
		},
		disagreerefund:function(ordernum){
			disagreerefund(ordernum);
		},
		change:function(pn){
			change(pn);
		}
	},
	created: function(){
		
	}
})

$("#getrefund").click(function(e){
	change(1);
})

function change(pn){
	$.ajax({
		type:"POST",
		url:"../order/getrefund/"+pn,
		async:true,
		success: function(res){	
			console.log("eee",res);
			if(res.status == 0)
			{
				refund.order = res.data.list;
				refund.pageNum = res.data.pageNum;
				refund.total = res.data.total;				
				refund.pages = res.data.pages;
				refund.prePage = res.data.prePage;
				refund.nextPage = res.data.nextPage;
				refund.isFirstPage = res.data.isFirstPage;
				refund.isLastPage = res.data.isLastPage;				
				refund.hasPreviousPage = res.data.hasPreviousPage;			
				refund.hasNextPage = res.data.hasNextPage;
				refund.navigatePages = res.data.navigatePages;
				refund.navigatepageNums = res.data.navigatepageNums;
				for(var i = 0; i < refund.order.length; i++){
					refund.order[i].updatetime = format(refund.order[i].updatetime);
				}
			}
			else if(res.status==1)
				alert(res.msg);
		}
	});
}

function agreerefund(ordernum,price){
	$.ajax({
		type:"POST",
		url:"../order/goRefund",
		data:{
			ordernum:ordernum,
			price:price
		},
		async:true,
		success: function(res){
			console.log(res);
			if(res.status == 1){
				alert(res.msg);
			}
			else if(res.status == 0)
			{
				alert(res.msg);
			}
			change(1);
		}
	});
}

function disagreerefund(ordernum){
	alert(ordernum);
	$.ajax({
		type:"POST",
		url:"../order/disagreerefund",
		data:{
			ordernum:ordernum
		},
		async:true,
		success: function(res){
			console.log(res);
			if(res.status == 1){
				alert(res.msg);
			}
			else if(res.status == 0)
			{
				alert(res.msg);
			}
			change(1);
		}
	});
}

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
