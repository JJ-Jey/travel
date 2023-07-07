package com.green.nowon.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.green.nowon.domain.dto.ItemSaveDTO;

public interface FileUploadService {

	Map<String, String> tempUploadProcess(MultipartFile temp);

	void saveProcess(ItemSaveDTO dto);

}
