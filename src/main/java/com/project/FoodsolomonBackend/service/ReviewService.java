package com.project.FoodsolomonBackend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.FoodsolomonBackend.dto.ReviewFormDto;
import com.project.FoodsolomonBackend.dto.ReviewImgDto;
import com.project.FoodsolomonBackend.entity.Review;
import com.project.FoodsolomonBackend.entity.ReviewImg;
import com.project.FoodsolomonBackend.repository.ReviewImgRepository;
import com.project.FoodsolomonBackend.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {

	private final ReviewRepository reviewRepository;
	
	private final ReviewImgRepository reviewImgRepository;
	
	public List<ReviewFormDto> getReviewDtl(Long rstrId) {
		
		List<Review> reviewList = reviewRepository.findByRstrIdOrderByIdAsc(rstrId);
		List<ReviewFormDto> reviewFormDtoList = new ArrayList<>();
		
		for (Review review : reviewList) {
			
			ReviewFormDto reviewFormDto = ReviewFormDto.of(review);
			
			Long reviewId = reviewFormDto.getId();
			List<ReviewImg> reviewImgList = reviewImgRepository.findByReviewIdOrderByIdAsc(reviewId);
			List<ReviewImgDto> reviewImgDtoList = new ArrayList<>();
			
			for (ReviewImg reviewImg : reviewImgList) {
				ReviewImgDto reviewImgDto = ReviewImgDto.of(reviewImg);
				reviewImgDtoList.add(reviewImgDto);
			}
			
			reviewFormDto.setReviewImgDto(reviewImgDtoList);
			reviewFormDtoList.add(reviewFormDto);
		}
		
		return reviewFormDtoList;
	}
	
	public long saveReview(ReviewFormDto reviewFormDto) {
		
		Review review = reviewFormDto.createReview();
		reviewRepository.save(review);
		
		// 이미지 저장하는 부분 구현
		
		return review.getId();
	}
}
