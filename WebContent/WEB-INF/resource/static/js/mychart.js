require.config({
    paths:{ 
        'echarts':'/resource/static/js/echarts.min.js',
        'echarts':'/resource/static/js/macarons.js',

    }
});

function initSexChart(echarts,theme){
    var myChart = echarts.init(document.getElementById('sexRate'),theme);

    // 指定图表的配置项和数据
    var option = {
            title : {
                text: '男女比例',
                //subtext: '纯属虚构',
                x:'center'
            },
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                data: ['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']
            },
    		toolbox: {
            show : true,
            feature : {
                dataView : {show: true, readOnly: false},
                magicType : {
                    show: true, 
                    type: ['pie', 'funnel'],
                    option: {
                        funnel: {
                            x: '25%',
                            width: '50%',
                            funnelAlign: 'center',
                            max: 1548
                        }
                    }
                },
                saveAsImage : {show: true}
            }
        },
        calculable : true,
            series : 
                [
                    {
                        name:'性别数量',
    					type:'pie',
    					radius : ['50%', '70%'],
                itemStyle : {
                    normal : {
                        label : {
                            show : false
                        },
                        labelLine : {
                            show : false
                        }
                    },
                    emphasis : {
                        label : {
                            show : true,
                            position : 'center',
                            textStyle : {
                                fontSize : '30',
                                fontWeight : 'bold'
                            }
                        }
                    }
                },
                        data:[
                            {value:335, name:'直接访问'},
                            {value:310, name:'邮件营销'},
                            {value:234, name:'联盟广告'},
                            {value:135, name:'视频广告'},
                            {value:1548, name:'搜索引擎'}
                        ],
                        itemStyle: {
                            emphasis: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                ],
        };
    
    // 使用刚指定的配置项和数据显示图表。
    var d1 = [];
    var d2 = [];
    for(var i = 0;i < sexData.length;i++){
    	d = sexData[i];
    	d1.push(d.sex);
    	var o = {name:d.sex,value:d.sexSum};
    	d2.push(o);
    }
    option.legend.data = d1;
    option.series[0].data = d2;
    myChart.setOption(option);
	
}
var lineOption = {
        title: {
            text: 'ECharts 入门示例'
        },
        tooltip: {},
        legend: {
            data:['销量']
        },
        xAxis: {
            data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"],
            axisLabel:{interval:0,rotate:-90}
        },
        yAxis: {},
        series: [{
            name: '销量',
            type: 'bar',
            data: [5, 20, 36, 10, 10, 20]
        }]
};

function initPupolarUser(echarts,theme){
	var myChart = echarts.init(document.getElementById('popularUser'),theme);

	var option = $.extend({},lineOption);
	option.title.text = "热门博主";
	option.legend.data = ['点赞总数'];
	var xData = [],SData = [];
    for(var i = 0;i < userData.length;i++){
    	d = userData[i];
    	//console.log(d);
    	xData.push(d.name);
    	//var o = {name:d.name,value:d.sexSum};
    	SData.push(d.likeSum);
    }
    //console.log(xData);
    //console.log(SData);
    option.series[0].name = '点赞数';
    option.series[0].data = SData;
    option.xAxis.data = xData;
    myChart.setOption(option);
}

function initPupolarArticle(echarts,theme){
	var myChart = echarts.init(document.getElementById('popularArticle'),theme);

	var option = $.extend({},lineOption);
	option.title.text = "热门文章";
	option.legend.data = ['点赞总数'];
	var xData = [],SData = [];
    for(var i = 0;i < articleData.length;i++){
    	d = articleData[i];
    	console.log(d);
    	xData.push(d.title);
    	//var o = {name:d.name,value:d.sexSum};
    	SData.push(d.likeCount);
    }
    console.log(xData);
    console.log(SData);
    option.series[0].name = '点赞数';
    option.series[0].data = SData;
    option.xAxis.data = xData;
    myChart.setOption(option);
}


require(['require','echarts.min','macarons'],function(require,echarts,macarons){
    console.log(macarons);
    initSexChart(echarts,macarons);
    initPupolarUser(echarts,macarons);
    initPupolarArticle(echarts,macarons);
});