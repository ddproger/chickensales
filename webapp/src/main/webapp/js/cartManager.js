// возвращает cookie если есть или undefined

function getCookie(name) {
	var cookie = " " + document.cookie;
	var search = " " + name + "=";
	var setStr = null;
	var offset = 0;
	var end = 0;
	if (cookie.length > 0) {
		offset = cookie.indexOf(search);
		if (offset != -1) {
			offset += search.length;
			end = cookie.indexOf(";", offset)
			if (end == -1) {
				end = cookie.length;
			}
			setStr = unescape(cookie.substring(offset, end));
		}
	}
	return(setStr);
}
// уcтанавливает cookie
function setCookie (name, value, expires, path, domain, secure) {
    document.cookie = name + "=" + escape(value) +
      ((expires) ? "; expires=" + expires : "") +
      ((path) ? "; path=" + path : "") +
      ((domain) ? "; domain=" + domain : "") +
      ((secure) ? "; secure" : "");
}
// удаляет cookie
function deleteCookie(name) {	
	setCookie(name, "", "Mon, 01-Jan-2010 00:00:00 GMT","/");
}

function addProduct() {
	var countEl = document.getElementById("Count");
	var cartEl = document.getElementById("cart");
	var costEl = document.getElementById("cost");
	var cost = costEl.innerHTML;
	var index = costEl.getAttribute('index');
	var count = getCookie("product"+index);
	var countInCart = getCookie("countProduct");
	if(countInCart=="NaN")countInCart=0;
	if(count=="NaN")count=0;
	if(count!=null)
		{
			setCookie('countProduct', parseInt(countInCart)+parseInt(countEl.value), "Mon, 01-Jan-2021 00:00:00 GMT", "/");
			count=parseInt(count)+parseInt(countEl.value);
			setCookie('product'+index, count, "Mon, 01-Jan-2021 00:00:00 GMT", "/");
			$result = parseInt(countInCart)+parseInt(countEl.value);
			cartEl.innerHTML="<span>В корзине: "+$result+" товаров</span>";
		}
		else {		
			setCookie('countProduct', parseInt(countInCart)+parseInt(countEl.value), "Mon, 01-Jan-2021 00:00:00 GMT", "/");
			setCookie('product'+index,parseInt(countInCart)+parseInt(countEl.value), "Mon, 01-Jan-2021 00:00:00 GMT", "/");
			$result = parseInt(countInCart)+parseInt(countEl.value);
			cartEl.innerHTML="<span>В корзине: "+$result+" товаров</span>";
		}

}
function deleteItem(id){
	var count = getCookie("product"+id);
	var countInCart = getCookie("countProduct");
	setCookie('countProduct', parseInt(countInCart)-parseInt(count), "Mon, 01-Jan-2021 00:00:00 GMT", "/");
	deleteCookie("product"+id);
	document.getElementById(id).style.visibility = "hidden";
}
var el = document.getElementById("addProduct");
el.addEventListener("click", addProduct, false);
