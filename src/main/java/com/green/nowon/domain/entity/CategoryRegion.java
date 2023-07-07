package com.green.nowon.domain.entity;

import javax.persistence.Id;
import javax.persistence.ManyToOne;

//@Entity
public class CategoryRegion {
	
	//ManyToMany 사용하는 대신 연계 테이블을 따로 만들어서 M:N 관계 설정 가능
	//pk를 설정해야 테이블이 만들어짐
	@Id
	private long no;
	
	@ManyToOne
	private RegionEntity region;
	
	@ManyToOne
	private CategoryEntity category;
	
}
