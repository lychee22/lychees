/**
 * Minified by jsDelivr using UglifyJS v3.0.24.
 * Original file: /npm/code-prettify@0.1.0/src/prettify.js
 * 
 * Do NOT use SRI with dynamically generated files! More information: https://www.jsdelivr.com/using-sri-with-dynamic-files
 */
var DecorationsT,JobT,SourceSpansT,IN_GLOBAL_SCOPE=!1,HACK_TO_FIX_JS_INCLUDE_PL,PR;window.PR_SHOULD_USE_CONTINUATION=!0;var prettyPrintOne,prettyPrint;!function(){function e(e){function n(e){var n=e.charCodeAt(0);if(92!==n)return n;var t=e.charAt(1);return(n=u[t])||("0"<=t&&t<="7"?parseInt(e.substring(1),8):"u"===t||"x"===t?parseInt(e.substring(2),16):e.charCodeAt(1))}function t(e){if(e<32)return(e<16?"\\x0":"\\x")+e.toString(16);var n=String.fromCharCode(e);return"\\"===n||"-"===n||"]"===n||"^"===n?"\\"+n:n}function r(e){var r=e.substring(1,e.length-1).match(new RegExp("\\\\u[0-9A-Fa-f]{4}|\\\\x[0-9A-Fa-f]{2}|\\\\[0-3][0-7]{0,2}|\\\\[0-7]{1,2}|\\\\[\\s\\S]|-|[^-\\\\]","g")),a=[],s="^"===r[0],l=["["];s&&l.push("^");for(var i=s?1:0,o=r.length;i<o;++i){var u=r[i];if(/\\[bdsw]/i.test(u))l.push(u);else{var c,d=n(u);i+2<o&&"-"===r[i+1]?(c=n(r[i+2]),i+=2):c=d,a.push([d,c]),c<65||d>122||(c<65||d>90||a.push([32|Math.max(65,d),32|Math.min(c,90)]),c<97||d>122||a.push([-33&Math.max(97,d),-33&Math.min(c,122)]))}}a.sort(function(e,n){return e[0]-n[0]||n[1]-e[1]});for(var p=[],f=[],i=0;i<a.length;++i)(g=a[i])[0]<=f[1]+1?f[1]=Math.max(f[1],g[1]):p.push(f=g);for(i=0;i<p.length;++i){var g=p[i];l.push(t(g[0])),g[1]>g[0]&&(g[1]+1>g[0]&&l.push("-"),l.push(t(g[1])))}return l.push("]"),l.join("")}for(var a=0,s=!1,l=!1,i=0,o=e.length;i<o;++i)if((d=e[i]).ignoreCase)l=!0;else if(/[a-z]/i.test(d.source.replace(/\\u[0-9a-f]{4}|\\x[0-9a-f]{2}|\\[^ux]/gi,""))){s=!0,l=!1;break}for(var u={b:8,t:9,n:10,v:11,f:12,r:13},c=[],i=0,o=e.length;i<o;++i){var d=e[i];if(d.global||d.multiline)throw new Error(""+d);c.push("(?:"+function(e){for(var n=e.source.match(new RegExp("(?:\\[(?:[^\\x5C\\x5D]|\\\\[\\s\\S])*\\]|\\\\u[A-Fa-f0-9]{4}|\\\\x[A-Fa-f0-9]{2}|\\\\[0-9]+|\\\\[^ux0-9]|\\(\\?[:!=]|[\\(\\)\\^]|[^\\x5B\\x5C\\(\\)\\^]+)","g")),l=n.length,i=[],o=0,u=0;o<l;++o)"("===(d=n[o])?++u:"\\"===d.charAt(0)&&(c=+d.substring(1))&&(c<=u?i[c]=-1:n[o]=t(c));for(o=1;o<i.length;++o)-1===i[o]&&(i[o]=++a);for(var o=0,u=0;o<l;++o)if("("===(d=n[o]))i[++u]||(n[o]="(?:");else if("\\"===d.charAt(0)){var c=+d.substring(1);c&&c<=u&&(n[o]="\\"+i[c])}for(o=0;o<l;++o)"^"===n[o]&&"^"!==n[o+1]&&(n[o]="");if(e.ignoreCase&&s)for(o=0;o<l;++o){var d=n[o],p=d.charAt(0);d.length>=2&&"["===p?n[o]=r(d):"\\"!==p&&(n[o]=d.replace(/[a-zA-Z]/g,function(e){var n=e.charCodeAt(0);return"["+String.fromCharCode(-33&n,32|n)+"]"}))}return n.join("")}(d)+")")}return new RegExp(c.join("|"),l?"gi":"g")}function n(e,n){function t(e){var o=e.nodeType;if(1==o){if(r.test(e.className))return;for(var u=e.firstChild;u;u=u.nextSibling)t(u);var c=e.nodeName.toLowerCase();"br"!==c&&"li"!==c||(a[i]="\n",l[i<<1]=s++,l[i++<<1|1]=e)}else if(3==o||4==o){var d=e.nodeValue;d.length&&(d=n?d.replace(/\r\n?/g,"\n"):d.replace(/[ \t\r\n]+/g," "),a[i]=d,l[i<<1]=s,s+=d.length,l[i++<<1|1]=e)}}var r=/(?:^|\s)nocode(?:\s|$)/,a=[],s=0,l=[],i=0;return t(e),{sourceCode:a.join("").replace(/\n$/,""),spans:l}}function t(e,n,t,r,a){if(t){var s={sourceNode:e,pre:1,langExtension:null,numberLines:null,sourceCode:t,spans:null,basePos:n,decorations:null};r(s),a.push.apply(a,s.decorations)}}function r(e){for(var n=void 0,t=e.firstChild;t;t=t.nextSibling){var r=t.nodeType;n=1===r?n?e:t:3===r&&O.test(t.nodeValue)?e:n}return n===e?void 0:n}function a(n,r){var a,s={};!function(){for(var t=n.concat(r),l=[],i={},o=0,u=t.length;o<u;++o){var c=t[o],d=c[3];if(d)for(var p=d.length;--p>=0;)s[d.charAt(p)]=c;var f=c[1],g=""+f;i.hasOwnProperty(g)||(l.push(f),i[g]=null)}l.push(/[\0-\uffff]/),a=e(l)}();var l=r.length,i=function(e){for(var n=e.sourceCode,o=e.basePos,c=e.sourceNode,d=[o,R],p=0,f=n.match(a)||[],g={},h=0,m=f.length;h<m;++h){var v,y=f[h],b=g[y],x=void 0;if("string"==typeof b)v=!1;else{var w=s[y.charAt(0)];if(w)x=y.match(w[1]),b=w[0];else{for(var S=0;S<l;++S)if(w=r[S],x=y.match(w[1])){b=w[0];break}x||(b=R)}!(v=b.length>=5&&"lang-"===b.substring(0,5))||x&&"string"==typeof x[1]||(v=!1,b=k),v||(g[y]=b)}var C=p;if(p+=y.length,v){var N=x[1],_=y.indexOf(N),P=_+N.length;x[2]&&(_=(P=y.length-x[2].length)-N.length);var E=b.substring(5);t(c,o+C,y.substring(0,_),i,d),t(c,o+C+_,N,u(E,N),d),t(c,o+C+P,y.substring(P),i,d)}else d.push(o+C,b)}e.decorations=d};return i}function s(e){var n=[],t=[];e.tripleQuotedStrings?n.push([_,/^(?:\'\'\'(?:[^\'\\]|\\[\s\S]|\'{1,2}(?=[^\']))*(?:\'\'\'|$)|\"\"\"(?:[^\"\\]|\\[\s\S]|\"{1,2}(?=[^\"]))*(?:\"\"\"|$)|\'(?:[^\\\']|\\[\s\S])*(?:\'|$)|\"(?:[^\\\"]|\\[\s\S])*(?:\"|$))/,null,"'\""]):e.multiLineStrings?n.push([_,/^(?:\'(?:[^\\\']|\\[\s\S])*(?:\'|$)|\"(?:[^\\\"]|\\[\s\S])*(?:\"|$)|\`(?:[^\\\`]|\\[\s\S])*(?:\`|$))/,null,"'\"`"]):n.push([_,/^(?:\'(?:[^\\\'\r\n]|\\.)*(?:\'|$)|\"(?:[^\\\"\r\n]|\\.)*(?:\"|$))/,null,"\"'"]),e.verbatimStrings&&t.push([_,/^@\"(?:[^\"]|\"\")*(?:\"|$)/,null]);var r=e.hashComments;r&&(e.cStyleComments?(r>1?n.push([E,/^#(?:##(?:[^#]|#(?!##))*(?:###|$)|.*)/,null,"#"]):n.push([E,/^#(?:(?:define|e(?:l|nd)if|else|error|ifn?def|include|line|pragma|undef|warning)\b|[^\r\n]*)/,null,"#"]),t.push([_,/^<(?:(?:(?:\.\.\/)*|\/?)(?:[\w-]+(?:\/[\w-]+)+)?[\w-]+\.h(?:h|pp|\+\+)?|[a-z]\w*)>/,null])):n.push([E,/^#[^\r\n]*/,null,"#"])),e.cStyleComments&&(t.push([E,/^\/\/[^\r\n]*/,null]),t.push([E,/^\/\*[\s\S]*?(?:\*\/|$)/,null]));var s=e.regexLiterals;if(s){var l=s>1?"":"\n\r",i=l?".":"[\\S\\s]",o="/(?=[^/*"+l+"])(?:[^/\\x5B\\x5C"+l+"]|\\x5C"+i+"|\\x5B(?:[^\\x5C\\x5D"+l+"]|\\x5C"+i+")*(?:\\x5D|$))+/";t.push(["lang-regex",RegExp("^"+$+"("+o+")")])}var u=e.types;u&&t.push([L,u]);var c=(""+e.keywords).replace(/^ | $/g,"");c.length&&t.push([P,new RegExp("^(?:"+c.replace(/[\s,]+/g,"|")+")\\b"),null]),n.push([R,/^\s+/,null," \r\n\t "]);var d="^.[^\\s\\w.$@'\"`/\\\\]*";return e.regexLiterals&&(d+="(?!s*/)"),t.push([A,/^@[a-z_$][a-z_$@0-9]*/i,null],[L,/^(?:[@_]?[A-Z]+[a-z][A-Za-z_$@0-9]*|\w+_t\b)/,null],[R,/^[a-z_$][a-z_$@0-9]*/i,null],[A,new RegExp("^(?:0x[a-f0-9]+|(?:\\d(?:_\\d+)*\\d*(?:\\.\\d*)?|\\.\\d\\+)(?:e[+\\-]?\\d+)?)[a-z]*","i"),null,"0123456789"],[R,/^\\[\s\S]?/,null],[T,new RegExp(d),null]),a(n,t)}function l(e,n,t){function r(e){var n=e.nodeType;if(1!=n||s.test(e.className)){if((3==n||4==n)&&t){var o=e.nodeValue,u=o.match(l);if(u){var c=o.substring(0,u.index);e.nodeValue=c;var d=o.substring(u.index+u[0].length);d&&e.parentNode.insertBefore(i.createTextNode(d),e.nextSibling),a(e),c||e.parentNode.removeChild(e)}}}else if("br"===e.nodeName)a(e),e.parentNode&&e.parentNode.removeChild(e);else for(var p=e.firstChild;p;p=p.nextSibling)r(p)}function a(e){function n(e,t){var r=t?e.cloneNode(!1):e,a=e.parentNode;if(a){var s=n(a,1),l=e.nextSibling;s.appendChild(r);for(var i=l;i;i=l)l=i.nextSibling,s.appendChild(i)}return r}for(;!e.nextSibling;)if(!(e=e.parentNode))return;for(var t,r=n(e.nextSibling,0);(t=r.parentNode)&&1===t.nodeType;)r=t;u.push(r)}for(var s=/(?:^|\s)nocode(?:\s|$)/,l=/\r\n?|\n/,i=e.ownerDocument,o=i.createElement("li");e.firstChild;)o.appendChild(e.firstChild);for(var u=[o],c=0;c<u.length;++c)r(u[c]);n===(0|n)&&u[0].setAttribute("value",n);var d=i.createElement("ol");d.className="linenums";for(var p=Math.max(0,n-1|0)||0,c=0,f=u.length;c<f;++c)(o=u[c]).className="L"+(c+p)%10,o.firstChild||o.appendChild(i.createTextNode(" ")),d.appendChild(o);e.appendChild(d)}function i(e){var n=/\bMSIE\s(\d+)/.exec(navigator.userAgent);n=n&&+n[1]<=8;var t=/\n/g,r=e.sourceCode,a=r.length,s=0,l=e.spans,i=l.length,o=0,u=e.decorations,c=u.length,d=0;u[c]=a;var p,f;for(f=p=0;f<c;)u[f]!==u[f+2]?(u[p++]=u[f++],u[p++]=u[f++]):f+=2;for(c=p,f=p=0;f<c;){for(var g=u[f],h=u[f+1],m=f+2;m+2<=c&&u[m+1]===h;)m+=2;u[p++]=g,u[p++]=h,f=m}c=u.length=p;var v=e.sourceNode,y="";v&&(y=v.style.display,v.style.display="none");try{for(;o<i;){l[o];var b,x=l[o+2]||a,w=u[d+2]||a,m=Math.min(x,w),S=l[o+1];if(1!==S.nodeType&&(b=r.substring(s,m))){n&&(b=b.replace(t,"\r")),S.nodeValue=b;var C=S.ownerDocument,N=C.createElement("span");N.className=u[d+1];var _=S.parentNode;_.replaceChild(N,S),N.appendChild(S),s<x&&(l[o+1]=S=C.createTextNode(r.substring(m,x)),_.insertBefore(S,N.nextSibling))}(s=m)>=x&&(o+=2),s>=w&&(d+=2)}}finally{v&&(v.style.display=y)}}function o(e,n){for(var t=n.length;--t>=0;){var r=n[t];I.hasOwnProperty(r)?f.console&&console.warn("cannot override language handler %s",r):I[r]=e}}function u(e,n){return e&&I.hasOwnProperty(e)||(e=/^\s*</.test(n)?"default-markup":"default-code"),I[e]}function c(e){var t=e.langExtension;try{var r=n(e.sourceNode,e.pre),a=r.sourceCode;e.sourceCode=a,e.spans=r.spans,e.basePos=0,u(t,a)(e),i(e)}catch(e){f.console&&console.log(e&&e.stack||e)}}function d(e,n,t){var r=t||!1,a=n||null,s=document.createElement("div");return s.innerHTML="<pre>"+e+"</pre>",s=s.firstChild,r&&l(s,r,!0),c({langExtension:a,numberLines:r,sourceNode:s,pre:1,sourceCode:null,basePos:null,spans:null,decorations:null}),s.innerHTML}function p(e,n){function t(e){return s.getElementsByTagName(e)}function a(){for(var n=f.PR_SHOULD_USE_CONTINUATION?h.now()+250:1/0;m<u.length&&h.now()<n;m++){for(var t=u[m],s=C,o=t;o=o.previousSibling;){var d=o.nodeType,p=(7===d||8===d)&&o.nodeValue;if(p?!/^\??prettify\b/.test(p):3!==d||/\S/.test(o.nodeValue))break;if(p){s={},p.replace(/\b(\w+)=([\w:.%+-]+)/g,function(e,n,t){s[n]=t});break}}var g=t.className;if((s!==C||y.test(g))&&!b.test(g)){for(var N=!1,_=t.parentNode;_;_=_.parentNode){var P=_.tagName;if(S.test(P)&&_.className&&y.test(_.className)){N=!0;break}}if(!N){t.className+=" prettyprinted";var E=s.lang;if(!E){var L;!(E=g.match(v))&&(L=r(t))&&w.test(L.tagName)&&(E=L.className.match(v)),E&&(E=E[1])}var A;if(x.test(t.tagName))A=1;else{var T=t.currentStyle,R=i.defaultView,k=T?T.whiteSpace:R&&R.getComputedStyle?R.getComputedStyle(t,null).getPropertyValue("white-space"):0;A=k&&"pre"===k.substring(0,3)}var $=s.linenums;($="true"===$||+$)||($=!!($=g.match(/\blinenums\b(?::(\d+))?/))&&(!$[1]||!$[1].length||+$[1])),$&&l(t,$,A),c({langExtension:E,sourceNode:t,numberLines:$,pre:A,sourceCode:null,basePos:null,spans:null,decorations:null})}}}m<u.length?f.setTimeout(a,250):"function"==typeof e&&e()}for(var s=n||document.body,i=s.ownerDocument||document,o=[t("pre"),t("code"),t("xmp")],u=[],d=0;d<o.length;++d)for(var p=0,g=o[d].length;p<g;++p)u.push(o[d][p]);o=null;var h=Date;h.now||(h={now:function(){return+new Date}});var m=0,v=/\blang(?:uage)?-([\w.]+)(?!\S)/,y=/\bprettyprint\b/,b=/\bprettyprinted\b/,x=/pre|xmp/i,w=/^code$/i,S=/^(?:pre|code|xmp)$/i,C={};a()}var f=window,g=["break,continue,do,else,for,if,return,while"],h=[[g,"auto,case,char,const,default,double,enum,extern,float,goto,inline,int,long,register,restrict,short,signed,sizeof,static,struct,switch,typedef,union,unsigned,void,volatile"],"catch,class,delete,false,import,new,operator,private,protected,public,this,throw,true,try,typeof"],m=[h,"alignas,alignof,align_union,asm,axiom,bool,concept,concept_map,const_cast,constexpr,decltype,delegate,dynamic_cast,explicit,export,friend,generic,late_check,mutable,namespace,noexcept,noreturn,nullptr,property,reinterpret_cast,static_assert,static_cast,template,typeid,typename,using,virtual,where"],v=[h,"abstract,assert,boolean,byte,extends,finally,final,implements,import,instanceof,interface,null,native,package,strictfp,super,synchronized,throws,transient"],y=[h,"abstract,add,alias,as,ascending,async,await,base,bool,by,byte,checked,decimal,delegate,descending,dynamic,event,finally,fixed,foreach,from,get,global,group,implicit,in,interface,internal,into,is,join,let,lock,null,object,out,override,orderby,params,partial,readonly,ref,remove,sbyte,sealed,select,set,stackalloc,string,select,uint,ulong,unchecked,unsafe,ushort,value,var,virtual,where,yield"],b=[h,"abstract,async,await,constructor,debugger,enum,eval,export,function,get,implements,instanceof,interface,let,null,set,undefined,var,with,yield,Infinity,NaN"],x="caller,delete,die,do,dump,elsif,eval,exit,foreach,for,goto,if,import,last,local,my,next,no,our,print,package,redo,require,sub,undef,unless,until,use,wantarray,while,BEGIN,END",w=[g,"and,as,assert,class,def,del,elif,except,exec,finally,from,global,import,in,is,lambda,nonlocal,not,or,pass,print,raise,try,with,yield,False,True,None"],S=[g,"alias,and,begin,case,class,def,defined,elsif,end,ensure,false,in,module,next,nil,not,or,redo,rescue,retry,self,super,then,true,undef,unless,until,when,yield,BEGIN,END"],C=[g,"case,done,elif,esac,eval,fi,function,in,local,set,then,until"],N=/^(DIR|FILE|array|vector|(de|priority_)?queue|(forward_)?list|stack|(const_)?(reverse_)?iterator|(unordered_)?(multi)?(set|map)|bitset|u?(int|float)\d*)\b/,_="str",P="kwd",E="com",L="typ",A="lit",T="pun",R="pln",k="src",$="(?:^^\\.?|[+-]|[!=]=?=?|\\#|%=?|&&?=?|\\(|\\*=?|[+\\-]=|->|\\/=?|::?|<<?=?|>>?>?=?|,|;|\\?|@|\\[|~|{|\\^\\^?=?|\\|\\|?=?|break|case|continue|delete|do|else|finally|instanceof|return|throw|try|typeof)\\s*",O=/\S/,I={};o(s({keywords:[m,y,v,b,x,w,S,C],hashComments:!0,cStyleComments:!0,multiLineStrings:!0,regexLiterals:!0}),["default-code"]),o(a([],[[R,/^[^<?]+/],["dec",/^<!\w[^>]*(?:>|$)/],[E,/^<\!--[\s\S]*?(?:-\->|$)/],["lang-",/^<\?([\s\S]+?)(?:\?>|$)/],["lang-",/^<%([\s\S]+?)(?:%>|$)/],[T,/^(?:<[%?]|[%?]>)/],["lang-",/^<xmp\b[^>]*>([\s\S]+?)<\/xmp\b[^>]*>/i],["lang-js",/^<script\b[^>]*>([\s\S]*?)(<\/script\b[^>]*>)/i],["lang-css",/^<style\b[^>]*>([\s\S]*?)(<\/style\b[^>]*>)/i],["lang-in.tag",/^(<\/?[a-z][^<>]*>)/i]]),["default-markup","htm","html","mxml","xhtml","xml","xsl"]),o(a([[R,/^[\s]+/,null," \t\r\n"],["atv",/^(?:\"[^\"]*\"?|\'[^\']*\'?)/,null,"\"'"]],[["tag",/^^<\/?[a-z](?:[\w.:-]*\w)?|\/?>$/i],["atn",/^(?!style[\s=]|on)[a-z](?:[\w:-]*\w)?/i],["lang-uq.val",/^=\s*([^>\'\"\s]*(?:[^>\'\"\s\/]|\/(?=\s)))/],[T,/^[=<>\/]+/],["lang-js",/^on\w+\s*=\s*\"([^\"]+)\"/i],["lang-js",/^on\w+\s*=\s*\'([^\']+)\'/i],["lang-js",/^on\w+\s*=\s*([^\"\'>\s]+)/i],["lang-css",/^style\s*=\s*\"([^\"]+)\"/i],["lang-css",/^style\s*=\s*\'([^\']+)\'/i],["lang-css",/^style\s*=\s*([^\"\'>\s]+)/i]]),["in.tag"]),o(a([],[["atv",/^[\s\S]+/]]),["uq.val"]),o(s({keywords:m,hashComments:!0,cStyleComments:!0,types:N}),["c","cc","cpp","cxx","cyc","m"]),o(s({keywords:"null,true,false"}),["json"]),o(s({keywords:y,hashComments:!0,cStyleComments:!0,verbatimStrings:!0,types:N}),["cs"]),o(s({keywords:v,cStyleComments:!0}),["java"]),o(s({keywords:C,hashComments:!0,multiLineStrings:!0}),["bash","bsh","csh","sh"]),o(s({keywords:w,hashComments:!0,multiLineStrings:!0,tripleQuotedStrings:!0}),["cv","py","python"]),o(s({keywords:x,hashComments:!0,multiLineStrings:!0,regexLiterals:2}),["perl","pl","pm"]),o(s({keywords:S,hashComments:!0,multiLineStrings:!0,regexLiterals:!0}),["rb","ruby"]),o(s({keywords:b,cStyleComments:!0,regexLiterals:!0}),["javascript","js","ts","typescript"]),o(s({keywords:"all,and,by,catch,class,else,extends,false,finally,for,if,in,is,isnt,loop,new,no,not,null,of,off,on,or,return,super,then,throw,true,try,unless,until,when,while,yes",hashComments:3,cStyleComments:!0,multilineStrings:!0,tripleQuotedStrings:!0,regexLiterals:!0}),["coffee"]),o(a([],[[_,/^[\s\S]+/]]),["regex"]);var D=f.PR={createSimpleLexer:a,registerLangHandler:o,sourceDecorator:s,PR_ATTRIB_NAME:"atn",PR_ATTRIB_VALUE:"atv",PR_COMMENT:E,PR_DECLARATION:"dec",PR_KEYWORD:P,PR_LITERAL:A,PR_NOCODE:"nocode",PR_PLAIN:R,PR_PUNCTUATION:T,PR_SOURCE:k,PR_STRING:_,PR_TAG:"tag",PR_TYPE:L,prettyPrintOne:IN_GLOBAL_SCOPE?f.prettyPrintOne=d:prettyPrintOne=d,prettyPrint:prettyPrint=IN_GLOBAL_SCOPE?f.prettyPrint=p:prettyPrint=p},z=f.define;"function"==typeof z&&z.amd&&z("google-code-prettify",[],function(){return D})}();
//# sourceMappingURL=/sm/f98c4282a5ef8f39d4c48db6f3324fac10cd794bb82ddc5f4adc53020ba9b7ba.map