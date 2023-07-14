package com.green.nowon.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.green.nowon.aws.AwsS3BucketUtil;
import com.green.nowon.domain.dto.VisualDTO;
import com.green.nowon.domain.entity.VisualEntity;
import com.green.nowon.domain.mapper.AdminMapper;
import com.green.nowon.service.FileUploadService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FileUploadServiceProcess implements FileUploadService {
	private final AwsS3BucketUtil dao;
	private final AmazonS3Client client;

	@Value("${cloud.aws.s3.bucket}")
	private String bucketName;
	@Value("${cloud.aws.s3.temp-path}")
	private String tempPath;
	@Value("${cloud.aws.s3.upload-path}")
	private String uploadPath;

	private final AdminMapper mapper;

	@Override
	public Map<String, String> tempUploadProcess(MultipartFile temp) {

		Map<String, String> resultMap = new HashMap<>();
		String newName = createNewFileName(temp.getOriginalFilename());
		String tempKey = tempPath + newName;
		try (InputStream is = temp.getInputStream()) {

			PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, tempKey, is, objectMetadata(temp));
			client.putObject(putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead));

			String imgUrl = client.getUrl(bucketName, tempKey).toString().substring(6); // https://

			resultMap.put("imgUrl", imgUrl);
			resultMap.put("orgName", temp.getOriginalFilename());
			resultMap.put("newName", newName);
			resultMap.put("tempKey", tempKey);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return resultMap;

	}

	private ObjectMetadata objectMetadata(MultipartFile temp) {
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentType(temp.getContentType());
		objectMetadata.setContentLength(temp.getSize());
		return objectMetadata;
	}

	private String createNewFileName(String orgName) {
		int idx = orgName.lastIndexOf(".");
		return UUID.randomUUID().toString() + orgName.substring(idx); // 확장자
	}

////////////////////////해결하기
	@Override
	public void saveProcess(VisualDTO dto) {
		List<String> oldNames = dto.getImgs().stream().filter(old -> old.trim().equals(""))
				.collect(Collectors.toList());

		List<String> newNames = dao.fromTempToProduct(dto.getTempKey());

		for (int i = 0; i < oldNames.size(); i++) {
			Boolean defYn = false;
			if (i == 0)
				defYn = true;

			String oldName = oldNames.get(i); // 원본 파일명
			String newName = newNames.get(i); // 새로운 파일명: 서버에 업로드된 이름

			mapper.save(VisualEntity.builder().oldName(oldName).newName(newName).build());
		}
	}

}
