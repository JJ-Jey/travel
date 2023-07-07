package com.green.nowon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.green.nowon.service.AdminService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AdminController {
	
	private final AdminService service;
	
	@GetMapping("/admin")
	public String admin(Model model) {
		return "admin/main";
	}
	
	@GetMapping("/admin/flights")
	public String adminFlight(Model model) {
		service.findAll(model);
		return "admin/flight/list";
	}
	
	@GetMapping("/admin/flights/{pno}")
	public String adminFlightDetail(@PathVariable long pno, Model model) {
		service.detailProcess(pno, model);
		return "admin/flight/detail";
	}
	/*
	 * @GetMapping("/admin/visuals") public String visualManagemanet() { return
	 * "admin/visual/main"; }
	 */
}
