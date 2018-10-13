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

var search = new Vue({
	el: "#search_form",
	data: {
		"game": [],
		"search_value": ""
	},
	methods: {
		change: function(e) {
			search_change(this.search_value);
		}
	},
	created: function() {

	}
})

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

//按钮切换active效果
$("#btn1").click(function(){
	$("#btn1").css({"background-color":"#474747" ,"color":"#FFFFFF"});
	$("#btn2").css({"background-color":"#FFFFFF" ,"color":"#3C3C3C"});
	$("#btn3").css({"background-color":"#FFFFFF" ,"color":"#3C3C3C"});
	$("#btn4").css({"background-color":"#FFFFFF" ,"color":"#3C3C3C"});
  });

$("#btn2").click(function(){
	$("#btn2").css({"background-color":"#474747" ,"color":"#FFFFFF"});
	$("#btn1").css({"background-color":"#FFFFFF" ,"color":"#3C3C3C"});
	$("#btn3").css({"background-color":"#FFFFFF" ,"color":"#3C3C3C"});
	$("#btn4").css({"background-color":"#FFFFFF" ,"color":"#3C3C3C"});
  });

$("#btn3").click(function(){
	$("#btn3").css({"background-color":"#474747" ,"color":"#FFFFFF"});
	$("#btn2").css({"background-color":"#FFFFFF" ,"color":"#3C3C3C"});
	$("#btn1").css({"background-color":"#FFFFFF" ,"color":"#3C3C3C"});
	$("#btn4").css({"background-color":"#FFFFFF" ,"color":"#3C3C3C"});
  });

$("#btn4").click(function(){
	$("#btn4").css({"background-color":"#474747" ,"color":"#FFFFFF"});
	$("#btn2").css({"background-color":"#FFFFFF" ,"color":"#3C3C3C"});
	$("#btn3").css({"background-color":"#FFFFFF" ,"color":"#3C3C3C"});
	$("#btn1").css({"background-color":"#FFFFFF" ,"color":"#3C3C3C"});
  });



//标签渲染
new Vue({
	el:"#gamecategory",
	data:{
		categorys: []
	},
	methods: {
		clickcategory:function(cg){
			window.location.href="main.html?location=etc&cg="+cg;
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
var hotnew = new Vue({
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
			window.location.href="main.html?location=etc&cg="+cg;
			var oEvent = ev || event;
            console.log("ffffffffff");
            oEvent.cancelBubble = true; 	
		},
		change:function(pn){
			queryajax(pn);
		}
	},
	created: function(){
		queryajax(1);
	}
})

function queryajax(pn){
	//alert("444");
	$.ajax({
		url: "../game/get_hotnew_gamelist/"+pn,
		type:'POST',
		contentType:"application/json; charset=utf-8",
		success:function(res){
			console.log(res);
			
			if(res.status==0)
			{
				hotnew.games = res.data.list;
				for(var i = 0; i < hotnew.games.length; i++)
				{
					hotnew.games[i].category = hotnew.games[i].category.split(",");
				}	
				hotnew.pageNum = res.data.pageNum;
				hotnew.total = res.data.total;				
				hotnew.pages = res.data.pages;
				hotnew.prePage = res.data.prePage;
				hotnew.nextPage = res.data.nextPage;
				hotnew.isFirstPage = res.data.isFirstPage;
				hotnew.isLastPage = res.data.isLastPage;				
				hotnew.hasPreviousPage = res.data.hasPreviousPage;			
				hotnew.hasNextPage = res.data.hasNextPage;
				hotnew.navigatePages = res.data.navigatePages;
				hotnew.navigatepageNums = res.data.navigatepageNums;
			}
		}
	})  
}

