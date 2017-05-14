function slideTogle(id){
	$("#"+id).slideToggle('fast');
}
function slideUp(id){
	 var tx = $(this).html();
	 var tv = $(this).attr('alt');
	 $("#"+id).slideUp('fast');
	 $(".order_list span").html(tx);
	 $(".order_text").html(tv);
}