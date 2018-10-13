
$(function() {
				// 循环轮播到上一个项目
				$("#prev-btn").click(function() {
					$("#myCarousel").carousel('prev');
				});
				// 循环轮播到下一个项目
				$("#next-btn").click(function() {
					$("#myCarousel").carousel('next');
				});
				// 循环轮播到上一个项目
				$("#prev-btn1").click(function() {
					$("#myCarousel1").carousel('prev');
				});
				// 循环轮播到下一个项目
				$("#next-btn1").click(function() {
					$("#myCarousel1").carousel('next');
				});
				// 循环轮播到上一个项目
				$("#prev-btn1").click(function() {
					$("#myCarousel2").carousel('prev');
				});
				// 循环轮播到下一个项目
				$("#next-btn1").click(function() {
					$("#myCarousel2").carousel('next');
				});
				// 循环轮播到上一个项目
				$("#prev-btn2").click(function() {
					$("#myCarousel3").carousel('prev');
				});
				// 循环轮播到下一个项目
				$("#next-btn2").click(function() {
					$("#myCarousel3").carousel('next');
				});
			})
			
/**
 * 新增一个全文跳转监听函数
 */
$(document).ready(function(){
	$("#toallgame").click(function(e){
		$("#frame").load("../views/etc.html")
	})
})

var search = new Vue({
	el:"#search_form",
	data:{
		"game": [],
		"search_value": ""
	},
	methods: {
		change:function(e){
			search_change(this.search_value);
		}
	},
	created: function(){
		
	}
})


function search_change(input_value){
	 $.ajax({
			url: "../game/search_game_byword",
			data:{"keyword":input_value},
			type:'GET',
			contentType:"application/json; charset=utf-8",
			success:function(res){
				console.log(res);
				if(res.status==0)
				{
					search.game = res.data;
				}
			}
		})  
}


//搜索框2
var search1 = new Vue({
	el:"#search_form1",
	data:{
		"game": [],
		"search_value": ""
	},
	methods: {
		change:function(e){
			search_change1(this.search_value);
		}
	},
	created: function(){
		
	}
})

function search_change1(input_value){
	 $.ajax({
			url: "../game/search_game_byword",
			data:{"keyword":input_value},
			type:'GET',
			contentType:"application/json; charset=utf-8",
			success:function(res){
				console.log(res);
				if(res.status==0)
				{
					search1.game = res.data;
				}
			}
		})  
}


$(document).ready(function(){
//搜索提示框
$('#search_input').click(function(){
	$("#search_suggest").width($("#search_input").width()+25),
    $('#search_suggest').show().css({
     top:$('#search_input').offset().top+$('#search_input').height()-67,
     left:$('#search_input').offset().left,
     position:'absolute'
    });
    });

$('body').click(function(e) {
if(e.target.id != 'search_input')
if ( $('#search_suggest').is(':visible') ) {
    $('#search_suggest').hide();
}
});

$('body').click(function(e) {
	if(e.target.id != 'search_input1')
	if ( $('#search_suggest1').is(':visible') ) {
	    $('#search_suggest1').hide();
	}
	});


$('#search_input1').click(function(){
	$("#search_suggest1").width($("#search_input1").width()+25),
    $('#search_suggest1').show().css({
     top:$('#search_input1').offset().top+$('#search_input1').height()+15,
     left:$('#search_input1').offset().left,
     position:'absolute'
    });
    });

})

//轮播图渲染
new Vue({
	el:"#myCarousel",
	data:{
		games: []
	},
	methods: {},
	created: function(){
		var that = this;
		$.ajax({
			url: "../game/get_notice_carouse",
			type:'POST',
			contentType:"application/json; charset=utf-8",
			success:function(res){
				//console.log(res);
				if(res.status==0)
				{
					that.games = res.data;
					//console.log(that.games[0].name);
				}
			}
		})  
	}
})

//热点预告渲染
new Vue({
	el:"#gamenotice",
	data:{
		games: [],
		sum:""
	},
	methods: {},
	created: function(){
		var that = this;
		$.ajax({
			url: "../game/get_notice_gamelist",
			type:'POST',
			contentType:"application/json; charset=utf-8",
			success:function(res){
				console.log(res);
				if(res.status==0)
				{
					that.games = res.data;
					var s = res.data.length;
					if(s%4==0) 
						s = s/4;
					else
						s = parseInt(s/4) + 1;
					that.sum = s;
					console.log(that.sum);
				}
			}
		})  
	}
})


//限时特惠渲染
new Vue({
	el:"#myCarousel3",
	data:{
		games: [],
		sum:""
	},
	methods: {},
	created: function(){
		var that = this;
		$.ajax({
			url: "../game/get_discount_gamelist",
			type:'POST',
			contentType:"application/json; charset=utf-8",
			success:function(res){
				if(res.status==0)
				{
					that.games = res.data;
					var s = res.data.length;
					if(s%3==0) 
						s = s/3;
					else
						s = parseInt(s/3) + 1;
					that.sum = s;
				}
			}
		})  
	}
})

//更多游戏渲染
var moregame = new Vue({
	el:"#rank",
	data:{
		games: []
	},
	methods: {
		details:function(id){
			window.location.href="main.html?location=details&id="+id;
			console.log("ddddd");
		},
		search:function(cg,ev){
			window.location.href="main.html?location=etc&cg="+cg;
			var oEvent = ev || event;
            //console.log("ffffffffff");
            oEvent.cancelBubble = true; 	
		},
		hotnew:function(){
			newhot();
		},
		weekhot:function(){
			weekhot();
		},
		newput:function(){
			newput();
		},
		discount:function(){
			discount();
		}
	},
	created: function(){
		newhot();
	}
})

//<火爆新品>
function newhot(){
	$.ajax({
		url: "../game/get_hotnew_gamelist/1",
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
			}
		}
	})  
}

//<本周热门>
function weekhot(){
	$.ajax({
		url: "../game/get_weekhot_gamelist/1",
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
			}
		}
	})  
}

//<最新上架>
function newput(){
	$.ajax({
		url: "../game/get_newput_gamelist/1",
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
			}
		}
	})  
}

//<折扣促销>
function discount(){
	$.ajax({
		url: "../game/get_discount_gamelist/1",
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
			}
		}
	})  
}

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
				}
			}
		})  
	}
})

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
