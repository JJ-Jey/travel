package com.green.nowon.aws;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.CopyObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
//@Component
public class AwsS3BucketUtil {
	
	private final AmazonS3Client client;

	@Value("${cloud.aws.s3.file-upload-bucket}")
	private String bucket;
	
	@Value("${cloud.aws.s3.temp-path}")
	private String tempPath;
	
	@Value("${cloud.aws.s3.uoload-path}")
	private String uploadPath;

	//temp 경로에 이미지 업로드
	public Map<String, String> tempUpload(MultipartFile mf) {
		
		Map<String, String> map = new HashMap<>();
		
		//tempKey: 임시로 저장된 이미지의 url
		String tempKey = tempPath + newFileName(mf.getOriginalFilename());
		
		try(InputStream is = mf.getInputStream()) {
			PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, tempKey, is, objectMetadata(mf));
			client.putObject(putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead)); //권한이 없으면 읽기 전용
			
			//미리보기 경로
			map.put("s3TempUrl", client.getUrl(bucket, tempKey).toString().substring(6)); //'https:' 6글자 이후부터 마지막까지
			map.put("orgName", mf.getOriginalFilename());
			map.put("tempKey", tempKey); //버킷 컨트롤하기위한 정보
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return map;
		
	}
	
	//UUID 사용
	private String newFileName(String orgName) {
		int idx = orgName.lastIndexOf(".");
		return UUID.randomUUID().toString() //새로운 이름
				+ orgName.substring(idx); //.확장자
	}

	//nanoTime 사용
	private String newFileNameByNanotime(String orgName) {
		int idx = orgName.lastIndexOf(".");
		return orgName.substring(0, idx) + "_" + (System.nanoTime()/1000000) //새로운 이름
				+ orgName.substring(idx); //.확장자
	}
	
	//파일 설정 정보
	private ObjectMetadata objectMetadata(MultipartFile mf) {
		
		ObjectMetadata objectMetadata = new ObjectMetadata();
		//String contentType = tempFile.getContentType();
		//long size = tempFile.getSize();
		objectMetadata.setContentType(mf.getContentType());
		objectMetadata.setContentLength(mf.getSize());
		
		return objectMetadata; 
	}

	
	//람다식에서 파라미터가 같기 때문에 둘 다 생략할 때 사용하는 표현: 파라미터 호출 구간 없음 
	//메소드(클래스.객체::메소드), 생성자(클래스::new 생성자) 모두 가능
	

	//Multipart 필요없음. 이미 bucket에 저장되어있는 것을 옮긴 후 복사하는 것임.
	public List<String> fromTempToProduct(List<String> tempKey) {
		
		//바디가 한 줄이면 중괄호와 return을 같이 생략 가능: 한 줄이면 무조건 return 라인이기 때문
		return tempKey.stream().filter(sourceKey->{
						return !sourceKey.trim().equals(""); //공백이면 걸러내기: false일 때 걸러냄 
					})
					.map(sourceKey->{
						String newFileName=sourceKey.substring(sourceKey.lastIndexOf("/")+1);
						String destinationKey = uploadPath + newFileName;
						
						CopyObjectRequest cpr=new CopyObjectRequest(bucket, sourceKey, bucket, destinationKey);
						
						client.copyObject(cpr.withCannedAccessControlList(CannedAccessControlList.PublicRead));
						client.deleteObject(bucket, sourceKey);
						System.out.println("이동 완료");
						
						return newFileName;
					})
					.collect(Collectors.toList()) //list에 담기
					;
		
		
		
		/*
		List<String> newNames = new ArrayList<>();
		
		for(String sourceKey : tempKey) {
			
			//sourceKey 는 임시 경로(tempKey)
			if(sourceKey.trim().equals("")) continue; //빈 문자열(이미지가 없음)이면 아래 문장들 실행하지 않고 다음 반복문 실행 
			
			//newFileName 은 DB에 저장할 데이터: 원본 이미지 파일명
			String newFileName = sourceKey.substring(sourceKey.lastIndexOf("/")+1);
			
			//destinationKey 는 영구 저장되는 이미지 url
			String destinationKey = productPath + newFileName;
			
			client.copyObject(bucket, sourceKey, bucket, destinationKey);
			System.out.println("파일 복사 완료");
			client.deleteObject(bucket, sourceKey);
			System.out.println("파일 삭제 완료");
			
			newNames.add(newFileName);
		}
		*/
	}

}
