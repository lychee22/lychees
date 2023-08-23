(window.webpackJsonp=window.webpackJsonp||[]).push([[112],{410:function(n,e,r){"use strict";r.r(e),e.default="# 基本柱状图\n\n柱状图（或称条形图）是一种通过柱形的长度来表现数据大小的一种常用图表类型。\n\n设置柱状图的方式，是将 `series` 的 `type` 设为 `'bar'`。\n\n[[配置项手册]](${optionPath}series-bar)\n\n## 最简单的柱状图\n\n最简单的柱状图可以这样设置：\n\n```js live\noption = {\n  xAxis: {\n    data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']\n  },\n  yAxis: {},\n  series: [\n    {\n      type: 'bar',\n      data: [23, 24, 18, 25, 27, 28, 25]\n    }\n  ]\n};\n```\n\n在这个例子中，横坐标是类目型的，因此需要在 `xAxis` 中指定对应的值；而纵坐标是数值型的，可以根据 `series` 中的 `data`，自动生成对应的坐标范围。\n\n## 多系列的柱状图\n\n我们可以用一个系列表示一组相关的数据，如果需要实现多系列的柱状图，只需要在 `series` 多添加一项就可以了——\n\n```js live\noption = {\n  xAxis: {\n    data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']\n  },\n  yAxis: {},\n  series: [\n    {\n      type: 'bar',\n      data: [23, 24, 18, 25, 27, 28, 25]\n    },\n    {\n      type: 'bar',\n      data: [26, 24, 18, 22, 23, 20, 27]\n    }\n  ]\n};\n```\n\n## 柱状图样式设置\n\n### 柱条样式\n\n柱条的样式可以通过 [`series.itemStyle`](${optionPath}series-bar.itemStyle) 设置，包括：\n\n- 柱条的颜色（`color`）；\n- 柱条的描边颜色（`borderColor`）、宽度（`borderWidth`）、样式（`borderType`）；\n- 柱条圆角的半径（`barBorderRadius`）；\n- 柱条透明度（`opacity`）；\n- 阴影（`shadowBlur`、`shadowColor`、`shadowOffsetX`、`shadowOffsetY`）。\n\n```js live\noption = {\n  xAxis: {\n    data: ['A', 'B', 'C', 'D', 'E']\n  },\n  yAxis: {},\n  series: [\n    {\n      type: 'bar',\n      data: [\n        10,\n        22,\n        28,\n        {\n          value: 43,\n          // 设置单个柱子的样式\n          itemStyle: {\n            color: '#91cc75',\n            shadowColor: '#91cc75',\n            borderType: 'dashed',\n            opacity: 0.5\n          }\n        },\n        49\n      ],\n      itemStyle: {\n        barBorderRadius: 5,\n        borderWidth: 1,\n        borderType: 'solid',\n        borderColor: '#73c0de',\n        shadowColor: '#5470c6',\n        shadowBlur: 3\n      }\n    }\n  ]\n};\n```\n\n在这个例子中，我们通过设置柱状图对应 `series` 的`itemStyle`，设置了柱条的样式。完整的配置项及其用法请参见配置项手册 [`series.itemStyle`](${optionPath}series-bar.itemStyle)。\n\n### 柱条宽度和高度\n\n柱条宽度可以通过 [`barWidth`](${optionPath}#series-bar.barWidth) 设置。比如在下面的例子中，将 `barWidth` 设为 `'20%'`，表示每个柱条的宽度就是类目宽度的 20%。由于这个例子中，每个系列有 5 个数据，20% 的类目宽度也就是整个 x 轴宽度的 4%。\n\n```js live\noption = {\n  xAxis: {\n    data: ['A', 'B', 'C', 'D', 'E']\n  },\n  yAxis: {},\n  series: [\n    {\n      type: 'bar',\n      data: [10, 22, 28, 43, 49],\n      barWidth: '20%'\n    }\n  ]\n};\n```\n\n另外，还可以设置 [`barMaxWidth`](${optionPath}series-bar.barMaxWidth) 限制柱条的最大宽度。对于一些特别小的数据，我们也可以为柱条指定最小高度 [`barMinHeight`](${optionPath}series-bar.barMinHeight)，当数据对应的柱条高度小于该值时，柱条高度将采用这个最小高度。\n\n### 柱条间距\n\n柱条间距分为两种，一种是不同系列在同一类目下的距离 [`barGap`](${optionPath}series-bar.barGap)，另一种是类目与类目的距离 [`barCategoryGap`](${optionPath}series-bar.barCategoryGap)。\n\n```js live\noption = {\n  xAxis: {\n    data: ['A', 'B', 'C', 'D', 'E']\n  },\n  yAxis: {},\n  series: [\n    {\n      type: 'bar',\n      data: [23, 24, 18, 25, 18],\n      barGap: '20%',\n      barCategoryGap: '40%'\n    },\n    {\n      type: 'bar',\n      data: [12, 14, 9, 9, 11]\n    }\n  ]\n};\n```\n\n在这个例子中，`barGap` 被设为 `'20%'`，这意味着每个类目（比如 `A`）下的两个柱子之间的距离，相对于柱条宽度的百分比。而 `barCategoryGap` 是 `'40%'`，意味着柱条每侧空余的距离，相对于柱条宽度的百分比。\n\n通常而言，设置 `barGap` 及 `barCategoryGap` 后，就不需要设置 `barWidth` 了，这时候的宽度会自动调整。如果有需要的话，可以设置 `barMaxWidth` 作为柱条宽度的上限，当图表宽度很大的时候，柱条宽度也不会太宽。\n\n> 在同一坐标系上，此属性会被多个柱状图系列共享。此属性应设置于此坐标系中最后一个柱状图系列上才会生效，并且是对此坐标系中所有柱状图系列生效。\n\n### 为柱条添加背景色\n\n有时，我们希望能够为柱条添加背景色。从 ECharts 4.7.0 版本开始，这一功能可以简单地用 [`showBackground`](${optionPath}series-bar.showBackground) 开启，并且可以通过 [`backgroundStyle`](${optionPath}series-bar.backgroundStyle) 配置。\n\n```js live\noption = {\n  xAxis: {\n    type: 'category',\n    data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']\n  },\n  yAxis: {\n    type: 'value'\n  },\n  series: [\n    {\n      data: [120, 200, 150, 80, 70, 110, 130],\n      type: 'bar',\n      showBackground: true,\n      backgroundStyle: {\n        color: 'rgba(220, 220, 220, 0.8)'\n      }\n    }\n  ]\n};\n```\n"}}]);
