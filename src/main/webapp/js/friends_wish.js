$(document).ready(function(){
	
})


var wish = new Vue({
	el:"#wish",
	data:{
		contents:"",
		friendname:""
	},
	methods:{
		pay:function(gameid,price,discount)
		{
			pay(gameid,price,discount);
		}
	},
	created:function(){
		var userid = getUrlParam("id");
		var name = getUrlParam("name");
		this.friendname = name;
		getMyWishList(userid);
	}
})

function pay(gameid,price,discount){
	if(discount!=null||discount!=0)
		price = price*discount*0.1;
	var ordernum = GetDateNow();
	alert(gameid+" "+price+" "+discount+" "+ordernum);
	$.ajax({
		type:"POST",
		url:"../send/gift",
		data:{
			username:wish.friendname,
			gameid:gameid,
			price:price,
			ordernum:ordernum,
			issend:1
		},
		async:true,
		success: function(res){
			console.log(res);
			if(res.status == 1){
				alert(res.msg);
			}
			else if(res.status == 0)
			{
				window.location.href = "../order/goAlipay?ordernum=" + ordernum;
			}
		}
	});
}


//根据id获取好友的愿望清单
function getMyWishList(id){
	$.ajax({
		type:"get",
		url:"../wish/get_friend_wishgame",
		data:{
			userid:id
		},
		async:true,
		success:function(res){
			console.log(res);
			if(res.status == 0){
				wish.contents = res.data;
			}
			else if(res.status == 2){
				wish.contents = null;
				alert(res.msg); 
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

/*
	 * 取路径参数
	 */
	function getUrlParam(name) {
	    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	    var r = window.location.search.substr(1).match(reg);
	    if (r != null) return decodeURI(r[2]); return null; 
	}