package com.green.nowon.aws;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class S3BucketService {
	
	//resource폴더 안에 저장하면 배포시 파일 용량이 커지는 단점 >> 보완하기 위해 서버에 올리기
	//디자인을 위한 자원들은 서버에 올리는 것이 좋음
	
	private final AmazonS3Client client;
	
	//application.properties(git)에서 region 정보는 필수 사항
	
	@Value("${cloud.aws.s3.bucket}")
	private String bucket;
	
	private String temp = "goods/temp/"; //bucket 하위 경로
	
	public void upload(MultipartFile mf) {
		System.out.println("AmazonS3Client >>>>>>>>> " + client);
		
		String contentType = mf.getContentType();
		long size = mf.getSize();
		String orgName = mf.getOriginalFilename(); //파일이름.확장자
		
		System.out.println("orgName: " + orgName);
		
		int index = orgName.lastIndexOf("."); //존재하면 . 위치의 인덱스값, 없으면 -1 출력
		
		String newName = orgName.substring(0, index) //0부터 . 전까지: 파일 이름만 추출
				+ "_" + (System.nanoTime()/1000000)
				+ orgName.substring(index); //확장자
		
		System.out.println("newName: " + newName);
		
		String uuid = UUID.randomUUID().toString(); //random으로 이름 생성
		
		System.out.println("uuid: " + uuid);
		
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentType(contentType);
		objectMetadata.setContentLength(size);
		
		String tempKey = temp + newName;
		
		try(InputStream is = mf.getInputStream()) {
			PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, tempKey, is, objectMetadata);
			client.putObject(putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead));

			//아마존 S3에 올라가있는 상태이기 때문에 img 태그의 src로 바로 사용 가능
			String uploadedUrl = client.getUrl(bucket, tempKey).toString();
			
			System.out.println("uploadedUrl >>>>>>>>>>> " + uploadedUrl);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
