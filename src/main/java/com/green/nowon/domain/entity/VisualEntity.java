package com.green.nowon.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Builder
@AllArgsConstructor //모든 필드를 default 초기화하는 생성자
@NoArgsConstructor //파라미터가 정의되지 않은 생성자
@Getter
@Table(name = "visual")
@Entity
public class VisualEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long no;
	private String oldName;
	private String newName;
	
}
