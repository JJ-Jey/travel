/**
 * 카테고리가 가변적일 때 사용하는 JS
 */

//카테고리 분류 개수
const CATE_EA = 4;

$(function(){
	createCategoryTag(CATE_EA);
})

function createCategoryTag(depth){
	for(var i=1; i<=depth; i++){
		/*가변 클래스도 생성 가능*/
		/*마지막 태그일 때는 ''(빈 문자열): onchange 이벤트 실행 X*/
		var tag = `
			<span class="cate-wrap">
				<select name="categoryNo" class="cate-${i}" ${i==depth?'':'onchange="category(this)"'}>
					<option value="0">${i}차 카테고리</option>
				</select>
			</span>
		`;
		
		$("#category").append(tag);
	}
	
	/*1차 카테고리는 페이지 로딩시 바로 띄움*/
	//순서를 위해 createCategoryTag 안으로 넣음
	category($(".cate-1"));
}

/*javaScript 기본 로직: 목표 태그 읽음 > result를 더함 > 목표 태그에 덮어씌움*/
function category(selectTag){
	
	var cateNo = $(selectTag).val();

	var cateWrap = $(selectTag).parents(".cate-wrap");
	
	var i = cateWrap.index(); //i == 0 이면 1차 카테고리
	
	var target = (i==0 && cateNo==0)?cateWrap:cateWrap.next();
	
	if(i>0 && cateNo == 0) return; //1차 카테고리가 아니면서 value=0이면 함수 종료
	
	/*비동기 처리 ajax. 현재는 get 요청이므로 $.get으로도 사용할 수 있음. 하지만 생략된 표현이 많아서 더 어려울 가능성 존재.*/
	/*서버에 전송하는 것이기 때문에 기본적으로 url, success가 필요하다고 볼 수 있음*/
	$.ajax({
		url:`/common/category-select/${cateNo}`,
		/*성공했을 때 처리: 필수는 아님*/
		success:function(result){
			//기존 하위 카테고리를 삭제한 후 출력하기 위함
			//삭제하지 않으면 하위 카테고리 select에 누적되어서 출력됨
			//target.find(".cate").remove();
			//하위 카테고리 전부를 리셋하기 위해 함수 생성
			targetNextAllTagReset(target);

			target.find("select").append(result); //result를 #cate-1 하위 태그로 삽입
		},
		/*실패했을 때 처리: 필수는 아님*/
		error:function(){
			console.log("비동기 처리 실패");
		}
	});
	
}

function targetNextAllTagReset(target){
	var ti = target.index();
	
	//$(".cate-wrap").each(function(){});
	//javaScript 람다식: 위 식과 기능이 거의 같음
	$(".cate-wrap").each((i, el) => {
		if(i>=ti){
			$(el).find(".cate").remove();
		}
	});
}
