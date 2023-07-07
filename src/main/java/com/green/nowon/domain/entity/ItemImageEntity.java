package com.green.nowon.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@SequenceGenerator(name = "gen_item_img", sequenceName = "seq_item_img", initialValue = 1, allocationSize = 1)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "item_image")
@Entity
public class ItemImageEntity {
	
	@Id
	@GeneratedValue(generator = "gen_item_img", strategy = GenerationType.SEQUENCE)
	private long no;
	
	@Column(nullable = false)
	private String url; //s3 경로
	
	@Column(nullable = false)
	private String orgName;
	
	@Column(nullable = false)
	private String newName;
	
	private boolean isList; //false: content-img
	
	private boolean isDef; //true: default-img
	
	private String bucketKey; //s3 파일명
	
	//단방향 로직: repository를 두 개 만들어야함 >> 양방향 로직일 경우 하나의 repository로 구성할 수 있음
	//대부분은 단방향 로직으로 해결 가능
	@ManyToOne
	private ItemEntity item; //컬럼명: item_no 로 자동설정
	
}
