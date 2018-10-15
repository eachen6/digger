/**
 * 待审核模块
 */
var check = new Vue({
	el:"#check",
	data:{
		checks:[]
	},
	methods:{},
	created:function(){
		getCheckList();//掉用函数
	}
})

/**
 * 获取待审核列表
 */
function getCheckList(){
	var that = this;
	$.ajax({
		type:"get",
		url:"../gameaudit/unaudited_gamelist",
		async:true,
		success:function(res){
			console.log(res)//通过打印返回内容确定数据格式，根据数据调整当前页面内容
			check.checks = res.data;//把值赋给VUE的变量
			for(var i = 0; i < check.checks.length; i++){
				check.checks[i].createtime = that.format(check.checks[i].createtime)
			}
		}
	});
}

/**
 * 时间戳装换格式函数
 * @param {Object} m
 */
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