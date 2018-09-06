/*轮播图界面*/
new Vue({
	el: '#carousel',
	data: {
		hereimg: [{
			img: '../img/exm4.jpg',
			gamename: 'NATUTO',
			gameprice: '100',
			local: 'www.bing.com'
		}, {
			img: '../img/exm2.jpg',
			gamename: 'NATUTO',
			gameprice: '100',
			local: 'www.baidu.com'
		}, {
			img: '../img/exm1.jpg',
			gamename: 'NATUTO',
			gameprice: '100',
			local: 'www.7k7k.com'
			
		}, {
			img: '../img/exm8.jpg',
			gamename: 'NATUTO',
			gameprice: '100',
			local: 'www.bilibili.com'
		}]
	}
})  

/*游戏列表界面*/
new Vue({
	el: '#gameList',
	data: {
		total: 2,
		navUrl1: 'www.baidu.com',
		navUrl2: 'www.bilibili.com',
		navUrl3: 'www.douyu.com',
		navUrl4: 'www.4399.com',
		pageUrl1: 'www.baidu.com',
		gameType: 'www.baidu.com',
		specifiedPage: 3
	},
	methods: {
		gameDetails: function(event) {
			console.log(event)
		},
		skip: function(event) {
			alert(this.specifiedPage)
		}
	}
})