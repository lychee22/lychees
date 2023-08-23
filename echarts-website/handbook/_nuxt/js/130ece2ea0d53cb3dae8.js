(window.webpackJsonp=window.webpackJsonp||[]).push([[111],{409:function(n,t,e){"use strict";e.r(t),t.default="# 动态排序柱状图\n\n## 基本设置\n\n动态排序柱状图是一种展示随时间变化的数据排名变化的图表，从 ECharts 5 开始内置支持。\n\n> 动态排序柱状图通常是横向的柱条，如果想要采用纵向的柱条，只要把本教程中的 X 轴和 Y 轴相反设置即可。\n\n1. 柱状图系列的 `realtimeSort` 设为 `true`，表示开启该系列的动态排序效果\n2. `yAxis.inverse` 设为 `true`，表示 Y 轴从下往上是从小到大的排列\n3. `yAxis.animationDuration` 建议设为 `300`，表示第一次柱条排序动画的时长\n4. `yAxis.animationDurationUpdate` 建议设为 `300`，表示第一次后柱条排序动画的时长\n5. 如果想只显示前 _n_ 名，将 `yAxis.max` 设为 _n - 1_，否则显示所有柱条\n6. `xAxis.max` 建议设为 `'dataMax'` 表示用数据的最大值作为 X 轴最大值，视觉效果更好\n7. 如果想要实时改变标签，需要将 `series.label.valueAnimation` 设为 `true`\n8. `animationDuration` 设为 `0`，表示第一份数据不需要从 `0` 开始动画（如果希望从 `0` 开始则设为和 `animationDurationUpdate` 相同的值）\n9. `animationDurationUpdate` 建议设为 `3000` 表示每次更新动画时长，这一数值应与调用 `setOption` 改变数据的频率相同\n10. 以 `animationDurationUpdate` 的频率调用 `setInterval`，更新数据值，显示下一个时间点对应的柱条排序\n\n## 示例\n\n完整的例子如下：\n\n```js live\nvar data = [];\nfor (let i = 0; i < 5; ++i) {\n  data.push(Math.round(Math.random() * 200));\n}\n\noption = {\n  xAxis: {\n    max: 'dataMax'\n  },\n  yAxis: {\n    type: 'category',\n    data: ['A', 'B', 'C', 'D', 'E'],\n    inverse: true,\n    animationDuration: 300,\n    animationDurationUpdate: 300,\n    max: 2 // only the largest 3 bars will be displayed\n  },\n  series: [\n    {\n      realtimeSort: true,\n      name: 'X',\n      type: 'bar',\n      data: data,\n      label: {\n        show: true,\n        position: 'right',\n        valueAnimation: true\n      }\n    }\n  ],\n  legend: {\n    show: true\n  },\n  animationDuration: 3000,\n  animationDurationUpdate: 3000,\n  animationEasing: 'linear',\n  animationEasingUpdate: 'linear'\n};\n\nfunction update() {\n  var data = option.series[0].data;\n  for (var i = 0; i < data.length; ++i) {\n    if (Math.random() > 0.9) {\n      data[i] += Math.round(Math.random() * 2000);\n    } else {\n      data[i] += Math.round(Math.random() * 200);\n    }\n  }\n  myChart.setOption(option);\n}\n\nsetInterval(function() {\n  update();\n}, 3000);\n```\n"}}]);
