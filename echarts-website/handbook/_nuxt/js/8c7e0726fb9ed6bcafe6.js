(window.webpackJsonp=window.webpackJsonp||[]).push([[57],{355:function(e,n,t){"use strict";t.r(n),n.default="# Step Line Chart\n\nThe normal line chart connects two points by straight line directly, while the step line chart, also known as square wave chart, uses only horizontal and vertical lines to connect the nearby items together. Compared with the normal line chart, the step line chart significantly shows the sudden changes of analyzed data.\n\nIn ECharts, `step` is used to characterize the connection type of the step line chart. It has three available values: `'start'`, `'end'`, and `'middle'`. For item A and B, these values corresponded that the corner occurred at A, B, and mid-point between A and B.\n\n```js live\noption = {\n  legend: {},\n  xAxis: {\n    type: 'category',\n    data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']\n  },\n  yAxis: {\n    type: 'value'\n  },\n  series: [\n    {\n      name: 'Step Start',\n      type: 'line',\n      step: 'start',\n      data: [120, 132, 101, 134, 90, 230, 210]\n    },\n    {\n      name: 'Step Middle',\n      type: 'line',\n      step: 'middle',\n      data: [220, 282, 201, 234, 290, 430, 410]\n    },\n    {\n      name: 'Step End',\n      type: 'line',\n      step: 'end',\n      data: [450, 432, 401, 454, 590, 530, 510]\n    }\n  ]\n};\n```\n\n> Please focus on the difference of line between three separate types.\n"}}]);
