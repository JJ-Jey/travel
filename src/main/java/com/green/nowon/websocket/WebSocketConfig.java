package com.green.nowon.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker //메시지 브로커가 지원하는 websocket 메시지 처리를 활성화
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	//메모리 기반 메시지 브로커가 접두사가 붙은 목적지에서 클라이언트에게 인사말 메시지를 다시 전달할 수 있도록 호출하는 것
  @Override
  public void configureMessageBroker(MessageBrokerRegistry config) {
	//서버 -> 클라이언트(웹, 구독자들): 보내는 메시지 브로커 >> 수신자(구독자)
    config.enableSimpleBroker("/topic", "/my-topic"); //여러개 등록 가능
    
    //클라이언트(웹) -> 서버: 메시지 전송시 요청하는 uri의 prefix >> 발행자
    config.setApplicationDestinationPrefixes("/app"); //uri: //ws://localhost:8080/app/uri
  }

  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
	//js: new SockJS('/my-ws') 주소 일치시키기 >> 접속할 때 필요
    registry.addEndpoint("/my-ws").withSockJS();
  }

}