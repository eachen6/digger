var order = new Vue({
	el:"#order",
	data:{
		content:""
	},
	methods:{
		deleteit: function(id){
			deleteTheOrder(id)
		}
	},
	created:function(){
		getOrderList();	
	}
})

function getOrderList(){
	$.ajax({
		type:"get",
		url:"../order/get_order",
		async:true,
		success:function(res){
			console.log(res);
			if(res.status == 0){
				order.content = res.data;
				for(var i = 0; i <order.content.length; i++){
					order.content[i].closetime = comptime(order.content[i].closetime);
					alert(order.content[i].closetime);
					order.content[i].createtime = format(order.content[i].createtime);
				}
				console.log(order.content);
			}
			
		}
	});
}

function deleteTheOrder(id){
	var that = this;
	$.ajax({
		type:"POST",
		url:"../order/delete_order",
		data:{
			id:id
		},
		async:true,
		success:function(res){
			console.log(res)
			if(res.status == 1)
				alert(res.msg)
				else if(res.status == 0)
				{
					alert(res.msg)
				}
				location.reload();
		}
	});
	console.log("执行删除操作,id为",id)
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

//比较当前时间和closetime的大小
function comptime(closetime){
	var timestamp =  Date.parse(new Date());
	if(timestamp>closetime)
		return true;
	else
		return false;
}