package com.green.nowon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.green.nowon.service.AlertService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AlertController {
	
	private final AlertService service;
	
	@GetMapping("/alert")
	public String alert(Model model) throws Exception {
		service.findAll(model);
		return "alert/list";
	}

}
