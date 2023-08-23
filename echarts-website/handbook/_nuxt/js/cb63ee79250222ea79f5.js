(window.webpackJsonp=window.webpackJsonp||[]).push([[120],{418:function(n,e,t){"use strict";t.r(e),e.default="# 阶梯线图\n\n阶梯线图又称方波图，它使用水平和垂直的线来连接两个数据点，而普通折线图则直接将两个点连接起来。阶梯线图能够很好地表达数据的突变。\n\n在 ECharts 中，系列的 `step` 属性用来表征阶梯线图的连接类型，它共有三种取值：`'start'`、`'middle'` 和 `'end'`，分别表示在当前点，当前点与下个点的中间点，下个点拐弯。\n\n```js live\noption = {\n  xAxis: {\n    type: 'category',\n    data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']\n  },\n  yAxis: {\n    type: 'value'\n  },\n  series: [\n    {\n      name: 'Step Start',\n      type: 'line',\n      step: 'start',\n      data: [120, 132, 101, 134, 90, 230, 210]\n    },\n    {\n      name: 'Step Middle',\n      type: 'line',\n      step: 'middle',\n      data: [220, 282, 201, 234, 290, 430, 410]\n    },\n    {\n      name: 'Step End',\n      type: 'line',\n      step: 'end',\n      data: [450, 432, 401, 454, 590, 530, 510]\n    }\n  ]\n};\n```\n\n> 请注意这个例子中不同的 `step` 取值对应的数据点和连线的区别。\n"}}]);
