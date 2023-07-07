package com.green.nowon.domain.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "category")
@Entity
public class CategoryEntity {

	// 간단하게 구성할 경우 enum으로 구성해도 됨

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long no; // 카테고리 번호

	private String name; // 카테고리 이름
	
	//셀프 조인 시 아래와 같이 구성: List로 구성하지 않기(OneToMany로 하지 말기)
	//fetch 기본값: eager >> 하나이기때문에 바로 읽음 >> lazy로 바꿔주기
	@ManyToOne(fetch = FetchType.LAZY) //fk: parrent_no
	private CategoryEntity parent; //상위 카테고리
	
	//각각 연계 테이블이 만들어지기 때문에 mappedBy로 연결시켜서 하나만 만들기
	//mappedBy가 없는 쪽이 관계의 owner: inverse(종속)는 기본적으로 읽기 전용(CUD 불가)
	//fetch 기본값: lazy >> List(여러 개)이기 때문에 요청할 때만 읽음
	@ManyToMany(mappedBy = "categoryList")
	private List<RegionEntity> regionList;
	
}
