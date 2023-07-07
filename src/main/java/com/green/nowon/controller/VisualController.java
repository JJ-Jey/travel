package com.green.nowon.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.green.nowon.domain.dto.ItemSaveDTO;
import com.green.nowon.service.FileUploadService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class VisualController {
	
	private final FileUploadService fileService;

	/*
	 * @ResponseBody
	 * 
	 * @GetMapping("/admin/visuals") public ModelAndView write() { return new
	 * ModelAndView("admin/visual/write"); }
	 */
	
	@GetMapping("/admin/visuals")
	public String write() {
		return "admin/visual/main";
	}
	
	@ResponseBody
	@PostMapping("/admin/visuals/temp-img")
	public Map<String, String> tempUpload(MultipartFile temp){
		return fileService.tempUploadProcess(temp);
	}
	
	@PostMapping("/admin/visuals/img")
	public String save(ItemSaveDTO dto) {
		fileService.saveProcess(dto);
		return "";
	}

}
