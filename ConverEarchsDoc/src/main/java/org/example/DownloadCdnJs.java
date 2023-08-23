package org.example;

import com.sun.deploy.net.HttpResponse;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.*;
import java.io.*;
import java.net.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DownloadCdnJs {
    public static void main(String[] args) throws Exception {
        //System.setProperty("https.protocols","TLSv1.1");
        //System.setProperty("https.protocols","TLSv1,TLSv1.1,TLSv1.2");
        String base="http://localhost:8082/";
        String text = "        jquery: 'https://fastly.jsdelivr.net/npm/jquery@2.2.4/dist/jquery.min.js',\n"
                + "        jquery_1_11_3: 'https://fastly.jsdelivr.net/npm/jquery@1.11.3/dist/jquery.min.js',\n"
                + "        bootstrapCSS: 'https://fastly.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css',\n" + "       " +
                " bootstrapJS: 'https://fastly.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js',\n" + "       " +
                " lodash: 'https://fastly.jsdelivr.net/npm/lodash@3.10.1/index.min.js',\n" + "        " +
                "perfectScrollbarJS: 'https://fastly.jsdelivr.net/npm/perfect-scrollbar@0.6.8/dist/js/min/perfect-scrollbar.min.js',\n"
                + "        perfectScrollbarCSS: 'https://fastly.jsdelivr.net/npm/perfect-scrollbar@0.6.8/dist/css/perfect-scrollbar.min.css',\n"
                + "        paceProgressBarJS: 'https://fastly.jsdelivr.net/npm/pace-progressbar@1.0.2/pace.min.js',\n"
                + "        sweetalertJS: 'https://fastly.jsdelivr.net/npm/sweetalert@2.1.2/dist/sweetalert.min.js',\n"
                + "        echartsMinJS_4_8_0: 'https://fastly.jsdelivr.net/npm/echarts@4.8.0/dist/echarts.min.js',\n"
                + "        prettifyJS: 'https://fastly.jsdelivr.net/npm/code-prettify@0.1.0/src/prettify.min.js',\n"
                + "        prettifyCSSHandlerJS: 'https://fastly.jsdelivr.net/npm/code-prettify@0.1.0/src/lang-css.js',\n"
                + "        jqueryLazyloadJS: 'https://fastly.jsdelivr.net/npm/jquery-lazyload@1.9.7/jquery.lazyload.min.js',\n"
                + "        vueJS: 'https://fastly.jsdelivr.net/npm/vue@2.6.11/dist/vue.min.js',\n"
                + "        elementUIJS: 'https://fastly.jsdelivr.net/npm/element-ui@2.15.13/lib/index.js',\n"
                + "        elementUICSS: 'https://fastly.jsdelivr.net/npm/element-ui@2.15.13/lib/theme-chalk/index.css',\n"
                + "        codeMirrorJS: 'https://fastly.jsdelivr.net/npm/codemirror@5.56.0/lib/codemirror.min.js',\n"
                + "        codeMirrorJSModeJS: 'https://fastly.jsdelivr.net/npm/codemirror@5.56.0/mode/javascript/javascript.js',\n"
                + "        beautifierJS: 'https://fastly.jsdelivr.net/npm/js-beautify@1.11.0/js/lib/beautifier.min.js',"


                +"other_3visual-map.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/zh/concepts/visual-map.md',\n" +
                "other_0encode.js: 'https://github.com/apache/echarts/blob/8eeb7e5abe207d0536c62ce1f4ddecc6adfdf85e/src/util/mapData/rawData/encode.js',\n" +
                "other_3download.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/en/basics/download.md',\n" +
                "other_0ecStat.min.js: 'https://cdn.jsdelivr.net/npm/echarts-stat@1/dist/ecStat.min.js',\n" +
                "other_0macarons.js: 'https://fastly.jsdelivr.net/npm/echarts/theme/macarons.js?_v_=20200710_1',\n" +
                "other_4view-lite.html: 'https://gallery.echartsjs.com/view-lite.html?cid=xHkRpZuY4z&v=1',\n" +
                "other_3rich-text.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/zh/how-to/label/rich-text.md',\n" +
                "other_3stacked-line.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/en/how-to/chart-types/line/stacked-line.md',\n" +
                "other_4list.html: 'https://lists.apache.org/list.html?dev@echarts.apache.org',\n" +
                "other_3style.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/en/concepts/style.md',\n" +
                "other_4ggrepel.html: 'https://cran.r-project.org/web/packages/ggrepel/vignettes/ggrepel.html',\n" +
                "other_3import.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/en/basics/import.md',\n" +
                "other_0javascript.js: 'https://cdn.jsdelivr.net/npm/codemirror@5.56.0/mode/javascript/javascript.js',\n" +
                "other_3server.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/en/how-to/cross-platform/server.md',\n" +
                "other_3rose.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/zh/how-to/chart-types/pie/rose.md',\n" +
                "other_0lazyload.min.js: 'https://fastly.jsdelivr.net/npm/vanilla-lazyload@12.0.0/dist/lazyload.min.js',\n" +
                "other_4editor.html: 'https://ecomfe.github.io/echarts-examples/public/editor.html?c=pie-legend',\n" +
                "other_3download.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/zh/basics/download.md',\n" +
                "other_35-2-0.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/en/basics/release-note/5-2-0.md',\n" +
                "other_3data-transform.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/zh/concepts/data-transform.md',\n" +
                "other_4editor.html: 'https://ecomfe.github.io/echarts-examples/public/editor.html?c=lines-ny',\n" +
                "other_4editor.html: 'http://localhost/echarts-website/examples/${lang}/editor.html?c=',\n" +
                "other_35-4-0.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/zh/basics/release-note/5-4-0.md',\n" +
                "other_4conduct.html: 'https://www.apache.org/foundation/policies/conduct.html',\n" +
                "other_0html5shiv.min.js: 'https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js',\n" +
                "other_3doughnut.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/en/how-to/chart-types/pie/doughnut.md',\n" +
                "other_0hm.js: 'https://hm.baidu.com/hm.js?54b918eee37cb8a7045f0fd0f0b24395',\n" +
                "other_41093.html: 'https://apachecon.com/acasia2021/zh/sessions/1093.html',\n" +
                "other_4pmc.html: 'https://www.apache.org/dev/pmc.html',\n" +
                "other_4view-lite.html: 'https://gallery.echartsjs.com/view-lite.html?cid=xr1kK7LoEf&v=1',\n" +
                "other_4editor.html: 'https://ecomfe.github.io/echarts-examples/public/editor.html?c=scatterGL-gps&amp;gl=1',\n" +
                "other_4editor.html: 'https://ecomfe.github.io/echarts-examples/public/editor.html?c=sankey-vertical',\n" +
                "other_0seedrandom.min.js: 'https://fastly.jsdelivr.net/npm/seedrandom@3.0.5/seedrandom.min.js',\n" +
                "other_3area-line.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/zh/how-to/chart-types/line/area-line.md',\n" +
                "other_0jquery.min.js: 'https://cdn.jsdelivr.net/npm/jquery@1.11.3/dist/jquery.min.js',\n" +
                "other_3stacked-bar.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/en/how-to/chart-types/bar/stacked-bar.md',\n" +
                "other_3transition.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/zh/how-to/animation/transition.md',\n" +
                "other_3event.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/en/concepts/event.md',\n" +
                "other_3help.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/en/basics/help.md',\n" +
                "other_4view-lite.html: 'https://gallery.echartsjs.com/view-lite.html?cid=xp1oqJoQqG',\n" +
                "other_4editor.html: 'https://ecomfe.github.io/echarts-examples/public/editor.html?c=map-bin',\n" +
                "other_4view-lite.html: 'https://gallery.echartsjs.com/view-lite.html?cid=area-simple',\n" +
                "other_0vintage.js: 'https://fastly.jsdelivr.net/npm/echarts/theme/vintage.js?_v_=20200710_1',\n" +
                "other_3doughnut.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/zh/how-to/chart-types/pie/doughnut.md',\n" +
                "other_3v5-upgrade-guide.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/en/basics/release-note/v5-upgrade-guide.md',\n" +
                "other_4editor.html: 'https://gallery.echartsjs.com/editor.html?c=xHyqn_rQ6g',\n" +
                "other_4editor.html: 'https://ecomfe.github.io/echarts-examples/public/editor.html?c=bar-polar-stack-radial',\n" +
                "other_0bootstrap.min.js: 'https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js',\n" +
                "other_4view-lite.html: 'https://gallery.echartsjs.com/view-lite.html?cid=xHJH93GqVf&v=1',\n" +
                "other_4view-lite.html: 'https://gallery.echartsjs.com/view-lite.html?cid=xrk6EfmqVf',\n" +
                "other_3axis.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/zh/concepts/axis.md',\n" +
                "other_0beautifier.min.js: 'https://cdn.jsdelivr.net/npm/js-beautify@1.11.0/js/lib/beautifier.min.js',\n" +
                "other_4view-lite.html: 'https://gallery.echartsjs.com/view-lite.html?cid=xry8WsXdOW',\n" +
                "other_41090.html: 'https://apachecon.com/acasia2021/zh/sessions/1090.html',\n" +
                "other_4view-lite.html: 'https://gallery.echartsjs.com/view-lite.html?cid=xHJhWhGm4M',\n" +
                "other_4view-lite.html: 'https://gallery.echartsjs.com/view-lite.html?cid=xHJIPHN9Nf',\n" +
                "other_3smooth-line.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/en/how-to/chart-types/line/smooth-line.md',\n" +
                "other_3smooth-line.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/zh/how-to/chart-types/line/smooth-line.md',\n" +
                "other_4sponsorship.html: 'https://www.apache.org/foundation/sponsorship.html',\n" +
                "other_0echarts.min.js: 'https://cdn.jsdelivr.net/npm/echarts@4/dist/echarts.min.js',\n" +
                "other_0jquery.min.js: 'https://cdn.jsdelivr.net/npm/jquery@2.2.4/dist/jquery.min.js',\n" +
                "other_4list.html: 'https://lists.apache.org/list.html?commits@echarts.apache.org',\n" +
                "other_3waterfall.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/en/how-to/chart-types/bar/waterfall.md',\n" +
                "other_0index.min.js: 'https://cdn.jsdelivr.net/npm/lodash@3.10.1/index.min.js',\n" +
                "other_3basic-line.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/zh/how-to/chart-types/line/basic-line.md',\n" +
                "other_3get-started.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/zh/get-started.md',\n" +
                "other_4echarts-5-event.html: 'https://echarts-5-live.bj.bcebos.com/echarts-5-event.html?ref=ec-event',\n" +
                "other_3edit-guide.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/en/meta/edit-guide.md',\n" +
                "other_0docsearch.min.js: 'https://fastly.jsdelivr.net/npm/docsearch.js@2/dist/cdn/docsearch.min.js',\n" +
                "other_0prettify.min.js: 'https://cdn.jsdelivr.net/npm/code-prettify@0.1.0/src/prettify.min.js',\n" +
                "other_0perfect-scrollbar.min.js: 'https://cdn.jsdelivr.net/npm/perfect-scrollbar@0.6.8/dist/js/min/perfect-scrollbar.min.js',\n" +
                "other_4editor.html: 'https://ecomfe.github.io/echarts-examples/public/editor.html?c=linesGL-ny&amp;gl=1',\n" +
                "other_4view-lite.html: 'https://gallery.echartsjs.com/view-lite.html?cid=scatter-punchCard',\n" +
                "other_3v5-feature.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/zh/basics/release-note/v5-feature.md',\n" +
                "other_4editor.html: 'https://ecomfe.github.io/echarts-examples/public/editor.html?c=sankey-product',\n" +
                "other_0dataTool.min.js: 'https://cdn.jsdelivr.net/npm/echarts@4/dist/extension/dataTool.min.js',\n" +
                "other_4view-lite.html: 'https://gallery.echartsjs.com/view-lite.html?cid=xByXtUE7Vz',\n" +
                "other_3waterfall.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/zh/how-to/chart-types/bar/waterfall.md',\n" +
                "other_4view-lite.html: 'https://gallery.echartsjs.com/view-lite.html?cid=xS1Tbdr8EG&v=1',\n" +
                "other_4view-lite.html: 'https://gallery.echartsjs.com/view-lite.html?cid=xrJfrjEc4z&v=1',\n" +
                "other_3dataset.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/zh/concepts/dataset.md',\n" +
                "other_4view-lite.html: 'https://gallery.echartsjs.com/view-lite.html?cid=xBy9E2oufM',\n" +
                "other_4ppmc.html: 'https://incubator.apache.org/guides/ppmc.html',\n" +
                "other_4view-lite.html: 'https://gallery.echartsjs.com/view-lite.html?cid=xrydEwN94f',\n" +
                "other_0jquery.lazyload.min.js: 'https://cdn.jsdelivr.net/npm/jquery-lazyload@1.9.7/jquery.lazyload.min.js',\n" +
                "other_41091.html: 'https://apachecon.com/acasia2021/zh/sessions/1091.html',\n" +
                "other_0index.min.js: 'https://cdn.jsdelivr.net/npm/compare-versions@3.6.0/index.min.js',\n" +
                "other_4view-lite.html: 'https://gallery.echartsjs.com/view-lite.html?cid=xr13FAH54f&v=1',\n" +
                "other_4view.html: 'http://localhost/echarts-website/examples/${lang}/view.html?c=',\n" +
                "other_4editor.html: 'https://ecomfe.github.io/echarts-examples/public/editor.html?c=treemap-show-parent',\n" +
                "other_4editor.html: 'https://ecomfe.github.io/echarts-examples/public/editor.html?c=radar2',\n" +
                "other_4view-lite.html: 'https://gallery.echartsjs.com/view-lite.html?cid=xp1oqJoQqG',\n" +
                "other_3dynamic-data.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/zh/how-to/data/dynamic-data.md',\n" +
                "other_4datavisualization.html: 'https://apachecon.com/acasia2021/zh/tracks/datavisualization.html',\n" +
                "other_0vue.min.js: 'https://cdn.jsdelivr.net/npm/vue@2.6.11/dist/vue.min.js',\n" +
                "other_3stacked-line.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/zh/how-to/chart-types/line/stacked-line.md',\n" +
                "other_3data-transform.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/en/concepts/data-transform.md',\n" +
                "other_41090.html: 'https://apachecon.com/acasia2021/sessions/1090.html',\n" +
                "other_3stacked-bar.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/zh/how-to/chart-types/bar/stacked-bar.md',\n" +
                "other_3server.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/zh/how-to/cross-platform/server.md',\n" +
                "other_3v5-upgrade-guide.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/zh/basics/release-note/v5-upgrade-guide.md',\n" +
                "other_41092.html: 'https://apachecon.com/acasia2021/zh/sessions/1092.html',\n" +
                "other_4view-lite.html: 'https://gallery.echartsjs.com/view-lite.html?cid=xHJ1un374z',\n" +
                "other_35-4-0.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/en/basics/release-note/5-4-0.md',\n" +
                "other_3bar-race.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/en/how-to/chart-types/bar/bar-race.md',\n" +
                "other_3import.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/zh/basics/import.md',\n" +
                "other_3basic-scatter.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/en/how-to/chart-types/scatter/basic-scatter.md',\n" +
                "other_3legend.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/zh/concepts/legend.md',\n" +
                "other_4view-lite.html: 'https://gallery.echartsjs.com/view-lite.html?cid=xH1-fnLcVG&v=1',\n" +
                "other_3help.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/zh/basics/help.md',\n" +
                "other_4view-lite.html: 'https://gallery.echartsjs.com/view-lite.html?cid=xry8WsXdOW&v=3',\n" +
                "other_4view-lite.html: 'https://gallery.echartsjs.com/view-lite.html?cid=xSkCyUwKNG&v=1',\n" +
                "other_4editor.html: 'https://gallery.echartsjs.com/editor.html?c=xry06afSje',\n" +
                "other_4view-lite.html: 'https://gallery.echartsjs.com/view-lite.html?cid=xByYRlN7Ef',\n" +
                "other_0echarts.min.js: 'https://cdn.jsdelivr.net/npm/echarts@4/dist/echarts.min.js?_v_=1611323308745',\n" +
                "other_4view-lite.html: 'https://gallery.echartsjs.com/view-lite.html?cid=xHJ-4tL84M',\n" +
                "other_3drag.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/zh/how-to/interaction/drag.md',\n" +
                "other_3style.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/zh/concepts/style.md',\n" +
                "other_35-3-0.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/zh/basics/release-note/5-3-0.md',\n" +
                "other_0lazyload.min.js: 'https://cdn.jsdelivr.net/npm/vanilla-lazyload@12.0.0/dist/lazyload.min.js',\n" +
                "other_4view-lite.html: 'https://gallery.echartsjs.com/view-lite.html?cid=xSkBiMSU4M&v=1',\n" +
                "other_3basic-pie.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/zh/how-to/chart-types/pie/basic-pie.md',\n" +
                "other_0dataTool.js: 'https://cdn.jsdelivr.net/npm/echarts@4/dist/extension/dataTool.js?_v_=1611323308745',\n" +
                "other_3step-line.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/en/how-to/chart-types/line/step-line.md',\n" +
                "other_3v5-feature.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/en/basics/release-note/v5-feature.md',\n" +
                "other_4view-lite.html: 'https://gallery.echartsjs.com/view-lite.html?cid=scatter-world-population',\n" +
                "other_0china.js: 'https://cdn.jsdelivr.net/npm/echarts@4/map/js/china.js',\n" +
                "other_0respond.min.js: 'https://oss.maxcdn.com/respond/1.4.2/respond.min.js',\n" +
                "other_4editor.html: 'https://ecomfe.github.io/echarts-examples/public/editor.html?c=bar-label-rotation',\n" +
                "other_0echarts.min.js: 'https://fastly.jsdelivr.net/npm/echarts/dist/echarts.min.js',\n" +
                "other_3basic-bar.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/en/how-to/chart-types/bar/basic-bar.md',\n" +
                "other_3basic-pie.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/en/how-to/chart-types/pie/basic-pie.md',\n" +
                "other_1docsearch.min.css: 'https://fastly.jsdelivr.net/npm/docsearch.js@2/dist/cdn/docsearch.min.css',\n" +
                "other_4target-size.html: 'https://www.w3.org/WAI/WCAG21/Understanding/target-size.html',\n" +
                "other_3basic-line.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/en/how-to/chart-types/line/basic-line.md',\n" +
                "other_3baidu-app.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/zh/how-to/cross-platform/baidu-app.md',\n" +
                "other_0jquery.waypoints.min.js: 'https://cdn.jsdelivr.net/npm/waypoints@4.0.0/lib/jquery.waypoints.min.js',\n" +
                "other_0bmap.js: 'https://cdn.jsdelivr.net/npm/echarts@4/dist/extension/bmap.js?_v_=1611323308745',\n" +
                "other_3legend.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/en/concepts/legend.md',\n" +
                "other_4view-lite.html: 'https://gallery.echartsjs.com/view-lite.html?cid=xH1vxib94f',\n" +
                "other_1bootstrap.min.css: 'https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css',\n" +
                "other_4jsapi_reference.html: 'https://lbsyun.baidu.com/cms/jsapi/reference/jsapi_reference.html',\n" +
                "other_3transition.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/en/how-to/animation/transition.md',\n" +
                "other_4editor.html: 'https://ecomfe.github.io/echarts-examples/public/editor.html?c=tree-orient-right-left',\n" +
                "other_0index.js: 'https://cdn.jsdelivr.net/npm/element-ui@2.13.2/lib/index.js',\n" +
                "other_4editor.html: 'https://ecomfe.github.io/echarts-examples/public/editor.html?c=map-polygon',\n" +
                "other_0shine.js: 'https://fastly.jsdelivr.net/npm/echarts/theme/shine.js?_v_=20200710_1',\n" +
                "other_3area-line.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/en/how-to/chart-types/line/area-line.md',\n" +
                "other_3chart-size.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/en/concepts/chart-size.md',\n" +
                "other_3axis.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/en/concepts/axis.md',\n" +
                "other_4view-lite.html: 'https://gallery.echartsjs.com/view-lite.html?cid=xIVoX5gZcT&v=1',\n" +
                "other_0world.js: 'https://cdn.jsdelivr.net/npm/echarts@4/map/js/world.js?_v_=1611323308745',\n" +
                "other_41091.html: 'https://apachecon.com/acasia2021/sessions/1091.html',\n" +
                "other_0lottie.min.js: 'https://fastly.jsdelivr.net/npm/lottie-web@5.7.6/build/player/lottie.min.js',\n" +
                "other_4editor.html: 'https://ecomfe.github.io/echarts-examples/public/editor.html?c=sankey-energy',\n" +
                "other_0world.js: 'https://cdn.jsdelivr.net/npm/echarts@4/map/js/world.js',\n" +
                "other_3aria.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/zh/best-practices/aria.md',\n" +
                "other_0ext-language_tools.js: 'https://cdn.jsdelivr.net/npm/ace-builds@1.4.12/src-min-noconflict/ext-language_tools.js',\n" +
                "other_4view-lite.html: 'https://gallery.echartsjs.com/view-lite.html?cid=gauge-car',\n" +
                "other_4explore.html: 'https://gallery.echartsjs.com/explore.html?u=bd-809368804',\n" +
                "other_3rose.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/en/how-to/chart-types/pie/rose.md',\n" +
                "other_4editor.html: 'https://gallery.echartsjs.com/editor.html?c=xryQDPYK0b',\n" +
                "other_0china.js: 'https://cdn.jsdelivr.net/npm/echarts@4/map/js/china.js?_v_=1611323308745',\n" +
                "other_41087.html: 'https://apachecon.com/acasia2021/sessions/1087.html',\n" +
                "other_0infographic.js: 'https://fastly.jsdelivr.net/npm/echarts/theme/infographic.js?_v_=20200710_1',\n" +
                "other_4view-lite.html: 'https://gallery.echartsjs.com/view-lite.html?cid=xBk7oUNwEz',\n" +
                "other_41092.html: 'https://apachecon.com/acasia2021/sessions/1092.html',\n" +
                "other_4editor.html: 'https://ecomfe.github.io/echarts-examples/public/editor.html?c=tree-vertical',\n" +
                "other_1index.css: 'https://cdn.jsdelivr.net/npm/element-ui@2.13.2/lib/theme-chalk/index.css',\n" +
                "other_35-3-0.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/en/basics/release-note/5-3-0.md',\n" +
                "other_0pace.min.js: 'https://cdn.jsdelivr.net/npm/pace-progressbar@1.0.2/pace.min.js',\n" +
                "other_4datavisualization.html: 'https://apachecon.com/acasia2021/tracks/datavisualization.html',\n" +
                "other_35-2-0.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/zh/basics/release-note/5-2-0.md',\n" +
                "other_3basic-bar.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/zh/how-to/chart-types/bar/basic-bar.md',\n" +
                "other_0index.min.js: 'https://fastly.jsdelivr.net/npm/compare-versions@3.6.0/index.min.js',\n" +
                "other_3basic-scatter.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/zh/how-to/chart-types/scatter/basic-scatter.md',\n" +
                "other_3dataset.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/en/concepts/dataset.md',\n" +
                "other_3coarse-pointer.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/en/how-to/interaction/coarse-pointer.md',\n" +
                "other_0dat.gui.min.js: 'https://cdn.jsdelivr.net/npm/dat.gui@0.6.5/build/dat.gui.min.js',\n" +
                "other_4editor.html: 'https://gallery.echartsjs.com/editor.html',\n" +
                "other_3visual-map.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/en/concepts/visual-map.md',\n" +
                "other_4view-lite.html: 'https://gallery.echartsjs.com/view-lite.html?cid=xSkO64PIEG&v=1',\n" +
                "other_1perfect-scrollbar.min.css: 'https://cdn.jsdelivr.net/npm/perfect-scrollbar@0.6.8/dist/css/perfect-scrollbar.min.css',\n" +
                "other_3canvas-vs-svg.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/zh/best-practices/canvas-vs-svg.md',\n" +
                "other_4editor.html: 'https://ecomfe.github.io/echarts-examples/public/editor.html?c=map-labels',\n" +
                "other_4thanks.html: 'https://www.apache.org/foundation/thanks.html',\n" +
                "other_41094.html: 'https://apachecon.com/acasia2021/sessions/1094.html',\n" +
                "other_0echarts.min.js: 'https://fastly.jsdelivr.net/npm/echarts@5/dist/echarts.min.js',\n" +
                "other_3README.md: 'https://github.com/apache/echarts/blob/release/extension-src/bmap/README.md',\n" +
                "other_4editor.html: 'https://ecomfe.github.io/echarts-examples/public/editor.html?c=bar-polar-real-estate',\n" +
                "other_4editor.html: 'https://ecomfe.github.io/echarts-examples/public/editor.html?c=bar-polar-stack',\n" +
                "other_0echarts.min.js: 'https://cdn.jsdelivr.net/npm/echarts@4.8.0/dist/echarts.min.js',\n" +
                "other_4view-lite.html: 'https://gallery.echartsjs.com/view-lite.html?cid=xIVoX5gZcT&v=1',\n" +
                "other_4view-lite.html: 'https://gallery.echartsjs.com/view-lite.html?cid=xrJIQEN5NM',\n" +
                "other_3edit-guide.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/zh/meta/edit-guide.md',\n" +
                "other_0lang-css.js: 'https://cdn.jsdelivr.net/npm/code-prettify@0.1.0/src/lang-css.js',\n" +
                "other_3canvas-vs-svg.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/en/best-practices/canvas-vs-svg.md',\n" +
                "other_4view-lite.html: 'https://gallery.echartsjs.com/view-lite.html?cid=xHyNDxOo4M',\n" +
                "other_3coarse-pointer.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/zh/how-to/interaction/coarse-pointer.md',\n" +
                "other_0codemirror.min.js: 'https://cdn.jsdelivr.net/npm/codemirror@5.56.0/lib/codemirror.min.js',\n" +
                "other_4editor.html: 'https://ecomfe.github.io/echarts-examples/public/editor.html?c=scatter-large',\n" +
                "other_3chart-size.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/zh/concepts/chart-size.md',\n" +
                "other_3bar-race.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/zh/how-to/chart-types/bar/bar-race.md',\n" +
                "other_3dynamic-data.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/en/how-to/data/dynamic-data.md',\n" +
                "other_4view-lite.html: 'https://gallery.echartsjs.com/view-lite.html?cid=xBJzdEItVz&v=1',\n" +
                "other_4index.html: 'https://apachecon.com/acasia2021/index.html',\n" +
                "other_4editor.html: 'https://ecomfe.github.io/echarts-examples/public/editor.html?c=candlestick-large',\n" +
                "other_3aria.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/en/best-practices/aria.md',\n" +
                "other_0bmap.min.js: 'https://cdn.jsdelivr.net/npm/echarts@4/dist/extension/bmap.min.js',\n" +
                "other_3rich-text.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/en/how-to/label/rich-text.md',\n" +
                "other_4ggrepel.html: 'https://cran.r-project.org/web/packages/ggrepel/ vignettes/ggrepel.html',\n" +
                "other_3wechat-app.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/zh/how-to/cross-platform/wechat-app.md',\n" +
                "other_4view-lite.html: 'https://gallery.echartsjs.com/view-lite.html?cid=xSyBN2i7Vf',\n" +
                "other_0scrollreveal.min.js: 'https://fastly.jsdelivr.net/npm/scrollreveal@4.0.7/dist/scrollreveal.min.js',\n" +
                "other_4view-lite.html: 'https://gallery.echartsjs.com/view-lite.html?cid=xS18jqmX4f',\n" +
                "other_4view-lite.html: 'https://gallery.echartsjs.com/view-lite.html?cid=xHySthj74z',\n" +
                "other_41094.html: 'https://apachecon.com/acasia2021/zh/sessions/1094.html',\n" +
                "other_0acorn.min.js: 'https://fastly.jsdelivr.net/npm/acorn@8.7.1/dist/acorn.min.js',\n" +
                "other_4editor.html: 'https://ecomfe.github.io/echarts-examples/public/editor.html?c=custom-gantt-flight',\n" +
                "other_0dark.js: 'https://fastly.jsdelivr.net/npm/echarts/theme/dark.js?_v_=20200710_1',\n" +
                "other_4view-lite.html: 'https://gallery.echartsjs.com/view-lite.html?cid=xHySlBkIEM&v=1',\n" +
                "other_0echarts-gl.min.js: 'https://cdn.jsdelivr.net/npm/echarts-gl@1/dist/echarts-gl.min.js',\n" +
                "other_3drag.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/en/how-to/interaction/drag.md',\n" +
                "other_3step-line.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/zh/how-to/chart-types/line/step-line.md',\n" +
                "other_4editor.html: 'https://ecomfe.github.io/echarts-examples/public/editor.html?c=lines-airline',\n" +
                "other_0roma.js: 'https://fastly.jsdelivr.net/npm/echarts/theme/roma.js?_v_=20200710_1',\n" +
                "other_41093.html: 'https://apachecon.com/acasia2021/sessions/1093.html',\n" +
                "other_41087.html: 'https://apachecon.com/acasia2021/zh/sessions/1087.html',\n" +
                "other_4explore.html: 'https://gallery.echartsjs.com/explore.html?u=bd-3056387051',\n" +
                "other_3event.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/zh/concepts/event.md',\n" +
                "other_3get-started.md: 'https://github.com/apache/echarts-handbook/tree/master/contents/en/get-started.md',\n" +
                "other_0ace.js: 'https://cdn.jsdelivr.net/npm/ace-builds@1.4.12/src-min-noconflict/ace.js',\n" +
                "other_4view-lite.html: 'https://gallery.echartsjs.com/view-lite.html?cid=xB1kG1rLEG&v=1'";

        List<String> adds=new ArrayList<>();
        String[] ts = text.split(",");
        List<HashMap<String,String>> maplist=new ArrayList<>();
        Arrays.stream(ts).parallel().forEach(t2->{
            String t = t2.trim().replace("\n", "");
            String url=t.substring(t.indexOf("'")+1,t .length()-1);
            String fs=t.substring(0,t.indexOf(":"));
            if (fs.indexOf(".")==-1){
                fs=fs+url.substring(url.lastIndexOf("."));
            }

            new File("cdnThirdParty").mkdirs();
            String print=String.format("reps.put(\"%s\",%s);",url,String.format("base+\"cdnThirdParty/%s\"",fs));
            HashMap<String,String> reps;
            HashMap<String,String> hmp=new HashMap<>();
            hmp.put("url",url);
            hmp.put("print",print);


            try {
                if (new File("cdnThirdParty/"+fs).exists()){
                    maplist.add(hmp);
                    return;
                }

              //  downloadFile(url,"cdnThirdParty/"+fs);
                //adds.add("cdnThirdParty/"+fs+","+url);
            } catch (Exception e) {
                //throw new RuntimeException(e);
                adds.add("cdnThirdParty/"+fs+","+url);
                e.printStackTrace();
            }

        });
        maplist.stream().sorted((h1,h2) -> {
            return h1.get("url").equals(h2.get("url"))?0:1;
        }).map(h->h.get("print")).sorted().forEach(System.out::println);

    }


    /**
     * 下载文件到本地
     *
     * @param urlString 被下载的文件地址
     * @param filename  本地文件名
     * @throws Exception 各种异常
     */
    public static void download(String urlString, String filename) throws Exception {
        System.out.println("dowload:"+urlString);
        // 构造URL
        URL url = new URL(urlString);
        // 打开连接
        URLConnection con = url.openConnection();
        con.setConnectTimeout(20000);
        // 输入流
        InputStream is = con.getInputStream();
        // 1K的数据缓冲
        byte[] bs = new byte[1024];
        // 读取到的数据长度
        int len;
        // 输出的文件流
        OutputStream os = new FileOutputStream(filename);
        // 开始读取
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        // 完毕，关闭所有链接
        os.close();
        is.close();
    }
    /**
     * 跳过ssl证书
     *
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    private static void trustAllHttpsCertificates()
            throws NoSuchAlgorithmException, KeyManagementException {
        TrustManager[] trustAllCerts = new TrustManager[1];
        trustAllCerts[0] = (TrustManager) new TrustAllManager();
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, null);
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    }
    //调用该类直接跳过ssl证书
    static {
        try {
            trustAllHttpsCertificates();
            HttpsURLConnection.setDefaultHostnameVerifier
                    (
                            (urlHostName, session) -> true
                    );
        } catch (Exception e) {
        }
    }
    public static <T> T getHttpRequestBase(Class<T> type, String path, CookieStore cs)
            throws InstantiationException, IllegalAccessException, URISyntaxException {
        String cookieStr = "";
        if (cs!=null) {
            List<Cookie> list = cs.getCookies();
            for (Cookie cookie : list) {
                cookieStr += cookie.getName() + "=" + cookie.getValue() + ";";
            }
        }

        HttpRequestBase base = (HttpRequestBase) type.newInstance();
        base.setURI(new URI(path));

        base.setHeader("Cookie", cookieStr);
        return (T) base;
    }
    private static TrustManager manager = new X509TrustManager() {

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            //

        }

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            //

        }
    };
    public static CloseableHttpClient createHttpClient() {
        return createHttpClient(DEFAULT_RETRY_TIMES, DEFAULT_SOCKETTIMEOUT);
    }
    private static final int DEFAULT_SOCKETTIMEOUT = 5000 * 100000000;// 默认等待响应时间(毫秒)
    private static final int DEFAULT_RETRY_TIMES = 0;// 默认执行重试的次数
    private static SSLConnectionSocketFactory socketFactory;
    private static void enableSSL() {
        try {
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new TrustManager[] { manager }, null);
            socketFactory = new SSLConnectionSocketFactory(context, NoopHostnameVerifier.INSTANCE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static CloseableHttpClient createHttpClient(int retryTimes, int socketTimeout) {
        RequestConfig.Builder builder = RequestConfig.custom();
        builder.setConnectTimeout(5000);// 设置连接超时时间，单位毫秒
        builder.setConnectionRequestTimeout(1000);// 设置从connect Manager获取Connection
        // 超时时间，单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的。
        builder.setSocketTimeout(socketTimeout);// 请求获取数据的超时时间，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用。
        RequestConfig defaultRequestConfig = builder.setCookieSpec(CookieSpecs.STANDARD_STRICT)
                .setExpectContinueEnabled(true)
                .setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
                .setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC)).build();
        // 开启HTTPS支持
        enableSSL();
        // 创建可用Scheme
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE).register("https", socketFactory).build();
        // 创建ConnectionManager，添加Connection配置信息
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(
                socketFactoryRegistry);
        HttpClientBuilder httpClientBuilder = HttpClients.custom();
        if (retryTimes > 0) {
            setRetryHandler(httpClientBuilder, retryTimes);
        }
        CloseableHttpClient httpClient = httpClientBuilder.setConnectionManager(connectionManager)
                .setDefaultRequestConfig(defaultRequestConfig).build();
        return httpClient;
    }
    private static void setRetryHandler(HttpClientBuilder httpClientBuilder, final int retryTimes) {
        HttpRequestRetryHandler myRetryHandler = new HttpRequestRetryHandler() {
            public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
                if (executionCount >= retryTimes) {
                    // Do not retry if over max retry count
                    return false;
                }
                if (exception instanceof InterruptedIOException) {
                    // Timeout
                    return false;
                }
                if (exception instanceof UnknownHostException) {
                    // Unknown host
                    return false;
                }
                if (exception instanceof ConnectTimeoutException) {
                    // Connection refused
                    return false;
                }
                if (exception instanceof SSLException) {
                    // SSL handshake exception
                    return false;
                }
                HttpClientContext clientContext = HttpClientContext.adapt(context);
                HttpRequest request = clientContext.getRequest();
                boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
                if (idempotent) {
                    // 如果请求被认为是幂等的，那么就重试
                    // Retry if the request is considered idempotent
                    return true;
                }
                return false;
            }
        };
        httpClientBuilder.setRetryHandler(myRetryHandler);
    }
    private static String getResult(CloseableHttpResponse httpResponse, String charset)
            throws ParseException, IOException {
        String result = null;
        if (httpResponse == null) {
            return result;
        }
        HttpEntity entity = httpResponse.getEntity();
        if (entity == null) {
            return result;
        }
        result = EntityUtils.toString(entity, charset);
        EntityUtils.consume(entity);// 关闭应该关闭的资源，适当的释放资源 ;也可以把底层的流给关闭了
        return result;
    }
    public static boolean downloadFile(String url,String target) throws IOException {
       return executeDownloadFile(null, null,url,
                new File(target), "utf-8", true);
    }
    public static boolean executeDownloadFile(CookieStore cs, CloseableHttpClient httpClient, String remoteFileUrl,
                                              File localFile, String charset, boolean closeHttpClient) throws ClientProtocolException, IOException {
        CloseableHttpResponse response = null;
        InputStream in = null;
        FileOutputStream fout = null;
        try {
            HttpGet httpget = getHttpRequestBase(HttpGet.class, remoteFileUrl, cs);
            ;
            if (httpClient == null) {
                httpClient = createHttpClient();
            }
            response = httpClient.execute(httpget);
            HttpEntity entity = response.getEntity();
            if (entity == null) {
                return false;
            }
            System.out.println("StatusCode is " + response.getStatusLine().getStatusCode());
/*            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException(getResult(response, charset));
            }*/
            in = entity.getContent();
            File file = localFile;
            fout = new FileOutputStream(file);
            int l = -1;
            byte[] tmp = new byte[1024];
            while ((l = in.read(tmp)) != -1) {
                fout.write(tmp, 0, l);
                // 注意这里如果用OutputStream.write(buff)的话，图片会失真
            }
            // 将文件输出到本地
            fout.flush();
            EntityUtils.consume(entity);
            return true;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        } finally {
            // 关闭低层流。
            if (fout != null) {
                try {
                    fout.close();
                } catch (Exception e) {
                }
            }
            if (response != null) {
                try {
                    response.close();
                } catch (Exception e) {
                }
            }
            if (closeHttpClient && httpClient != null) {
                try {
                    httpClient.close();
                } catch (Exception e) {
                }
            }
        }
    }



    private static class TrustAllManager implements X509TrustManager {
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
        public void checkServerTrusted(X509Certificate[] certs, String authType) {
        }
        public void checkClientTrusted(X509Certificate[] certs, String authType) {
        }
    }
}
