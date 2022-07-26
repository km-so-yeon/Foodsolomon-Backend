package com.project.FoodsolomonBackend.rstr.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.project.FoodsolomonBackend.file.service.FileService;
import com.project.FoodsolomonBackend.rstr.entity.RstrImg;
import com.project.FoodsolomonBackend.rstr.repository.RstrImgRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class RstrImgService {

	@Value("${rstrImgLocation}")
	private String rstrImgLocation;
	
	private final RstrImgRepository rstrImgRepository;
	
	private final FileService fileService;
	
	public void saveRstrImg(RstrImg rstrImg, MultipartFile rstrImgFile) throws Exception{
		
		String imgName = "";
		String imgUrl = "";
		
		// 파일 업로드
		imgName = fileService.uploadFile(rstrImgLocation, rstrImgFile.getBytes());
		imgUrl = "/images/rstr/" + imgName;
		
		// 이미지 정보 저장
		rstrImg.updateRstrImg(imgUrl);
		rstrImgRepository.save(rstrImg);
	}
	
	public void deleteRstrImg(Long rstrImgId) throws Exception{
		
		Optional<RstrImg> oRstrImg = rstrImgRepository.findById(rstrImgId);
		
		if (oRstrImg.isPresent()) {
			
			// 기존 이미지 파일 삭제
			RstrImg rstrImg = oRstrImg.get();
			fileService.deleteFile(rstrImg.getUriPath());
			
			rstrImg.deleteRstrImg();
			
		}
		
	}
}
