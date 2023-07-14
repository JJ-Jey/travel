package com.green.nowon.service;

import org.springframework.ui.Model;

import com.green.nowon.domain.entity.PromotionEntity;

public interface AdminService {

	void findAll(Model model);

	void detailProcess(long pno, Model model);

	void update(long pno, PromotionEntity dto);

	void delete(long pno);

}
