var charts=[];$(document).ready(function(){var a=$("#left-chart-nav ul"),t=location.pathname.match(/demo.html/);for(var e in CHART_TYPES)a.append($("<li>").append('<a class="left-chart-nav-link" id="left-chart-nav-'+e+'" href="'+(t?"examples.html":"")+"#chart-type-"+e+'"><div class="chart-icon"></div><div class="chart-name">'+CHART_TYPES[e]+"</div></a>"));$(".chart-area").height($(".chart-area").width()/5*4);var h=null,i=null;$(window).scroll(function(){var a=-$(".chart-list-panel")[0].getBoundingClientRect().top,t=parseInt($("#chart-type-scatter h3").css("margin-top"),10);if(!h){var e=$(".chart-list-panel").children(),r=0;h=[];for(var l=0;l<e.length;++l)r+=$($(".chart-list-panel").children()[l]).height()+t,h.push({height:r,id:$($(".chart-list-panel").children()[l]).attr("id").slice("chart-type-".length)})}for(l=0;l<h.length;++l)if(h[l].height>a){i!==h[l].id&&($("#left-chart-nav li").removeClass("active"),$("#left-chart-nav-"+h[l].id).parent().addClass("active"),i=h[l].id);break}})});
