package com.green.nowon.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.green.nowon.domain.entity.PromotionEntity;
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
	
	@GetMapping("/admin/promotions")
	public String adminFlight(Model model) {
		service.findAll(model);
		return "admin/promotion/list";
	}
	
	@GetMapping("/admin/promotions/{pno}")
	public String adminFlightDetail(@PathVariable long pno, Model model) {
		service.detailProcess(pno, model);
		return "admin/promotion/detail";
	}
	/*
	 * @GetMapping("/admin/visuals") public String visualManagemanet() { return
	 * "admin/visual/main"; }
	 */
	
	@PutMapping("/admin/promotions/{pno}")
	public String promotionUpdate(@PathVariable long pno, @Param("PromotionEntity") PromotionEntity dto) {
		service.update(pno, dto);
		return "redirect:/admin/promotions/" + pno;
	}
	
	@PatchMapping("/admin/promotions/{pno}")
	public String promotionDelete(@PathVariable long pno) {
		service.delete(pno);
		return "redirect:/admin/promotions";
	}
	
}
