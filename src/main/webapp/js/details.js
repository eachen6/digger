
/**
 * QRCode生成
 */
//var qrcode = new QRCode(document.getElementById("qrcode"), {
//	width : 100,
//	height : 100
//});
//
//
$(document).ready(function(){
	
//	var qrText = "扫描付款";
//	qrcode.makeCode(qrText);
	
	$("#btn_wish").click(function(e){
	game.iswish = (game.iswish == false) ? true : false
	console.log("之后",game.iswish)
})

})

var game = new Vue({
	el:'#game',
	data:{
//		head_img:"https://cdn.rail.tgp.qq.com/info/games/2000352/27d1c5e3f37e219feb118895f7f37165.jpg",
		prename:"所有游戏",
//		gamename:"NBA2KOL2",
		iswish:true,
//		cover_img:"https://cdn.rail.tgp.qq.com/info/games/2000352/2c2dc799c98d1904cd062e60a0924cef.jpg",
//		video_src:"https://media.w3.org/2010/05/sintel/trailer.mp4",
		anno:{
			img:"https://shp.qpic.cn/tgp_hpic/0/1534348829368_articleCover_e88348b8-0da0-40e0-accf-ceaea3b56d4e/0",
			title:"8月16日停机更新公告",
			part:"60祯、球员数据更新来啦",
			createdtime:"2018-08-16 00:01:41"
		},
		selective:[
		],
		id:null,
		contents:""
	},
	methods:{
		addToWish:function(){
			//增加到愿望清单
			addToWish(this.id)
			console.log("执行了添加到愿望清单功能")
		},
		deleteFromWish:function(){
			//从愿望清单中删除
			deleteFromWish(this.id)
			console.log("执行了从愿望清单中取消的功能")
		}
	},
	created: function(){
		var that = this;
		$.ajax({
			type:"get",
			url:"../game/detail/4",
			async:true,
			success: function(res){
				console.log(res.data)
				that.contents = res.data[0]
				that.id = res.data[0].id;
				that.selective = that.contents.category.split(",");
			}
		});
//		$.ajax({
//			type:"get",
//			url:"../wish/get_wishgame",
//			data:{
//				gameid:4
//			},
//			async:true,
//			success: function(res){
//				if(res.status == 1){
//					this.iswish = false;
//				}
//				else if(res.status == 0)
//				{
//					this.iswish = true
//				}
//			}
//		});
		getWishState(4);
	}
})

function getWishState(id){
	$.ajax({
			type:"get",
			url:"../wish/get_wishgame",
			data:{
				gameid:4
			},
			async:true,
			success: function(res){
				if(res.status == 1){
					game.iswish = false;
				}
				else if(res.status == 0)
				{
					game.iswish = true
				}
			}
		});
}

function addToWish(id){
	var that = this;
	$.ajax({
		type:"POST",
		url:"../wish/add_wishgame",
		data:{
			gameid:id
		},
		async:true,
		success:function(res){
			console.log(res)
			getWishState(id)
		}
	});
}

function deleteFromWish(id){
	var that = this;
	$.ajax({
		type:"POST",
		url:"../wish/delete_wishgame",
		data:{
			gameid:id
		},
		async:true,
		success:function(res){
			console.log(res)
			getWishState(id)
		}
	});
}


