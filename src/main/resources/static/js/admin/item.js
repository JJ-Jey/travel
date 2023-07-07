/**
 * 
 */

/*i 태그(동적 태그) 최대 개수*/
const I_TAG = 3;

$(function(){
	createTag();
})

/*collection or array mapping: 이미지가 여러 개일 때 객체 단위로 매핑하기*/
function imgSend(){
	var bks = $(".bucket-key");
	var ons = $(".org-name");
	var nns = $(".new-name");
	
	var dto = [];
	
	for(var i=0; i<bks.length; i++){
		dto[i] = {
			bucketKey : bks[i],
			orgName : ons[i],
			newName : nns[i]
		}
	}
	
	/*ajax 비동기로 여러 이미지 매핑하기 위해서는 dto를 배열로 받아와야 함*/
	/*controller에서 responsebody, repuestbody 설정해야함*/

	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	
	$.ajax({
		beforeSend:function(xhr) {
			xhr.setRequestHeader(header, token);
	},
	url: "/admin/item/test",
	type: "post",
	data: JSON.stringify(dto)
	})
	
}

/*동적 태그 생성*/
function createTag(){
	var tag = `
		<i>
			<label class="img-wrap">
				<span>+</span>
				<input type="file" onchange="tempUpload(this)">
			</label>
			<input type="hidden" class="bucket-key" name="bucketKey">
			<input type="hidden" class="org-name" name="orgName">
			<input type="hidden" class="new-name" name="newName">
		</i>
		`
	
	$("#img-area").append(tag);
}

/*비동기 이미지 업로드: 상품등록 페이지에서 이미지 선택시 보여주기 위한 로직*/
function tempUpload(fileTag){
	
	/*여기서의 this와 아래 success function에서의 this는 다름: fileTag vs map*/
	var target = $(fileTag);

	/*저장할 파일 정보: list 형태이므로.. 그냥 외우기*/
	var img = target[0].files[0];
	
	var formData = new FormData();
	
	/*AdminController의 파라미터와 일치해야 함: MultipartFile tempImg*/
	formData.append("tempImg", img);
	
	/*csrf 토큰 가져오기*/
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	
	$.ajax({
		/*beforeSend: 비동기 요청이 보내지기 전에 실행 >> csrf 적용 위해*/
		beforeSend:function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		url:"/admin/item/temp",
		type:"post",
		data: formData,
		/*binary로 보내기 위해 문자열로 보내기 해제*/
		contentType: false,
		processData: false,
		success: function(map){
			/*여기서의 this와 위 tempUpload function에서의 this는 다름: fileTag vs map*/
			console.log("이미지경로: " + map.url);
			target.parents(".img-wrap").css("background-image", `url(${map.url})`);
			target.parents(".img-wrap").siblings(".bucket-key").val(map.bucketKey);
			target.parents(".img-wrap").siblings(".org-name").val(map.orgName);
			target.parents(".img-wrap").siblings(".new-name").val(map.newName);
			
			//추가 이미지 삽입하기 위한 태그 생성
			//1. 최대 몇 개의 이미지를 추가 삽입할지
			/*추가 삽입된 i태그의 종 개수*/
			var iTagEa = $("#img-area > i").length;
			console.log("tot: "+iTagEa);
			
			/*현재 i태그의 포지션: 0부터 시작하므로 1을 더해줌*/
			/*index(): 부모 기준으로 내가 몇 번째인지 확인 가능 >> p태그 내부의 i태그의 인덱스*/
			var pos = target.parents("i").index()+1;
			console.log("pos: "+pos);
			
			/*if(iTagEa == I_TAG) return; 아래와 같은 로직*/
			if(iTagEa < I_TAG && (iTagEa-pos) <= 0) createTag(); /*위와 같은 로직*/
			
			//2. 중간 이미지 수정시 추가 태그 생성되지 않게 하기
			
		}
	})
}