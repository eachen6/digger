
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
	
	/*
	 * 跳转
	 */
//	$(function(){
//		var id=getUrlParam('id');
//		if(id!=null){
//			gamedetails(id); //获取游戏属性
//			//gameiswish(id);  //判断用户是否已将该游戏加入愿望清单了
//			gameisbuy(id);   //判断这款游戏是否已被用户购买过了 
//		}
//		else 
//			history.go(-1);
//	});
	
	//点击愿望清单的切换
	$("#btn_wish").click(function(e){
	    game.iswish = (game.iswish == false) ? true : false
	    console.log("之后",game.iswish)
    });

    //点击确认购买动作
    $("#sureforpay").click(function(e){
        
    })
    
})

/*
	 * 取路径参数
	 */
	function getUrlParam(name) {
	    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	    var r = window.location.search.substr(1).match(reg);
	    if (r != null) return decodeURI(r[2]); return null; 
	}


var game = new Vue({
	el:'#game',
	data:{
//		head_img:"https://cdn.rail.tgp.qq.com/info/games/2000352/27d1c5e3f37e219feb118895f7f37165.jpg",
		prename:"所有游戏",
//		gamename:"NBA2KOL2",
		iswish:false,
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
		var id=getUrlParam('id');
		console.log(id)
		if(id!=null){
			getWishState(id);
			gamedetails(id);
		}else 
			history.go(-1);
	}
})

function getWishState(id){
	$.ajax({
			type:"get",
			url:"../wish/get_wishgame",
			data:{
				gameid:id
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



//

/*//获取用户是否
function gameiswish(id){
	$.ajax({
		type:"get",
		url:"../game/iswish/4",
		async:true,
		success: function(res){
			console.log(res.data)
			game.contents = res.data[0]
			game.selective = game.contents.category.split(",");
		}
	});
}
*/
//获取游戏属性（包括折扣信息），其中让这款游戏的点击量+1（有判断是否刷点击量的机制）
function gamedetails(id){
	$.ajax({
		type:"get",
		url:"../game/detail/"+id,
		async:true,
		success: function(res){
			console.log(res.data)
			game.contents = res.data[0]
			game.id = game.contents.id;
			game.selective = game.contents.category.split(",");
		}
	});
}

