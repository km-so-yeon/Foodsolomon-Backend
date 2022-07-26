package com.project.FoodsolomonBackend.rstr.dto;

import java.util.Date;

import org.modelmapper.ModelMapper;

import com.project.FoodsolomonBackend.rstr.entity.RstrImg;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RstrImgDto {

	private Long id;
	
	private Long rstrId;
	
	private String uriPath;
	
	private String status;
	
	private Date createdTime;
	
	private Date updatedTime;
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public static RstrImgDto of(RstrImg rstrImg) {
		return modelMapper.map(rstrImg, RstrImgDto.class);
	}
}
