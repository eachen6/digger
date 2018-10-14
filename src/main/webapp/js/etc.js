var select = 1; //判断选择的按钮
var searchvalue = "" //保存搜索关键词
	
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
			var name=getUrlParam('name');
			if(name!=null)
			   searchgame(name,1);
		})
		
		})
	
/*轮播图界面*/
new Vue({
	el: '#myCarousel',
	data: {
		games: []
	},
	method: {},
	created: function() {
		var that = this;
		$.ajax({
			type: "POST",
			url: "../game/get_hotsale_carouse",
			async: true,
			success: function(res) {
				console.log(res.data, "1");
				if(res.status==0){
				that.games = res.data;
				}
			}
		});
	}
})

/*游戏列表界面*/
new Vue({
	el: '#gameList',
	data: {
		specifiedPage: 3,
		total:'',
		"gamelist": []
	},
	methods: {},
	created: function() {
		var that = this;
		$.ajax({
			type: "get",
			url: "../game/get_total_gamelist",
			async: true,
			success: function(res) {
				console.log(res.data, "2");
				
				that.gamelist = res.data;
				for(var i = 0; i < that.gamelist.length; i++)
				{
					that.gamelist[i].category = that.gamelist[i].category.split(",");
				}
			}
		});
		$.ajax({
			type: "get",
			url: "../game/get_total_game",
			async: true,
			success: function(res) {
				console.log(res.data, "3");
				that.total = res.data;
			}
		});
	}
})

/*游戏总数*/
//new Vue({
//	el: '#gametotal',
//	data: {
//		total: ''
//	},
//	methods: {},
//	created: function() {
//		var that = this;
//		$.ajax({
//			type: "get",
//			url: "../game/get_total_game",
//			async: true,
//			success: function(res) {
//				console.log(res.data, "3");
//				that.total = res.data;
//			}
//		});
//	}
//})

//搜索框提示渲染
var search = new Vue({
	el: "#search_form",
	data: {
		"game": [],
		"search_value": ""
	},
	methods: {
		change: function(e) {
			search_change(this.search_value);
		},
	    searchgame:function(name){
	    	searchgame(name,1);
	    },
	    searchgame1:function(){
	    	searchgame(this.search_value,1);
	    }
	},
	created: function() {

	}
})

//根据关键词自动跳出提示词
function search_change(input_value) {
	$.ajax({
		url: "../game/search_game_byword",
		data: {
			"keyword": input_value
		},
		type: 'GET',
		contentType: "application/json; charset=utf-8",
		success: function(res) {
			if(res.status == 0) {
				search.game = res.data;
			}
		}
	})
}

//根据关键词进行搜索
function searchgame(name,pn){
	searchvalue = name;
	select = 5;
	$.ajax({
		url: "../game/search_game_byname/"+name+"/"+pn,
		type: 'GET',
		contentType: "application/json; charset=utf-8",
		success: function(res) {
			if(res.status == 0) {
				moregame.games = res.data.list;
				for(var i = 0; i < moregame.games.length; i++)
				{
					moregame.games[i].category = moregame.games[i].category.split(",");
				}	
				moregame.pageNum = res.data.pageNum;
				moregame.total = res.data.total;				
				moregame.pages = res.data.pages;
				moregame.prePage = res.data.prePage;
				moregame.nextPage = res.data.nextPage;
				moregame.isFirstPage = res.data.isFirstPage;
				moregame.isLastPage = res.data.isLastPage;				
				moregame.hasPreviousPage = res.data.hasPreviousPage;			
				moregame.hasNextPage = res.data.hasNextPage;
				moregame.navigatePages = res.data.navigatePages;
				moregame.navigatepageNums = res.data.navigatepageNums;
				$("#rankbtn").hide();
				$("#mypp").text("搜索    "+name+" 共"+moregame.total+"款");
				$('body,html').animate({scrollTop: $('#mypp').offset().top}, 500);
			}
		}
	})
}


$(document).ready(function() {
	//搜索提示框
	$('#search_input').click(function() {
		$("#search_suggest").width($("#search_input").width() + 25),
			$('#search_suggest').show().css({
				top: $('#search_input').offset().top + $('#search_input').height() - 67,
				left: $('#search_input').offset().left,
				position: 'absolute'
			});
	});

	$('body').click(function(e) {
		if(e.target.id != 'search_input')
			if($('#search_suggest').is(':visible')) {
				$('#search_suggest').hide();
			}
	})
})

// 循环轮播到上一个项目
$("#prev-btn").click(function() {
	$("#myCarousel").carousel('prev');
});
// 循环轮播到下一个项目
$("#next-btn").click(function() {
	$("#myCarousel").carousel('next');
});


//标签渲染
new Vue({
	el:"#gamecategory",
	data:{
		categorys: []
	},
	methods: {
		searchgame:function(name){
			searchgame(name,1);
			$('body,html').animate({scrollTop: $('#mypp').offset().top}, 500);
		}
	},
	created: function(){
		var that = this;
		$.ajax({
			url: "../category/search_alllabel",
			type:'POST',
			contentType:"application/json; charset=utf-8",
			success:function(res){
				if(res.status==0)
				{
					that.categorys = res.data;
					console.log(res.data);
				}
			}
		})  
	}
})

//更多游戏<火爆新品>渲染
var moregame = new Vue({
	el:"#rank",
	data:{
		games: [],
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
		details:function(id){
			window.location.href="main.html?location=details&id="+id;
			console.log("ddddd");
		},
		search:function(cg,ev){
			//searchgame(cg,pn)
			window.location.href="main.html?location=etc&name="+cg;
			var oEvent = ev || event;
            console.log("ffffffffff");
            oEvent.cancelBubble = true; 	
		},
		change:function(pn){
			//alert(select);
			if(select == 1)
				newhot(pn);
			else if(select == 2)
				weekhot(pn);
			else if(select == 3)
				newput(pn);
			else if(select == 4)
				discount(pn);
			else if(select == 5)
				searchgame(searchvalue,pn)
		}
	},
	created: function(pn){
		newhot(1);
	}
})

//<火爆新品>
function newhot(pn){
	$.ajax({
		url: "../game/get_hotnew_gamelist/"+pn,
		type:'POST',
		contentType:"application/json; charset=utf-8",
		success:function(res){
			console.log(res);
			if(res.status==0)
			{
				moregame.games = res.data.list;
				for(var i = 0; i < moregame.games.length; i++)
				{
					moregame.games[i].category = moregame.games[i].category.split(",");
				}	
				moregame.pageNum = res.data.pageNum;
				moregame.total = res.data.total;				
				moregame.pages = res.data.pages;
				moregame.prePage = res.data.prePage;
				moregame.nextPage = res.data.nextPage;
				moregame.isFirstPage = res.data.isFirstPage;
				moregame.isLastPage = res.data.isLastPage;				
				moregame.hasPreviousPage = res.data.hasPreviousPage;			
				moregame.hasNextPage = res.data.hasNextPage;
				moregame.navigatePages = res.data.navigatePages;
				moregame.navigatepageNums = res.data.navigatepageNums;
			}
		}
	})  
}

//<本周热门>
function weekhot(pn){
	$.ajax({
		url: "../game/get_weekhot_gamelist/"+pn,
		type:'POST',
		contentType:"application/json; charset=utf-8",
		success:function(res){
			console.log(res);
			if(res.status==0)
			{
				moregame.games = res.data.list;
				for(var i = 0; i < moregame.games.length; i++)
				{
					moregame.games[i].category = moregame.games[i].category.split(",");
					moregame.pageNum = res.data.pageNum;
					moregame.total = res.data.total;				
					moregame.pages = res.data.pages;
					moregame.prePage = res.data.prePage;
					moregame.nextPage = res.data.nextPage;
					moregame.isFirstPage = res.data.isFirstPage;
					moregame.isLastPage = res.data.isLastPage;				
					moregame.hasPreviousPage = res.data.hasPreviousPage;			
					moregame.hasNextPage = res.data.hasNextPage;
					moregame.navigatePages = res.data.navigatePages;
					moregame.navigatepageNums = res.data.navigatepageNums;
				}
			}
		}
	})  
}

//<最新上架>
function newput(pn){
	$.ajax({
		url: "../game/get_newput_gamelist/"+pn,
		type:'POST',
		contentType:"application/json; charset=utf-8",
		success:function(res){
			console.log(res);
			if(res.status==0)
			{
				moregame.games = res.data.list;
				for(var i = 0; i < moregame.games.length; i++)
				{
					moregame.games[i].category = moregame.games[i].category.split(",");
					moregame.pageNum = res.data.pageNum;
					moregame.total = res.data.total;				
					moregame.pages = res.data.pages;
					moregame.prePage = res.data.prePage;
					moregame.nextPage = res.data.nextPage;
					moregame.isFirstPage = res.data.isFirstPage;
					moregame.isLastPage = res.data.isLastPage;				
					moregame.hasPreviousPage = res.data.hasPreviousPage;			
					moregame.hasNextPage = res.data.hasNextPage;
					moregame.navigatePages = res.data.navigatePages;
					moregame.navigatepageNums = res.data.navigatepageNums;
				}
			}
		}
	})  
}

//<折扣促销>
function discount(pn){
	$.ajax({
		url: "../game/get_discount_gamelist/"+pn,
		type:'POST',
		contentType:"application/json; charset=utf-8",
		success:function(res){
			console.log(res);
			if(res.status==0)
			{
				moregame.games = res.data.list;
				for(var i = 0; i < moregame.games.length; i++)
				{
					moregame.games[i].category = moregame.games[i].category.split(",");
					moregame.pageNum = res.data.pageNum;
					moregame.total = res.data.total;				
					moregame.pages = res.data.pages;
					moregame.prePage = res.data.prePage;
					moregame.nextPage = res.data.nextPage;
					moregame.isFirstPage = res.data.isFirstPage;
					moregame.isLastPage = res.data.isLastPage;				
					moregame.hasPreviousPage = res.data.hasPreviousPage;			
					moregame.hasNextPage = res.data.hasNextPage;
					moregame.navigatePages = res.data.navigatePages;
					moregame.navigatepageNums = res.data.navigatepageNums;
				}
			}
		}
	})  
}


//按钮切换active效果
$("#btn1").click(function(){
	select = 1;
	$("#btn1").css({"background-color":"#474747" ,"color":"#FFFFFF"});
	$("#btn2").css({"background-color":"#FFFFFF" ,"color":"#3C3C3C"});
	$("#btn3").css({"background-color":"#FFFFFF" ,"color":"#3C3C3C"});
	$("#btn4").css({"background-color":"#FFFFFF" ,"color":"#3C3C3C"});
	moregame.change(1);
  });

$("#btn2").click(function(){
	select = 2;
	$("#btn2").css({"background-color":"#474747" ,"color":"#FFFFFF"});
	$("#btn1").css({"background-color":"#FFFFFF" ,"color":"#3C3C3C"});
	$("#btn3").css({"background-color":"#FFFFFF" ,"color":"#3C3C3C"});
	$("#btn4").css({"background-color":"#FFFFFF" ,"color":"#3C3C3C"});
	moregame.change(1);
  });

$("#btn3").click(function(){
	select = 3;
	$("#btn3").css({"background-color":"#474747" ,"color":"#FFFFFF"});
	$("#btn2").css({"background-color":"#FFFFFF" ,"color":"#3C3C3C"});
	$("#btn1").css({"background-color":"#FFFFFF" ,"color":"#3C3C3C"});
	$("#btn4").css({"background-color":"#FFFFFF" ,"color":"#3C3C3C"});
	moregame.change(1);
  });

$("#btn4").click(function(){
	select = 4;
	$("#btn4").css({"background-color":"#474747" ,"color":"#FFFFFF"});
	$("#btn2").css({"background-color":"#FFFFFF" ,"color":"#3C3C3C"});
	$("#btn3").css({"background-color":"#FFFFFF" ,"color":"#3C3C3C"});
	$("#btn1").css({"background-color":"#FFFFFF" ,"color":"#3C3C3C"});
	moregame.change(1);
  });

