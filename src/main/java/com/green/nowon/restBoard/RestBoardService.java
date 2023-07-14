package com.green.nowon.restBoard;

import org.springframework.web.servlet.ModelAndView;

public interface RestBoardService {

	ModelAndView restListProcess(int page);

	ModelAndView restListProcess(BoardSearchDTO dto);

}
