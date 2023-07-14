/**
 * 
 */

const PRODUCT_MAX = 5;// 상품이미지 등록할 최대개수

$(function() {
	//input type=file 인 태그가 이미지가 변경되었을때 
	//tempUpload 함수 실행
	//$(":file").change(tempUpload);//처음로딩시에 존재하는 file태그에만 적용됩니다.
	$('#summernote').summernote();
	$("#temp-imgs-wrap").append(createTag(0));

	/* 
	ajax_req('/test-fail','get').done(function(data){
		console.log("test성공시 실행됩니다.");
	});
	ajax_req('/test2','get').done(function(data){
		console.log(data+",test성공시 실행됩니다.");
	});
	ajax_req('/test2','post').done(function(data,status,xhr){
		console.log(data+",test성공시 실행됩니다.");
		var headers = xhr.getAllResponseHeaders();
	   
		var statusCode = status;
		console.log(">>>"+headers);
		console.log(">>>"+statusCode);
	});
	 */
});



function tempUpload(el) {//tag에서 넘긴 this
	var target = $(el);
	var formData = new FormData();
	//var file=$(this).files[0];//선택한 파일
	formData.append("tempFile", target[0].files[0]);
	formData.append("tempKey", target.siblings(".temp-key").val());//필요없으면 제거하기위해 필요

	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});

	//ajax 로 서버에 요청하는 방법
	$.ajax({
		url: "/admin/visuals/temp-img",//서버에 요청하는 주소
		type: "POST", //요청방법
		data: formData,
		contentType: false,//파일전송시 설정
		processData: false,//파일전송시 설정
		//성공시 실행하는 메서드
		success: function(map) {
			//target==input태그 와 같은공간에있는태그들중 label태그 
			var url = map.s3TempUrl;
			target.siblings("label").css("background-image", `url(${url})`);
			target.siblings(".temp-key").val(map.tempKey);
			//alert(target.siblings(".temp-key").val());
			//추가 이미지 삽입 태그생성
			var len = $("#temp-imgs-wrap :file").length;
			console.log("len:" + len);
			//console.log("idx:"+target.parent().index());
			//3개까지만 허용 또는 수정시에는 추가생성하지 않아요
			if (len == PRODUCT_MAX || target.parent().index() < len - 1) return;
			target.parent().after(createTag(len));
		},
		error: function(error) {
			console.log("실패!")
		}
	});//ajax end

}//tempUpload()

//함수 호출시 동적으로 return에 정의된 span태그가 구성됨
function createTag(i) {
	return `
			<span>
				<label for="img-${i}" class="img-area">+</label>
				<input type="file" id="img-${i}" name="imgs" onchange="tempUpload(this)" >
				<input type="hidden" class="temp-key" name="tempKey">
			</span>
		`;
}

function ajax_req(url, type) {
	return $.ajax({
		url: url,
		type: type
	})
		.always(function() {
			console.log(url + ">>항상 실행됩니다.");
		})
		.fail(function() {
			console.log(url + ">>실패시 실행됩니다.");
		});
}

//서버에 전송전에 입력데이터가 정상인지 확인하고 비정상이면 메세지
function checkOk() {
	//var title=document.getElementById("title").value;
	//var title=$.trim($("#title").val());//앞뒤쓸데없는 공백제거한 문자
	//$.trim( 문자열 );--> 문자열 앞뒤 공백제거
	// js str.trim() : str은 문자열변수 
	var msg;
	var title = $("#title").val();
	if (title.trim() == "") {
		msg = "상품명을 입렵해주세요.";
		$("#msg").text(msg)
		return false;
	}
	var price = $("#price").val();
	if (price.trim() == "") {
		msg = "가격을 입력해주세요.";
		$("#msg").text(msg)
		return false;
	}
	var content = $("#summernote").val();
	if (content.trim() == "") {
		msg = "상세정보를 입력해주세요.";
		$("#msg").text(msg)
		return false;
	}

	var fileLength = $("#temp-imgs-wrap :file")[0].files.length;
	if (fileLength == 0) {
		msg = "하나 이상의 이미지를 등록해야 합니다.";
		$("#msg").text(msg)
		return false;
	}

	//return true;
}