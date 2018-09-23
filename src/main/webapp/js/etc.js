

/*轮播图界面*/
new Vue({
	el: '#carousel',
	data: {
		hereimg: [{
			img: '../img/exm4.jpg',
			gamename: 'NATUTO',
			gameprice: '100',
			local: 'www.bing.com'
		}, {
			img: '../img/exm2.jpg',
			gamename: 'NATUTO',
			gameprice: '100',
			local: 'www.baidu.com'
		}, {
			img: '../img/exm1.jpg',
			gamename: 'NATUTO',
			gameprice: '100',
			local: 'www.7k7k.com'
			
		}, {
			img: '../img/exm8.jpg',
			gamename: 'NATUTO',
			gameprice: '100',
			local: 'www.bilibili.com'
		}]
	}
})  

/*游戏列表界面*/
new Vue({
	el: '#gameList',
	data: {
		total: 2,
		navUrl1: 'www.baidu.com',
		navUrl2: 'www.bilibili.com',
		navUrl3: 'www.douyu.com',
		navUrl4: 'www.4399.com',
		pageUrl1: 'www.baidu.com',
		gameType: 'www.baidu.com',
		specifiedPage: 3
	},
	methods: {
		gameDetails: function(event) {
			console.log(event)
		},
		skip: function(event) {
			alert(this.specifiedPage)
		}
	}
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
				if(res.status==0)
				{
					search.game = res.data;
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
    });

$('body').click(function(e) {
if(e.target.id != 'search_input')
if ( $('#search_suggest').is(':visible') ) {
    $('#search_suggest').hide();
}
})
})

