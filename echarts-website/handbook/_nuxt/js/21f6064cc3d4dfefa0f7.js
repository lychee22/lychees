(window.webpackJsonp=window.webpackJsonp||[]).push([[77],{375:function(e,n,t){"use strict";t.r(n),n.default="# Apache ECharts 5 升级指南\n\n本指南面向那些希望将 echarts 4.x（以下简称 `v4`）升级到 echarts 5.x（以下简称 `v5`）的用户。大家可以在 [ECharts 5 新特性](${lang}/basics/release-note/v5-feature) 中了解这次`v5`带来了哪些值得升级的新特性。在绝大多数情况下，开发者用不着为这个升级做什么额外的事，因为 echarts 一直尽可能地保持 API 的稳定和向后兼容。但是，`v5` 仍然带来了一些非兼容改动，需要特别关注。此外，在一些情况下，`v5` 提供了更好的 API 用来取代之前的 API，这些被取代的 API 将不再被推荐使用（当然，我们尽量兼容了这些改动）。我们会在这篇文档里尽量详尽得解释这些改动。\n\n## 非兼容性改变\n\n### 默认主题（theme）\n\n首先是默认主题的改动，`v5` 在配色等主题设计上做了很多的优化来达到更好的视觉效果。如果大家依旧想保留旧版本的颜色，可以手动声明颜色，如下：\n\n```js\nchart.setOption({\n  color: [\n    '#c23531',\n    '#2f4554',\n    '#61a0a8',\n    '#d48265',\n    '#91c7ae',\n    '#749f83',\n    '#ca8622',\n    '#bda29a',\n    '#6e7074',\n    '#546570',\n    '#c4ccd3'\n  ]\n  // ...\n});\n```\n\n或者，做一个简单的 `v4` 主题：\n\n```js\nvar themeEC4 = {\n  color: [\n    '#c23531',\n    '#2f4554',\n    '#61a0a8',\n    '#d48265',\n    '#91c7ae',\n    '#749f83',\n    '#ca8622',\n    '#bda29a',\n    '#6e7074',\n    '#546570',\n    '#c4ccd3'\n  ]\n};\nvar chart = echarts.init(dom, themeEC4);\nchart.setOption(/* ... */);\n```\n\n### 引用 ECharts\n\n#### 去除 default exports 的支持\n\n如果使用者在 `v4` 中这样引用了 echarts：\n\n```js\nimport echarts from 'echarts';\n// 或者按需引入\nimport echarts from 'echarts/lib/echarts';\n```\n\n这两种方式，`v5` 中不再支持了。\n\n使用者需要如下更改代码解决这个问题：\n\n```js\nimport * as echarts from 'echarts';\n// 按需引入\nimport * as echarts from 'echarts/lib/echarts';\n```\n\n#### 按需引入\n\n在 5.0.1 中，我们引入了新的[按需引入接口](${lang}/basics/import)\n\n```js\nimport * as echarts from 'echarts/core';\nimport { BarChart } from 'echarts/charts';\nimport { GridComponent } from 'echarts/components';\n// 注意，新的接口中默认不再包含 Canvas 渲染器，需要显示引入，如果需要使用 SVG 渲染模式则使用 SVGRenderer\nimport { CanvasRenderer } from 'echarts/renderers';\n\necharts.use([BarChart, GridComponent, CanvasRenderer]);\n```\n\n如果之前是使用`import 'echarts/lib/chart/bar'`引入，新的接口对应的是`import {BarChart} from 'echarts/charts'`;\n\n为了方便大家了解自己的配置项需要引入哪些模块，我们新的示例编辑页面添加了生成按需引入代码的功能，大家可以在示例编辑页的`完整代码`标签下选中按需引入后查看需要引入的模块以及相关代码。\n\n在大部分情况下，我们都推荐大家尽可能用这套新的按需引入接口，它可以最大程度的利用打包工具 tree-shaking 的能力，并且可以有效解决命名空间冲突的问题而且防止了内部结构的暴露。如果你依旧在使用 CommonJS 的模块写法，之前的方式我们也依旧是支持的：\n\n```js\nconst echarts = require('echarts/lib/echarts');\nrequire('echarts/lib/chart/bar');\nrequire('echarts/lib/component/grid');\n```\n\n其次，因为我们的源代码已使用 TypeScript 重写，`v5` 将不再支持从 `echarts/src` 引用文件，需要改为从`echarts/lib`引入。\n\n#### 依赖调整\n\n> 注意：该部分只针对为了保证较小的打包体积而是用按需引入接口的开发者，如果是全量引入的不需要关注\n\n为了保证 tree-shaking 后的体积足够小，我们去除了一些之前会默认被打包进来的依赖。比如前面提到的在使用新的按需引入接口的时候，`CanvasRenderer`将不再被默认引入，这样可以保证只需要使用 SVG 渲染模式的时候不会把不需要的 Canvas 渲染代码也一起打包进来，除此之外，还有下面这些依赖的改动：\n\n- 在使用折线图，柱状图中不再默认引入直角坐标系组件，因此之前使用下面的引入方式\n\n```js\nconst echarts = require('echarts/lib/echarts');\nrequire('echarts/lib/chart/bar');\nrequire('echarts/lib/chart/line');\n```\n\n需要再单独引入`grid`组件\n\n```js\nrequire('echarts/lib/component/grid');\n```\n\n参考 issue：[#14080](https://github.com/apache/echarts/issues/14080), [#13764](https://github.com/apache/echarts/issues/13764)\n\n- 默认不再引入`aria`组件，如果需要的话可以手动引入。\n\n```js\nimport { AriaComponent } from 'echarts/components';\necharts.use(AriaComponent);\n```\n\n或者：\n\n```js\nrequire('echarts/lib/component/aria');\n```\n\n### 去除内置的 geoJSON\n\n`v5` 移除了内置的 geoJSON（原先在 `echarts/map` 文件夹下）。这些 geoJSON 文件本就一直来源于第三方。如果使用者仍然需要他们，可以去从老版本中得到，或者自己寻找更合适的数据然后通过 registerMap 接口注册到 ECharts 中。\n\n### 浏览器兼容性\n\n`v5` 不再支持 IE8 浏览器。我们不再继续维护和升级之前的 [VML 渲染器](https://github.com/ecomfe/zrender/tree/4.3.2/src/vml) 来着实现 IE8 的兼容。如果使用者确实有很强的需求，那么欢迎提 pull request 来升级 VML 渲染器，或者单独维护一个第三方 VML 渲染器，我们从 `v5.0.1` 开始支持注册独立的渲染器了。\n\n### 配置项调整\n\n#### 视觉样式设置的优先级改变\n\n`v5` 对调了 [visualMap 组件](${optionPath}visualMap) 和 [itemStyle](${optionPath}series-scatter.itemStyle) | [lineStyle](${optionPath}series-scatter.lineStyle) | [areaStyle](${optionPath}series-scatter.areaStyle) 的视觉样式优先级。\n\n具体来说，`v4` 中，[visualMap 组件](${optionPath}visualMap) 中生成的视觉样式（如，颜色、图形类型、图形尺寸等）的优先级，比开发者在 [itemStyle](${optionPath}series-scatter.itemStyle) | [lineStyle](${optionPath}series-scatter.lineStyle) | [areaStyle](${optionPath}series-scatter.areaStyle) 中设置的样式的优先级高，也就是说如果他们同时设置的话，前者会生效而后者不会生效。这带来了些麻烦：假如使用者在使用 [visualMap 组件](${optionPath}visualMap) 时，又想针对某个数据项对应的图形，设置 [itemStyle](${optionPath}series-scatter.itemStyle) 样式，则做不到。`v5` 中于是提高了 [itemStyle](${optionPath}series-scatter.itemStyle) | [lineStyle](${optionPath}series-scatter.lineStyle) | [areaStyle](${optionPath}series-scatter.areaStyle) 的优先级，使他们能生效。\n\n在绝大多处情况下，这个变化并不会带来什么影响。但是为保险起见，使用者在升级 `v4` 到 `v5` 时，还是可以检查下，是否有同时使用 [visualMap](${optionPath}visualMap) 和 [itemStyle](${optionPath}series-scatter.itemStyle) | [lineStyle](${optionPath}series-scatter.lineStyle) | [areaStyle](${optionPath}series-scatter.areaStyle) 的情况。\n\n#### 富文本的 `padding`\n\n`v5` 调整了 [rich.?.padding](${optionPath}series-scatter.label.rich.<style_name>.padding) 的格式使其更符合 CSS 的规范。`v4` 里，例如 `rich.?.padding: [11, 22, 33, 44]` 表示 `padding-top` 是 `33` 且 `padding-bottom` 是 `11`。在 `v5` 中调整了上下的位置，`rich.?.padding: [11, 22, 33, 44]` 表示 `padding-top` 是 `11` 且 `padding-bottom` 是 `33`。\n\n如果使用者有在使用 [rich.?.padding](${optionPath}series-scatter.label.rich.<style_name>.padding)，需要注意调整下这个顺序。\n\n## 扩展的兼容\n\n如果想要升级到 `v5` ，下面这些扩展需要升级到最新的版本实现兼容。\n\n- [echarts-gl](https://github.com/ecomfe/echarts-gl)\n- [echarts-wordcloud](https://github.com/ecomfe/echarts-wordcloud)\n- [echarts-liquidfill](https://github.com/ecomfe/echarts-liquidfill)\n\n## 不再推荐使用的 API\n\n一些 API（包括接口调用，事件监听和配置项）在 `v5` 中不再推荐使用。当然，使用者仍然可以用他们，只是会在 dev 模式下，在 console 中打印一些 warning，并不会影响功能。但是从长远维护考虑，我们还是推荐升级成新的 API。\n\n下面是不再推荐使用的 API 以及推荐的新 API：\n\n- 图形元素 transform 相关的属性被改变了：\n  - 变更点：\n    - `position: [number, number]` 改为 `x: number` / `y: number`。\n    - `scale: [number, number]` 改为 `scaleX: number` / `scaleY: number`。\n    - `origin: [number, number]` 改为 `originX: number` / `originY: number`。\n  - `position`、`scale` 和 `origin` 仍然支持，但已不推荐使用。\n  - 它影响到这些地方：\n    - 在`graphic`组件中：每个元素的声明。\n    - 在 `custom series` 中：`renderItem` 返回的每个元素的声明。\n    - 直接使用 zrender 图形元素时。\n- Text 相关的属性被改变：\n  - 变更点：\n    - 图形元素附带的文本的声明方式被改变：\n      - 除了 `Text` 元素之外，其他元素中的属性 `style.text` 都不推荐使用了。取而代之的是新属性 `textContent` 和 `textConfig`，他们能带来更丰富的功能。\n      - 其中，下面左边部分的这些属性已不推荐使用或废弃。请使用下面的右边部分的属性：\n        - textPosition => textConfig.position\n        - textOffset => textConfig.offset\n        - textRotation => textConfig.rotation\n        - textDistance => textConfig.distance\n    - 下面左边部分的属性在 `style` 和 `style.rich.?` 中已不推荐使用或废弃。请使用下面右边的属性：\n      - textFill => fill\n      - textStroke => stroke\n      - textFont => font\n      - textStrokeWidth => lineWidth\n      - textAlign => align\n      - textVerticalAlign => verticalAlign\n      - textLineHeight => lineHeight\n      - textWidth => width\n      - textHeight => hight\n      - textBackgroundColor => backgroundColor\n      - textPadding => padding\n      - textBorderColor => borderColor\n      - textBorderWidth => borderWidth\n      - textBorderRadius => borderRadius\n      - textBoxShadowColor => shadowColor\n      - textBoxShadowBlur => shadowBlur\n      - textBoxShadowOffsetX => shadowOffsetX\n      - textBoxShadowOffsetY => shadowOffsetY\n    - 注：这些属性并没有变化：\n      - textShadowColor\n      - textShadowBlur\n      - textShadowOffsetX\n      - textShadowOffsetY\n  - 它影响到这些地方：\n    - 在 `graphic` 组件中：每个元素的声明。（原来的写法仍兼容，但在一些很复杂的情况下，可能效果不完全一致。）\n    - 在自定义系列（`custom series`）中：`renderItem` 返回中的每个元素的声明。（原来的写法仍兼容，但在一些很复杂的情况下，可能效果不完全一致。）\n    - 直接使用 zrender API 创建图形元素。（不再兼容，原写法被废弃。）\n- 图表实例上的 API：\n  - `chart.one(...)` 已不推荐使用。\n- `label`。\n  - 属性 `color`、`textBorderColor`、`backgroundColor`、`borderColor` 中，值 `auto` 已不推荐使用，而推荐使用 `'inherit'` 代替。\n- `hoverAnimation`:\n  - 选项 `series.hoverAnimation` 已不推荐使用，使用 `series.emphasis.scale` 代替之。\n- 折线图（`line series`）：\n  - 选项 `series.clipOverflow` 已不推荐使用，使用 `series.clip` 代替之。\n- 自定义系列（`custom series`）。\n  - 在 `renderItem` 中，`api.style(...)` 和 `api.styleEmphasis(...)` 已不推荐使用。因为这两个接口其实并不真正必要，也很难保证向后兼容。用户可以通过 `api.visual(...)` 获取系统自动分配的视觉信息。\n- 旭日图（`sunburst`）：\n  - 动作类型 `highlight` 已被弃用，请使用 `sunburstHighlight` 代替。\n  - 动作类型 `downplay` 已被弃用，请使用 `sunburstUnhighlight` 代替。\n  - 选项 `series.downplay` 已被弃用，请使用 `series.blur` 代替。\n  - 选项 `series.highlightPolicy` 已不适用，请使用 `series.emphasis.focus` 代替。\n- 饼图（`pie`）：\n  - 下面左边部分的 action 名已经不推荐使用。请使用右边的 action 名。\n    - `pieToggleSelect` => `toggleSelect`。\n    - `pieSelect` => `select`。\n    - `pieUnSelect` => `unselect`。\n  - 下面左边部分的事件名已经不推荐使用。请使用右边的事件名。\n    - `pieselectchanged` => `selectchanged`。\n    - `pieselected` => `selected`。\n    - `pieunselected` => `unselected`。\n  - 选项 `series.label.margin` 已经不推荐使用。使用 `series.label.edgeDistance` 代替。\n  - 选项 `series.clockWise` 已经不推荐使用。使用 `series.clockwise` 代替。\n  - 选项 `series.hoverOffset` 已经不推荐使用。使用 `series.emphasis.scaleSize` 代替。\n- 地图（`map series`）：\n  - 下文左边部分的 action 名已经不推荐使用。请使用右边的 action 名。\n    - `mapToggleSelect` => `toggleSelect`。\n    - `mapSelect` => `select`。\n    - `mapUnSelect` => `unselect`。\n  - 下面左边部分的事件名已经不推荐使用。请使用右边的事件名。\n    - `mapselectchanged` => `selectchanged`。\n    - `mapselected` => `selected`。\n    - `mapunselected` => `unselected`。\n  - 选项 `series.mapType` 已经不推荐使用。使用 `series.map` 代替。\n  - 选项 `series.mapLocation` 已经不推荐使用。\n- 关系图（`graph series`）：\n  - 选项 `series.focusNodeAdjacency` 已经不推荐使用。使用 `series.emphasis: { focus: 'adjacency'}` 代替。\n- 仪表盘（`gauge series`）：\n  - 选项 `series.clockWise` 已经不推荐使用。使用 `series.clockwise` 代替。\n  - 选项 `series.hoverOffset` 已经不推荐使用。使用 `series.emphasis.scaleSize` 代替。\n- `dataZoom` 组件：\n  - 选项 `dataZoom.handleIcon` 如果使用 `SVGPath`，需要前缀 `path://`。\n- 雷达图（`radar`）：\n  - 选项 `radar.name` 已经不推荐使用。使用 `radar.axisName` 代替。\n  - 选项 `radar.nameGap` 已经不推荐使用。使用 `radar.axisNameGap` 代替。\n- Parse and format：\n  - `echarts.format.formatTime` 已经不推荐使用。使用 `echarts.time.format` 代替。\n  - `echarts.number.parseDate` 已经不推荐使用。使用 `echarts.time.parse` 代替。\n  - `echarts.format.getTextRect` 已经不推荐使用。\n"}}]);
