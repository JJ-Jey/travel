package com.green.nowon.websocket;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class WebSocketController {
	
	private final RabbitTemplate rabbitTemplate;
	
	@Value("${spring.rabbitmq.template.exchange}")
	private String exchange;
	
	@MessageMapping("/sendMsg") // /app/sendMsg
	//@SendTo("/topic/msg") //구독하는 url에 이벤트 발생
	public void sendMsg(MyMessage message) {
		rabbitTemplate.convertAndSend(exchange, "green.nowon.msg", message);
	}

	@MessageMapping("/hello") // /app/hello
	//@SendTo("/topic/msg")
	public void helloMsg(MyMessage message) {
		rabbitTemplate.convertAndSend(exchange, "green.nowon.hello", message);
	}
	
}
