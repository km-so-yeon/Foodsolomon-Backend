package com.project.FoodsolomonBackend.dto;

import java.util.Date;

import org.modelmapper.ModelMapper;

import com.project.FoodsolomonBackend.entity.ReviewImg;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReviewImgDto {

	private Long id;
	
	private Long reviewId;
	
	private String uriPath;
	
	private String status;
	
	private Date createdTime;
	
	private Date updatedTime;
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public static ReviewImgDto of(ReviewImg reviewImg) {
		return modelMapper.map(reviewImg, ReviewImgDto.class);
	}
}
