package com.project.FoodsolomonBackend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.FoodsolomonBackend.dto.ReviewFormDto;
import com.project.FoodsolomonBackend.service.ReviewService;

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
	public Long reviewNew(@RequestBody ReviewFormDto reviewFormDto) {
		
		return reviewService.saveReview(reviewFormDto);
	}
}
