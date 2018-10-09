
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
		contents:""
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
				that.selective = that.contents.category.split(",");
			}
		});
	}
})