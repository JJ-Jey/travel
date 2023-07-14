/**
 * 
 */

var otherCheckbox;

var msg;
var in_txt;
var target;


$(function(){
	$("#btn-mailsend").click(mailsendClicked);
	$("#btn-authCheck").click(authChecked);
				
	var birth = $('input[name=year]').val() + $('input[name=month]').val() + $('input[name=day]').val();
	$('input[name=birth]').val(birth);
	
	$("#allagr").click(checkAllClicked); //'전체동의' 체크박스
	
	//'전체동의'를 제외하고 나머지 동의가 체크되면 otherCheckboxClicked 함수 실행
	otherCheckbox=$(".terms :checkbox").not("#allagr"); //'전체동의'를 제외한 나머지 체크박스
	otherCheckbox.click(otherCheckboxClicked);
	
	$("#email").blur(emailCheck);
	$("#password-f").blur(passwordFCheck);
	$("#password-s").blur(passwordSCheck);
	$("#name").blur(nameCheck);
	
	//$("#email").keyup(emailKeyup);
	
});

//유효성 검사: 이메일 중복 체크
function emailKeyup(){
	var in_email = document.getElementById('email').value.trim();
	
	//email 형식은 최소 다섯 글자(n@n.n)이므로 6글자 이상일 때만 체크하겠다는 의미
	if(in_email.length < 6)return;
	
	//target=$(this).parents(".input-box").find(".msg").text(msg);
	
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});
	var target = $(this).parents(".input-box").find(".msg").text(msg);
	//서버-DB로 접근: 비동기식 >> 페이지 전체를 로딩하는 것이 아니라 필요한 데이터만 불러오는 것
	$.ajax({
		url: "/sign/email-check", //요청
		type: "post", //method
		data: {email:in_email},
		//비동기 요청이 정상적으로 처리되면 success 함수 실행
		success: function(result){ //응답이 성공하면 함수 실행
			//target.val(result);
			alert(result);
		}
	});
}

function nameCheck(){
	var regName=/^[가-힣]{2,10}$/;
	in_txt=$(this).val().trim();
	target=$(this).parents(".input-box").find(".msg").text(msg);
	
	if(in_txt==""){
		msg="이릅을 입력하세요.";
		target.css("color", "red");
	} else if(regName.test(in_txt)){
		msg="이름 형식과 일치합니다.";
		target.css("color", "green");
	} else{
		msg="이름 형식과 일치하지 않습니다.";
		target.css("color", "red");
	}
	target.text(msg).show();
}

function passwordSCheck(){
	
	var passCheck=$(this).parents(".input-box").find("#password-f").text();
	in_txt=$(this).val().trim();
	var target2=$(this).parents(".input-box").find(".msg").text(msg);
	
	if(in_txt==passCheck){
		msg="비밀번호가 일치합니다.";
		target2.css("color", "green");
	} else{
		msg="비밀번호가 일치하지 않습니다.";
		target2.css("color", "red");
		alert(msg);
	}
	target2.text(msg).show();
}

function passwordFCheck(){
	var regPass=/^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$/;
	in_txt=$(this).val().trim();
	var target1=$(this).parents(".input-box").find(".msg").text(msg);

	if(in_txt==""){
		msg="비밀번호를 입력하세요.";
		target1.css("color", "red");
	} else if(regPass.test(in_txt)){
		msg="비밀번호 형식이 맞습니다.";
		target1.css("color", "green");
	} else{
		msg="비밀번호 형식이 아닙니다.";
		target1.css("color", "red");
	}
	
	target1.text(msg).show();
}

function emailCheck(){
	//이메일 형식
	var regEmail = /^[\da-zA-Z]([-_.]?[\da-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,4}$/i; // \d = 0-9
	
	in_txt=$(this).val().trim();
	target=$(this).parents(".input-box").find(".msg").text(msg);
	
	if(in_txt==""){
		msg="이메일을 입력하세요.";
		target.css("color","red");
	} else if(regEmail.test(in_txt)){
		msg="이메일 형식이 맞습니다.";
		target.css("color","green");
	} else{
		msg="이메일 형식이 아닙니다.";
		target.css("color","red");
	}
	
	//input(id="email")의 부모 중 class="input-box" 에서 class="msg"인 자식 요소 내부에 var msg의 문자열 적용
	
	
	target.text(msg).show();
}

function checkAllClicked(){
	var status=$(this).is(":checked");
	if(status==true){
		//모든 체크박스 체크상태로 변경
		otherCheckbox.prop("checked", true);
	} else{
		//모든 체크박스 체크 해제
		otherCheckbox.prop("checked", false);		
	}
}

function otherCheckboxClicked(){
	//체크박스의 체크 여부 확인 가능: true or false
	var status=$(this).is(":checked");
	
	//하나라도 꺼지면 전체동의 false
	if(status==false){
		$("#allagr").prop("checked", false);
	} else{
		//'전체동의'를 제외한 나머지 중 체크상태인 박스의 개수
		var checkedEa=$(".terms :checked").not("#allagr").length;
		
		//가 전체 체크박스 개수와 동일하면 '전체동의' true
		if(checkedEa==otherCheckbox.length)$("#allagr").prop("checked", true);
	}
}

			var code;
			
function mailsendClicked(){
				//보낼 메일 주소
				//javaScript: 
				//var email = document.getElementById("emailCheck").value();
				//아래는 jquery
				var email = $("#emailCheck").val();
				
				var token = $("meta[name='_csrf']").attr("content");
				var header = $("meta[name='_csrf_header']").attr("content");
				$(document).ajaxSend(function(e, xhr, options) {
					xhr.setRequestHeader(header, token);
				});
				
				$.ajax({
					url: "/email",
					type: "post",
					data: {toEmail : email}, //key : value >> controller mapping과 맞춰야함
					success: function(authCode){ //임시로(f12에 나옴).. 원래는 메일 들어가서 확인해야 함
						code = authCode;
						$("#btn-check").show();
					}
				})
}
			
function authChecked(){
				var inCode = $("#in-code").val().trim();
				var msg = (code == inCode) ? "인증완료" : "인증실패" ;
				alert(msg);
}