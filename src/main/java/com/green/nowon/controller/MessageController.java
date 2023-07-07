package com.green.nowon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MessageController {
	
	@GetMapping("/message")
	public String message() {
		return "message/communication";
	}

}
