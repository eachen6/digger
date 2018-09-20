$(function() {
				// 循环轮播到上一个项目
				$("#prev-btn").click(function() {
					$("#myCarousel").carousel('prev');
				});
				// 循环轮播到下一个项目
				$("#next-btn").click(function() {
					$("#myCarousel").carousel('next');
				});
				// 循环轮播到上一个项目
				$("#prev-btn1").click(function() {
					$("#myCarousel1").carousel('prev');
				});
				// 循环轮播到下一个项目
				$("#next-btn1").click(function() {
					$("#myCarousel1").carousel('next');
				});
				// 循环轮播到上一个项目
				$("#prev-btn1").click(function() {
					$("#myCarousel2").carousel('prev');
				});
				// 循环轮播到下一个项目
				$("#next-btn1").click(function() {
					$("#myCarousel2").carousel('next');
				});
				// 循环轮播到上一个项目
				$("#prev-btn2").click(function() {
					$("#myCarousel3").carousel('prev');
				});
				// 循环轮播到下一个项目
				$("#next-btn2").click(function() {
					$("#myCarousel3").carousel('next');
				});
			});
			
/**
 * 新增一个全文跳转监听函数
 */
$(document).ready(function(){
	$("#toallgame").click(function(e){
		$("#frame").load("../views/etc.html")
	})
})


//搜索提示框
$('#search_input').click(function(){
    	$("#search_suggest").width($("#search_input").width()+25),
        $('#search_suggest').show().css({
         top:$('#search_input').offset().top+$('#search_input').height()+15,
         left:$('#search_input').offset().left,
         position:'absolute'
        });
        });
   
        
       $('body').click(function(e) {
       if(e.target.id != 'search_input')
       if ( $('#search_suggest').is(':visible') ) {
           $('#search_suggest').hide();
       }
       });
