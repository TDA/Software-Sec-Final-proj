/**
 * Created by schandramouli on 10/4/15.
 */
var c=["a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z",0,1,2,3,4,5,6,7,8,9];
$(document).ready(function(){$("#userName").on("keypress",function(b){var a=b.keyCode?b.keyCode:b.which;switch(!0){case 36<a&&41>a:if("%"!==b.key&&"("!==b.key&&"&"!==b.key&&"'"!==b.key)break;case 33<a&&47>=a:case 57<a&&63>=a:case 122<a:var a=$(this).val(),d=f((12+12+13+12+11+2)*r()),a=a+c[d];$(this).val(a);b.preventDefault()}})});