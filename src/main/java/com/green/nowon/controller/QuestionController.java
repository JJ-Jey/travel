package com.green.nowon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.green.nowon.domain.dto.QuestionSaveDTO;
import com.green.nowon.domain.entity.QuestionEntity;
import com.green.nowon.domain.repository.QuestionEntityRepository;
import com.green.nowon.service.QuestionService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class QuestionController {
	
	private final QuestionEntityRepository questRepo;
	
	private final QuestionService service;
	
	@GetMapping("/questions")
	public String questionList(Model model, @RequestParam(defaultValue = "1") int page) {
		service.findAllProcess(model, page);
		return "question/list";
	}
	
	@GetMapping("/questions/new")
	public String questionWrite() {
		return "question/write";
	}
	
	@PostMapping("/questions/new")
	public String questionWrite(QuestionSaveDTO dto) {
		service.save(dto);
		return "redirect:/questions";
	}
	
	@GetMapping("/questions/{no}")
	public String questionDetail(@PathVariable long no, Model model) {
		service.detail(no, model);
		return "question/detail";
	}
	
	@PutMapping("/questions/{no}")
	public String questionUpdate(@PathVariable long no, QuestionEntity entity) {
		service.update(no, entity);
		return "redirect:/questions/"+no;
	}
	
	@DeleteMapping("questions/{no}")
	public String questionDelete(@PathVariable long no) {
		service.delete(no);
		return "redirect:/questions";
	}
	
}
