package com.green.nowon.service;

import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.green.nowon.domain.dto.PromotionSaveDTO;
import com.green.nowon.domain.entity.PromotionEntity;

public interface AdminService {

	void findAll(Model model);

	void detailProcess(long pno, Model model);

	void update(long pno, PromotionEntity dto);

	void delete(long pno);

	Map<String, String> tempUploadProcess(MultipartFile tempFile);

	void promotionSaveProcess(PromotionSaveDTO dto);

	void listJoinProcess(int page, Model model);

}
