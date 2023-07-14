package com.green.nowon.restBoard;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController // @Controller+@ResponseBody
public class RestBoardController {
	private final RestBoardService service;
	/*
	@GetMapping("/rest-boards")
	public ResponseEntity<ModelAndView> restBoard() {
		return ResponseEntity.ok().body(new ModelAndView("rest/page"));
	}
	*/
	//*
	@GetMapping("/rest-boards/page")
	public ModelAndView restBoard() {
		return new ModelAndView("rest/page");
	}
	//*/
	
	@PatchMapping("/rest-boards")
	public ModelAndView restList(@RequestParam(defaultValue = "1") int page) {
		return service.restListProcess(page);
	}
	
	@PatchMapping("/rest-boards/search")
	public ModelAndView restSearchList(BoardSearchDTO dto) {
		return service.restListProcess(dto);
	}
	
	
}
