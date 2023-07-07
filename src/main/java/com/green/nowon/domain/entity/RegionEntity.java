package com.green.nowon.domain.entity;

import java.util.List;
import java.util.Vector;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@SequenceGenerator(name = "gen_region", sequenceName = "seq_region", initialValue = 1001, allocationSize = 1)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "region")
@Entity
public class RegionEntity extends BaseDateEntity {
	
	@Id
	@GeneratedValue(generator = "gen_item", strategy = GenerationType.SEQUENCE)
	private long no;
	
	@Column(nullable = false)
	private String name;
	
	
	//Role과 비슷한 구성
	
	//ManyToMany로 구성하면 추가 컬럼을 생성할 수 없기때문에 실무에서는 잘 사용하지 않음
	
	@Builder.Default //빌더 적용 시 자동으로 들어감
	//각각 연계 테이블이 만들어지기 때문에 mappedBy로 연결시켜서 하나만 만들기
	@JoinTable(name = "region_category", //연계테이블의 이름을 설정할 수 있음
			joinColumns = @JoinColumn(name = "region_no"), //owner table = item
			inverseJoinColumns = @JoinColumn(name = "category_no") //inverse table(종속 테이블) = category
			)
	@ManyToMany //mappedBy가 없는 쪽이 관계의 owner: 기본적으로 cacade
	private List<CategoryEntity> categoryList = new Vector<>();
	
	public RegionEntity addCategory(CategoryEntity category) {
		categoryList.add(category);
		return this;
	}
}
