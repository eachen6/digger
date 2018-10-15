var wish = new Vue({
	el:"#wish",
	data:{
		contents:""
	},
	methods:{
		
	},
	created:function(){
		getMyWishList();
	}
})

function getMyWishList(){
	var that = this;
	$.ajax({
		type:"get",
		url:"../wish/get_myself_wishgame",
		async:true,
		success:function(res){
			if(res.status == 0){
				wish.contents = res.data;
				for(var i = 0; i < wish.contents.length; i++){
					wish.contents[i].createtime = that.format(wish.contents[i].createtime);
				}
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