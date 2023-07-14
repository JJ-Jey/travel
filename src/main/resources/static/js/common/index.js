/**
 * 
 */
 $(function(){
	 goodsListAll();
	 $("#gnb .gnb-icon").hover(gnbHover,gnbHoverOut);
 });
 
 
 function gnbHover(){
	 var check=$("#gnb-list ul").hasClass("cate-wrap");
	 if(check){$("#gnb-list").children(".cate-wrap").stop().slideDown(200);return;}
	 $.ajax({
		 url:"/category/0",
		 success:function(result){
			 $("#gnb-list").html(result);
			 $("#gnb-list").children(".cate-wrap").stop().slideDown(200);
		 }
	 })
 }
 function gnbHoverOut(){
	 $("#gnb-list").children(".cate-wrap").stop().hide(0);
 }
 function cateHover(liTag){
	 var parentNo=$(liTag).attr("value");
	 var check=$(liTag).find("ul").hasClass("cate-wrap");
	 if(check){$(liTag).children(".sub").children(".cate-wrap").stop().slideDown(200);return;}
	 $.ajax({
		 url:`/category/${parentNo}`,
		 success:function(result){
			 $(liTag).children(".sub").html(result);
			 $(liTag).children(".sub").children(".cate-wrap").stop().slideDown(200);
		 }
	 });
 }
 function cateHoverOut(liTag){
	 $(liTag).children(".sub").children(".cate-wrap").stop().hide(0);
 }

 /*비동기*/
 function goodsListAll(){
	 $.ajax({
		 url:"/goods",
		 success: function(result){
			 $("#goods>.wrap").html(result);
		 }
	 });
 }
 
 
 function goodsListOfCategoryNo(aTag){
	
	$.ajax({
		url:$(aTag).attr("href"),
		success:function(result){
			$("#goods>.wrap").html(result);
		}
	});
 }