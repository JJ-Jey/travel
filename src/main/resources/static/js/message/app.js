/**
 * 
 */
var stompClient = null;

function sendMessage(){
	//var sendMessage = document.getElementById("message").value;
	var sendMessage = $.trim($("#message").val());
	if(sendMessage==""){
		alert('메시지가 입력되지 않았어요');
		$("#message").val(""); //input 박스 리셋
		return;
	}
	
	//key : value 형식으로 된 JSON 객체
	var data = {
		name : $("#name").val(),
		content : sendMessage
	};
	
	//메시지 전달
	//stringify: JSON 형식을 문자열로 나열하기(데이터 전송을 위해)
	stompClient.send("/app/sendMsg",{},JSON.stringify(data))
}

function connect(){
	//socket 접속
	var socket = new SockJS("/my-ws");
	stompClient = Stomp.over(socket);
	
	//stomp 객체를 통해 접속
	stompClient.connect({},function(frame){
		console.log("Connected: " + frame);
		
		var data = {
			name : "hello",
			content : $("#name").val() + "님 입장하셨습니다."
		};
		
		stompClient.send("/app/hello",{},JSON.stringify(data));
		
		//메시지 수신을 위한 구독 설정: 구독(subscribe)할 브로커 >> 수신 준비
		stompClient.subscribe('/topic/msg',function(msgData){
			//자바의 메시지 객체 -> JSON(name : value)
			//name: 자바에서 필드명
			//value: 실제 메시지 내용
			//msg 는 head와 body로 구성 -> body(name, content) 필드를 가져오기 위함
			var msg = JSON.parse(msgData.body);
			
			var me = $("#name").val();
			
			var msgText = `
				<tr>
					<td class="msg-left ${msg.name == me ? `me` : ``}">
						${msg.name != "hello" ? `<div>${msg.name}</div>` : ``}
						<div class="name">${msg.content}</div>
					</td>
				</tr>
				`;
			
			$("#greetings").append(msgText);
			
		})
	});
	
	connectMode(true);
};


function disconnect(){
	//socket close: stomp 객체 close를 통해 접속 해제
	if(stompClient != null){
		stompClient.disconnect();
	}
	console.log(">>> Disconnected")
	
	connectMode(false);
}

//버튼 스위칭 디자인
function connectMode(isTrue){
	$("#connect").prop("disabled", isTrue);
	$("#disconnect").prop("disabled", !isTrue);
	$("#name").prop("disabled", isTrue);
}

$(function(){
	/* 모든 form 태그의 submit action을 비활성화: refresh를 하지 않고 비동기 처리하기 위함 */
	$("form").on('submit',function(e){
		/* 해당 태그의 기본 동작을 실행하지 않음: form, a 태그에 주로 사용 */
		e.preventDefault();
	})
	/* 위와 같은 식
	$("form").submit(function(e){
		e.preventDefault();
	})
	*/
	
	$("#connect").click(connect);
	$("#disconnect").click(disconnect);
	$("#send").click(sendMessage);
})