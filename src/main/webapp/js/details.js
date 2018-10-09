
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
		"gamelist": []
	},
	methods: {
		
	},
	created: function(){
		var that = this;
		$.ajax({
			type:"get",
			url:"../game/detail/4",
			async:true,
			success: function(res){
				that.gamelist = res.data;
			}
		});
	}
})