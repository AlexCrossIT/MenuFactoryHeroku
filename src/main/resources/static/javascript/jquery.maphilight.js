(function(b){var g=!!document.createElement("canvas").getContext;var h=function(){var a=document.createElement("div");a.innerHTML='<v:shape id="vml_flag1" adj="1" />';a=a.firstChild;a.style.behavior="url(#default#VML)";return a?"object"==typeof a.v:!0}();if(g||h){var m=g?function(a){a=b('<canvas style="width:'+a.width+"px;height:"+a.height+'px;"></canvas>').get(0);a.getContext("2d").clearRect(0,0,a.width,a.height);return a}:function(a){return b('<var style="zoom:1;overflow:hidden;display:block;width:'+
a.width+"px;height:"+a.height+'px;"></var>').get(0)};var n=function(a){return!a.complete||"undefined"!=typeof a.naturalWidth&&0===a.naturalWidth?!1:!0};var k={position:"absolute",left:0,top:0,padding:0,border:0};var l=!1;b.i.h=function(a){a=b.extend({},b.i.h.m,a);g||l||(b(window).ready(function(){document.namespaces.add("v","urn:schemas-microsoft-com:vml");var c=document.createStyleSheet();b.o("shape rect oval circ fill stroke imagedata group textbox".split(" "),function(){c.addRule("v\\:"+this,"behavior: url(#default#VML); antialias:true")})}),
l=!0);return this.o(function(){var c;var d=b(this);if(!n(this))return window.setTimeout(function(){d.h(a)},200);var e=b.extend({},a,b.s?d.s():!1,d.data("maphilight"));if(c=d.get(0).getAttribute("usemap")){var f=b('map[name="'+c.substr(1)+'"]');d.is('img,input[type="image"]')&&c&&0<f.size()&&(d.G("maphilighted")&&(c=d.parent(),d.insertBefore(c),c.remove(),b(f).u(".maphilight").find("area[coords]").u(".maphilight")),c=b("<div></div>").g({display:"block",background:'url("'+this.src+'")',position:"relative",
padding:0,width:this.width,height:this.height}),e.j&&(!0===e.j?c.l(b(this).B("class")):c.l(e.j)),d.before(c).g("opacity",0).g(k).remove(),h&&d.g("filter","Alpha(opacity=0)"),c.append(d),e=m(this),b(e).g(k),e.height=this.height,e.width=this.width,b(f),b(f).P("alwaysOn.maphilight").find("area[coords]"),d.before(e),d.l("maphilighted"))}})};b.i.h.m={fill:!0,fillColor:"000000",D:.2,stroke:!0,strokeColor:"ff0000",strokeOpacity:1,strokeWidth:1,C:!0,A:!1,H:!1,F:!1,j:!0,I:!1,N:0,O:0,M:6,shadowColor:"000000",
K:.8,L:"outside",J:!1}}else b.i.h=function(){return this}})(jQuery);