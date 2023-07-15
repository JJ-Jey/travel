package com.green.nowon.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.green.nowon.aws.AwsS3BucketUtil;
import com.green.nowon.domain.dto.PageData;
import com.green.nowon.domain.dto.PromotionListDTO;
import com.green.nowon.domain.dto.PromotionSaveDTO;
import com.green.nowon.domain.entity.PromotionEntity;
import com.green.nowon.domain.entity.PromotionImageEntity;
import com.green.nowon.domain.mapper.AdminMapper;
import com.green.nowon.domain.mapper.PromotionImageMapper;
import com.green.nowon.service.AdminService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AdminServiceProcess implements AdminService {
	
	private final AwsS3BucketUtil dao;
	
	private final AdminMapper mapper;
	
	private final PromotionImageMapper imageMapper;
	
	@Override
	public void findAll(Model model) {
		//model.addAttribute("list", mapper.findAll());
		model.addAttribute("list", mapper.findAllJoinImage().stream()
				.map(PromotionListDTO::new)
				.collect(Collectors.toList())
				);
	}

	@Override
	public void detailProcess(long pno, Model model) {
		model.addAttribute("detail", mapper.findByPno(pno));
	}

	@Override
	public void update(@Param("pno") long pno, @Param("PromotionEntity") PromotionEntity dto) {
		mapper.update(pno, dto);
	}

	@Override
	public void delete(long pno) {
		mapper.delete(pno);
	}

	@Override
	public Map<String, String> tempUploadProcess(MultipartFile tempFile) {
		return dao.tempUpload(tempFile);
	}

	@Override
	public void promotionSaveProcess(PromotionSaveDTO dto) {
		//상품 데이터 저장
				PromotionEntity entity = dto.toProductEntity();
				mapper.savePromotion(entity);
				
				//여러개 파일 저장, temp에 있는 파일 이동(또는 복사 후 삭제) 후 업로드된 이름을 리턴
				List<String> orgNames = dto.getImgs().stream()
							.filter(org->!org.trim().equals("")) //""값(아무것도 없는 데이터) 제거
							.collect(Collectors.toList());

				List<String> newNames = dao.fromTempToProduct(dto.getTempKey());

				long pno = entity.getPno(); //fk
				
				//파일 개수만큼 돌아가면서 저장
				for(int i = 0; i<orgNames.size(); i++) {
					Boolean defYn=false;
					if(i==0)defYn=true;
					
					String orgName = orgNames.get(i); //원본 파일명
					String newName = newNames.get(i); //새로운 파일명: 서버에 업로드된 이름
					
					imageMapper.save(PromotionImageEntity.builder()
														.orgName(orgName)
														.newName(newName)
														.pno(pno)
														.defYn(defYn)
														.build());
					
				}
	}

	@Override
	public void listJoinProcess(int page, Model model) {
		int limit = 5; //한 페이지에 표현할 행 개수
		int offset = (page - 1) * limit; //행 시작 위치
		PageData data = PageData.builder()
				.limit(limit).offset(offset)
				.build();
		
		List<PromotionEntity> result = mapper.findByAll(data);
		
		//총 게시글 수
		int countAll = mapper.countAll();
				
		//총 페이지 수
		int totPage = countAll / limit;
		if(countAll % limit != 0) { //나머지를 표현 할 페이지 하나 더 필요
			totPage++;
		}
				
		model.addAttribute("list2", result);
		model.addAttribute("tot", totPage);
		
		model.addAttribute("list", mapper.findByAllJoinFile(data).stream()
				.map(PromotionListDTO::new)
				.collect(Collectors.toList()));
		
		String path = "//s3.ap-northeast-2.amazonaws.com/myweb.fileupload.bucket/visual/images/";
		model.addAttribute("files", path + imageMapper.findByPnoAndDefYn(10));
	}

}
