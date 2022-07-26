package com.project.FoodsolomonBackend.review.controller;

import static com.project.FoodsolomonBackend.config.exception.BaseResponseStatus.SUCCESS;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.FoodsolomonBackend.config.exception.BaseException;
import com.project.FoodsolomonBackend.config.exception.BaseResponse;
import com.project.FoodsolomonBackend.review.dto.ReviewFormDto;
import com.project.FoodsolomonBackend.review.service.ReviewService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ReviewController {
	
	private final ReviewService reviewService;

	@GetMapping(value="/review")
	public List<ReviewFormDto> reviewDtl(Long rstrId) {
		
		List<ReviewFormDto> reviewFormDtoList = reviewService.getReviewDtl(rstrId);
		
		return reviewFormDtoList;
	}
	
	@PostMapping(value="/review/new")
	public BaseResponse<Integer> saveReview(@RequestBody ReviewFormDto reviewFormDto, @RequestParam("reviewImgFile") List<MultipartFile> reviewImgFileList) {
		
		try {
			
			reviewService.saveReview(reviewFormDto, reviewImgFileList);
			
		} catch(BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}
		
		return new BaseResponse<>(SUCCESS);
	}
	
	@PostMapping(value="/review")
	public BaseResponse<Integer> updateReview(@RequestBody ReviewFormDto reviewFormDto, @RequestParam("reviewImgFile") List<MultipartFile> reviewImgFileList) {

		try {
			
			reviewService.updateReview(reviewFormDto, reviewImgFileList);
			
		} catch (BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}
		
		return new BaseResponse<>(SUCCESS);
	}
}
