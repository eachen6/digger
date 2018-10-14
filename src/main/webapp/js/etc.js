/*轮播图界面*/
new Vue({
	el: '#carousel',
	data: {
		hereimg: []
	},
	method: {},
	created: function() {
		var that = this;
		$.ajax({
			type: "get",
			url: "../game/get_hotsale_carouse",
			async: true,
			success: function(res) {
				console.log(res.data, "1");
				that.hereimg = res.data;
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
