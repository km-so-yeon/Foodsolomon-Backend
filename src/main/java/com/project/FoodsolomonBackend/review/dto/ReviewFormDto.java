package com.project.FoodsolomonBackend.review.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.project.FoodsolomonBackend.review.entity.Review;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReviewFormDto {
	
	private Long id;
	
	private Long rstrId;
	
	private Long writerId;
	
	private String title;
	
	private String content;
	
	private String status;
	
	private int rate;
	
	private Date createdTime;
	
	private Date updatedTime;
	
	private List<ReviewImgDto> reviewImgDto = new ArrayList<>();
	
	private List<Long> reviewImgIds = new ArrayList<>();
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public Review createReview() {
		return modelMapper.map(this, Review.class);
	}
	
	public static ReviewFormDto of(Review review) {
		return modelMapper.map(review, ReviewFormDto.class);
	}
}
