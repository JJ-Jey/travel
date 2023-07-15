package com.green.nowon.controller;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.green.nowon.domain.dto.PromotionSaveDTO;
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
	public String adminFlight(@RequestParam(defaultValue = "1") int page, Model model) {
		service.listJoinProcess(page, model);
		//service.findAll(model);
		return "admin/promotion/list";
	}
	
	@GetMapping("/admin/promotions/new")
	public String promotionRegi() {
		return "admin/promotion/registration";
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
	
	@PostMapping("/admin/promotions")
	public String promotionSave(PromotionSaveDTO dto) {
		service.promotionSaveProcess(dto);
		return "redirect:/admin/promotions";
	}
	
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
	
	@ResponseBody //return 타입없이 응답하는 데이터: 성공시 js 데이터 형식(json)으로 자동으로 변환해서 응답 >> success 함수의 파라미터로
	//void 뿐만아니라 모든 데이터 타입이 올 수 있음
	@PostMapping("/products/temp-upload")
	//js에서 설정한 파일 이름: tempFile 로 파라미터를 받아와야 mapping 가능
	public Map<String, String> tempUpload(MultipartFile tempFile, String tempKey) {
		//System.out.println("tempKey: "+tempKey); //tempKey 초기값은 "" 비어있는 문자열
		//업로드된 서버 주소(현재는 s3의 경로), 파일 이름 등 필요
		
		return service.tempUploadProcess(tempFile);
	}
}
