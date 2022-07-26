package com.project.FoodsolomonBackend.review.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.project.FoodsolomonBackend.file.service.FileService;
import com.project.FoodsolomonBackend.review.entity.ReviewImg;
import com.project.FoodsolomonBackend.review.repository.ReviewImgRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewImgService {

	private final FileService fileService;
	
	private final ReviewImgRepository reviewImgRepository;
	
	public void saveReviewImg(ReviewImg reviewImg, MultipartFile reviewImgFile) throws Exception{
		
		String imgName = "";
		String imgUrl = "";
		
		// 파일 업로드
		imgName = fileService.uploadFile(imgName, reviewImgFile.getBytes());
		imgUrl = "/images/review" + imgName;
		
		// 리뷰 이미지 정보 저장
		reviewImg.updateReviewImg(imgUrl);
		reviewImgRepository.save(reviewImg);
	}
	
	public void updateReviewImg(Long reviewImgId, MultipartFile reviewImgFile) throws Exception{
		
		if(!reviewImgFile.isEmpty()) {
			Optional<ReviewImg> oReviewImg = reviewImgRepository.findById(reviewImgId);
			
			if (oReviewImg.isPresent()) {
				
				// 기존 이미지 파일 삭제
				ReviewImg reviewImg = oReviewImg.get();
				fileService.deleteFile(reviewImg.getUriPath());
				
				// 파일 재업로드
				String imgName = fileService.uploadFile("/images/review", reviewImgFile.getBytes());
				String imgUrl = "/images/review" + imgName;
				
				reviewImg.updateReviewImg(imgUrl);
			}
		}
	}
}
