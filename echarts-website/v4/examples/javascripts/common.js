function closeApacheBanner(e){document.getElementById("apache-banner").remove(),e&&(_hmt.push(["_trackEvent","apacheBanner","close"]),Cookies.set("apache-banner-closed","true",{expires:7}))}function logApache(){_hmt.push(["_trackEvent","apacheBanner","visit"])}$(document).ready(function(){"echarts.apache.org"!==location.host&&-1===location.pathname.indexOf("editor.html")&&-1===location.pathname.indexOf("view.html")&&(document.getElementById("apache-banner").style.display="block","true"===Cookies.get("apache-banner-closed")&&closeApacheBanner(!1))}),function(){function i(){for(var e=0,n={};e<arguments.length;e++){var t,o=arguments[e];for(t in o)n[t]=o[t]}return n}function u(e){return e.replace(/(%[0-9A-Z]{2})+/g,decodeURIComponent)}(function e(p){function a(){}function t(e,n,t){if("undefined"!=typeof document){"number"==typeof(t=i({path:"/"},a.defaults,t)).expires&&(t.expires=new Date(+new Date+864e5*t.expires)),t.expires=t.expires?t.expires.toUTCString():"";try{var o=JSON.stringify(n);/^[\{\[]/.test(o)&&(n=o)}catch(e){}n=p.write?p.write(n,e):encodeURIComponent(String(n)).replace(/%(23|24|26|2B|3A|3C|3E|3D|2F|3F|40|5B|5D|5E|60|7B|7D|7C)/g,decodeURIComponent),e=encodeURIComponent(String(e)).replace(/%(23|24|26|2B|5E|60|7C)/g,decodeURIComponent).replace(/[\(\)]/g,escape);var r,c="";for(r in t)t[r]&&(c+="; "+r,!0!==t[r]&&(c+="="+t[r].split(";")[0]));return document.cookie=e+"="+n+c}}function n(e,n){if("undefined"!=typeof document){for(var t={},o=document.cookie?document.cookie.split("; "):[],r=0;r<o.length;r++){var c=o[r].split("="),a=c.slice(1).join("=");n||'"'!==a.charAt(0)||(a=a.slice(1,-1));try{var i=u(c[0]),a=(p.read||p)(a,i)||u(a);if(n)try{a=JSON.parse(a)}catch(e){}if(t[i]=a,e===i)break}catch(e){}}return e?t[e]:t}}return(window.Cookies=a).set=t,a.get=function(e){return n(e,!1)},a.getJSON=function(e){return n(e,!0)},a.remove=function(e,n){t(e,"",i(n,{expires:-1}))},a.defaults={},a.withConverter=e,a})(function(){})}();
