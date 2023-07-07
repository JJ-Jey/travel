package com.green.nowon.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.CopyObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class FileUploadUtil {
	
	
	/**
	 * 
	 * @param s3Client AmazonS3Client 객체
	 * @param bucket aws 버킷 이름
	 * @param path 버킷 내부 경로
	 * @param img MultipartFile 객체로 이미지 정보
	 * @return
	 */
	//static으로 구성하면 @component일 필요 없음
	public static Map<String, String> s3Upload(AmazonS3Client s3Client, String bucket, String path, MultipartFile img) {
		
		String newName = newFileNameByNanotime(img.getOriginalFilename());
		
		//static method는 static 메소드만 호출할 수 있음 >> newFileNameByNanotime() 도 static으로 설정
		//버킷 내부의 이미지 경로: 내부 주소 + 업로드되는 이름
		String bucketKey = path + newName;
		

		//업로드: 클라이언트 객체를 통해 put하기
		try {
			PutObjectRequest or = new PutObjectRequest(bucket, bucketKey, img.getInputStream(), objectMetadata(img));
			s3Client.putObject(or.withCannedAcl(CannedAccessControlList.PublicRead));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		//Map Collection을 이용해 return을 2개 받음
		Map<String, String> result = new HashMap<>();
		
		result.put("url", s3Client.getUrl(bucket, bucketKey).toString().substring(6)); //https: 이후부터 주소 시작
		result.put("bucketKey", bucketKey);
		result.put("orgName", img.getOriginalFilename());
		result.put("newName", newName);
		
		return result;
	}
	
	/**
	 * 
	 * @param path static 하위 경로 "/images/upload/temp"
	 * @param tempImg MultipartFile 객체
	 * @return
	 */
	//기존 파일 업로드 방식: classpath를 활용하여 binary로 업로드
	public static Map<String, String> classPathUpload(String path, MultipartFile tempImg) {
		
		ClassPathResource cpr = new ClassPathResource("static" + path);
		
		String newFileName = newFileName(tempImg.getOriginalFilename());
		
		try {
			File folder = cpr.getFile();
			if(!folder.exists()) {
				folder.createNewFile();
			}
			tempImg.transferTo(new File(folder, newFileName)); //bin으로 올라감
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Map<String, String> result = new HashMap<>();
		result.put("url", path + newFileName);
		result.put("orgName", tempImg.getOriginalFilename());
		
		return result;
	}
	
	
	//파일 업로드 후 폴더 이동
	public static String s3TempToSrc(AmazonS3Client s3Client, String bucket, String tempKey, String uploadKey) {
		CopyObjectRequest cor = new CopyObjectRequest(bucket, tempKey, bucket, uploadKey);
		s3Client.copyObject(cor.withCannedAccessControlList(CannedAccessControlList.PublicRead));
		
		s3Client.deleteObject(bucket, tempKey);
		
		return s3Client.getUrl(bucket, uploadKey).toString().substring(6);
	}
	
	
	
	
	
	
	
	
	
	//UUID 사용
	private static String newFileName(String orgName) {
		int idx = orgName.lastIndexOf(".");
		return UUID.randomUUID().toString() //새로운 이름
				+ orgName.substring(idx); //.확장자
	}
	
	//nanoTime 사용
	private static String newFileNameByNanotime(String orgName) {
		int idx = orgName.lastIndexOf(".");
		return orgName.substring(0, idx) + "_" + (System.nanoTime()/1000000) //새로운 이름
				+ orgName.substring(idx); //.확장자
	}
	
	private static ObjectMetadata objectMetadata(MultipartFile mf) {
		
		ObjectMetadata objectMetadata = new ObjectMetadata();

		objectMetadata.setContentType(mf.getContentType()); //파일 타입
		objectMetadata.setContentLength(mf.getSize()); //파일 사이즈
		
		return objectMetadata; 
	}

	

	
	

}
