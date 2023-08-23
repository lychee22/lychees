package org.example;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Hello world!
 */
public class App {
    static HashMap<String,String> reps=new HashMap<>();
    static HashMap<String,String> reps2=new HashMap<>();
    static HashMap<String,String> reps3=new HashMap<>();
    static String project="echarts-website";
    static String base2="/"+project;
    static String base=base2+"/";
    static List<String> lastrep=new ArrayList<>();
    static{
        lastrep.add("https://registry.npmmirror.com"+","+base+"cdnThirdParty/"+"registry.npmmirror.com");
        lastrep.add("https://fastly.jsdelivr.net/npm"+","+base+"cdnThirdParty/"+"fastly.jsdelivr.net");
        lastrep.add("http://cdn.jsdelivr.net/npm"+","+base+"cdnThirdParty/"+"cdn.jsdelivr.net");


        lastrep.add("//registry.npmmirror.com"+","+base+"cdnThirdParty/"+"registry.npmmirror.com");
        lastrep.add("//fastly.jsdelivr.net/npm"+","+base+"cdnThirdParty/"+"fastly.jsdelivr.net");
        lastrep.add("//cdn.jsdelivr.net/npm"+","+base+"cdnThirdParty/"+"cdn.jsdelivr.net");

/*        lastrep.add(""+","+base2+"cdnThirdParty/"+"");
        lastrep.add(""+","+base2+"cdnThirdParty/"+"");
        lastrep.add(""+","+base2+"cdnThirdParty/"+"");
        lastrep.add(""+","+base2+"cdnThirdParty/"+"");
        lastrep.add(""+","+base2+"cdnThirdParty/"+"");
        lastrep.add(""+","+base2+"cdnThirdParty/"+"");
        lastrep.add(""+","+base2+"cdnThirdParty/"+"");
        lastrep.add(""+","+base2+"cdnThirdParty/"+"");
        lastrep.add(""+","+base2+"cdnThirdParty/"+"");
        lastrep.add(""+","+base2+"cdnThirdParty/"+"");*/



        //首页URL替换
        reps2.put("https://echarts.apache.org",base2);
        reps2.put("http://echarts.baidu.com",base2);
        reps2.put("https://ecomfe.github.io/echarts-doc",base2);
        reps2.put("https://cdn.jsdelivr.net/gh/apache/echarts-website@asf-site",base2);
        reps2.put("https://cdn.jsdelivr.net/gh/apache/echarts-website@asf-site",base2);


        reps.putAll(reps2);

        reps3.put("\"/handbook","\"/"+project+"/handbook");
        reps3.put("\"/examples","\"/"+project+"/examples");
        reps3.put("\"/layouts","\"/"+project+"/layouts");
        reps3.put("\"/v4","\"/"+project+"/v4");
        reps3.put("\"/zh","\"/"+project+"/zh");
        reps3.put("\"/en","\"/"+project+"/en");
        reps3.put("\"/components","\"/"+project+"/components");
        reps3.put("id=\"apache-banner\"","id=\"apache-banner2\" style=\"display:none;\"");


        //第三方文件
        reps.put("http://localhost/echarts-website/examples/${lang}/editor.html?c=",base+"cdnThirdParty/other_4editor.html");
        reps.put("https://apachecon.com/acasia2021/index.html",base+"cdnThirdParty/other_4index.html");
        reps.put("https://apachecon.com/acasia2021/sessions/1087.html",base+"cdnThirdParty/other_41087.html");
        reps.put("https://apachecon.com/acasia2021/sessions/1090.html",base+"cdnThirdParty/other_41090.html");
        reps.put("https://apachecon.com/acasia2021/sessions/1091.html",base+"cdnThirdParty/other_41091.html");
        reps.put("https://apachecon.com/acasia2021/sessions/1092.html",base+"cdnThirdParty/other_41092.html");
        reps.put("https://apachecon.com/acasia2021/sessions/1093.html",base+"cdnThirdParty/other_41093.html");
        reps.put("https://apachecon.com/acasia2021/sessions/1094.html",base+"cdnThirdParty/other_41094.html");
        reps.put("https://apachecon.com/acasia2021/tracks/datavisualization.html",base+"cdnThirdParty/other_4datavisualization.html");
        reps.put("https://apachecon.com/acasia2021/zh/sessions/1087.html",base+"cdnThirdParty/other_41087.html");
        reps.put("https://apachecon.com/acasia2021/zh/sessions/1090.html",base+"cdnThirdParty/other_41090.html");
        reps.put("https://apachecon.com/acasia2021/zh/sessions/1091.html",base+"cdnThirdParty/other_41091.html");
        reps.put("https://apachecon.com/acasia2021/zh/sessions/1092.html",base+"cdnThirdParty/other_41092.html");
        reps.put("https://apachecon.com/acasia2021/zh/sessions/1093.html",base+"cdnThirdParty/other_41093.html");
        reps.put("https://apachecon.com/acasia2021/zh/sessions/1094.html",base+"cdnThirdParty/other_41094.html");
        reps.put("https://apachecon.com/acasia2021/zh/tracks/datavisualization.html",base+"cdnThirdParty/other_4datavisualization.html");
        reps.put("https://cdn.jsdelivr.net/npm/ace-builds@1.4.12/src-min-noconflict/ace.js",base+"cdnThirdParty/other_0ace.js");
        reps.put("https://cdn.jsdelivr.net/npm/ace-builds@1.4.12/src-min-noconflict/ext-language_tools.js",base+"cdnThirdParty/other_0ext-language_tools.js");
/*        reps.put("https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css",base+"cdnThirdParty/other_1bootstrap.min.css");
        reps.put("https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js",base+"cdnThirdParty/other_0bootstrap.min.js");*/
        reps.put("https://cdn.jsdelivr.net/npm/code-prettify@0.1.0/src/lang-css.js",base+"cdnThirdParty/other_0lang-css.js");
        reps.put("https://cdn.jsdelivr.net/npm/code-prettify@0.1.0/src/prettify.min.js",base+"cdnThirdParty/other_0prettify.min.js");
        reps.put("https://cdn.jsdelivr.net/npm/codemirror@5.56.0/lib/codemirror.min.js",base+"cdnThirdParty/other_0codemirror.min.js");
        reps.put("https://cdn.jsdelivr.net/npm/codemirror@5.56.0/mode/javascript/javascript.js",base+"cdnThirdParty/other_0javascript.js");
        reps.put("https://cdn.jsdelivr.net/npm/compare-versions@3.6.0/index.min.js",base+"cdnThirdParty/other_0index.min.js");
        reps.put("https://cdn.jsdelivr.net/npm/dat.gui@0.6.5/build/dat.gui.min.js",base+"cdnThirdParty/other_0dat.gui.min.js");
        reps.put("https://cdn.jsdelivr.net/npm/echarts-gl@1/dist/echarts-gl.min.js",base+"cdnThirdParty/other_0echarts-gl.min.js");
        reps.put("https://cdn.jsdelivr.net/npm/echarts-stat@1/dist/ecStat.min.js",base+"cdnThirdParty/other_0ecStat.min.js");
        reps.put("https://cdn.jsdelivr.net/npm/echarts@4.8.0/dist/echarts.min.js",base+"cdnThirdParty/other_0echarts.min.js");
        reps.put("https://cdn.jsdelivr.net/npm/echarts@4/dist/echarts.min.js",base+"cdnThirdParty/other_0echarts.min.js");
        reps.put("https://cdn.jsdelivr.net/npm/echarts@4/dist/echarts.min.js?_v_=1611323308745",base+"cdnThirdParty/other_0echarts.min.js");
        reps.put("https://cdn.jsdelivr.net/npm/echarts@4/dist/extension/bmap.js?_v_=1611323308745",base+"cdnThirdParty/other_0bmap.js");
        reps.put("https://cdn.jsdelivr.net/npm/echarts@4/dist/extension/bmap.min.js",base+"cdnThirdParty/other_0bmap.min.js");
        reps.put("https://cdn.jsdelivr.net/npm/echarts@4/dist/extension/dataTool.js?_v_=1611323308745",base+"cdnThirdParty/other_0dataTool.js");
        reps.put("https://cdn.jsdelivr.net/npm/echarts@4/dist/extension/dataTool.min.js",base+"cdnThirdParty/other_0dataTool.min.js");
        reps.put("https://cdn.jsdelivr.net/npm/echarts@4/map/js/china.js",base+"cdnThirdParty/other_0china.js");
        reps.put("https://cdn.jsdelivr.net/npm/echarts@4/map/js/china.js?_v_=1611323308745",base+"cdnThirdParty/other_0china.js");
        reps.put("https://cdn.jsdelivr.net/npm/echarts@4/map/js/world.js",base+"cdnThirdParty/other_0world.js");
        reps.put("https://cdn.jsdelivr.net/npm/echarts@4/map/js/world.js?_v_=1611323308745",base+"cdnThirdParty/other_0world.js");
/*        reps.put("https://cdn.jsdelivr.net/npm/element-ui@2.13.2/lib/index.js",base+"cdnThirdParty/other_0index.js");
        reps.put("https://cdn.jsdelivr.net/npm/element-ui@2.13.2/lib/theme-chalk/index.css",base+"cdnThirdParty/other_1index.css");*/
        reps.put("https://cdn.jsdelivr.net/npm/jquery-lazyload@1.9.7/jquery.lazyload.min.js",base+"cdnThirdParty/other_0jquery.lazyload.min.js");
        reps.put("https://cdn.jsdelivr.net/npm/jquery@1.11.3/dist/jquery.min.js",base+"cdnThirdParty/other_0jquery.min.js");
        reps.put("https://cdn.jsdelivr.net/npm/jquery@2.2.4/dist/jquery.min.js",base+"cdnThirdParty/other_0jquery.min.js");
        reps.put("https://cdn.jsdelivr.net/npm/js-beautify@1.11.0/js/lib/beautifier.min.js",base+"cdnThirdParty/other_0beautifier.min.js");
        reps.put("https://cdn.jsdelivr.net/npm/lodash@3.10.1/index.min.js",base+"cdnThirdParty/other_0index.min.js");
        reps.put("https://cdn.jsdelivr.net/npm/pace-progressbar@1.0.2/pace.min.js",base+"cdnThirdParty/other_0pace.min.js");
        reps.put("https://cdn.jsdelivr.net/npm/perfect-scrollbar@0.6.8/dist/css/perfect-scrollbar.min.css",base+"cdnThirdParty/other_1perfect-scrollbar.min.css");
        reps.put("https://cdn.jsdelivr.net/npm/perfect-scrollbar@0.6.8/dist/js/min/perfect-scrollbar.min.js",base+"cdnThirdParty/other_0perfect-scrollbar.min.js");
        reps.put("https://cdn.jsdelivr.net/npm/vanilla-lazyload@12.0.0/dist/lazyload.min.js",base+"cdnThirdParty/other_0lazyload.min.js");
        reps.put("https://cdn.jsdelivr.net/npm/vue@2.6.11/dist/vue.min.js",base+"cdnThirdParty/other_0vue.min.js");
        reps.put("https://cdn.jsdelivr.net/npm/waypoints@4.0.0/lib/jquery.waypoints.min.js",base+"cdnThirdParty/other_0jquery.waypoints.min.js");
        reps.put("https://cran.r-project.org/web/packages/ggrepel/ vignettes/ggrepel.html",base+"cdnThirdParty/other_4ggrepel.html");
        reps.put("https://cran.r-project.org/web/packages/ggrepel/vignettes/ggrepel.html",base+"cdnThirdParty/other_4ggrepel.html");
        reps.put("https://echarts-5-live.bj.bcebos.com/echarts-5-event.html?ref=ec-event",base+"cdnThirdParty/other_4echarts-5-event.html");
        reps.put("https://ecomfe.github.io/echarts-examples/public/editor.html?c=bar-label-rotation",base+"cdnThirdParty/other_4editor.html");
        reps.put("https://ecomfe.github.io/echarts-examples/public/editor.html?c=bar-polar-real-estate",base+"cdnThirdParty/other_4editor.html");
        reps.put("https://ecomfe.github.io/echarts-examples/public/editor.html?c=bar-polar-stack",base+"cdnThirdParty/other_4editor.html");
        reps.put("https://ecomfe.github.io/echarts-examples/public/editor.html?c=bar-polar-stack-radial",base+"cdnThirdParty/other_4editor.html");
        reps.put("https://ecomfe.github.io/echarts-examples/public/editor.html?c=candlestick-large",base+"cdnThirdParty/other_4editor.html");
        reps.put("https://ecomfe.github.io/echarts-examples/public/editor.html?c=custom-gantt-flight",base+"cdnThirdParty/other_4editor.html");
        reps.put("https://ecomfe.github.io/echarts-examples/public/editor.html?c=lines-airline",base+"cdnThirdParty/other_4editor.html");
        reps.put("https://ecomfe.github.io/echarts-examples/public/editor.html?c=lines-ny",base+"cdnThirdParty/other_4editor.html");
        reps.put("https://ecomfe.github.io/echarts-examples/public/editor.html?c=linesGL-ny&amp;gl=1",base+"cdnThirdParty/other_4editor.html");
        reps.put("https://ecomfe.github.io/echarts-examples/public/editor.html?c=map-bin",base+"cdnThirdParty/other_4editor.html");
        reps.put("https://ecomfe.github.io/echarts-examples/public/editor.html?c=map-labels",base+"cdnThirdParty/other_4editor.html");
        reps.put("https://ecomfe.github.io/echarts-examples/public/editor.html?c=map-polygon",base+"cdnThirdParty/other_4editor.html");
        reps.put("https://ecomfe.github.io/echarts-examples/public/editor.html?c=pie-legend",base+"cdnThirdParty/other_4editor.html");
        reps.put("https://ecomfe.github.io/echarts-examples/public/editor.html?c=radar2",base+"cdnThirdParty/other_4editor.html");
        reps.put("https://ecomfe.github.io/echarts-examples/public/editor.html?c=sankey-energy",base+"cdnThirdParty/other_4editor.html");
        reps.put("https://ecomfe.github.io/echarts-examples/public/editor.html?c=sankey-product",base+"cdnThirdParty/other_4editor.html");
        reps.put("https://ecomfe.github.io/echarts-examples/public/editor.html?c=sankey-vertical",base+"cdnThirdParty/other_4editor.html");
        reps.put("https://ecomfe.github.io/echarts-examples/public/editor.html?c=scatter-large",base+"cdnThirdParty/other_4editor.html");
        reps.put("https://ecomfe.github.io/echarts-examples/public/editor.html?c=scatterGL-gps&amp;gl=1",base+"cdnThirdParty/other_4editor.html");
        reps.put("https://ecomfe.github.io/echarts-examples/public/editor.html?c=tree-orient-right-left",base+"cdnThirdParty/other_4editor.html");
        reps.put("https://ecomfe.github.io/echarts-examples/public/editor.html?c=tree-vertical",base+"cdnThirdParty/other_4editor.html");
        reps.put("https://ecomfe.github.io/echarts-examples/public/editor.html?c=treemap-show-parent",base+"cdnThirdParty/other_4editor.html");

        reps.put("https://fastly.jsdelivr.net/npm/acorn@8.7.1/dist/acorn.min.js",base+"cdnThirdParty/other_0acorn.min.js");
/*        reps.put("https://fastly.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css",base+"cdnThirdParty/bootstrapCSS.css");
        reps.put("https://fastly.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js",base+"cdnThirdParty/bootstrapJS.js");*/
        reps.put("https://fastly.jsdelivr.net/npm/code-prettify@0.1.0/src/lang-css.js",base+"cdnThirdParty/prettifyCSSHandlerJS.js");
        reps.put("https://fastly.jsdelivr.net/npm/code-prettify@0.1.0/src/prettify.min.js",base+"cdnThirdParty/prettifyJS.js");
        reps.put("https://fastly.jsdelivr.net/npm/codemirror@5.56.0/lib/codemirror.min.js",base+"cdnThirdParty/codeMirrorJS.js");
        reps.put("https://fastly.jsdelivr.net/npm/codemirror@5.56.0/mode/javascript/javascript.js",base+"cdnThirdParty/codeMirrorJSModeJS.js");
        reps.put("https://fastly.jsdelivr.net/npm/compare-versions@3.6.0/index.min.js",base+"cdnThirdParty/other_0index.min.js");
        reps.put("https://fastly.jsdelivr.net/npm/docsearch.js@2/dist/cdn/docsearch.min.css",base+"cdnThirdParty/other_1docsearch.min.css");
        reps.put("https://fastly.jsdelivr.net/npm/docsearch.js@2/dist/cdn/docsearch.min.js",base+"cdnThirdParty/other_0docsearch.min.js");

/*        reps.put("https://fastly.jsdelivr.net/npm/echarts/dist/echarts.min.js",base+"cdnThirdParty/other_0echarts.min.js");
        reps.put("https://fastly.jsdelivr.net/npm/echarts/theme/dark.js?_v_=20200710_1",base+"cdnThirdParty/other_0dark.js");
        reps.put("https://fastly.jsdelivr.net/npm/echarts/theme/infographic.js?_v_=20200710_1",base+"cdnThirdParty/other_0infographic.js");
        reps.put("https://fastly.jsdelivr.net/npm/echarts/theme/macarons.js?_v_=20200710_1",base+"cdnThirdParty/other_0macarons.js");
        reps.put("https://fastly.jsdelivr.net/npm/echarts/theme/roma.js?_v_=20200710_1",base+"cdnThirdParty/other_0roma.js");
        reps.put("https://fastly.jsdelivr.net/npm/echarts/theme/shine.js?_v_=20200710_1",base+"cdnThirdParty/other_0shine.js");
        reps.put("https://fastly.jsdelivr.net/npm/echarts/theme/vintage.js?_v_=20200710_1",base+"cdnThirdParty/other_0vintage.js");
        reps.put("https://fastly.jsdelivr.net/npm/echarts@5/dist/echarts.min.js",base+"cdnThirdParty/other_0echarts.min.js");*/

        reps.put("https://fastly.jsdelivr.net/npm/echarts@4.8.0/dist/echarts.min.js",base+"cdnThirdParty/echartsMinJS_4_8_0.js");
/*        reps.put("https://fastly.jsdelivr.net/npm/element-ui@2.15.13/lib/index.js",base+"cdnThirdParty/elementUIJS.js");
        reps.put("https://fastly.jsdelivr.net/npm/element-ui@2.15.13/lib/theme-chalk/index.css",base+"cdnThirdParty/elementUICSS.css");*/
        reps.put("https://fastly.jsdelivr.net/npm/jquery-lazyload@1.9.7/jquery.lazyload.min.js",base+"cdnThirdParty/jqueryLazyloadJS.js");
        reps.put("https://fastly.jsdelivr.net/npm/jquery@1.11.3/dist/jquery.min.js",base+"cdnThirdParty/jquery_1_11_3.js");
        reps.put("https://fastly.jsdelivr.net/npm/jquery@2.2.4/dist/jquery.min.js",base+"cdnThirdParty/jquery.js");
        reps.put("https://fastly.jsdelivr.net/npm/js-beautify@1.11.0/js/lib/beautifier.min.js",base+"cdnThirdParty/beautifierJS.js");
        reps.put("https://fastly.jsdelivr.net/npm/lodash@3.10.1/index.min.js",base+"cdnThirdParty/lodash.js");
        reps.put("https://fastly.jsdelivr.net/npm/lottie-web@5.7.6/build/player/lottie.min.js",base+"cdnThirdParty/other_0lottie.min.js");
        reps.put("https://fastly.jsdelivr.net/npm/pace-progressbar@1.0.2/pace.min.js",base+"cdnThirdParty/paceProgressBarJS.js");
        reps.put("https://fastly.jsdelivr.net/npm/perfect-scrollbar@0.6.8/dist/css/perfect-scrollbar.min.css",base+"cdnThirdParty/perfectScrollbarCSS.css");
        reps.put("https://fastly.jsdelivr.net/npm/perfect-scrollbar@0.6.8/dist/js/min/perfect-scrollbar.min.js",base+"cdnThirdParty/perfectScrollbarJS.js");
        reps.put("https://fastly.jsdelivr.net/npm/scrollreveal@4.0.7/dist/scrollreveal.min.js",base+"cdnThirdParty/other_0scrollreveal.min.js");
        reps.put("https://fastly.jsdelivr.net/npm/seedrandom@3.0.5/seedrandom.min.js",base+"cdnThirdParty/other_0seedrandom.min.js");
        reps.put("https://fastly.jsdelivr.net/npm/sweetalert@2.1.2/dist/sweetalert.min.js",base+"cdnThirdParty/sweetalertJS.js");
        reps.put("https://fastly.jsdelivr.net/npm/vanilla-lazyload@12.0.0/dist/lazyload.min.js",base+"cdnThirdParty/other_0lazyload.min.js");
        reps.put("https://fastly.jsdelivr.net/npm/vue@2.6.11/dist/vue.min.js",base+"cdnThirdParty/vueJS.js");

        reps.put("https://gallery.echartsjs.com/editor.html",base+"cdnThirdParty/other_4editor.html");
        reps.put("https://gallery.echartsjs.com/editor.html?c=xHyqn_rQ6g",base+"cdnThirdParty/other_4editor.html");
        reps.put("https://gallery.echartsjs.com/editor.html?c=xry06afSje",base+"cdnThirdParty/other_4editor.html");
        reps.put("https://gallery.echartsjs.com/editor.html?c=xryQDPYK0b",base+"cdnThirdParty/other_4editor.html");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/en/basics/download.md",base+"cdnThirdParty/other_3download.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/en/basics/help.md",base+"cdnThirdParty/other_3help.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/en/basics/import.md",base+"cdnThirdParty/other_3import.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/en/basics/release-note/5-2-0.md",base+"cdnThirdParty/other_35-2-0.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/en/basics/release-note/5-3-0.md",base+"cdnThirdParty/other_35-3-0.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/en/basics/release-note/5-4-0.md",base+"cdnThirdParty/other_35-4-0.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/en/basics/release-note/v5-feature.md",base+"cdnThirdParty/other_3v5-feature.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/en/basics/release-note/v5-upgrade-guide.md",base+"cdnThirdParty/other_3v5-upgrade-guide.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/en/best-practices/aria.md",base+"cdnThirdParty/other_3aria.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/en/best-practices/canvas-vs-svg.md",base+"cdnThirdParty/other_3canvas-vs-svg.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/en/concepts/axis.md",base+"cdnThirdParty/other_3axis.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/en/concepts/chart-size.md",base+"cdnThirdParty/other_3chart-size.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/en/concepts/data-transform.md",base+"cdnThirdParty/other_3data-transform.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/en/concepts/dataset.md",base+"cdnThirdParty/other_3dataset.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/en/concepts/event.md",base+"cdnThirdParty/other_3event.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/en/concepts/legend.md",base+"cdnThirdParty/other_3legend.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/en/concepts/style.md",base+"cdnThirdParty/other_3style.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/en/concepts/visual-map.md",base+"cdnThirdParty/other_3visual-map.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/en/get-started.md",base+"cdnThirdParty/other_3get-started.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/en/how-to/animation/transition.md",base+"cdnThirdParty/other_3transition.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/en/how-to/chart-types/bar/bar-race.md",base+"cdnThirdParty/other_3bar-race.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/en/how-to/chart-types/bar/basic-bar.md",base+"cdnThirdParty/other_3basic-bar.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/en/how-to/chart-types/bar/stacked-bar.md",base+"cdnThirdParty/other_3stacked-bar.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/en/how-to/chart-types/bar/waterfall.md",base+"cdnThirdParty/other_3waterfall.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/en/how-to/chart-types/line/area-line.md",base+"cdnThirdParty/other_3area-line.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/en/how-to/chart-types/line/basic-line.md",base+"cdnThirdParty/other_3basic-line.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/en/how-to/chart-types/line/smooth-line.md",base+"cdnThirdParty/other_3smooth-line.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/en/how-to/chart-types/line/stacked-line.md",base+"cdnThirdParty/other_3stacked-line.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/en/how-to/chart-types/line/step-line.md",base+"cdnThirdParty/other_3step-line.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/en/how-to/chart-types/pie/basic-pie.md",base+"cdnThirdParty/other_3basic-pie.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/en/how-to/chart-types/pie/doughnut.md",base+"cdnThirdParty/other_3doughnut.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/en/how-to/chart-types/pie/rose.md",base+"cdnThirdParty/other_3rose.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/en/how-to/chart-types/scatter/basic-scatter.md",base+"cdnThirdParty/other_3basic-scatter.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/en/how-to/cross-platform/server.md",base+"cdnThirdParty/other_3server.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/en/how-to/data/dynamic-data.md",base+"cdnThirdParty/other_3dynamic-data.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/en/how-to/interaction/coarse-pointer.md",base+"cdnThirdParty/other_3coarse-pointer.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/en/how-to/interaction/drag.md",base+"cdnThirdParty/other_3drag.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/en/how-to/label/rich-text.md",base+"cdnThirdParty/other_3rich-text.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/en/meta/edit-guide.md",base+"cdnThirdParty/other_3edit-guide.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/zh/basics/download.md",base+"cdnThirdParty/other_3download.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/zh/basics/help.md",base+"cdnThirdParty/other_3help.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/zh/basics/import.md",base+"cdnThirdParty/other_3import.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/zh/basics/release-note/5-2-0.md",base+"cdnThirdParty/other_35-2-0.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/zh/basics/release-note/5-3-0.md",base+"cdnThirdParty/other_35-3-0.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/zh/basics/release-note/5-4-0.md",base+"cdnThirdParty/other_35-4-0.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/zh/basics/release-note/v5-feature.md",base+"cdnThirdParty/other_3v5-feature.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/zh/basics/release-note/v5-upgrade-guide.md",base+"cdnThirdParty/other_3v5-upgrade-guide.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/zh/best-practices/aria.md",base+"cdnThirdParty/other_3aria.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/zh/best-practices/canvas-vs-svg.md",base+"cdnThirdParty/other_3canvas-vs-svg.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/zh/concepts/axis.md",base+"cdnThirdParty/other_3axis.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/zh/concepts/chart-size.md",base+"cdnThirdParty/other_3chart-size.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/zh/concepts/data-transform.md",base+"cdnThirdParty/other_3data-transform.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/zh/concepts/dataset.md",base+"cdnThirdParty/other_3dataset.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/zh/concepts/event.md",base+"cdnThirdParty/other_3event.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/zh/concepts/legend.md",base+"cdnThirdParty/other_3legend.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/zh/concepts/style.md",base+"cdnThirdParty/other_3style.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/zh/concepts/visual-map.md",base+"cdnThirdParty/other_3visual-map.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/zh/get-started.md",base+"cdnThirdParty/other_3get-started.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/zh/how-to/animation/transition.md",base+"cdnThirdParty/other_3transition.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/zh/how-to/chart-types/bar/bar-race.md",base+"cdnThirdParty/other_3bar-race.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/zh/how-to/chart-types/bar/basic-bar.md",base+"cdnThirdParty/other_3basic-bar.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/zh/how-to/chart-types/bar/stacked-bar.md",base+"cdnThirdParty/other_3stacked-bar.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/zh/how-to/chart-types/bar/waterfall.md",base+"cdnThirdParty/other_3waterfall.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/zh/how-to/chart-types/line/area-line.md",base+"cdnThirdParty/other_3area-line.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/zh/how-to/chart-types/line/basic-line.md",base+"cdnThirdParty/other_3basic-line.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/zh/how-to/chart-types/line/smooth-line.md",base+"cdnThirdParty/other_3smooth-line.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/zh/how-to/chart-types/line/stacked-line.md",base+"cdnThirdParty/other_3stacked-line.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/zh/how-to/chart-types/line/step-line.md",base+"cdnThirdParty/other_3step-line.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/zh/how-to/chart-types/pie/basic-pie.md",base+"cdnThirdParty/other_3basic-pie.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/zh/how-to/chart-types/pie/doughnut.md",base+"cdnThirdParty/other_3doughnut.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/zh/how-to/chart-types/pie/rose.md",base+"cdnThirdParty/other_3rose.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/zh/how-to/chart-types/scatter/basic-scatter.md",base+"cdnThirdParty/other_3basic-scatter.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/zh/how-to/cross-platform/baidu-app.md",base+"cdnThirdParty/other_3baidu-app.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/zh/how-to/cross-platform/server.md",base+"cdnThirdParty/other_3server.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/zh/how-to/cross-platform/wechat-app.md",base+"cdnThirdParty/other_3wechat-app.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/zh/how-to/data/dynamic-data.md",base+"cdnThirdParty/other_3dynamic-data.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/zh/how-to/interaction/coarse-pointer.md",base+"cdnThirdParty/other_3coarse-pointer.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/zh/how-to/interaction/drag.md",base+"cdnThirdParty/other_3drag.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/zh/how-to/label/rich-text.md",base+"cdnThirdParty/other_3rich-text.md");
        reps.put("https://github.com/apache/echarts-handbook/tree/master/contents/zh/meta/edit-guide.md",base+"cdnThirdParty/other_3edit-guide.md");
        reps.put("https://github.com/apache/echarts/blob/8eeb7e5abe207d0536c62ce1f4ddecc6adfdf85e/src/util/mapData/rawData/encode.js",base+"cdnThirdParty/other_0encode.js");
        reps.put("https://github.com/apache/echarts/blob/release/extension-src/bmap/README.md",base+"cdnThirdParty/other_3README.md");
        reps.put("https://hm.baidu.com/hm.js?54b918eee37cb8a7045f0fd0f0b24395",base+"cdnThirdParty/other_0hm.js");
        reps.put("https://incubator.apache.org/guides/ppmc.html",base+"cdnThirdParty/other_4ppmc.html");
        reps.put("https://lbsyun.baidu.com/cms/jsapi/reference/jsapi_reference.html",base+"cdnThirdParty/other_4jsapi_reference.html");
        reps.put("https://lists.apache.org/list.html?commits@echarts.apache.org",base+"cdnThirdParty/other_4list.html");
        reps.put("https://lists.apache.org/list.html?dev@echarts.apache.org",base+"cdnThirdParty/other_4list.html");
        reps.put("https://www.apache.org/dev/pmc.html",base+"cdnThirdParty/other_4pmc.html");
        reps.put("https://www.apache.org/foundation/policies/conduct.html",base+"cdnThirdParty/other_4conduct.html");
        reps.put("https://www.apache.org/foundation/sponsorship.html",base+"cdnThirdParty/other_4sponsorship.html");
        reps.put("https://www.apache.org/foundation/thanks.html",base+"cdnThirdParty/other_4thanks.html");
        reps.put("https://www.w3.org/WAI/WCAG21/Understanding/target-size.html",base+"cdnThirdParty/other_4target-size.html");
        reps3.putAll(reps);




    }

    public static void main(String[] args) throws IOException {
        String folderPath = "D:\\dev\\workspace\\nodejs\\echarts\\echarts-website";
        String folderPath2 = "D:\\dev\\workspace\\nodejs\\echarts\\echarts-website2";
        String folderPath3 = "D:\\dev\\server\\apache-tomcat-8.5_8082\\webapps\\echarts-website";
/*        if (1+1==2){
            copyDirectory("fix", folderPath3);
            return;
        }*/

        File folder = new File(folderPath);
        List<File> fileList = traverseFolder(folder);
        // 创建一个HashSet
        Set<String> set = new HashSet<>();
        Set<String> set2 = new HashSet<>();
        // 将HashSet转换为同步的Set
        Set<String> synchronizedSet = Collections.synchronizedSet(set);
        Set<String> synchronizedSet2 = Collections.synchronizedSet(set2);
        Set<String> st= reps.keySet();
        Set<String> st2= reps2.keySet();
        Set<String> st3= reps3.keySet();
        fileList.parallelStream().filter(f->!f.getAbsolutePath().startsWith("D:\\dev\\workspace\\nodejs\\echarts\\echarts-website2\\.git")).forEach(f->{
            String target=f.getAbsolutePath().replace(folderPath,folderPath2);
            String target2 = f.getAbsolutePath().replace(folderPath,folderPath3);

            if (f.getName().endsWith(".html")||f.getName().endsWith(".js")){

                try {
                    String fstr=readHtmlFile(f.getAbsolutePath());
                    synchronizedSet.addAll(extractUrls(fstr));
                    if (f.getName().endsWith(".html")){
                        //去除些没用的东西
/*                        int index=fstr.indexOf("<!-- Baidu Tongji-->");
                        if (index>0){
                            fstr=fstr.substring(0,fstr.indexOf("<!-- Baidu Tongji-->"))+"</script>";
                        }*/

                        for (String item : st3) {
                            fstr=fstr.replaceAll(Pattern.quote(item),reps3.get(item));
                        }
                        //
                        if (f.getAbsolutePath().contains("\\handbook\\zh")){
                            //修正使用手册界面指南无效
                           // fstr=fstr.substring(0,fstr.lastIndexOf("</html>"))+"<script>$(function(){  $(\".nav-item a\").click(function(){if($(this).attr(\"href\")){window.location.href=($(this).attr(\"href\"))}})  }  )</script></html>";

                        }

                    }else if (f.getName().endsWith(".js")){
                        String tempstr=fstr;
                        if (f.getAbsolutePath().contains("examples\\examples\\js") && tempstr.indexOf("$.getScript")!=-1){
                            synchronizedSet2.addAll(extractUrls2(tempstr));
                        }
                        for (String item : st2) {
                            fstr=fstr.replaceAll(Pattern.quote(item),reps2.get(item));
                        }
                        for (String item : st) {
                            fstr=fstr.replaceAll(Pattern.quote(item),reps.get(item));
                        }

                    }
                    for (int i = 0; i < lastrep.size(); i++) {
                        String last=lastrep.get(i);
                        fstr=fstr.replaceAll(Pattern.quote(last.split(",")[0]),last.split(",")[1]);
                    }
                    saveFile(fstr, target);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }else {
                try {
                    copyFile(f.getAbsolutePath(),target);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                copyFile(target,target2);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        Predicate<String> predicate=s->{
            boolean exists=false;

            for (String item : st) {
                if (s.startsWith(item)){
                    exists=true;
                }
            }
            return !exists;
        };
        synchronizedSet.stream().filter(predicate).forEach(System.out::println);
        System.out.println("-----------------------------------------------------");
        String[] staticRes=new String[]{".js",".css",".svg",".md",".html"};
        synchronizedSet.stream().filter(predicate).forEach(l->{
            for (int i = 0; i < staticRes.length; i++) {
                String s=staticRes[i];
                if (l.toString().endsWith(s)||l.toString().contains(s+"?")){
                    String fp=l.toString();
                    int index=fp.lastIndexOf("?");
                    if (index!=-1){
                        fp=fp.substring(0,index);
                    }
                    fp=fp.substring(fp.lastIndexOf("/")+1,fp.length());
                   // System.out.println("other_"+i+fp+": '"+l+"',");
                }
            }

        });
        copyDirectory("cdnThirdParty", folderPath2+"\\cdnThirdParty");
        copyDirectory("cdnThirdParty", folderPath3+"\\cdnThirdParty");
        copyDirectory("fix", folderPath2);
        copyDirectory("fix", folderPath3);

        //D:\dev\workspace\idea\myproject\ConverEarchsDoc\cdnThirdParty

        System.out.println("-----------------------------------------------------");
/*        synchronizedSet2.stream().forEach(l->{
            System.out.println(l);
        });*/
    }
    public static void copyDirectory(String sourceDir, String targetDir) throws IOException {
        File sourceDirectory = new File(sourceDir);
        File targetDirectory = new File(targetDir);

        // 检查源目录是否存在
        if (!sourceDirectory.exists() || !sourceDirectory.isDirectory()) {
            throw new IllegalArgumentException("Source directory does not exist or is not a directory.");
        }

        // 检查目标目录是否存在，如果不存在则创建
        if (!targetDirectory.exists()) {
            targetDirectory.mkdirs();
        }

        // 遍历源目录中的所有文件和子目录
        File[] files = sourceDirectory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    // 如果是文件，则复制到目标目录
                    Path sourcePath = file.toPath();
                    Path targetPath = new File(targetDirectory, file.getName()).toPath();
                    Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                } else if (file.isDirectory()) {
                    // 如果是目录，则递归调用copyDirectory方法复制目录
                    String subSourceDir = file.getAbsolutePath();
                    String subTargetDir = new File(targetDirectory, file.getName()).getAbsolutePath();
                    copyDirectory(subSourceDir, subTargetDir);
                }
            }
        }
    }
    public static void saveFile(String content, String filePath) {
        try {
            // 创建文件对象
            File file = new File(filePath);

            // 创建父文件夹（如果不存在）
            file.getParentFile().mkdirs();

            // 创建BufferedWriter对象
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            // 写入文件内容
            writer.write(content);

            // 关闭写入流
            writer.close();

            System.out.println("文件保存成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String readHtmlFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        }

        return content.toString();
    }

    public static List<String> extractUrls(String input) {
        List<String> urls = new ArrayList<>();

        Pattern pattern = Pattern.compile("\"(https?://[^\"]+)\"");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            String url = matcher.group(1);
            urls.add(url);
        }

        return urls;
    }
    public static List<String> extractUrls2(String input) {
        List<String> urls = new ArrayList<>();

        Pattern pattern = Pattern.compile("'(https?://[^\"]+?)'");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            String url = matcher.group(1);
            urls.add(url);
        }

        return urls;
    }

    public static List<File> traverseFolder(File folder) {
        List<File> fileList = new ArrayList<>();

        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        // 递归遍历子文件夹
                        List<File> subFiles = traverseFolder(file);
                        fileList.addAll(subFiles);
                    } else {
                        fileList.add(file);
                    }
                }
            }
        }

        return fileList;
    }
    public static void copyFile(String sourceFilePath, String destinationFilePath) throws IOException {
        File sourceFile = new File(sourceFilePath);
        File destinationFile = new File(destinationFilePath);

        // 创建目标文件夹（如果不存在）
        File destinationFolder = destinationFile.getParentFile();
        if (!destinationFolder.exists()) {
            destinationFolder.mkdirs();
        }

        // 拷贝文件
        Path sourcePath = sourceFile.toPath();
        Path destinationPath = destinationFile.toPath();
        Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);

        System.out.println("文件拷贝成功：" + sourceFilePath + " -> " + destinationFilePath);
    }
}
