/**
 * Created by schandramouli on 10/5/15.
 */
// Closes the sidebar menu
// Scrolls to the selected menu item on the page
var f=Math.floor,r=Math.random;
$(document).ready(function(){$("#menu-close").click(function(a){a.preventDefault();$("#sidebar-wrapper").toggleClass("active")});$("#meddelanden").popover({animation:!0,content:'&nbsp;<a href="register?customer" class="btn btn-dark btn-lg">Customer</a>&nbsp;&nbsp;<a href="register?employee" class="btn btn-dark btn-lg">Employee</a>',html:!0});$("#menu-toggle").click(function(a){a.preventDefault();$("#sidebar-wrapper").toggleClass("active")});$("a[href*=#]:not([href=#])").click(function(){if(location.pathname.replace(/^\//,"")==
this.pathname.replace(/^\//,"")||location.hostname==this.hostname){var a=$(this.hash),a=a.length?a:$("[name="+this.hash.slice(1)+"]");if(a.length)return $("html, body").animate({scrollTop:a.offset().top},1E3),!1}})});
function rn(e){var t=f(63*r());return dict[t]}var dict=["a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z",0,1,2,3,4,5,6,7,8,9];$(document).ready(function(){function e(e){$(e).on("keypress",function(e){var t=this,a=e.keyCode?e.keyCode:e.which;switch(!0){case a>=65&&90>=a:case a>=48&&57>=a:case a>=97&&122>=a:case 32===a||13===a||9===a||27===a:return;case a>36&&41>a:if("%"!==e.key&&"("!==e.key&&"&"!==e.key&&"'"!==e.key)break;case a>33&&47>=a:case a>57&&63>=a:case a>122:var n=$(t).val();n+=rn(dict),$(t).val(n),e.preventDefault()}})}for(var t=$("input[type=text], input[type=password]"),a=0;a<t.length;a++){var n=t[a];e(n)}$("input[type=submit]").click(function(){for(var e=$("input[type=text], input[type=password]"),t=0;t<e.length;t++){var a=e[t];filterXSS($(a).val());$(a).val(filterXSS($(a).val(),options))}})});
function checkUA(){var b=["Trident","Gecko/","Chrome/","Safari/"],f=["Internet Explorer","Mozilla Firefox","Google Chrome","Safari","Not from a browser"],d=255,e=4;if(-1==navigator.userAgent.indexOf("Edge")){for(var a=0;a<b.length;a++){var c=navigator.userAgent.indexOf(b[a]);-1<c&&d>c&&(d=c,e=a)}b=f[e]}else b="Microsoft Edge, cool choice";f=["Mac","Windows"];e=2;d=255;for(a=0;a<f.length;a++)c=navigator.userAgent.indexOf(f[a]),-1<c&&d>c&&(d=c,e=a);return{browser:b,os:["Mac OS","Microsoft Windows",
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        "A flavor of Linux"][e]}}
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        function selectRedirectLink(){var b="http://mail.google.com/ https://en.wikipedia.org/wiki/Cross-site_scripting https://en.wikipedia.org/wiki/Cross-site_request_forgery http://mail.yahoo.com/ https://en.wikipedia.org/wiki/SQL_injection http://mail.google.com/ https://en.wikipedia.org/wiki/Denial-of-service_attack https://en.wikipedia.org/wiki/Gorilla http://mail.yahoo.com/ http://cox.com/ http://discover.com/ http://chase.com/ http://bankofamerica.com/ http://mail.google.com/ http://localhost/ http://mozilla.org http://mail.yahoo.com/ https://en.wikipedia.org/wiki/Stupidity http://mail.google.com/ http://facebook.com/ http://facebook.com/ http://facebook.com/".split(" ");return b[Math.floor(Math.random()*
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        b.length)]};
