/**
 * 
 */

var page = new Vue({
	el:"#mydiv",
	data:{
		pageNum: "",
		total: "",
		pages: "",
	    prePage:"",
		nextPage:"",
		isFirstPage:"",
		isLastPage:"",
		hasPreviousPage:"",
		hasNextPage:"",
		navigatePages:"",
		navigatepageNums:[],
		list:[]
	},
	methods: {
		change:function(pn){
			alert(pn);
			mypage(pn);
		}
	},
	created: function(){
		mypage(1);
		console.log(1)
	}
})

function mypage(pn){
	$.ajax({
		url: "../user/pagetest",
		data:{"pn":pn},
		type:'GET',
		contentType:"application/json; charset=utf-8",
		success:function(res){
			console.log(res);
			if(res.status==0)
			{
				page.pageNum = res.data.pageNum;
				page.total = res.data.total;
				page.pages = res.data.pages;
				page.prePage = res.data.prePage;
				page.nextPage = res.data.nextPage;
				page.isFirstPage = res.data.isFirstPage;
				page.isLastPage = res.data.isLastPage;
				page.hasPreviousPage = res.data.hasPreviousPage;
				page.hasNextPage = res.data.hasNextPage;
				page.navigatePages = res.data.navigatePages;
				page.navigatepageNums = res.data.navigatepageNums;
				page.list = res.data.list;
				//alert(page.total);
			}
		}
	}) 
}