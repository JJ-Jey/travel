/**
 * 
 */

var menuList;
var contentList;

/*$(document).ready(function(){})*/
$(function(){
	menuList=$(".menu-box > .menu");
	contentList=$(".content-box > .content");

	menuList.click(menuClicked);	
});

//클릭하면 > 모든 li 태그의 view 클래스를 지우고 > 클릭된 li 태그에 view 클래스 부여
function menuClicked(){
	var idx=$(this).index(); //클릭된 요소의 index: 0,1,2
	
	//메뉴 변경
	menuList.removeClass("view");
	//this: 클릭된 태그
	$(this).addClass("view");
	
	//내용 변경
	contentList.removeClass("view");
	contentList.eq(idx).addClass("view");
}























