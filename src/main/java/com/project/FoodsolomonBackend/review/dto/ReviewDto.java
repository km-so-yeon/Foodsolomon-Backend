package com.project.FoodsolomonBackend.review.dto;

import java.util.Date;

import org.modelmapper.ModelMapper;

import com.project.FoodsolomonBackend.review.entity.Review;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReviewDto {

	private Long id;
	
	private Long rstrId;
	
	private Long writerId;
	
	private String title;
	
	private String content;
	
	private String status;
	
	private int rate;
	
	private Date createdTime;
	
	private Date updatedTime;
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public static ReviewDto of(Review review) {
		return modelMapper.map(review, ReviewDto.class);
	}
}
