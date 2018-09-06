/*
 * 取路径参数
 */
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURI(r[2]); return null; 
}

$(document).ready(function(){
	/*
	 * 跳转
	 */
	$(function(){
		var name=getUrlParam('location');
		var id=getUrlParam('id');
		if(name==null){
			$("#frame").load("firstindex.html");
		}
//		else if(name=="djyw"||name=="tzgs"||name=="dngs"||name=="xzzq"){
//			if(id==null){
//				$("#frame").load("publicityList.html");
//			}
//			else{
//				$("#frame").load("publicity.html");
//			}
//		}
		else if(name=="home"){
			$("#frame").load("home.html");
		}
		else if(name=="community"){
			$("#frame").load("details.html");
		}
		else if(name=="etc"){
			$("#frame").load("etc.html");
		}
		
		$(".loading").fadeOut(500);
	})
})