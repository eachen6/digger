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
     top:$('#search_input').offset().top+$('#search_input').height()+15,
     left:$('#search_input').offset().left,
     position:'absolute'
    });
	alert($('#search_input').offset().left);

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
