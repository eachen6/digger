/**
 * 新闻公告详情页
 */
/*
 * 取路径参数
 */
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURI(r[2]); return null; 
}


var publicity = new Vue({
	el : '#publicity',
	data :{
		datas:[]
	},
	created	: function(){
		var id=getUrlParam('id');//新闻公告id
		getDetails(id);
	},
	methods	:{
		updateMessage: function (){
			var self = this;
			this.$nextTick(function () {
				document.getElementById("details").innerHTML = publicity.datas.content; 				
              })
		}
	}
})
function getDetails(id){//获取详情
	var that = this;
	$.ajax({
		type : "GET",// 请求方式
		url : "../announcement/get_announcementbyid",// 地址，就是json文件的请求路径
		data:{
			id:id
		},
		success : function(result) {// 返回的参数就是 action里面所有的有get和set方法的参数
			if (result.status == 0) {
				publicity.datas = result.data;
				publicity.datas.createtime = that.format(publicity.datas.createtime);
				publicity.updateMessage();
			} else {
				alert(result.msg);
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