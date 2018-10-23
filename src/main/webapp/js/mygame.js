var mygame = new Vue({
	el:"#mygame",
	data:{
		content:""
	},
	methods:{
		
	},
	created:function(){
		$.ajax({
			type:"get",
			url:"../order/get_mygame",
			async:true,
			success:function(res){
				this.content = res.data;
			}
		});
	}
})
