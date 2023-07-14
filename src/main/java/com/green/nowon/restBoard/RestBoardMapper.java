package com.green.nowon.restBoard;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

@Mapper
public interface RestBoardMapper {

	@Select("select * from question order by no desc")
	List<com.green.nowon.restBoard.BoardSearchDTO> findAll(RowBounds rowBounds);
	
	@Select("select count(*) from question")
	int coutAll();

	List<com.green.nowon.restBoard.BoardSearchDTO> findAllBySearch(BoardSearchDTO dto, RowBounds rowBounds);

	int coutAllBySearch(BoardSearchDTO dto);

}
