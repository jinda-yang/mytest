<%--
  Created by IntelliJ IDEA.
  User: Jinda
  Date: 2024-08-20
  Time: 16:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>echarts</title>
</head>
<body>
    <div id="d1" style="width: 30%;height: 500px;"></div>
    <div id="d2" style="width: 30%;height: 500px;"></div>
    <div id="d3" style="width: 30%;height: 500px;"></div>
</body>
<script src="../js/jquery-3.7.1.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/echarts/4.0.0/echarts.js"></script>
<script>
    var chartDom1 = document.getElementById("d1");
    var myChart1 = echarts.init(chartDom1);
    var list = [];
    var chartDom2 = document.getElementById("d2");
    var myChart2 = echarts.init(chartDom2);
    var chartDom3 = document.getElementById("d3");
    var myChart3 = echarts.init(chartDom3);
    var names = [];
    var nums = [];
    $.get("${pageContext.request.contextPath}/echarts",function (data) {
        var numList = eval("("+data+")");
        for (var i = 0; i < numList.length; i++) {
            list.push({value: numList[i].num, name: numList[i].name});
            names.push(numList[i].name);
            nums.push(numList[i].num);
        }
        // console.log(names)
        // console.log(nums)
        var option1 = {
            title: {
                text: 'Referer of a Website',
                subtext: 'Fake Data',
                left: 'center'
            },
            tooltip: {
                trigger: 'item'
            },
            legend: {
                orient: 'vertical',
                left: 'left'
            },
            series: [
                {
                    name: 'Access From',
                    type: 'pie',
                    radius: '50%',
                    data: list,
                    emphasis: {
                        itemStyle: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        };
        myChart1.setOption(option1);

        var option2 = {
            xAxis: {
                type: 'category',
                data: names
            },
            yAxis: {
                type: 'value'
            },
            series: [
                {
                    data: nums,
                    type: 'bar'
                }
            ]
        };
        myChart2.setOption(option2);

        var option3 = {
            xAxis: {
                type: 'category',
                data: names
            },
            yAxis: {
                type: 'value'
            },
            series: [
                {
                    data: nums,
                    type: 'line'
                }
            ]
        };
        myChart3.setOption(option3);
    });
</script>
</html>
