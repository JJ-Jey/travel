package com.green.nowon.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder //빌더 패턴 객체 생성하기 위한 annotation. 파라미터 없는 생성자에는 적용 불가.
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "promotion_image")
@Entity
public class PromotionImageEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long pino;
	private String orgName;
	private String newName;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	private long pno; //fk: Product 테이블의 pk 값만 저장

	private boolean defYn;
}
