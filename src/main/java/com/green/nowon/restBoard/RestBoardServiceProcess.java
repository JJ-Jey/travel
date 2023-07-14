package com.green.nowon.restBoard;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.green.nowon.util.PageData;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RestBoardServiceProcess implements RestBoardService {

	private final RestBoardMapper mapper;
	
	@Override
	public ModelAndView restListProcess(int page) {
		ModelAndView mv=new ModelAndView("rest/list");
		int limit=5;
		RowBounds rowBounds=new RowBounds((page-1)*limit, limit);
		mv.addObject("list", mapper.findAll(rowBounds));
		mv.addObject("pd", PageData.create(page, limit, mapper.coutAll(), 5));
		return mv;
	}

	@Override
	public ModelAndView restListProcess(BoardSearchDTO dto) {
		ModelAndView mv=new ModelAndView("rest/list");
		int limit=5;
		RowBounds rowBounds=new RowBounds((dto.getPage()-1)*limit, limit);
		
		mv.addObject("list", mapper.findAllBySearch(dto,rowBounds));
		
		mv.addObject("pd", PageData.create(dto.getPage(), limit, mapper.coutAllBySearch(dto), 5));
		return mv;
	}

}
