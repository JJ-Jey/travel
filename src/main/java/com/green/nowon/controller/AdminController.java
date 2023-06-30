package com.green.nowon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
	
	@GetMapping("/admin")
	public String admin(Model model) {
		return "admin/list";
	}
	
	@GetMapping("/admin/flight")
	public String adminFlight() {
		return "admin/flight";
	}
	
	@GetMapping("/admin/event")
	public String adminEvent() {
		return "admin/event";
	}
	

}
