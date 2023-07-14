/**
 * 
 */
function goodsSummited(){
	var data=$("#form-goods").serialize();
	
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});
	
	console.log(data);
	$.ajax({
		url:"/admin/visuals/img",
		type:"post",
		data: data,
		success: function(result){
			$("a[href='/admin/goods/new']").trigger("click");
		}
	});
	
}
 
 function tempUpload(fileEl){
	 console.log($(fileEl));
	 var fileData=$(fileEl)[0].files[0];
	 var formData = new FormData();
	 formData.append("temp", fileData);
	 
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});
	 $.ajax({
		 url:"/admin/visuals/temp-img",
		 type:"POST",
		 contentType: false,
		 processData: false,
		 data: formData,
		 success: function(resultMap){
			 $(fileEl).parent().css("background-image",`url(${resultMap.imgUrl})`);
			 $(fileEl).parents(".img-wrap").find(".orgName").val(resultMap.orgName);
			 $(fileEl).parents(".img-wrap").find(".newName").val(resultMap.newName);
			 $(fileEl).parents(".img-wrap").find(".tempKey").val(resultMap.tempKey);
			 
			 var def=$(fileEl).parents(".img-wrap").find(".def").val();
			 if(def=="true")return;//대표이미지이면 함수종료
			 
			 //추가이미지일떄 태그추가	
			 var addLength=$(".add-img>.img-wrap").length;
			 console.log("추가이미지 개수:"+addLength);
			 var targetIdx=$(fileEl).parents(".img-wrap").index()+1;
			 console.log("수정하는 이미지 위치:"+ targetIdx);
			 //추가하면 안되는경우 
			 //1.태그가 5개가 완료된경우
			 //2. 5개만들기전 이전이미지를 수정하면
			 if(addLength >= 5 || targetIdx < addLength)return;
			 
			 var appendTag=`
			 <div class="img-wrap">
				<label class="pre-img"><input type="file" onchange="tempUpload(this)"></label>
				<input type="hidden" class="tempKey" name="tempKey">
				<input type="hidden" class="orgName" name="orgName">
				<input type="hidden" class="newName" name="newName">
				<input type="hidden" class="def" name="def" value="false">
			 </div>
			 `;
			 $(".add-img").append(appendTag);
			 console.log("태그추가됨~");
			
		 }
	  });
 }