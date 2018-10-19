$(document).ready(function(){
	//提示框
	$(function(){
		toastr.options= {
		"closeButton":true,//显示关闭按钮
		"debug":false,//启用debug
		"positionClass":"toast-top-center",//弹出的位置
		"showDuration":"300",//显示的时间
		"hideDuration":"1000",//消失的时间
		"timeOut":"3000",//停留的时间
		"extendedTimeOut":"1000",//控制时间
		"showEasing":"swing",//显示时的动画缓冲方式
		"hideEasing":"linear",//消失时的动画缓冲方式
		"showMethod":"fadeIn",//显示时的动画方式
		"hideMethod":"fadeOut"//消失时的动画方式
		};
		});  
})


var wish = new Vue({
	el:"#wish",
	data:{
		contents:""
	},
	methods:{
		deletewish:function(gameid){
			deletemywish(gameid);
		},
		pay:function(gameid,price,discount)
		{
			pay(gameid,price,discount);
		}
	},
	created:function(){
		getMyWishList();
	}
})

function pay(gameid,price,discount){
	if(discount!=null||discount!=0)
		price = price*discount*0.1;
	var ordernum = GetDateNow();
	alert(gameid+" "+price+" "+discount+" "+ordernum);
	$.ajax({
		type:"POST",
		url:"../order/create",
		data:{
			gameid:gameid,
			price:price,
			ordernum:ordernum,
			issend:0
		},
		async:true,
		success: function(res){
			console.log(res);
			if(res.status == 1){
				toastr.info(res.msg);
			}
			else if(res.status == 0)
			{
				toastr.success(res.msg);
				window.location.href = "../order/goAlipay?ordernum=" + ordernum;
			}
		}
	});
}

//删除我的愿望清单
function deletemywish(gameid){
	$.ajax({
		type:"POST",
		url:"../wish/delete_wishgame",
		data:{
			gameid:gameid
		},
		async:true,
		success:function(res){
			console.log(res);
			if(res.status == 0){
				getMyWishList();
				toastr.success(res.msg);	
			}
			else if(res.status == 1)
			{
				toastr.info(res.msg); 
			}
		}
	});
}

//获取我的愿望清单
function getMyWishList(){
	$.ajax({
		type:"get",
		url:"../wish/get_myself_wishgame",
		async:true,
		success:function(res){
			console.log(res);
			if(res.status == 0){
				wish.contents = res.data;
			}
			else if(res.status == 2){
				wish.contents = null;
				toastr.info(res.msg); 
			}
		}
	});
}


function add0(m){return m<10?'0'+m:m }
function format(shijianchuo)
{
//shijianchuo是整数，否则要parseInt转换
var time = new Date(shijianchuo);
var y = time.getFullYear();
var m = time.getMonth()+1;
var d = time.getDate();
var h = time.getHours();
var mm = time.getMinutes();
var s = time.getSeconds();
return y+'-'+add0(m)+'-'+add0(d)+' '+add0(h)+':'+add0(mm)+':'+add0(s);
}

//生成订单号
function GetDateNow() {
	var vNow = new Date();
	var sNow = "";
	sNow += String(vNow.getFullYear());
	sNow += String(vNow.getMonth() + 1);
	sNow += String(vNow.getDate());
	sNow += String(vNow.getHours());
	sNow += String(vNow.getMinutes());
	sNow += String(vNow.getSeconds());
	sNow += String(vNow.getMilliseconds());
	return sNow;
}
